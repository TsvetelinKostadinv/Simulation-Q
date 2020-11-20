/*
 * 08/11/2020 16:41:05
 * ComplexConstants.java created by Tsvetelin
 */
package com.simulation_q.math.complex_number;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
     * Standard implementation of the 2 + 0i
     * If you want specific implementation just look at the factory
     * classes
     */
    _2(_1.value.divide( _1.value )),
    
    /**
     * Standard implementation of the 1/2 + 0i
     * If you want specific implementation just look at the factory
     * classes
     */
    _HALF(ComplexNumber.Algebraic.real( "0.5" )),
    
    /**
     * Standard implementation of the 1/4 + 0i
     * If you want specific implementation just look at the factory
     * classes
     */
    _QUARTER(ComplexNumber.Algebraic.real( "0.25" )),
    
    /**
     * Standard implementation of the 0 + 1i
     * If you want specific implementation just look at the factory
     * classes
     */
    _i(ComplexNumber.Algebraic.imaginaryUnit()),
    SQRT_2(
        ComplexNumber.Algebraic.real(
            new BigDecimal( "2" ).sqrt(
                new MathContext(
                    AlgebraicComplexNumber.MAX_PRECISION ,
                    RoundingMode.HALF_UP ) ) )),
    _1_OVER_SQRT_2(_1.value.divide( SQRT_2.value )),
    ;
    
    public final ComplexNumber value;
    
    private ComplexConstants ( ComplexNumber value )
    {
        this.value = value;
    }
}
