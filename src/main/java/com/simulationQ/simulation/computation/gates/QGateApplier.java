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
        
        
        final Vector vectorOfReg = reg.getComputationalVector();
        final Matrix extended = extendMatrixForWholeRegister( gate.getOperation() ,
                                                              startingVerticalIndex,
                                                              vectorOfReg.size() );
        
        
        
        try
        {
            return debugApply( extended , reg );
        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param operation
     * @param startingVerticalIndex
     * @param sizeOfVector
     * @return
     */
    public static Matrix extendMatrixForWholeRegister ( final Matrix operation ,
                                                        final int startingVerticalIndex ,
                                                        final int sizeOfVector )
    {
    
        Matrix extended = Matrix.multiplicativeIdentity( 1 , 1 );
        final Matrix ident_2x2 = Matrix.multiplicativeIdentity( 2 , 2 );
    
        for ( int i = 0 ; i < startingVerticalIndex ; i++ )
        {
            extended = MatrixOperations.productKronecker( extended ,
                                                          ident_2x2 );
        }
    
        extended = MatrixOperations.productKronecker( extended ,
                                                      operation);
    
        while ( extended.getColons() < sizeOfVector )
        {
            extended = MatrixOperations.productKronecker( extended ,
                                                          ident_2x2 );
        }
    
        return extended;
    }

    /**
     * @param gate
     *            - the gate to apply it should be the appropriate size
     * @param reg
     * @return
     */
    public static QRegister applyAppropriateGate ( final QGate gate , final QRegister reg )
    {
        if ( gate.getNumberInputCoeficients() != reg.getComputationalVector()
                                                    .size() )
            throw new IllegalArgumentException( "Cannot apply a gate that is not stretched" );

        Vector res = new Vector( reg.getComputationalVector()
                                    .multiply( gate.getOperation() )
                                    .getMatrix()[0] );

        return new QRegister( res );
    }

    public static Qubit applyGateToQubit ( final QGate gate , final Qubit qubit )
    {
        final Vector qbit = qubit.getAsVector();
        final Matrix operation = gate.getOperation();

        final Vector res = MatrixOperations.multiply( operation , qbit );

        return new Qubit( res.getAt( 0 ) , res.getAt( 1 ) );
    }

    public static QRegister debugApply ( final Matrix operation ,
                                    final QRegister reg ) throws Exception
    {
        final Vector vec = reg.getComputationalVector();

        final Vector res = MatrixOperations.multiply( operation , vec );

        return new QRegister( res );
    }
    
//    public static void main ( String [] args )
//    {
//        final Matrix a = NOT.OPERATION_MATRIX;
//        System.out.println( "--- Index = 0 ---" );
//        System.out.println( extendMatrixForWholeRegister( a , 0 , 4 ) );
//        System.out.println( "--- Index = 1 ---" );
//        System.out.println( extendMatrixForWholeRegister( a , 1 , 4 ) );
//        
//        System.out.println(  );
//        System.out.println(  );
//        
//        final Matrix b = Hadamard.OPERATION_MATRIX;
//        System.out.println( "--- Index = 0 ---" );
//        System.out.println( extendMatrixForWholeRegister( b , 0 , 4 ) );
//        System.out.println( "--- Index = 1 ---" );
//        System.out.println( extendMatrixForWholeRegister( b , 1 , 4 ) );
//    }
}
