/*
 * 28/10/2020 23:21:32
 * ComplexNumber.java created by Tsvetelin
 */
package com.simulation_q.math.complex_numbers;

import java.math.BigDecimal;

import com.simulation_q.math.Field;

/**
 * Represents a number in the complex field
 * 
 * @author Tsvetelin
 */
public interface ComplexNumber extends Field< ComplexNumber >
{
    /**
     * @return the real part of the complex number
     */
    BigDecimal getReal ();
    
    /**
     * @return the imaginary part of the complex number
     */
    BigDecimal getImaginary ();
    
    /**
     * The distance from this complex number to the origin
     * 
     * @return the modulus( rounded to the greater precision from the real
     *             and imaginary parts)
     */
    BigDecimal modulus ();
    
    /**
     * @return The absolute value of the complex number
     * @see    AlgebraicComplexNumber#modulus
     */
    default BigDecimal abs ()
    {
        return this.modulus();
    }
    
    /**
     * @return the conjugate of the number
     */
    ComplexNumber conjugate ();
    
    /**
     * Lifts the complex number to a positive power
     * 
     * @param   power
     * @return        this^power
     * @apiNote       this is better done in polar coordinates
     */
    ComplexNumber power ( final int power );
    
    /**
     * @return sin(this)
     */
    ComplexNumber sin ();
    
    /**
     * @return cos(this)
     */
    ComplexNumber cos ();
    
    /**
     * @return tan(this)
     */
    default ComplexNumber tan ()
    {
        return this.sin().divide( this.cos() );
    }
    
    /**
     * @return cotan(this)
     */
    default ComplexNumber cotan ()
    {
        // technically I can do
        // this.tan().invert(), but that
        // would be even slower
        return this.cos().divide( this.sin() );
    }
    
    /**
     * Factory interface to construct complex number in algebraic form
     * 
     * @author Tsvetelin
     */
    final class Algebraic
    {
        private Algebraic ()
        {
        }
        
        /**
         * This is the number that represents the origin point = 0 + 0i
         */
        public static ComplexNumber origin ()
        {
            return AlgebraicComplexNumber.ORIGIN;
        }
        
        /**
         * This is the number that represents a single unit in the real axis
         * direction = 1 + 0i
         */
        public static ComplexNumber realUnit ()
        {
            return AlgebraicComplexNumber.REAL_UNIT;
        }
        
        /**
         * This is the number that represents a single unit in the imaginary
         * axis direction = 0 + 1i
         */
        public static ComplexNumber imaginaryUnit ()
        {
            return AlgebraicComplexNumber.IMAG_UNIT;
        }
        
        /**
         * Creates a complex number in the form real + 0i
         * 
         * @param  real
         * @return      real + 0i
         */
        public static AlgebraicComplexNumber real ( BigDecimal real )
        {
            return new AlgebraicComplexNumber( real , BigDecimal.ZERO );
        }
        
        /**
         * Creates a complex number in the form real + 0i
         * 
         * @param  real
         * @return      real + 0i
         */
        public static AlgebraicComplexNumber real ( double real )
        {
            return new AlgebraicComplexNumber(
                new BigDecimal( real ) ,
                BigDecimal.ZERO );
        }
        
        /**
         * Creates a complex number in the form real + 0i
         * 
         * @param  real
         * @return      real + 0i
         */
        public static AlgebraicComplexNumber real ( String real )
        {
            return new AlgebraicComplexNumber(
                new BigDecimal( real ) ,
                BigDecimal.ZERO );
        }
        
        /**
         * Creates a complex number in the form 0 + imaginary*i
         * 
         * @param  imaginary
         * @return           0 + imaginary*i
         */
        public static AlgebraicComplexNumber imaginary ( BigDecimal imaginary )
        {
            return new AlgebraicComplexNumber( BigDecimal.ZERO , imaginary );
        }
        
        /**
         * Creates a complex number in the form 0 + imaginary*i
         * 
         * @param  imaginary
         * @return           0 + imaginary*i
         */
        public static AlgebraicComplexNumber imaginary ( double imaginary )
        {
            return new AlgebraicComplexNumber(
                BigDecimal.ZERO ,
                new BigDecimal( imaginary ) );
        }
        
        /**
         * Creates a complex number in the form 0 + imaginary*i
         * 
         * @param  imaginary
         * @return           0 + imaginary*i
         */
        public static AlgebraicComplexNumber imaginary ( String imaginary )
        {
            return new AlgebraicComplexNumber(
                BigDecimal.ZERO ,
                new BigDecimal( imaginary ) );
        }
    }
}
