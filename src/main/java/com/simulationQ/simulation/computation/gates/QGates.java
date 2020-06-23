/*
 * 22/03/2020 11:52:27
 * QGates.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.gates;


import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.simulationQ.simulation.computation.gates.impl.Hadamard;
import com.simulationQ.simulation.computation.gates.impl.NOT;
import com.simulationQ.simulation.computation.gates.impl.PauliY;
import com.simulationQ.simulation.computation.gates.impl.PauliZ;


/**
 * @author Tsvetelin
 *
 */
public abstract class QGates
{

    private static final Set< QGate > GATES = new HashSet<>();
    
    
    static {
        // Implementations of single qubit gates
        Hadamard H = new Hadamard();
        NOT NOT = new NOT();
        PauliY Y = new PauliY();
        PauliZ Z = new PauliZ();
    }

    /**
     * 
     */
    private QGates ()
    {}

    public static Set< QGate > getAllGates ()
    {
        return Collections.unmodifiableSet( GATES );
    }

    /**
     * @param qGate
     */
    protected static void addGate ( QGate qGate )
    {
        GATES.add( qGate );
    }

    /**
     * @param gate
     * @return
     */
    public static Optional< QGate > getGateByName ( String gate )
    {
        return GATES.stream().filter( x -> x.getName().equals( gate ) ).findFirst();
    }

}
