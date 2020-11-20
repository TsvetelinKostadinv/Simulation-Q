/*
 * 26/02/2020 15:12:59
 * QGateApplierTest.java created by Tsvetelin
 */
package com.simulation;

import com.simulation_q.engine.gate.QGate;
import com.simulation_q.engine.gate.QGateApplier;
import com.simulation_q.engine.gate.QGates;
import com.simulation_q.engine.qubit.Qubit;

/**
 * @author Tsvetelin
 */
public abstract class QGateApplierTest
{
    
    public static void main ( String [] args )
    {
        final Qubit on = Qubit.ON;
        final Qubit off = Qubit.OFF;
        
        final QGate oper = QGates.HADAMARD;
        
        System.out.println( "------ H * |1>" );
        System.out.println( QGateApplier.apply( oper , on ) );
        
        System.out.println();
        
        System.out.println( "------ H * |0>" );
        System.out.println( QGateApplier.apply( oper , off ) );
        
    }
    
}
