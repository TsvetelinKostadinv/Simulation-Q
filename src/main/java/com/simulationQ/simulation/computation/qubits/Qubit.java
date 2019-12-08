/*
 * 29/09/2019 15:51:28
 * Qubit.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.qubits;


import java.math.BigDecimal;

import com.simulationQ.simulation.util.math.QMath;
import com.simulationQ.simulation.util.math.QMath.Constants;
import com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.simulation.util.math.matrices.vectors.Vector;


/**
 * @author Tsvetelin
 *
 */
public class Qubit
{

    public static final Qubit QUBIT_ON        = new Qubit( ComplexNumber.ORIGIN ,
                                                           ComplexNumber.REAL_UNIT );

    public static final Qubit QUBIT_OFF       = new Qubit( ComplexNumber.REAL_UNIT ,
                                                           ComplexNumber.ORIGIN );

    public static final Qubit QUBIT_HALF_HALF = new Qubit( ComplexNumber.ONE_OVER_SQRT_2 ,
                                                           ComplexNumber.ONE_OVER_SQRT_2 );

    private ComplexNumber     ketOFF;

    private ComplexNumber     ketON;

    public Qubit ( ComplexNumber ketOFF , ComplexNumber ketON )
            throws IllegalArgumentException
    {
        super();

        if ( QMath.checkLinearCombinationEqualToOne( ketOFF.abs() ,
                                                     ketON.abs() ) )
        {
            this.ketOFF = ketOFF;
            this.ketON = ketON;
        } else
        {
            throw new IllegalArgumentException(
                                                "The sum of the squares of both the ketON and ketOFF must equate to 1" );
        }
    }

    /**
     * @return the ketOFF
     */
    public ComplexNumber getKetOFF ()
    {
        return ketOFF;
    }

    /**
     * @return the ketON
     */
    public ComplexNumber getKetON ()
    {
        return ketON;
    }

    public Qubit collapseSuperposition ()
    {
        return QCollapser.collapse( this );
    }

    public Vector getAsVector ()
    {
        return new Vector( new ComplexNumber[] { ketOFF, ketON } );
    }

    @Override
    public boolean equals ( Object obj )
    {
        return obj instanceof Qubit
                && ( ( Qubit ) obj ).ketOFF.equals( this.ketOFF )
                && ( ( Qubit ) obj ).ketON.equals( this.ketON );
    }

    @Override
    public String toString ()
    {
        return String.format(
                              "%s |0> + (%s) |1>" ,
                              this.ketOFF.toString() ,
                              this.ketON.toString() );
    }

    public static void main ( String [] args )
    {
        System.out.println( "Testing..." );

        final int iterations = 1000000;

        final ComplexNumber oneSqrt2 = new ComplexNumber( Constants.ONE_OVER_SQRT_2.value ,
                                                          BigDecimal.ZERO );

        System.out.println( oneSqrt2.abs().pow( 2 ) );

        Qubit q = new Qubit( oneSqrt2 , oneSqrt2 );

        int ones = 0;
        int zeroes = 0;

        for ( int i = 0 ; i < iterations ; i++ )
        {

            boolean isOn = q.collapseSuperposition().equals( QUBIT_ON );

            if ( isOn )
            {
                ones++;
            } else
            {
                zeroes++;
            }
        }

        System.out.println( "Final results: " );
        System.out.println( "On states: " + ones );
        System.out.println( "Off state: " + zeroes );
        System.out.println( "Total: " + ( ones + zeroes ) );

    }

}
