/*
 * 12/05/2020 16:18:55
 * BiggerGatesImpl.java created by Tsvetelin
 */
package com;

import com.simulationQ.simulation.computation.QCollapser;
import com.simulationQ.simulation.computation.QFinalStateCalculator;
import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.register.CRegister;
import com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.simulation.util.math.matrices.Matrix;

import static com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber.*;

/**
 * @author Tsvetelin
 *
 */
public class BiggerGatesImpl
{

    public BiggerGatesImpl ()
    {}
    
    private static final Matrix operation = new Matrix( new ComplexNumber[][] {
        { REAL_UNIT , ORIGIN , ORIGIN , ORIGIN },
        { ORIGIN , REAL_UNIT , ORIGIN , ORIGIN },
        { ORIGIN , ORIGIN , ORIGIN , REAL_UNIT },
        { ORIGIN , ORIGIN , REAL_UNIT , ORIGIN }
    });
    
    /**
     * @param args
     */
    public static void main ( String [] args )
    {
        final var reg = new CRegister( "11" );
        final var program = new QProgram();
        
        final var cnot = new QGate("CNOT",operation,2,2,"")
        {};
        
        program.addPart( cnot , 0 );
        
        final var finalState = QFinalStateCalculator.calculateFinalState( program , reg );
        
        System.out.println( QCollapser.collapseToString( finalState ) );
        
    }

}
