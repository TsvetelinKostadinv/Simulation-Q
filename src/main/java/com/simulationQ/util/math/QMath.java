/*
 * 06/10/2019 19:26:59
 * Math.java created by Tsvetelin
 */
package com.simulationQ.util.math;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


/**
 * @author Tsvetelin
 *
 */
public interface QMath
{

    /**
     * This is the default precision for calculations
     */
    public static final int PRECISION = 10;

    /**
     * Some constants for calculation
     * 
     * @author Tsvetelin
     *
     */
    public static enum Constants
    {

        /**
         * Obviously sqrt(2)
         */
        SQRT_2(
                BigDecimal.ONE.add( BigDecimal.ONE )
                              .sqrt( new MathContext( PRECISION ) )
        ),
        /**
         * Obviously 1/sqrt(2)
         */
        ONE_OVER_SQRT_2(
                BigDecimal.ONE.divide( SQRT_2.value ,
                                       new MathContext( PRECISION ) )
        ),
        /**
         * Obviously sqrt(3)
         */
        SQRT_3(
                BigDecimal.ONE.add( BigDecimal.ONE )
                              .add( BigDecimal.ONE )
                              .sqrt( new MathContext( PRECISION ) )
        ),
        /**
         * Obviously 1/sqrt(2)
         */
        ONE_OVER_SQRT_3(
                BigDecimal.ONE.divide( SQRT_3.value ,
                                       new MathContext( PRECISION ) )
        );

        /**
         * The value of the constant
         */
        public final BigDecimal value;

        /**
         * 
         * @param a
         *            - value of the constant
         */
        private Constants ( BigDecimal a )
        {
            this.value = a;
        }
    }

    /**
     * 
     * Refresher on linear combination: a^2+b^2
     * 
     * @param a
     * @param b
     * @return true if the linear combination was 1, and false otherwise
     */
    public static boolean checkLinearCombinationEqualToOne ( final BigDecimal a ,
                                                             final BigDecimal b )
    {
        return a.pow( 2 )
                .add( b.pow( 2 ) )
                .sqrt( new MathContext( PRECISION ) )
                .setScale( BigDecimal.ONE.scale(), RoundingMode.HALF_UP )
                .equals( BigDecimal.ONE );
    }
}
