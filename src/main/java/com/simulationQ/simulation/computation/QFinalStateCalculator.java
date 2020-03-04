/*
 * 08/12/2019 14:03:39
 * QFinalStateCalculator.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation;


import com.simulationQ.simulation.computation.gates.QGateApplier;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.register.QRegister;


/**
 * @author Tsvetelin
 *
 */
public interface QFinalStateCalculator
{

    public static QRegister calculateFinalState ( QProgram program ,
                                                  QRegister startState )
    {

        return program.stream()
                      .reduce( startState ,
                               ( state ,
                                 programPart ) -> QGateApplier.apply( programPart.getOper() ,
                                                                      state ,
                                                                      programPart.getStartIndexInRegister() ) ,
                               ( oldState , newState ) -> newState );
    }

}
