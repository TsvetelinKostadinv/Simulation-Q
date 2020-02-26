/*
 * 25/11/2019 23:27:05
 * QGate.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates;


import java.util.Objects;

import com.simulationQ.simulation.util.math.matrices.Matrix;


/**
 * @author Tsvetelin
 *
 */
public abstract class QGate
{

    private final Matrix operation;

    private final int    numberInputCoeficients;

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

        this.numberInputCoeficients = numberInputBits;
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
    public int getNumberInputCoeficients ()
    {
        return numberInputCoeficients;
    }

    /**
     * @return the informationForGate
     */
    public String getInformationForGate ()
    {
        return informationForGate;
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

        return true;
        // return matrix.pow( period )
        // .equals( new Matrix( Matrix.multiplicativeIdentity( matrix.getRows()
        // ,
        // matrix.getColons() ) ) );
    }

    private static final boolean isMatrixSquare ( Matrix a )
    {
        return a.getRows() == a.getColons();
    }
}
