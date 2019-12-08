/*
 * 27/11/2019 22:35:02
 * NOT.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.simulation.util.math.matrices.Matrix;

/**
 * @author Tsvetelin
 *
 */
public class NOT extends QGate
{

    public static final Matrix OPERATION_MATRIX     = new Matrix( new ComplexNumber[][] {
            { ComplexNumber.ORIGIN, ComplexNumber.REAL_UNIT },
            { ComplexNumber.REAL_UNIT, ComplexNumber.ORIGIN }
    } );

    public static final int    NUMBER_OF_INPUT_BITS = 1;

    public static final int    PERIOD               = 1;

    public static final String INFORMATION          = "A not gate. analogus to a clasical computer, it flips the qubit";

    /**
     * @param operation
     * @param numberInputBits
     * @param periodOfOperation
     * @param information
     */
    public NOT ()
    {
        super( OPERATION_MATRIX , NUMBER_OF_INPUT_BITS , PERIOD , INFORMATION );
    }

}
