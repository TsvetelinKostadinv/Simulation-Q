/*
 * 24/06/2020 15:34:19
 * QIdentity.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;


import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.simulation.util.math.matrices.Matrix;

import static com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber.*;


/**
 * @author Tsvetelin
 *
 */
public class QIdentity extends QGate
{

    public static final String name      = "#";

    public static final Matrix operation = new Matrix( new ComplexNumber[][] {
            { REAL_UNIT, ORIGIN },
            { ORIGIN, REAL_UNIT },
    } );

    public QIdentity ()
    {
        super( name ,
               operation ,
               1 ,
               1 ,
               "Leaves the qubit unchanged" );
    }

}
