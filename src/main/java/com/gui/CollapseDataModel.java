/*
 * 20/03/2020 13:17:14
 * QCollapserModel.java created by Tsvetelin
 */
package com.gui;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.simulationQ.simulation.computation.QCollapser;
import com.simulationQ.simulation.computation.QFinalStateCalculator;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.register.CRegister;
import com.simulationQ.simulation.computation.qubits.register.QRegister;

/**
 * @author Tsvetelin
 */
public interface CollapseDataModel
{
    
    // TODO this should take in a Register and a program which will be
    // constructed in the controller
    static Map< String , Number > generateCollapseData (
        final CRegister starting ,
        final QProgram program ,
        final int count )
    {
        final Map< String , Number > res = new LinkedHashMap<>();
        if ( starting.size() == 0 )
            return res;
        
        final QRegister finalState =
            QFinalStateCalculator.calculateFinalState(
                program ,
                starting );
        
        final List< String > results =
            QCollapser.generateCollapseData(
                finalState ,
                count );
        
        for ( String possibility : QCollapser
            .generateBinaryStringValues( starting.size() ) )
        {
            res.put( possibility , 0 );
        }
        
        for ( String state : results )
        {
// res.put( state , res.get( state ).intValue() + 1 );
            res.compute( state , ( str , n ) -> n.intValue() + 1 );
        }
        
        return res;
    }
    
    /**
     * @param  register
     * @param  collapses
     * @return
     */
    static Map< String , Number > generateCollapseData (
        QRegister register ,
        Integer count )
    {
        
        final Map< String , Number > res = new LinkedHashMap<>();
        if ( register.size() == 0 )
            return res;
        final List< String > results =
            QCollapser.generateCollapseData(
                register ,
                count );
        
        for ( String possibility : QCollapser
            .generateBinaryStringValues( register.size() ) )
        {
            res.put( possibility , 0 );
        }
        
        for ( String state : results )
        {
// res.put( state , res.get( state ).intValue() + 1 );
            res.compute( state , ( str , n ) -> n.intValue() + 1 );
        }
        
        return res;
    }
}
