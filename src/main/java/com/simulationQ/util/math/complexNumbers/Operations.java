/*
 * 28/09/2019 15:41:12
 * Operations.java created by Tsvetelin
 */
package com.simulationQ.util.math.complexNumbers;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * Interface for operations on complex numbers
 * 
 * @author Tsvetelin
 *
 */
public interface Operations
{

    /**
     * This is the precision with which the modulus(and for now only the
     * modulus) will be calculated
     */
    public static final int PRECISION = 100;

    /**
     * 
     * @param a
     * @return the modulus of the parameter
     */
    public static BigDecimal modulus ( ComplexNumber a )
    {
        return a.getReal().pow( 2 ).add( a.getImaginary().pow( 2 ) )
                .sqrt( new MathContext( PRECISION ) );
    }

    /**
     * Negates the real part of the number
     * 
     * @return the same number with negated real part
     */
    public static ComplexNumber negateReal ( ComplexNumber a )
    {
        return new ComplexNumber( a.getReal().negate() , a.getImaginary() );
    }

    /**
     * 
     * @return the conjugate of the number
     */
    public static ComplexNumber conjugate ( ComplexNumber a )
    {
        return new ComplexNumber( a.getReal() , a.getImaginary().negate() );
    }

    /**
     * 
     * @param a
     * @return -a
     */
    public static ComplexNumber negate ( ComplexNumber a )
    {
        return new ComplexNumber( a.getReal().negate() ,
                a.getImaginary().negate() );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a+b
     */
    public static ComplexNumber add ( ComplexNumber a , ComplexNumber b )
    {
        return new ComplexNumber(
                a.getReal().add( b.getReal() ) ,
                a.getImaginary().add( b.getImaginary() ) );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a-b
     */
    public static ComplexNumber subtract (
            ComplexNumber a ,
            ComplexNumber b )
    {
        return add( a , negate( b ) );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a*b
     */
    public static ComplexNumber multiply (
            ComplexNumber a ,
            ComplexNumber b )
    {
        BigDecimal real = a.getReal().multiply( b.getReal() )
                .subtract( a.getImaginary().multiply( b.getImaginary() ) );
        BigDecimal imaginary = a.getReal().multiply( b.getImaginary() )
                .add( a.getImaginary().multiply( b.getReal() ) );

        return new ComplexNumber( real , imaginary );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a/b
     */
    public static ComplexNumber divide ( ComplexNumber a , ComplexNumber b )
    {
        ComplexNumber output = multiply( a , conjugate( b ) );
        BigDecimal divisor = modulus( b ).pow( 2 );
        return new ComplexNumber(
                output.getReal().divide( divisor ) ,
                output.getImaginary().divide( divisor ) );
    }

    /**
     * 
     * @param a - complex number
     * @return The absolute value of the complex number
     */
    public static BigDecimal abs ( ComplexNumber a )
    {
        return modulus( a );
    }

    /**
     * 
     * @param a
     * @param power
     * @return a^power
     */
    public static ComplexNumber power ( ComplexNumber a , int power )
    {
        ComplexNumber res = a;
        for ( int i = 0 ; i < power ; i++ )
        {
            res = multiply( res , a );
        }
        return res;
    }

    /**
     * 
     * @param a
     * @return sin(a)
     */
    public static ComplexNumber sin ( ComplexNumber a )
    {
        double exponent = Math.exp( a.getImaginary().doubleValue() );
        double exponentInv = 1 / exponent;
        double real = Math.sin( a.getReal().doubleValue() )
                * ( exponent + exponentInv ) / 2;
        double imaginary = Math.cos( a.getReal().doubleValue() )
                * ( exponent - exponentInv ) / 2;
        return new ComplexNumber( real , imaginary );
    }

    /**
     * 
     * @param a
     * @return cos(a)
     */
    public static ComplexNumber cos ( ComplexNumber a )
    {
        double exponent = Math.exp( a.getImaginary().doubleValue() );
        double exponentInv = 1 / exponent;
        double real = Math.cos( a.getReal().doubleValue() )
                * ( exponent + exponentInv ) / 2;
        double imaginary = -Math.sin( a.getReal().doubleValue() )
                * ( exponent - exponentInv ) / 2;
        return new ComplexNumber( real , imaginary );
    }

    /**
     * 
     * @param a
     * @return tan(a)
     */
    public static ComplexNumber tan ( ComplexNumber a )
    {
        return divide( sin( a ) , cos( a ) );
    }

    /**
     * 
     * @param a
     * @return cotan(a)
     */
    public static ComplexNumber cotan ( ComplexNumber a )
    {
        return divide( ComplexNumber.REAL_UNIT , tan( a ) );
    }

    /**
     * 
     * Parses a string to a complex number
     * 
     * @param s
     *            - the string to be parsed
     * @return an optional of a complex number, if there is something in the
     *         optional the parse was successful, if it is empty the parse
     *         was not successfull
     */
    public static Optional< ComplexNumber > parse ( String s )
    {
        String fullRegex = "^([-+]?[0-9]*(\\.|,)?[0-9]+)( )*(\\+|\\-)( )*([-+]?[0-9]*(\\.|,)?[0-9]+)+\\*?(I|i)$";
        String numberRegex = "[-+]?[0-9]*(\\.|,)?[0-9]+";
        if ( s.matches( fullRegex ) )
        {
            Matcher m = Pattern.compile( numberRegex ).matcher( s );

            m.find();
            BigDecimal real = new BigDecimal(
                    s.substring( m.start() , m.end() ) );
            m.find();
            BigDecimal imaginary = new BigDecimal(
                    s.substring( m.start() , m.end() ) );

            return Optional.of( new ComplexNumber( real , imaginary ) );

        } else
        {
            return Optional.empty();
        }
    }
}
