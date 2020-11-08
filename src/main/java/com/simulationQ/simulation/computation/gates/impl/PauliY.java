/*
 * 27/11/2019 22:37:03
 * PauliY.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
public class PauliY extends QGate
{
    
    public static final String NAME = "Pauli-Y";
    
    public static final Matrix OPERATION_MATRIX =
        Matrix.of(
            new ComplexNumber[][] {
                { _0.value , _i.value.negate() } ,
                { _i.value , _0.value }
            } ).get();
    
    public static final int NUMBER_OF_INPUT_BITS = 1;
    
    public static final int PERIOD = 1;
    
    public static final String INFORMATION =
        "Flips the bloch sphere around the y axis";
    
    public PauliY ()
    {
        super(
            NAME ,
            OPERATION_MATRIX ,
            NUMBER_OF_INPUT_BITS ,
            PERIOD ,
            INFORMATION );
    }
    
}
