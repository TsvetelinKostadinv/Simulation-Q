/*
 * 26/09/2019 16:01:30
 * ComplexNumber.java created by Tsvetelin
 */
package com.simulation_q.math.complex_numbers;

import java.math.BigDecimal;
import java.math.MathContext;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A class to represent a complex number with all it's operations.
 * Behaves like the java objects that represent BigDecimal or
 * BigInteger, i.e. this class is immutable and every operation
 * returns a new instance of the complex number.
 * 
 * @author  Tsvetelin
 * @apiNote uses BigDecimal
 * @see     BigDecimal
 */
@Data
@RequiredArgsConstructor
public class AlgebraicComplexNumber implements ComplexNumber
{
    
    /**
     * This is the number that represents the origin point = 0 + 0i
     */
    public static final AlgebraicComplexNumber ORIGIN =
        new AlgebraicComplexNumber(
            BigDecimal.ZERO ,
            BigDecimal.ZERO );
    
    /**
     * This is the number that represents a single unit in the real axis
     * direction = 1 + 0i
     */
    public static final AlgebraicComplexNumber REAL_UNIT =
        new AlgebraicComplexNumber(
            BigDecimal.ONE ,
            BigDecimal.ZERO );
    
    /**
     * This is the number that represents a single unit in the imaginary
     * axis
     * direction = 0 + 1i
     */
    public static final AlgebraicComplexNumber IMAG_UNIT =
        new AlgebraicComplexNumber(
            BigDecimal.ZERO ,
            BigDecimal.ONE );
    
    private final BigDecimal real;
    private final BigDecimal imaginary;
    
    /**
     * Constructs the complex number with the given arguments in the form
     * <b>real + imaginary * i</b>
     * 
     * @param      real
     * @param      imaginary
     * @deprecated           shouldn't be used, because of the floating
     *                           point loss of precision
     */
    @Deprecated
    private AlgebraicComplexNumber ( double real , double imaginary )
    {
        super();
        this.real = BigDecimal.valueOf( real );
        this.imaginary = BigDecimal.valueOf( imaginary );
    }
    
    /**
     * Constructs the complex number with the given arguments in the form
     * <b>real + imaginary * i</b> <br>
     * NOTE: The same constraints are in place as with the constructor of
     * BigDecimal
     * 
     * @param real
     * @param imaginary
     * @see             BigDecimal
     */
    public AlgebraicComplexNumber ( String real , String imaginary )
    {
        super();
        this.real = new BigDecimal( real );
        this.imaginary = new BigDecimal( imaginary );
    }
    
    @Override
    public ComplexNumber additiveIdentity ()
    {
        return ORIGIN;
    }
    
    @Override
    public ComplexNumber add ( ComplexNumber other )
    {
        
        return new AlgebraicComplexNumber(
            this.getReal()
                .add( other.getReal() )
                .stripTrailingZeros() ,
            this.getImaginary()
                .add( other.getImaginary() )
                .stripTrailingZeros() );
    }
    
    @Override
    public ComplexNumber negate ()
    {
        return new AlgebraicComplexNumber(
            this.getReal().negate() ,
            this.getImaginary().negate() );
    }
    
    @Override
    public ComplexNumber multiplicativeIdentity ()
    {
        return REAL_UNIT;
    }
    
    /**
     * @apiNote the precision of the result is the greater precision of
     *              the 2 operands, for real and imaginary parts
     *              respectively
     */
    @Override
    public ComplexNumber multiply ( ComplexNumber other )
    {
        
        final BigDecimal real =
            this.getReal()
                .multiply( other.getReal() )
                .subtract(
                    this.getImaginary()
                        .multiply( other.getImaginary() ) )
                .stripTrailingZeros();
        
        final BigDecimal imaginary =
            this.getReal()
                .multiply( other.getImaginary() )
                .add(
                    this.getImaginary()
                        .multiply( other.getReal() ) )
                .stripTrailingZeros();
        
        return new AlgebraicComplexNumber( real , imaginary );
    }
    
    @Override
    public ComplexNumber invert ()
    {
        // a - bi
        // ---------
        // a^2 - b^2
        return this.conjugate()
            .divide( this.power( 2 ) );
    }
    
    @Override
    public ComplexNumber divide ( ComplexNumber other )
    {
        ComplexNumber output = this.multiply( other.conjugate() );
        BigDecimal divisor = other.modulus().pow( 2 );
        return new AlgebraicComplexNumber(
            output.getReal()
                .divide( divisor )
                .stripTrailingZeros() ,
            output.getImaginary()
                .divide( divisor )
                .stripTrailingZeros() );
    }
    
    @Override
    public BigDecimal modulus ()
    {
        // TODO check precision on the sqrt
        int precision =
            this.getReal().precision() > this.getImaginary().precision() ?
                this.getReal().precision() :
                    this.getImaginary().precision();
        
        return this.getReal()
            .pow( 2 )
            .add( this.getImaginary().pow( 2 ) )
            .sqrt( new MathContext( precision ) );
    }
    
    @Override
    public ComplexNumber conjugate ()
    {
        return new AlgebraicComplexNumber(
            this.getReal() ,
            this.getImaginary().negate() );
    }
    
    @Override
    public ComplexNumber power ( int power )
    {
        // TODO Add polar coordinate representation
        ComplexNumber res = REAL_UNIT;
        for ( int i = 0 ; i < power ; i++ )
        {
            if ( power > 0 )
            {
                res = res.multiply( this );
            } else
            {
                res = res.divide( this );
            }
        }
        
        return res;
    }
    
    @Override
    public ComplexNumber sin ()
    {
        double exponent = Math.exp( this.getImaginary().doubleValue() );
        double exponentInv = 1 / exponent;
        double real =
            Math.sin( this.getReal().doubleValue() )
                * ( exponent + exponentInv ) / 2;
        double imaginary =
            Math.cos( this.getReal().doubleValue() )
                * ( exponent - exponentInv ) / 2;
        return new AlgebraicComplexNumber( real , imaginary );
    }
    
    @Override
    public ComplexNumber cos ()
    {
        double exponent = Math.exp( this.getImaginary().doubleValue() );
        double exponentInv = 1 / exponent;
        double real =
            Math.cos( this.getReal().doubleValue() )
                * ( exponent + exponentInv ) / 2;
        double imaginary =
            -Math.sin( this.getReal().doubleValue() )
                * ( exponent - exponentInv ) / 2;
        return new AlgebraicComplexNumber( real , imaginary );
    }
    
    @Override
    public String toString ()
    {
        String realPart = this.real.toString();
        char sign =
            this.getImaginary().compareTo( BigDecimal.ZERO ) >= 0 ? '+' : '-';
        String imaginaryPart = this.imaginary.abs().toString();
        return String.format( "%s%s%s*i" , realPart , sign , imaginaryPart );
    }
    
}
