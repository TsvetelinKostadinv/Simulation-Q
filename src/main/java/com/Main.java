/*
 * 25/06/2020 00:37:21
 * Main.java created by Tsvetelin
 */
package com;

import java.util.Arrays;
import java.util.Optional;

import com.gui.GUIStarter;
import com.scripting.ScriptExecutor;
import com.simulationQ.simulation.computation.QCollapser;
import com.simulationQ.simulation.computation.qubits.register.QRegister;

/**
 * @author Tsvetelin
 */
public class Main
{
    
    /**
     * @param args
     */
    public static void main ( String [] args )
    {
        if ( args.length == 0 )
        {
            GUIStarter.main( args );
        } else
            if ( args.length == 2
                && args[0] == "-s" )
            {
                Optional< QRegister > register =
                    ScriptExecutor.executeScript( args[1] );
                
                register.ifPresentOrElse(
                    reg -> System.out
                        .println( "Result: " + QCollapser.collapse( reg ) ) ,
                    () -> System.err.println( "EXECUTION FAILED" ) );
            } else
            {
                System.err.println(
                    "Unrecognised comman flags: " + Arrays.toString( args ) );
            }
        
    }
    
}
