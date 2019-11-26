/*
 * 25/11/2019 23:27:05
 * QGate.java created by Tsvetelin
 */
package com.simulationQ.computation.gates;


import java.util.Objects;

import com.simulationQ.computation.qubits.Qubit;
import com.simulationQ.computation.qubits.register.QRegister;
import com.simulationQ.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.util.math.matrices.Matrix;
import com.simulationQ.util.math.matrices.MatrixOperations;
import com.simulationQ.util.math.matrices.vectors.Vector;


/**
 * @author Tsvetelin
 *
 */
public abstract class QGate
{

    private final Matrix operation;

    private final int    numberInputBits;

    private final String informationForGate;

    /**
     * 
     * @param operation
     * @param numberInputBits
     * @param periodOfOperation
     * @param information
     */
    public QGate ( Matrix operation ,
            int numberInputBits ,
            int periodOfOperation ,
            String information )
    {
        super();
        Objects.requireNonNull( operation );

        if ( !isMatrixSquare( operation )
                || !appropriateSizeForInputBits( operation , numberInputBits )
                || !checkMatrixUnarity( operation , periodOfOperation ) )
        {
            throw new IllegalArgumentException( " Cannot construct gate with the given input " );
        }

        this.numberInputBits = numberInputBits;
        this.operation = operation;
        this.informationForGate = information;
    }

    /**
     * @return the operation
     */
    public Matrix getOperation ()
    {
        return operation;
    }

    /**
     * @return the numberInputBits
     */
    public int getNumberInputBits ()
    {
        return numberInputBits;
    }

    /**
     * @return the informationForGate
     */
    public String getInformationForGate ()
    {
        return informationForGate;
    }

    public QRegister apply ( QRegister reg )
    {
        if ( reg.size() != this.numberInputBits )
            throw new IllegalArgumentException( "The register must be with the same number of bits as the input of the gate!" );

        System.out.println( "Applying: " );
        System.out.println( this.operation );
        System.out.println( "To:" );
        System.out.println( reg );

        if ( this.numberInputBits == 1 )
        {

            final Vector qubitVector = reg.getComputationalVector();
            
            final Matrix qubitVectorTransposed = MatrixOperations.transpose( reg.getComputationalVector() );
            
            final Vector res = MatrixOperations.multiply( this.operation , qubitVector );

            return new QRegister( new Qubit[] { new Qubit( res.getAt( 0 ) , res.getAt( 1 ) ) } );

        } else
        {
            // TODO Implement more bits in the gates
            throw new UnsupportedOperationException( "More bits are not as of yet supported!" );
        }

    }

    /**
     * @param operation2
     * @param numberInputBits2
     * @return
     */
    private static final boolean appropriateSizeForInputBits ( Matrix operation ,
                                                               int numberInputBits )
    {
        return operation.getRows() == Math.pow( 2 , numberInputBits );
    }

    private static final boolean checkMatrixUnarity ( Matrix matrix ,
                                                      int period )
    {
        // return matrix.pow( period )
        // .equals( new Matrix( Matrix.multiplicativeIdentity( matrix.getRows()
        // ,
        // matrix.getColons() ) ) );
        // TODO Fix rounding issue!!
        return true;
    }

    private static final boolean isMatrixSquare ( Matrix a )
    {
        return a.getRows() == a.getColons();
    }

    public static void main ( String [] args )
    {
        Matrix a = new Matrix( new ComplexNumber[][] {
                { ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT },
                { ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT.negate() }
        } ).multiplyWithScalar( ComplexNumber.ONE_OVER_SQRT_2 );

        Qubit q = Qubit.QUBIT_ON;

        System.out.println( q );

        QRegister reg = new QRegister( new Qubit[] { q } );

        QGate g = new QGate( a , 1 , 2 , "" )
        {};

        System.out.println( "Got: " + g.apply( reg ) );
        
        System.out.println( "Again: " + g.apply( g.apply( reg ) ) );

    }

}
