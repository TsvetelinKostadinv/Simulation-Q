/*
 * 06/12/2019 09:47:23
 * SimulationRound.java created by Tsvetelin
 */
package com.simulationQ;

import com.simulation_q.engine.collapse.QRegisterCollapser;
import com.simulation_q.engine.qubit.QRegister;

/**
 * @author Tsvetelin
 */
public class SimulationRound implements Runnable
{
    
    private final QRegister qReg;
    
// private final long rounds; FIXME needs to use these rounds
    
    private QRegister results;
    
    /**
     * Constructs a object representing the finished state of the
     * computation,
     * 
     * @param qReg
     *                   - the register to be collapsed
     * @param rounds
     *                   - how many times to collapse
     */
    public SimulationRound ( final QRegister qReg , final long rounds )
    {
        this.qReg = qReg;
// this.rounds = rounds;
    }
    
    @Override
    public void run ()
    {
        this.results = runSimulation();
    }
    
    /**
     * @return the state after collapse
     */
    public QRegister runSimulation ()
    {
        // FIXME this.rounds);
        return QRegisterCollapser.collapse( this.qReg );
    }
    
    /**
     * @return the results
     */
    public QRegister getResults ()
    {
        return this.results;
    }
    
}
