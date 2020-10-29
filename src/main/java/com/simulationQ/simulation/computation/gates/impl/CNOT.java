/*
 * 14/06/2020 18:01:58
 * CNOT.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import java.io.Serializable;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.matrices.Matrix;
import com.simulation_q.math.complex_numbers.ComplexNumber;

import static com.simulation_q.math.complex_numbers.ComplexNumber.Algebraic.*;

/**
 * @author Tsvetelin
 */
public class CNOT extends QGate implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    public static final String NAME = "CNOT";
    
    public static final Matrix OPERATION =
        new Matrix(
            new ComplexNumber[][] {
                { realUnit() , origin() , origin() , origin() } ,
                { origin() , realUnit() , origin() , origin() } ,
                { origin() , origin() , origin() , realUnit() } ,
                { origin() , origin() , realUnit() , origin() }
            } );
    
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
