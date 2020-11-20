/*
 * 16/11/2020 16:22:10
 * QGateApplicationTest.java created by Tsvetelin
 */
package com.simulation_q.engine.gate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.simulation_q.engine.qubit.Qubit;
import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
class QGateApplicationTest
{
    
    @Test
    void singleQubitApplicationTest ()
    {
        final Qubit on = Qubit.OFF;
        final Matrix hadamardMatrix =
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _1.value } ,
                    { _1.value , _1.value.negate() }
                } ).get().multiply( _1_OVER_SQRT_2.value );
        final QGate hadamard = new QGate( hadamardMatrix , 1 , 2 );
        
        assertEquals(
            Qubit.HALF_HALF ,
            QGateApplier.apply( hadamard , on ) );
        
    }
    
}
