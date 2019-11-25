/*
 * 06/10/2019 22:57:03
 * VectorOperations.java created by Tsvetelin
 */
package com.simulationQ.util.math.matrices.vectors;

import com.simulationQ.util.math.matrices.MatrixOperations;

/**
 * 
 * An extension to arithmetic operations for vectors
 * 
 * @author Tsvetelin
 *
 */
public interface VectorOperations extends MatrixOperations
{
    public static Vector tensorProduct( final Vector a , final Vector b)
    {
        return a.tensorProduct(b);
    }
    
    public Vector tensorProduct( final Vector a);
    
    public Vector get( final int indexFrom, final int indexTo );
    
    public int size();
}
