/*
 * 04/03/2020 14:26:10
 * QProgramTesting.java created by Tsvetelin
 */
package com.simulation;

import com.simulationQ.simulation.computation.QFinalStateCalculator;
import com.simulationQ.simulation.computation.gates.impl.Hadamard;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.Qubit;
import com.simulationQ.simulation.computation.qubits.register.QRegister;

/**
 * @author Tsvetelin
 *
 */
public abstract class QProgramTesting
{

    public static void main ( String [] args )
    {
        final QRegister reg = new QRegister( Qubit.QUBIT_ON , Qubit.QUBIT_ON );
        final Hadamard gate = new Hadamard();
        
        final QProgram program = new QProgram();
        
        program.addPart( gate , 0 );
        program.addPart( gate , 1 );
        program.addPart( gate , 0 );
        program.addPart( gate , 1 );
        
        final QRegister res = QFinalStateCalculator.calculateFinalState( program , reg );
        
        System.out.println( res );

    }

}
