/*
 * 12/05/2020 16:18:55
 * BiggerGatesImpl.java created by Tsvetelin
 */
package com.simulation;

import com.simulationQ.simulation.computation.QCollapser;
import com.simulationQ.simulation.computation.QFinalStateCalculator;
import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.register.CRegister;
import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;


/**
 * @author Tsvetelin
 */
public class BiggerGatesImplTest
{
    
    public BiggerGatesImplTest ()
    {
    }
    
    private static final Matrix operation =
        Matrix.of(
            new ComplexNumber[][] {
                { _1.value , _0.value , _0.value , _0.value } ,
                { _0.value , _1.value , _0.value , _0.value } ,
                { _0.value , _0.value , _0.value , _1.value } ,
                { _0.value , _0.value , _1.value , _0.value }
            } ).get();
    
    /**
     * @param args
     */
    public static void main ( String [] args )
    {
        final var reg = new CRegister( "11" );
        final var program = new QProgram();
        
        final var cnot = new QGate( "CNOT" , operation , 2 , 2 , "" ) {};
        
        program.addPart( cnot , 0 );
        
        final var finalState =
            QFinalStateCalculator.calculateFinalState( program , reg );
        
        System.out.println( QCollapser.collapseToString( finalState ) );
        
    }
    
}
