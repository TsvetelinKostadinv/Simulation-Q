/*
 * 27/11/2019 21:39:22
 * Main.java created by Tsvetelin
 */
package com.simulationQ;

import com.simulationQ.gui.MainWindow;

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
     * @param args
     */
    public static void main ( String [] args )
    {
        Thread main = new Thread( new MainWindow() );
        
        main.start();
    }

}
