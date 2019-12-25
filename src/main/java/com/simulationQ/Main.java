/*
 * 27/11/2019 21:39:22
 * Main.java created by Tsvetelin
 */
package com.simulationQ;


/**
 * @author Tsvetelin
 *
 */
public class Main
{

    /**
     * 
     */
    public Main ()
    {}
    
    /**
     * 
     * @param args
     */
    public static void main ( String [] args )
    {
        try
        {
            switch( args[0] )
            {
                case "gui": startInGUIMode(args); break;
                case "nogui": startInNOGUIMode(args); break;
                default: System.out.println( "Unexpected command: " + args[0] );
            }
        } catch ( IndexOutOfBoundsException e )
        {
            System.out.println( "You must specify the mode first - gui/nogui" );
        }
    }

    /**
     * @param args
     */
    private static void startInGUIMode ( String [] args )
    {
        System.out.println( "String GUI" );
        
    }

    /**
     * @param args
     */
    private static void startInNOGUIMode ( String [] args )
    {
        System.out.println( "String NOGUI" );
    }
    
}
