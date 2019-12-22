/*
 * 10/11/2019 17:15:52
 * QCollapser.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.simulationQ.simulation.computation.qubits.Qubit;
import com.simulationQ.simulation.computation.qubits.register.CRegister;
import com.simulationQ.simulation.computation.qubits.register.QRegister;
import com.simulationQ.simulation.util.math.QMath;


/**
 * @author Tsvetelin
 *
 */
public interface QCollapser
{

    public static final String FORMAT = "N %d -> %s";

    /**
     * Collapse a single qubit a single time, very inaccurate
     * 
     * @param q
     *            - the qubit
     * @return the collapsed state
     */
    public static Qubit collapse ( final Qubit q )
    {
        final BigDecimal onChance = q.getKetON()
                                     .abs()
                                     .pow( 2 )
                                     .setScale( QMath.PRECISION ,
                                                RoundingMode.FLOOR );

        final BigDecimal decision = new BigDecimal( Math.random() );

        return decision.compareTo( onChance ) == -1 ? Qubit.QUBIT_ON
                : Qubit.QUBIT_OFF;
    }

    /**
     * Collapses the qubit multiple times, specifically n-times
     * 
     * @param q
     *            - the qubit
     * @param n
     *            - the number of times, the bigger, the better
     * @return the collapsed state
     */
    public static Qubit collapse ( final Qubit q , final long n )
    {
        long on = 0 , off = 0;

        for ( long i = 0 ; i < n ; i++ )
        {
            if ( collapse( q ).equals( Qubit.QUBIT_ON ) )
                on++;
            else
                off++;
        }

        return on < off ? Qubit.QUBIT_OFF : Qubit.QUBIT_ON;
    }

    /**
     * 
     * collapses n times q and generates logs
     * 
     * @param q
     *            - the qubit
     * @param n
     *            - the number of collapses
     * @return a list of strings for the collapses
     */
    public static List< String > generateCollapseData ( final Qubit q ,
                                                        final long n )
    {
        final List< String > res = new LinkedList<>();

        for ( long i = 0 ; i < n ; i++ )
        {
            res.add( String.format( FORMAT , n , collapse( q ) ) );
        }

        return res;
    }

    /**
     * 
     * Generates the collapses and then applies the formatting
     * 
     * @param q
     *            - the qubit
     * @param n
     *            - the number of times
     * @param format
     * @return a list of logs with the specified formatting
     */
    public static List< String > generateCollapseData ( final Qubit q ,
                                                        final long n ,
                                                        final String format )
    {
        final List< String > res = new LinkedList<>();

        for ( long i = 0 ; i < n ; i++ )
        {
            res.add( String.format( format , n , collapse( q ) ) );
        }

        return res;
    }

    /**
     * Collapses the given register
     * 
     */
    public static CRegister collapse ( final QRegister reg )
    {
        return new CRegister( collapseToString( reg ) );
    }

    /**
     * Collapses the register but returns a string representing the bits
     */
    public static String collapseToString ( final QRegister reg )
    {
        final BigDecimal [] coeficients = Arrays.stream( reg.getComputationalVector()
                                                            .getScalars() )
                                                .map( x -> x.abs()
                                                            .pow( 2 ) )
                                                .map( x -> x.setScale( QMath.PRECISION ,
                                                                       RoundingMode.FLOOR ) )
                                                .collect( Collectors.toUnmodifiableList() )
                                                .toArray( new BigDecimal[1] );

        final BigDecimal sum = Arrays.stream( coeficients )
                                     .reduce( BigDecimal.ZERO ,
                                              ( res ,
                                                next ) -> res.add( next ) );

        final List< String > values = generateBinaryStringValues( QMath.log2( coeficients.length ) );

        final int index = indexOfRange( coeficients , QMath.RND_SUPPLY , sum );

        return values.get( index );
    }

    /**
     * 
     * Collapses the register n-times
     * 
     * @param qReg
     * @param rounds
     * @return
     */
    public static QRegister collapse ( final QRegister reg , final long rounds )
    {
        final int [] counters = Arrays.stream( "0".repeat( reg.size() )
                                                  .split( "" ) )
                                      .mapToInt( Integer::parseInt )
                                      .toArray();

        final List< String > possibilities = generateBinaryStringValues( reg.size() );

        for ( int i = 0 ; i < rounds ; i++ )
        {
            final String collapsed = collapseToString( reg );

            final int index = possibilities.indexOf( collapsed );

            counters[index]++;
        }

        int maxIndex = 0;
        int max = counters[0];

        for ( int i = 1 ; i < counters.length ; i++ )
        {
            if ( max < counters[i] )
            {
                max = counters[i];
                maxIndex = i;
            }
        }

        return new CRegister( possibilities.get( maxIndex ) );
    }

    /**
     * 
     * Generates the possible combinations of 0's and 1's given the number of
     * bits
     * 
     * @param bits
     *            - the number of bits
     * @return a list of the combinations
     */
    public static List< String > generateBinaryStringValues ( final int bits )
    {
        return IntStream.range( 0 , QMath.pow( 2 , bits ) )
                        .mapToObj( Integer::toBinaryString )
                        .map( ( String x ) -> x.length() < bits
                                ? "0".repeat( bits - x.length() ) + x
                                : x )
                        .collect( Collectors.toList() );
    }

    /**
     * 
     * NOTE: You should not be using this, if you want, do at your own risk
     * 
     * @param coefs
     *            - the length of each interval
     * @param rnd
     *            - the supplier of random numbers
     * @param sumOfCoefs
     *            - the length of the whole interval
     * @return the index of the interval in which the random number fell
     * @apiNote This is for internal use ONLY, do use at your own risk
     */
    public static int indexOfRange ( final BigDecimal [] coefs ,
                                     final Supplier< BigDecimal > rnd ,
                                     final BigDecimal sumOfCoefs )
    {
        BigDecimal decision = rnd.get()
                                 .multiply( sumOfCoefs ,
                                            new MathContext( QMath.PRECISION ,
                                                             RoundingMode.HALF_UP ) );

        BigDecimal currentSum = BigDecimal.ZERO;
        int index = 0;
        int counter = 0;

        while ( decision.compareTo( currentSum ) >= 0 )
        {
            currentSum = currentSum.add( coefs[counter] );

            index++;
            counter++;
        }

        return index - 1;
    }
}
