/*
 * 08/12/2019 14:03:39
 * QFinalStateCalculator.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation;


import java.util.List;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.computation.qubits.register.QRegister;


/**
 * @author Tsvetelin
 *
 */
public interface QFinalStateCalculator
{

    public static QRegister calculateFinalState ( List< QGate > gates ,
                                                  QRegister startState )
    {

        return gates.stream()
             .reduce( startState ,
                      ( state , gate ) -> gate.apply( state ) ,
                      ( oldState , newState ) -> newState );
    }
    
}
