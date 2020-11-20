/*
 * 26/02/2020 15:28:43
 * ExtendingMatricesTest.java created by Tsvetelin
 */
package com.simulation;

import com.simulation_q.engine.gate.QGates;
import com.simulation_q.math.matrix.Matrix;

/**
 * @author Tsvetelin
 */
public abstract class ExtendingMatricesTest
{
    public static void main ( String [] args )
    {
        final Matrix oper = QGates.NOT.getOperation();
        final Matrix identity =
            Matrix.multiplicativeIdentity( oper.getRows() , oper.getColumns() );
        
        System.out
            .println( oper.productKronecker( identity ) );
        
    }
}
