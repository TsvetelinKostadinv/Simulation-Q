/*
 * 06/10/2019 19:26:59
 * Math.java created by Tsvetelin
 */
package com.simulationQ.simulation.util.math;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;
import java.util.function.Supplier;


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
    
    public static final Supplier<BigDecimal> RND_SUPPLY = () -> new BigDecimal( new Random().nextDouble() );

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
                .setScale( BigDecimal.ONE.scale() , RoundingMode.HALF_UP )
                .equals( BigDecimal.ONE );
    }

    /**
     * 
     * The log2 of the supplied number
     * 
     * @apiNote as it returns <b>int</b> the supplied size should be a power of
     *          2
     * 
     * @param num
     * @return log2(size)
     */
    public static int log2 ( int num )
    {
        switch ( num )
            {
                case 1 :
                    return 0;
                case 2 :
                    return 1;
                case 4 :
                    return 2;
                case 8 :
                    return 3;
                case 16 :
                    return 4;
                case 32 :
                    return 5;
                case 64 :
                    return 6;
                case 128 :
                    return 7;
                case 256 :
                    return 8;
                case 512 :
                    return 9;
                case 1024 :
                    return 10;
                default :
                    throw new IllegalArgumentException( "This method works only with powers of 2" );
            }

    }

    /**
     * @param base
     * @param pow
     * @return
     */
    public static int pow ( final int base , final int pow )
    {
        if( pow < 0 ) throw new ArithmeticException( "The power must be greater than 0" );
        
        int res = 1;
        
        for( int i=0;i<pow;i++ ) res *= base;
        
        return res;
    }
}
