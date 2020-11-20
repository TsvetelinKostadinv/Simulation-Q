/*
 * 24/12/2019 13:32:13
 * EngineTryingOfConcurrencyTest.java created by Tsvetelin
 */
package com.simulation;

/**
 * @author Tsvetelin
 */
public class EngineTryingOfConcurrencyTest
{
    
//    /**
//     * 
//     */
//    public EngineTryingOfConcurrencyTest ()
//    {
//    }
//    
//    /**
//     * @param args
//     */
//    public static void main ( String [] args )
//    {
//        try ( final Engine eng = Engine.instance )
//        {
//            final QRegister reg =
//                new QRegister(
//                    new Qubit[] {
//                        Qubit.QUBIT_HALF_HALF , Qubit.QUBIT_HALF_HALF
//                    } );
//            
//            final SimulationRound round = new SimulationRound( reg , 100_000 );
//            
//// QRegister res = round.runSimulation();
////
//// System.out.println( "Result blocking: " + res );
//            
//            Future< QRegister > resAsync = eng.runSimulation( round );
//            
//            while ( !resAsync.isDone() )
//            {
//            } // waiting for execution
//            
//            System.out.println( "Result concurrent: " + resAsync.get() );
//            
//        } catch ( Exception e )
//        {
//            e.printStackTrace();
//        }
//    }
}
