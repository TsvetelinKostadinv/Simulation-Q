/*
 * 16/11/2020 19:09:43
 * QMath.java created by Tsvetelin
 */
package com.simulation_q.math;

/**
 * @author Tsvetelin
 */
public class QMath
{
    private QMath ()
    {
    }
    
    private static final double log_10_2 = Math.log10( 2 );
    
    public static double log2 ( double num )
    {
        return Math.log10( num ) / log_10_2;
    }
    
}
