/*
 * 16/11/2020 17:52:29
 * QubitCollapser.java created by Tsvetelin
 */
package com.simulation_q.engine.collapse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.simulation_q.engine.qubit.Qubit;
import com.simulation_q.engine.util.RandomUtil;

/**
 * Utility class for collapsing qubits in superpositions
 * 
 * @author Tsvetelin
 */
public final class QubitCollapser
{
    private QubitCollapser ()
    {
    }
    
    /**
     * A safe version of the collapse method, which for performance
     * reasons does not perform null checks
     * 
     * @param   qubit
     * @param   decisionSupplier
     * @return                   the collapsed qubit to a uniform
     *                               state
     *                               of 0 or 1
     * @apiNote                  TODO a better approach may be to
     *                               calculate the distance from the
     *                               decision to each of the
     *                               coefficients and choose the
     *                               closer one
     */
    private static Qubit safeCollapse (
        final Qubit qubit ,
        final Supplier< BigDecimal > decisionSupplier )
    {
        final int realScale = qubit.getKetON().getReal().scale();
        final int imaginaryScale = qubit.getKetON().getImaginary().scale();
        
        final int largerScale =
            realScale > imaginaryScale ? realScale : imaginaryScale;
        
        final BigDecimal onChance =
            qubit.getKetON()
                .abs()
                .pow( 2 )
                .setScale( largerScale , RoundingMode.FLOOR )
                .stripTrailingZeros();
        
        if ( onChance.equals( BigDecimal.ZERO ) )
        {
            // we need to handle the chance that the qubit has a probability of
            // zero to be on
            return Qubit.OFF;
        }
        
        final BigDecimal decision = decisionSupplier.get();
        
        return decision.compareTo( onChance ) <= 0 ?
            Qubit.ON :
                Qubit.OFF;
    }
    
    /**
     * Collapses the qubit a single time with the given decision maker
     * 
     * @param  qubit
     * @param  decisionSupplier     - the random number supplier which
     *                                  should be in the range 0 to 1
     * @return                      the collapsed qubit to a uniform
     *                                  state
     *                                  of 0 or 1
     * @throws NullPointerException - if any of the arguments are null
     */
    public static Qubit collapse (
        final Qubit qubit ,
        final Supplier< BigDecimal > decisionSupplier )
        throws NullPointerException
    {
        Objects.requireNonNull( qubit );
        Objects.requireNonNull( decisionSupplier );
        return safeCollapse( qubit , decisionSupplier );
    }
    
    /**
     * Collapses the qubit with a standard decision making algorithm
     * 
     * @param  qubit
     * @return                      the collapsed qubit to a uniform
     *                                  state
     *                                  of 0 or 1
     * @throws NullPointerException - if the qubit was null
     */
    public static Qubit collapse ( final Qubit qubit )
        throws NullPointerException
    {
        return collapse( qubit , RandomUtil.decisionSupplier );
    }
    
    /**
     * Generates the data sequentially and eagerly. Collapses the qubit
     * the inputted number of times and goes through the data generation
     * function each time
     * 
     * @param  <T>
     * @param  qubit
     * @param  times
     * @param  dataFunction
     * @param  decisionSupplier
     * @return                          a list of all the collapse cycles
     * @throws NullPointerException     - if any of the arguments are null
     * @throws IllegalArgumentException - if times < 0
     */
    public static < T > List< T > collectCollapseData (
        final Qubit qubit ,
        final long times ,
        final BiFunction< Long , Qubit , T > dataFunction ,
        final Supplier< BigDecimal > decisionSupplier )
        throws NullPointerException ,
        IllegalArgumentException
    {
        Objects.requireNonNull( qubit );
        Objects.requireNonNull( dataFunction );
        
        List< T > res = new LinkedList<>();
        
        for ( long i = 0 ; i < times ; i++ )
        {
            res.add(
                dataFunction
                    .apply( i , safeCollapse( qubit , decisionSupplier ) ) );
        }
        
        return res;
    }
    
    /**
     * Generates the data sequentially and eagerly. Collapses the qubit
     * the inputted number of times and goes through the data generation
     * function each time
     * 
     * @param  <T>
     * @param  qubit
     * @param  times
     * @param  dataFunction
     * @param  decisionSupplier
     * @return                          a list of all the collapse cycles
     * @throws NullPointerException     - if any of the arguments are null
     * @throws IllegalArgumentException - if times < 0
     */
    public static < T > List< T > collectCollapseData (
        final Qubit qubit ,
        final long times ,
        final BiFunction< Long , Qubit , T > dataFunction )
        throws NullPointerException ,
        IllegalArgumentException
    {
        return collectCollapseData(
            qubit ,
            times ,
            dataFunction ,
            RandomUtil.decisionSupplier );
    }
    
    /**
     * Generates a lazy stream which will collapse the qubits when they
     * are needed
     * 
     * @param  qubit
     * @param  times
     * @param  decisionSupplier
     * @return                          a lazy stream of the collapsed
     *                                      qubits, which has exactly
     *                                      times elements
     * @throws NullPointerException     - if any of the arguments are null
     * @throws IllegalArgumentException - if times < 0
     */
    public static Stream< Qubit > collectCollapseData (
        final Qubit qubit ,
        final long times ,
        final Supplier< BigDecimal > decisionSupplier )
        throws NullPointerException ,
        IllegalArgumentException
    {
        Objects.requireNonNull( qubit );
        Objects.requireNonNull( decisionSupplier );
        if ( times < 0 )
        {
            throw new IllegalArgumentException(
                String.format( "Cannot collapse %s times" , times ) );
        }
        return LongStream.range( 0 , times )
            .mapToObj( __ -> safeCollapse( qubit , decisionSupplier ) );
    }
    
    /**
     * Generates a lazy stream which will collapse the qubits when they
     * are needed
     * 
     * @param  qubit
     * @param  times
     * @param  decisionSupplier
     * @return                          a lazy stream of the collapsed
     *                                      qubits, which has exactly
     *                                      times elements
     * @throws NullPointerException     - if any of the arguments are null
     * @throws IllegalArgumentException - if times < 0
     */
    public static Stream< Qubit > collectCollapseData (
        final Qubit qubit ,
        final long times )
        throws NullPointerException ,
        IllegalArgumentException
    {
        return collectCollapseData(
            qubit ,
            times ,
            RandomUtil.decisionSupplier );
    }
    
}
