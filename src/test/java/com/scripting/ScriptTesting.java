/*
 * 24/06/2020 00:32:24
 * ScriptTesting.java created by Tsvetelin
 */
package com.scripting;

import com.simulation_q.engine.collapse.QRegisterCollapser;

/**
 * @author Tsvetelin
 */
public interface ScriptTesting
{
    public static void main ( String [] args )
    {
        ScriptExecutor.executeScript( "src/test/java/com/scripting/test.sqr" )
            .map( reg -> QRegisterCollapser.collapse( reg ) )
            .ifPresent( System.out::println );
        
    }
}
