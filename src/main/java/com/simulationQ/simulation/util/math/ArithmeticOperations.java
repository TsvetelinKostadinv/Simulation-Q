/*
 * 06/10/2019 18:18:59
 * ArithmeticOperations.java created by Tsvetelin
 */
package com.simulationQ.simulation.util.math;


/**
 * 
 * Base interface for all arithmetic objects <br>
 * all that us needed to implement is: add, multiply, negate and invert, all
 * other functions can be overriden but they have default values
 * 
 * @author Tsvetelin
 *
 */
public interface ArithmeticOperations < T extends ArithmeticOperations< T > >
{

    /**
     * 
     * @param a
     * @return this+a
     */
    public T add ( final T a );

    /**
     * 
     * Note it may be position sensitive!!
     * 
     * @param a
     * @return this*a
     */
    public T multiply ( final T a );

    /**
     * 
     * @return -this
     */
    public T negate ();

    /**
     * 
     * @param n
     * @return this^n
     */
    public T pow ( final int n );

    /**
     * 
     * This should return an object which is the identity with respect to
     * multiplication. In other words anything multiplied with the object
     * returned from this method should not be changed
     * 
     * @return the multiplicative identity for this class
     */
    public T multiplicativeIdentity ();

    /**
     * 
     * This should return an object which is the identity with respect to
     * addition. In other words anything added with the object
     * returned from this method should not be changed
     * 
     * @return the additive identity for this class
     */
    public T additiveIdentity ();

    /**
     * 
     * @param a
     * @return this -a
     */
    public default T subtract ( final T a )
    {
        return this.add( a.negate() );
    }

    /**
     * 
     * @param a
     * @return this/a
     */
    public default T divide ( final T a )
    {
        return this.multiply( a.invert() );
    }

    /**
     * 
     * @return 1/this
     */
    public default T invert ()
    {
        return this.pow( -1 );
    }

    /**
     * 
     * @return this^2
     */
    public default T square ()
    {
        return this.pow( 2 );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a+b
     */
    public static < T extends ArithmeticOperations< T > > T add ( final T a ,
                                                                  final T b )
    {
        return a.add( b );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a-b
     */
    public static < T extends ArithmeticOperations< T > > T subtract ( final T a ,
                                                                       final T b )
    {
        return a.subtract( b );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a*b
     */
    public static < T extends ArithmeticOperations< T > > T multiply ( final T a ,
                                                                       final T b )
    {
        return a.multiply( b );
    }

    /**
     * 
     * @param a
     * @param b
     * @return a/b
     */
    public static < T extends ArithmeticOperations< T > > T divide ( final T a ,
                                                                     final T b )
    {
        return a.divide( b );
    }

    /**
     * 
     * @param a
     * @return -a
     */
    public static < T extends ArithmeticOperations< T > > T negate ( final T a )
    {
        return a.negate();
    }

    /**
     * 
     * @param a
     * @return 1/a
     */
    public static < T extends ArithmeticOperations< T > > T invert ( final T a )
    {
        return a.invert();
    }

    /**
     * 
     * @param a
     * @param n
     * @return a^n
     */
    public static < T extends ArithmeticOperations< T > > T pow ( final T a ,
                                                                  final int n )
    {
        return a.pow( n );
    }
}
