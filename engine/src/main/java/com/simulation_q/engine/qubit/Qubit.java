/*
 * 29/09/2019 15:51:28
 * Qubit.java created by Tsvetelin
 */
package com.simulation_q.engine.qubit;

import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.vector.Vector;

import lombok.Data;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The purest of qubits(which means that the squares of the
 * coefficient must equal one)
 * 
 * @author Tsvetelin
 */
@Data
public class Qubit
{
    
    public static final Qubit ON =
        new Qubit( _0.value , _1.value );
    
    public static final Qubit OFF =
        new Qubit( _1.value , _0.value );
    
    public static final Qubit HALF_HALF =
        new Qubit(
            _1_OVER_SQRT_2.value ,
            _1_OVER_SQRT_2.value );
    
    private final ComplexNumber ketOFF;
    
    private final ComplexNumber ketON;
    
    public Qubit ( ComplexNumber ketOFF , ComplexNumber ketON )
        throws IllegalArgumentException
    {
        super();
        
        if ( isPureState( ketON , ketOFF ) )
        {
            this.ketOFF = ketOFF;
            this.ketON = ketON;
        } else
        {
            throw new IllegalArgumentException(
                "The sum of the squares of both the ketON and ketOFF must equate to 1" );
        }
    }
    
    /**
     * Converts this qubit to it's vector state
     * 
     * @return a vector representation of this qubit
     */
    public Vector castToVector ()
    {
        return new Vector( new ComplexNumber[] { ketOFF , ketON } );
    }
    
    @Override
    public String toString ()
    {
        return String.format(
            "%s |0> + (%s) |1>" ,
            this.ketOFF.toString() ,
            this.ketON.toString() );
    }
    
    /**
     * Pure state is defined as the sum of on and off coefficients
     * squared, which should be one
     * 
     * @param   onCoef
     * @param   offCoef
     * @return          true if the state is pure
     *                      ( onCoef^2 + offCoef^2 == 1 ), false otherwise
     * @apiNote         keeps track of the rounding error in decimal
     *                      computations
     */
    static boolean isPureState (
        ComplexNumber onCoef ,
        ComplexNumber offCoef )
    {
        return onCoef.abs()
            .pow( 2 )
            .add( offCoef.abs().pow( 2 ) )
            .setScale( 1 , RoundingMode.HALF_UP )
            .stripTrailingZeros()
            .equals( BigDecimal.ONE );
    }
    
}
