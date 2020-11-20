/*
 * 17/11/2020 10:28:05
 * QubitCollapserTest.java created by Tsvetelin
 */
package com.simulation_q.engine.collapse;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.simulation_q.engine.qubit.Qubit;

/**
 * @author Tsvetelin
 */
public class QubitCollapserTest
{
    @Test
    void collapseOfBaseState ()
    {
        assertEquals(
            Qubit.ON ,
            QubitCollapser.collapse( Qubit.ON ) ,
            "Collapse should not change the base state" );
        assertEquals(
            Qubit.OFF ,
            QubitCollapser.collapse( Qubit.OFF ) ,
            "Collapse should not change the base state" );
        
        assertEquals(
            Qubit.ON ,
            QubitCollapser.collapse( Qubit.ON , () -> BigDecimal.ZERO ) ,
            "Collapse should not change the base state ON, regardless of the decision function" );
        assertEquals(
            Qubit.ON ,
            QubitCollapser.collapse( Qubit.ON , () -> BigDecimal.ONE ) ,
            "Collapse should not change the base state ON, regardless of the decision function" );
        
        assertEquals(
            Qubit.OFF ,
            QubitCollapser.collapse( Qubit.OFF , () -> BigDecimal.ZERO ) ,
            "Collapse should not change the base state OFF, regardless of the decision function" );
        assertEquals(
            Qubit.OFF ,
            QubitCollapser.collapse( Qubit.OFF , () -> BigDecimal.ONE ) ,
            "Collapse should not change the base state OFF, regardless of the decision function" );
    }
}
