/*
 * 06/12/2019 09:45:06
 * Engine.java created by Tsvetelin
 */
package com.simulationQ;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.simulation_q.engine.qubit.QRegister;

/**
 * @author Tsvetelin
 */
public class Engine implements Closeable
{
    
    public static final Engine instance = new Engine();
    
    private static final int NEEDED_THREADS = 2;
    
    private ExecutorService ex;
    
    /**
     * 
     */
    private Engine ()
    {
        this.ex = Executors.newFixedThreadPool( NEEDED_THREADS );
    }
    
    public Future< QRegister > runSimulation ( SimulationRound round )
    {
        return ex.submit( round::runSimulation );
    }
    
    @Override
    public void close () throws IOException
    {
        ex.shutdown();
    }
    
}
