/*
 * 26/02/2020 15:12:59
 * QGateApplierTest.java created by Tsvetelin
 */
package com.simulation;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.computation.gates.QGateApplier;
import com.simulationQ.simulation.computation.gates.impl.Hadamard;
import com.simulationQ.simulation.computation.qubits.Qubit;

/**
 * @author Tsvetelin
 */
public abstract class QGateApplierTest
{
    
    public static void main ( String [] args )
    {
        final Qubit on = Qubit.QUBIT_ON;
        final Qubit off = Qubit.QUBIT_OFF;
        
        final QGate oper = new Hadamard();
        
        System.out.println( "------ H * |1>" );
        System.out.println( QGateApplier.applyGateToQubit( oper , on ) );
        
        System.out.println();
        
        System.out.println( "------ H * |0>" );
        System.out.println( QGateApplier.applyGateToQubit( oper , off ) );
        
    }
    
}
