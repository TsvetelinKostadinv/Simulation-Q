/*
 * 27/11/2019 22:27:55
 * Hadamard.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.simulation.util.math.matrices.Matrix;

/**
 * @author Tsvetelin
 *
 */
public class Hadamard extends QGate
{

    public static final Matrix OPERATION_MATRIX     = new Matrix( new ComplexNumber[][] {
            { ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT },
            { ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT.negate() }
    } ).multiplyWithScalar( ComplexNumber.ONE_OVER_SQRT_2 );

    public static final int    NUMBER_OF_INPUT_BITS = 1;

    public static final int    PERIOD               = 1;

    public static final String INFORMATION          = "Hadamard gate acts on a single cubit."
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
        super( Hadamard.OPERATION_MATRIX ,
               Hadamard.NUMBER_OF_INPUT_BITS ,
               Hadamard.PERIOD ,
               Hadamard.INFORMATION );
    }

}
