/*
 * 27/11/2019 22:37:03
 * PauliY.java created by Tsvetelin
 */
package com.simulationQ.computation.gates.impl;


import com.simulationQ.computation.gates.QGate;
import com.simulationQ.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.util.math.matrices.Matrix;


/**
 * @author Tsvetelin
 *
 */
public class PauliY extends QGate
{

    public static final Matrix OPERATION_MATRIX     = new Matrix( new ComplexNumber[][] {
            { ComplexNumber.ORIGIN, ComplexNumber.IMAG_UNIT.negate() },
            { ComplexNumber.IMAG_UNIT, ComplexNumber.ORIGIN }
    } );

    public static final int    NUMBER_OF_INPUT_BITS = 1;

    public static final int    PERIOD               = 1;

    public static final String INFORMATION          = "Flips the bloch sphere around the y axis";
    
    public PauliY ()
    {
        super( OPERATION_MATRIX , NUMBER_OF_INPUT_BITS , PERIOD , INFORMATION );
    }

}
