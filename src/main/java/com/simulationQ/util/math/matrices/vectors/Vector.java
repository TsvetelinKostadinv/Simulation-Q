/*
 * 06/10/2019 22:57:31
 * Vector.java created by Tsvetelin
 */
package com.simulationQ.util.math.matrices.vectors;


import com.simulationQ.util.math.matrices.Matrix;
import com.simulationQ.util.math.matrices.MatrixOperations;


/**
 * 
 * Vector implementation with integration with matrices
 * 
 * @author Tsvetelin
 *
 */
public class Vector extends Matrix implements VectorOperations
{

    /**
     * 
     * @param size
     *            - the dimensions of the vector
     */
    public Vector ( final int size )
    {
        super( 1 , size );
    }

    /**
     * 
     * @param data
     */
    public Vector ( final Double [] data )
    {
        super( new Double[][] { data } );

    }

    @Override
    public Matrix multiply ( Matrix a )
    {

        return super.multiply( MatrixOperations.transpose( a ) );
    }

    // public static void main ( String [] args )
    // {
    // Double[] m1 = {1.0 , 1.0};
    // Double[] m2 = {1.0 , 2.0};
    //
    // Vector v1 = new Vector( m1 );
    // Vector v2 = new Vector( m2 );
    //
    // System.out.println( v1 );
    // System.out.println( "*" );
    // System.out.println( v2 );
    // System.out.println( "------" );
    // System.out.println( v1.multiply( v2 ) );
    // System.out.println( );
    // System.out.println( v2.multiply( v1 ) );
    // }

}
