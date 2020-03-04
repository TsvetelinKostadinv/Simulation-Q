/*
 * 08/12/2019 13:57:36
 * Mediator.java created by Tsvetelin
 */
package com.simulationQ;


/**
 * 
 * This is the mediator between the GUI and the engine
 * 
 * @author Tsvetelin
 *
 */
public final class Mediator
{

    public static final Mediator instance = new Mediator();

    /**
     * 
     */
    private Mediator ()
    {}

//    public final Future< QRegister > runSimulation ( final List< QGate > gates ,
//                                                     final QRegister startState ,
//                                                     final long rounds )
//    {
//        final QRegister finalState = QFinalStateCalculator.calculateFinalState( gates ,
//                                                                                startState );
//        final SimulationRound round = new SimulationRound( finalState ,
//                                                           rounds );
//        return Engine.instance.runSimulation( round );
//    }
    

}
