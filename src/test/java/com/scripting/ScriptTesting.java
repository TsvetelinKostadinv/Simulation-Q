/*
 * 24/06/2020 00:32:24
 * ScriptTesting.java created by Tsvetelin
 */
package com.scripting;


/**
 * @author Tsvetelin
 *
 */
public interface ScriptTesting
{
    public static void main ( String [] args )
    {
        ScriptExecutor.executeScript( "src/text/java/com/scripting/text.sqr" );
    }
}
