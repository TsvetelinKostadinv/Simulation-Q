/*
 * 14/06/2020 18:01:58
 * CNOT.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import java.io.Serializable;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
public class CNOT extends QGate implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    public static final String NAME = "CNOT";
    
    public static final Matrix OPERATION =
        Matrix.of(
            new ComplexNumber[][] {
                { _1.value , _0.value , _0.value , _0.value } ,
                { _0.value , _1.value , _0.value , _0.value } ,
                { _0.value , _0.value , _0.value , _1.value } ,
                { _0.value , _0.value , _1.value , _0.value }
            } ).get();
    
    public CNOT ()
    {
        super(
            NAME ,
            OPERATION ,
            2 ,
            2 ,
            "A controlled not gate" );
    }
    
}
