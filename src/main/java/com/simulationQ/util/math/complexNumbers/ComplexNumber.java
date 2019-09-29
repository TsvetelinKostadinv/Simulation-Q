/*
 * 26/09/2019 16:01:30
 * ComplexNumber.java created by Tsvetelin
 */
package com.simulationQ.util.math.complexNumbers;


import java.math.BigDecimal;


/**
 * 
 * A class to represent a complex number with all it's operations
 * 
 * @apiNote uses BigDecimal
 * 
 * @author Tsvetelin
 *
 */
public final class ComplexNumber
{

    /**
     * This is the number that represents the origin point = 0 + 0i
     */
    public static final ComplexNumber ORIGIN    = new ComplexNumber( 0 , 0 );

    /**
     * This is the number that represents a single unit in the real axis
     * direction = 1 + 0i
     */
    public static final ComplexNumber REAL_UNIT = new ComplexNumber( 1 , 0 );

    /**
     * This is the number that represents a single unit in the imaginary axis
     * direction = 0 + 1i
     */
    public static final ComplexNumber IMAG_UNIT = new ComplexNumber( 0 , 1 );

    /**
     * The real part of the number
     */
    private final BigDecimal          real;

    /**
     * The imaginary part of the number
     */
    private final BigDecimal          imaginary;

    /**
     * Constructs a new complex number object, without arguments the given
     * number represents the origin point a.k.a (0, 0)
     * 
     */
    public ComplexNumber ()
    {
        super();
        this.real = BigDecimal.ZERO;
        this.imaginary = BigDecimal.ZERO;
    }

