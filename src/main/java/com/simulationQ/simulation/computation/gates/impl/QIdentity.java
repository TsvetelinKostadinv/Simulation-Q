/*
 * 24/06/2020 15:34:19
 * QIdentity.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
public class QIdentity extends QGate
{
    
    public static final String name = "#";
    
    public static final Matrix operation =
        Matrix.of(
            new ComplexNumber[][] {
                { _1.value , _0.value } ,
                { _0.value , _1.value } ,
            } ).get();
    
    public QIdentity ()
    {
        super(
            name ,
            operation ,
            1 ,
            1 ,
            "Leaves the qubit unchanged" );
    }
    
}
