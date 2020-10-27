/*
 * 27/10/2019 18:59:08
 * TriFunction.java created by Tsvetelin
 */
package com.simulationQ.simulation.util.math.functional;

/**
 * An extension to the Java functional interfaces
 * 
 * @author Tsvetelin
 */
@FunctionalInterface
public interface TriFunction < T , U , V , R >
{
    /**
     * Applies the function to the given arguments
     * 
     * @param  t the function argument
     * @param  u the function argument
     * @param  v the function argument
     * @return   the result
     */
    public R apply ( final T t , final U u , final V v );
    
}
