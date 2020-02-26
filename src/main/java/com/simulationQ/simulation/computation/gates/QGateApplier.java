/*
 * 24/12/2019 14:43:19
 * QGateApplier.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates;


import java.util.Objects;

import com.simulationQ.simulation.computation.qubits.Qubit;
import com.simulationQ.simulation.computation.qubits.register.QRegister;
import com.simulationQ.simulation.util.math.QMath;
import com.simulationQ.simulation.util.math.matrices.Matrix;
import com.simulationQ.simulation.util.math.matrices.MatrixOperations;
import com.simulationQ.simulation.util.math.matrices.vectors.Vector;


/**
 * @author Tsvetelin
 *
 */
public interface QGateApplier
{

    public static QRegister apply ( final QGate gate ,
                                    final QRegister reg ,
                                    final int startingVerticalIndex )
    {
        Objects.requireNonNull( gate );
        Objects.requireNonNull( reg );

        if ( startingVerticalIndex
                + gate.getOperation().getRows() > QMath.pow( 2 , reg.size() ) )
            throw new IllegalArgumentException( "The starting index cannot be such that the operation cannot fit!" );

        final Matrix extended = extendMatrixForWholeRegister( gate.getOperation() ,
                                                              QMath.pow( 2 ,
                                                                         startingVerticalIndex ) ,
                                                              QMath.pow( 2 ,
                                                                         reg.size() ) );
        
        System.out.println( "Adapted: " );
        System.out.println( extended );

        Vector res = MatrixOperations.multiply( extended , reg.getComputationalVector() );

        return new QRegister( res );
    }

    /**
     * @param gate
     *            - the gate to apply it should be the appropriate size
     * @param reg
     * @return
     */
    public static QRegister apply ( final QGate gate , final QRegister reg )
    {
        if ( gate.getNumberInputCoeficients() != reg.getComputationalVector()
                                                    .size() )
            throw new IllegalArgumentException( "Cannot apply a gate that is not stretched" );

        Vector res = new Vector( reg.getComputationalVector()
                                    .multiply( gate.getOperation() )
                                    .getMatrix()[0] );

        return new QRegister( res );
    }

    public static Qubit apply ( final QGate gate , final Qubit qubit )
    {
        final Vector qbit = qubit.getAsVector();
        final Matrix operation = gate.getOperation();

        final Vector res = MatrixOperations.multiply( operation , qbit );

        return new Qubit( res.getAt( 0 ) , res.getAt( 1 ) );
    }

    public static QRegister apply ( final Matrix operation ,
                                    final QRegister reg ) throws Exception
    {
        final Vector vec = reg.getComputationalVector();

        final Vector res = MatrixOperations.multiply( operation , vec );

        return new QRegister( res );
    }

    /**
     * @param operation
     * @param startingVerticalIndex
     * @param sizeOfRegister
     * @return
     */
    public static Matrix extendMatrixForWholeRegister ( final Matrix operation ,
                                                        final int startingVerticalIndex ,
                                                        final int sizeOfRegister )
    {

        Matrix extended = Matrix.multiplicativeIdentity( 2 , 2 );
        final Matrix ident_2x2 = Matrix.multiplicativeIdentity( 2 , 2 );

        for ( int i = 1 ; i < startingVerticalIndex ; i++ )
        {
            extended = MatrixOperations.productKronecker( extended ,
                                                          ident_2x2 );
        }

        extended = MatrixOperations.productKronecker( operation ,
                                                      extended);

        while ( extended.getColons() < sizeOfRegister )
        {
            extended = MatrixOperations.productKronecker( extended ,
                                                          ident_2x2 );
        }

        return extended;
    }
}