    /**
     * 
     * Constructs the complex number with the given arguments in the form
     * <b>real + imaginary * i</b>
     * 
     * @param real
     * @param imaginary
     */
    public ComplexNumber ( BigDecimal real , BigDecimal imaginary )
    {
        super();
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * 
     * Constructs the complex number with the given arguments in the form
     * <b>real + imaginary * i</b>
     * 
     * @param real
     * @param imaginary
     */
    public ComplexNumber ( double real , double imaginary )
    {
        super();
        this.real = BigDecimal.valueOf( real );
        this.imaginary = BigDecimal.valueOf( imaginary );
    }

    /**
     * 
     * Constructs the complex number with the given arguments in the form
     * <b>real + imaginary * i</b> <br>
     * 
     * NOTE: The same constraints are in place as with the constructor of
     * BigDecimal
     * 
     * @param real
     * @param imaginary
     * @see BigDecimal
     */
    public ComplexNumber ( String real , String imaginary )
    {
        super();
        this.real = new BigDecimal( real );
        this.imaginary = new BigDecimal( imaginary );
    }

    /**
     * 
     * Parses the argument to the form
     * <b>real + imaginary * i</b> <br>
     * 
     * If it cannot be parsed an exception is thrown
     * 
     * @param real
     * @param imaginary
     * @see Operations.parse()
     */
    public ComplexNumber ( String s )
    {
        super();
        ComplexNumber copy = Operations.parse( s ).get();
        this.real = copy.real;
        this.imaginary = copy.imaginary;
    }

    public static ComplexNumber real ( BigDecimal real )
    {
        return new ComplexNumber( real , BigDecimal.ZERO );
    }

    public static ComplexNumber real ( double real )
    {
        return new ComplexNumber( new BigDecimal( real ) , BigDecimal.ZERO );
    }

    public static ComplexNumber real ( String real )
    {
        return new ComplexNumber( new BigDecimal( real ) , BigDecimal.ZERO );
    }

    public static ComplexNumber imaginary ( BigDecimal imaginary )
    {
        return new ComplexNumber( BigDecimal.ZERO , imaginary );
    }

    public static ComplexNumber imaginary ( double imaginary )
    {
        return new ComplexNumber( BigDecimal.ZERO ,
                new BigDecimal( imaginary ) );
    }

    public static ComplexNumber imaginary ( String imaginary )
    {
        return new ComplexNumber( BigDecimal.ZERO ,
                new BigDecimal( imaginary ) );
    }

    /**
     * 
     * @return the real part of the number
     */
    public BigDecimal getReal ()
    {
        return real;
    }

    /**
     * 
     * @return the imaginary part of the number
     */
    public BigDecimal getImaginary ()
    {
        return imaginary;
    }

    /**
     * The modulus of the number which is equal to sqrt(real^2 + imaginary^2)
     * 
     * @return
     */
    public BigDecimal modulus ()
    {
        return Operations.modulus( this );
    }

    /**
     * Negates the real part of the number
     * <br>
     * NOTE: This is a persistent method
     * 
     * @return the same number with negated real part
     */
    public ComplexNumber negateReal ()
    {
        return Operations.negateReal( this );
    }

    /**
     * <br>
     * NOTE: This is a persistent method
     * 
     * @return the conjugate of the number
     */
    public ComplexNumber conjugate ()
    {
        return Operations.conjugate( this );
    }

    /**
     * Negates the whole number
     * 
     * <br>
     * NOTE: This is a persistent method
     * 
     * @return -(this)
     */
    public ComplexNumber negate ()
    {
        return Operations.negate( this );
    }

    /**
     * Adds a ComplexNumber to this number
     * 
     * <br>
     * NOTE: This is a persistent method
     * 
     * @param a
     *            - the number to be added
     * @return the sum: this + a
     */
    public ComplexNumber add ( ComplexNumber a )
    {
        return Operations.add( this , a );
    }

    /**
     * 
     * Subtracts the argument from the current number
     * 
     * <br>
     * NOTE: This is a persistent method
     * 
     * @param a
     *            - the number to be subtracted
     * @return the difference: this - a
     */
    public ComplexNumber subtract ( ComplexNumber a )
    {
        return Operations.subtract( this , a );
    }

    /**
     * 
     * Multiplies the current complex number with the argument
     * <br>
     * NOTE: This is a persistent method
     * 
     * @param a
     * @return the product
     */
    public ComplexNumber multiply ( ComplexNumber a )
    {
        return Operations.multiply( this , a );
    }

    /**
     * 
     * Divides the current complex number by the argument
     * <br>
     * NOTE: This is a persistent method
     * 
     * @param a
     * @return the quotient
     */
    public ComplexNumber divide ( ComplexNumber a )
    {
        return Operations.divide( this , a );
    }

    /**
     * 
     * Raises the current complex number to the n-th power
     * <br>
     * NOTE: This is a persistent method
     * 
     * @param n
     * @return the n-th power of the number
     */
    public ComplexNumber power ( int n )
    {
        return Operations.power( this , n );
    }

    /**
     * 
     * @return The absolute value of the complex number
     */
    public BigDecimal abs ()
    {
        return Operations.abs( this );
    }

    /**
     * <br>
     * NOTE: This is a persistent method
     * 
     * @return sin(x), where x is the current complex number
     */
    public ComplexNumber sin ()
    {
        return Operations.sin( this );
    }

    /**
     * <br>
     * NOTE: This is a persistent method
     * 
     * @return cos(x), where x is the current complex number
     */
    public ComplexNumber cos ()
    {
        return Operations.cos( this );
    }

    /**
     * <br>
     * NOTE: This is a persistent method
     * 
     * @return tan(x), where x is the current complex number
     */
    public ComplexNumber tan ()
    {
        return Operations.tan( this );
    }

    /**
     * <br>
     * NOTE: This is a persistent method
     * 
     * @return cotan(x), where x is the current complex number
     */
    public ComplexNumber cotan ()
    {
        return Operations.cotan( this );
    }

    @Override
    public boolean equals ( Object obj )
    {
        return obj instanceof ComplexNumber
                && ( (ComplexNumber) obj ).real.equals( this.real )
                && ( (ComplexNumber) obj ).imaginary.equals( this.imaginary );
    }

    @Override
    public String toString ()
    {
        String realPart = this.real.toString();
        char sign = this.imaginary.abs().equals( this.imaginary ) ? '+' : '-';
        String imaginaryPart = this.imaginary.abs().toString();
        return String.format( "%s%s%s*i" , realPart , sign , imaginaryPart );
    }

}
