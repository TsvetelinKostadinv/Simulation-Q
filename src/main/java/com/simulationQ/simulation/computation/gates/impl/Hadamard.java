/*
 * 27/11/2019 22:27:55
 * Hadamard.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
public class Hadamard extends QGate
{
    
    public static final String NAME = "Hadamard";
    
    public static final Matrix OPERATION_MATRIX =
        Matrix.of(
            new ComplexNumber[][] {
                { _1.value , _1.value } ,
                { _1.value , _1.value.negate() }
            } ).get();//.multiplyWithScalar( ComplexNumber.ONE_OVER_SQRT_2 );
    
    public static final int NUMBER_OF_INPUT_BITS = 1;
    
    public static final int PERIOD = 1;
    
    public static final String INFORMATION =
        "Hadamard gate acts on a single cubit."
            + " It is named after Jacques  Salomon Hadamard."
            + " It maps a qubit to a 50-50 qubit that has equal probability of collapsing to a |0> or |1>";
    
    /**
     * @param operation
     * @param numberInputBits
     * @param periodOfOperation
     * @param information
     */
    public Hadamard ()
    {
        super(
            Hadamard.NAME ,
            Hadamard.OPERATION_MATRIX ,
            Hadamard.NUMBER_OF_INPUT_BITS ,
            Hadamard.PERIOD ,
            Hadamard.INFORMATION );
    }
    
}
