/*
 * 16/11/2020 20:04:26
 * QRegisterCollapser.java created by Tsvetelin
 */
package com.simulation_q.engine.collapse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.simulation_q.engine.qubit.QRegister;
import com.simulation_q.engine.qubit.Qubit;
import com.simulation_q.engine.util.RandomUtil;
import com.simulation_q.math.QMath;

/**
 * Utility for collapsing whole registers
 * 
 * @author Tsvetelin
 */
public final class QRegisterCollapser
{
    private QRegisterCollapser ()
    {
    }
    
    /**
     * Collapses the register but returns a string representing the bits
     * 
     * @param  reg
     * @param  decisionSupplier
     * @return                  a string consisting of 1s and 0s which
     *                              represent the final state
     */
    public static String collapseToString (
        final QRegister reg ,
        final Supplier< BigDecimal > decisionSupplier )
    {
        final int globalPrecision = reg.getRegisterPrecision();
        final BigDecimal [] coeficients =
            Arrays.stream(
                reg.castToVector()
                    .getScalars() )
                .map(
                    x -> x.abs()
                        .pow( 2 ) )
                .map(
                    x -> x.setScale(
                        globalPrecision ,
                        RoundingMode.HALF_UP ) )
                .toArray( len -> new BigDecimal[len] );
        
        final BigDecimal sum =
            Arrays.stream( coeficients )
                .reduce(
                    BigDecimal.ZERO ,
                    ( res , next ) -> res.add( next ) );
        
        final List< String > values =
            generateBinaryStringValues(
                (int) QMath.log2( reg.castToVector().size() ) );
        
        final int index =
            indexOfRange( coeficients , decisionSupplier , sum );
        
        return values.get( index );
    }
    
    /**
     * Collapses the register but returns a string representing the bits
     * 
     * @param  reg
     * @param  decisionSupplier
     * @return                  a string consisting of 1s and 0s which
     *                              represent the final state
     */
    public static String collapseToString ( final QRegister reg )
    {
        return collapseToString( reg , RandomUtil.decisionSupplier );
    }
    
    /**
     * Collapses the given register with the decision supplier
     * 
     * @param  reg
     * @param  decisionSupplier
     * @return                  the collapsed register which is not in
     *                              superposition anymore
     */
    public static QRegister collapse (
        QRegister reg ,
        final Supplier< BigDecimal > decisionSupplier )
    {
        final Qubit [] bits =
            collapseToString( reg , decisionSupplier ).chars()
                .mapToObj( ch -> ch == '1' ? Qubit.ON : Qubit.OFF )
                .toArray( len -> new Qubit[len] );
        return new QRegister( bits );
    }
    
    /**
     * Collapses the given register with the decision supplier
     * 
     * @param  reg
     * @param  decisionSupplier
     * @return                  the collapsed register which is not in
     *                              superposition anymore
     */
    public static QRegister collapse ( QRegister reg )
    {
        return collapse( reg , RandomUtil.decisionSupplier );
    }
    
    /**
     * Generates the possible combinations of 0's and 1's given the number
     * of bits
     * 
     * @param  bits
     *                  - the number of bits
     * @return      a list of the combinations
     */
    private static List< String > generateBinaryStringValues ( final int bits )
    {
        return IntStream.range( 0 , (int) Math.pow( 2 , bits ) )
            .mapToObj( Integer::toBinaryString )
            .map( ( String x ) -> x.length() < bits ? "0".repeat( bits - x.length() ) + x : x )
            .collect( Collectors.toList() );
    }
    
    /**
     * Sums up the coefficients and generates a random value from the
     * supplier. Then returns the index of the range in which the random
     * fell in
     * 
     * @param  coefs
     *                        - the length of each interval
     * @param  rnd
     *                        - the supplier of random numbers
     * @param  sumOfCoefs
     *                        - the length of the whole interval
     * @return            the index of the interval in which the random
     *                        number fell
     */
    private static int indexOfRange (
        final BigDecimal [] coefs ,
        final Supplier< BigDecimal > rnd ,
        final BigDecimal sumOfCoefs )
        throws NullPointerException
    {
        Objects.requireNonNull( coefs );
        Objects.requireNonNull( rnd );
        Objects.requireNonNull( sumOfCoefs );
        
        BigDecimal decision =
            rnd.get().multiply( sumOfCoefs ).stripTrailingZeros();
        
        if ( decision.equals( BigDecimal.ZERO ) )
        {
            for ( int i = 0 ; i < coefs.length ; i++ )
            {
                if ( !coefs[i].equals( BigDecimal.ZERO ) )
                {
                    // if the decision is 0 then we do not want to change the
                    // register
                    return i;
                }
            }
        }
        
        BigDecimal currentSum = BigDecimal.ZERO;
        int index = 0;
        int counter = 0;
        
        while ( decision.compareTo( currentSum ) > 0 )
        {
            currentSum = currentSum.add( coefs[counter] );
            
            index++;
            counter++;
        }
        
        return index - 1;
    }
}
