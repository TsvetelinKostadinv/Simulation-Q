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
    /**
     * 
     * Multiplies the vectors in a specific way, not sure of the term
     * 
     * @param a
     * @param b
     * @return
     */
    public static Vector tensorProduct( final Vector a , final Vector b)
    {
        return a.tensorProduct(b);
    }
    
    /**
     * 
     * Makes the tensor product of this and a
     * 
     * @param a
     * @return
     */
    public Vector tensorProduct( final Vector a);
    
    /**
     * 
     * Pretty much like List.sublist
     * 
     * @param indexFrom
     * @param indexTo
     * @return
     */
    public Vector get( final int indexFrom, final int indexTo );
    
    /**
     * 
     * @return size of the vector
     */
    public int size();
}
