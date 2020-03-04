/*
 * 26/02/2020 15:28:43
 * ExtendingMatricesTest.java created by Tsvetelin
 */
package com.simulation;

import com.simulationQ.simulation.computation.gates.impl.NOT;
import com.simulationQ.simulation.util.math.matrices.Matrix;
import com.simulationQ.simulation.util.math.matrices.MatrixOperations;

/**
 * @author Tsvetelin
 *
 */
public abstract class ExtendingMatricesTest
{
    public static void main ( String [] args )
    {
        final Matrix oper = NOT.OPERATION_MATRIX;
        final Matrix identity = Matrix.multiplicativeIdentity( oper.getRows() , oper.getColons() );
        
        System.out.println( MatrixOperations.productKronecker( oper , identity ) );
        
    }
}
