/*
 * 14/06/2020 18:01:58
 * CNOT.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates.impl;

import java.io.Serializable;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.util.math.matrices.Matrix;


/**
 * @author Tsvetelin
 *
 */
public class CNOT extends QGate implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final String NAME = "CNOT";
    
    
    public CNOT ( String name ,
            Matrix operation ,
            int numberInputBits ,
            int periodOfOperation ,
            String information )
    {
        super( name ,
               operation ,
               numberInputBits ,
               periodOfOperation ,
               information );
        // TODO Auto-generated constructor stub
    }

}
