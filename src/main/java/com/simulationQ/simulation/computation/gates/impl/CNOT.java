/*
 * 14/06/2020 18:01:58
 * CNOT.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;


import static com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber.ORIGIN;
import static com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber.REAL_UNIT;

import java.io.Serializable;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.simulation.util.math.matrices.Matrix;


/**
 * @author Tsvetelin
 *
 */
public class CNOT extends QGate implements Serializable
{

    private static final long   serialVersionUID = 1L;

    public static final String  NAME             = "CNOT";

    public static final Matrix OPERATION        = new Matrix( new ComplexNumber[][] {
            { REAL_UNIT, ORIGIN, ORIGIN, ORIGIN },
            { ORIGIN, REAL_UNIT, ORIGIN, ORIGIN },
            { ORIGIN, ORIGIN, ORIGIN, REAL_UNIT },
            { ORIGIN, ORIGIN, REAL_UNIT, ORIGIN }
    } );

    public CNOT ()
    {
        super( NAME ,
               OPERATION ,
               2 ,
               2 ,
               "A controlled not gate" );
    }

}
