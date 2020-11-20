/*
 * 04/03/2020 14:26:10
 * QProgramTesting.java created by Tsvetelin
 */
package com.simulation;

import com.simulationQ.simulation.computation.QFinalStateCalculator;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulation_q.engine.gate.QGate;
import com.simulation_q.engine.gate.QGates;
import com.simulation_q.engine.qubit.QRegister;
import com.simulation_q.engine.qubit.Qubit;

/**
 * @author Tsvetelin
 */
public abstract class QProgramTesting
{
    
    public static void main ( String [] args )
    {
        final QRegister reg = new QRegister( Qubit.ON , Qubit.ON );
        final QGate gate = QGates.HADAMARD;
        
        final QProgram program = new QProgram();
        
        program.addPart( gate , 0 );
        program.addPart( gate , 1 );
        program.addPart( gate , 0 );
        program.addPart( gate , 1 );
        
        final QRegister res =
            QFinalStateCalculator.calculateFinalState( program , reg );
        
        System.out.println( res );
        
    }
    
}
