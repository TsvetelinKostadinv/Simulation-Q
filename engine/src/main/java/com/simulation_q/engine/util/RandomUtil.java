/*
 * 16/11/2020 18:00:38
 * RandomUtil.java created by Tsvetelin
 */
package com.simulation_q.engine.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

/**
 * A class for easier work with random values
 * 
 * @author Tsvetelin
 */
public final class RandomUtil
{
    private RandomUtil ()
    {
    }
    
    /**
     * At the time of initialization of the class this field will get
     * calculated and never after so it would be different from run to
     * run, but will be random to a certain extent
     */
    private static final Random RANDOM = new Random( new Date().getTime() );
    
    /**
     * Supplier of random values between 0 and 1
     */
    public static final Supplier< BigDecimal > decisionSupplier =
        () -> new BigDecimal( RANDOM.nextDouble() );
    
}
