/*
 * 17/11/2020 11:09:40
 * QRegisterTest.java created by Tsvetelin
 */
package com.simulation_q.engine.collapse;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.simulation_q.engine.qubit.QRegister;
import com.simulation_q.engine.qubit.Qubit;

/**
 * @author Tsvetelin
 */
class QRegisterTest
{
    @Test
    void simpleCollapseOfRegister ()
    {
        QRegister reg =
            new QRegister( new Qubit[] { Qubit.ON , Qubit.ON , Qubit.ON } );
        
        assertEquals(
            reg ,
            QRegisterCollapser.collapse( reg ) ,
            "The collapse of a basic register should not change it" );
        
        assertEquals(
            reg ,
            QRegisterCollapser.collapse( reg , () -> BigDecimal.ZERO ) ,
            "The collapse of a basic register should not change it" );
        
        assertEquals(
            reg ,
            QRegisterCollapser.collapse( reg , () -> BigDecimal.ONE ) ,
            "The collapse of a basic register should not change it" );
        
    }
    
    @Test
    void superpositionCollapseTest ()
    {
        QRegister reg =
            new QRegister(
                new Qubit[] {
                    Qubit.HALF_HALF , Qubit.HALF_HALF , Qubit.HALF_HALF } );
        
        QRegister expectedWhenCollapseWithAHalf =
            new QRegister(
                new Qubit[] {
                    Qubit.OFF , Qubit.ON , Qubit.ON } );
        
        QRegister expectedWhenCollapseWithAQuarter =
            new QRegister(
                new Qubit[] {
                    Qubit.OFF , Qubit.OFF , Qubit.ON } );
        
        QRegister allON =
            new QRegister(
                new Qubit[] {
                    Qubit.ON , Qubit.ON , Qubit.ON } );
        
        QRegister allOFF =
            new QRegister(
                new Qubit[] {
                    Qubit.OFF , Qubit.OFF , Qubit.OFF } );
        
        assertEquals(
            expectedWhenCollapseWithAHalf ,
            QRegisterCollapser.collapse(
                reg ,
                () -> BigDecimal.ONE.divide( new BigDecimal( "2" ) ) ) ,
            "The collapse of a basic register should not change it" );
        
        assertEquals(
            expectedWhenCollapseWithAQuarter ,
            QRegisterCollapser.collapse(
                reg ,
                () -> BigDecimal.ONE.divide( new BigDecimal( "4" ) ) ) ,
            "The collapse of a basic register should not change it" );
        
        assertEquals(
            allON ,
            QRegisterCollapser.collapse(
                reg ,
                () -> BigDecimal.ONE ) ,
            "The collapse of a basic register should not change it" );
        
        assertEquals(
            allOFF ,
            QRegisterCollapser.collapse(
                reg ,
                () -> BigDecimal.ZERO ) ,
            "The collapse of a basic register should not change it" );
        
    }
    
}
