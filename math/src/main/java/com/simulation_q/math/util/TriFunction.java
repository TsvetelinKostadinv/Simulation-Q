/*
 * 17/11/2020 13:07:02
 * TriFunction.java created by Tsvetelin
 */
package com.simulation_q.math.util;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function taking 3 arguments and returning a result
 * 
 * @author Tsvetelin
 */
public interface TriFunction < T , U , V , R >
{
    /**
     * Applies the function and returns the result
     * 
     * @param  t
     * @param  u
     * @param  v
     * @return
     */
    R apply ( T t , U u , V v );
    
    /**
     * Composes the function to this trifunction
     * 
     * @param  <newR>
     * @param  f
     * @return                      the composition of this and f
     * @throws NullPointerException - if f is null
     */
    default < newR > TriFunction< T , U , V , newR > andThen (
        Function< R , newR > f )
        throws NullPointerException
    {
        Objects.requireNonNull( f );
        
        return ( t , u , v ) -> f.apply( apply( t , u , v ) );
    }
}
