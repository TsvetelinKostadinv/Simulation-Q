/*
 * 27/10/2020 22:09:26
 * Algebra.java created by Tsvetelin
 */
package com.simulation_q.math;

/**
 * Laws for an algebra
 * 
 * @author Tsvetelin
 */
public interface Field < T extends Field< T > >
{
    /**
     * The element which does not change the elemnt which is added to it.
     * Think of a zero
     * 
     * @return the additive identity
     */
    T additiveIdentity ();
    
    /**
     * Adds 2 elements together
     * 
     * @param  other
     * @return       this + other
     */
    T add ( final T other );
    
    /**
     * Negates the element. Note this is used to generate an
     * implementation of subtraction
     * 
     * @return -this
     */
    T negate ();
    
    /**
     * Element which does not change the element it is multiplied with.
     * Think of a 1
     * 
     * @return the multiplicative identity
     */
    T multiplicativeIdentity ();
    
    /**
     * Multiplies 2 elements together
     * 
     * @param  other
     * @return       this * other
     */
    T multiply ( final T other );
    
    /**
     * The inverse of the element. Note that this is used to generate an
     * implementation of division
     * 
     * @return 1/this
     */
    T invert ();
    
    /**
     * Subtracts 2 elements.
     * 
     * @param  other
     * @return       this - other
     */
    default T subtract ( final T other )
    {
        return this.add( other.negate() );
    }
    
    /**
     * Divides 2 elements
     * 
     * @param  other
     * @return       this/other
     */
    default T divide ( final T other )
    {
        return this.multiply( other.invert() );
    }
}
