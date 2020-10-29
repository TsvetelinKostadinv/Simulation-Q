/*
 * 24/06/2020 15:34:19
 * QIdentity.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.matrices.Matrix;
import com.simulation_q.math.complex_numbers.ComplexNumber;

import static com.simulation_q.math.complex_numbers.ComplexNumber.Algebraic.*;

/**
 * @author Tsvetelin
 */
public class QIdentity extends QGate
{
    
    public static final String name = "#";
    
    public static final Matrix operation =
        new Matrix(
            new ComplexNumber[][] {
                { realUnit() , origin() } ,
                { origin() , realUnit() } ,
            } );
    
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
