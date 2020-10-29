/*
 * 27/11/2019 22:40:45
 * PauliZ.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.matrices.Matrix;
import com.simulation_q.math.complex_numbers.ComplexNumber;

import static com.simulation_q.math.complex_numbers.ComplexNumber.Algebraic.*;

/**
 * @author Tsvetelin
 */
public class PauliZ extends QGate
{
    
    public static final String NAME = "Pauli-Z";
    
    public static final Matrix OPERATION_MATRIX =
        new Matrix(
            new ComplexNumber[][] {
                { realUnit(), origin() } ,
                { origin() , realUnit().negate() }
            } );
    
    public static final int NUMBER_OF_INPUT_BITS = 1;
    
    public static final int PERIOD = 1;
    
    public static final String INFORMATION =
        "Leaves the |0> unchanged, but flips |1> to -|1>";
    
    public PauliZ ()
    {
        super(
            NAME ,
            OPERATION_MATRIX ,
            NUMBER_OF_INPUT_BITS ,
            PERIOD ,
            INFORMATION );
    }
    
}
