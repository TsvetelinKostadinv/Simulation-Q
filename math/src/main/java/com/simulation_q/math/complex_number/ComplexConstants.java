/*
 * 08/11/2020 16:41:05
 * ComplexConstants.java created by Tsvetelin
 */
package com.simulation_q.math.complex_number;

/**
 * Complex constants with the default implementation
 * 
 * @author Tsvetelin
 */
public enum ComplexConstants
{
    /**
     * Standard implementation of the 0 + 0i
     * If you want specific implementation just look at the factory
     * classes
     */
    _0(ComplexNumber.Algebraic.origin()),
    
    /**
     * Standard implementation of the 1 + 0i
     * If you want specific implementation just look at the factory
     * classes
     */
    _1(ComplexNumber.Algebraic.realUnit()),
    
    /**
     * Standard implementation of the 0 + 1i
     * If you want specific implementation just look at the factory
     * classes
     */
    _i(ComplexNumber.Algebraic.imaginaryUnit()),
    ;
    
    public final ComplexNumber value;
    
    private ComplexConstants ( ComplexNumber value )
    {
        this.value = value;
    }
}
