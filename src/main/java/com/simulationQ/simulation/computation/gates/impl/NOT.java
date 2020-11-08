/*
 * 27/11/2019 22:35:02
 * NOT.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
public class NOT extends QGate
{
    
    public static final String NAME = "NOT";
    
    public static final Matrix OPERATION_MATRIX =
        Matrix.of(
            new ComplexNumber[][] {
                { _0.value, _1.value } ,
                { _1.value , _0.value }
            } ).get();
    
    public static final int NUMBER_OF_INPUT_BITS = 1;
    
    public static final int PERIOD = 1;
    
    public static final String INFORMATION =
        "A not gate. analogus to a clasical computer, it flips the qubit";
    
    /**
     * @param operation
     * @param numberInputBits
     * @param periodOfOperation
     * @param information
     */
    public NOT ()
    {
        super(
            NAME ,
            OPERATION_MATRIX ,
            NUMBER_OF_INPUT_BITS ,
            PERIOD ,
            INFORMATION );
    }
    
}
