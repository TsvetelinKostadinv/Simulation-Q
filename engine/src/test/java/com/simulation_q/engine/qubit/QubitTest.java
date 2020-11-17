/*
 * 16/11/2020 15:03:15
 * QubitTest.java created by Tsvetelin
 */
package com.simulation_q.engine.qubit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
class QubitTest
{
    
    @Test
    void pureStateTest ()
    {
        assertTrue(
            Qubit.isPureState( _1.value , _0.value ) ,
            "1 + 0 is pure" );
        assertTrue(
            Qubit.isPureState( _0.value , _1.value ) ,
            "0 + 1 is pure" );
        assertTrue(
            Qubit.isPureState( _1_OVER_SQRT_2.value , _1_OVER_SQRT_2.value ) ,
            "(1/sqrt(2))^2 + (1/sqrt(2))^2 == 1/2 + 1/2 == 1" );
        
        assertFalse(
            Qubit.isPureState( _1.value , _1.value ) ,
            "1 + 1 is not pure" );
        assertFalse(
            Qubit.isPureState( _0.value , _0.value ) ,
            "0 + 0 is not pure" );
    }
    
    @Test
    void constructorTest ()
    {
        assertDoesNotThrow(
            () -> new Qubit( _1.value , _0.value ) ,
            "The pure state is a valid state" );
        assertDoesNotThrow(
            () -> new Qubit( _0.value , _1.value ) ,
            "The pure state is a valid state" );
        
        assertThrows(
            IllegalArgumentException.class ,
            () -> new Qubit( _1.value , _1.value ) ,
            "A qubit was constructed without a pure state" );
    }
    
    @Test
    void equalsTest ()
    {
        assertTrue(
            new Qubit( _1.value , _0.value ).equals( Qubit.OFF ) ,
            "The qubit is fully off" );
        assertTrue(
            new Qubit( _0.value , _1.value ).equals( Qubit.ON ) ,
            "The qubit is fully on" );
    }
    
}
