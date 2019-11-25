/*
 * 06/10/2019 22:57:31
 * Vector.java created by Tsvetelin
 */
package com.simulationQ.util.math.matrices.vectors;


import java.util.Arrays;
import java.util.Iterator;
import com.simulationQ.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.util.math.matrices.Matrix;
import com.simulationQ.util.math.matrices.MatrixOperations;


/**
 * 
 * Vector implementation with integration with matrices
 * 
 * @author Tsvetelin
 *
 */
public class Vector extends Matrix implements VectorOperations , Iterable< ComplexNumber >
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
    public Vector ( final ComplexNumber [] data )
    {
        super( new ComplexNumber[][] { data } );
    }
    
    public static final Vector multiplicativeIdentity(int size)
    {
        ComplexNumber[] res = new ComplexNumber[size];
        
        res[0] = ComplexNumber.REAL_UNIT;
        
        for( int i=1;i<size;i++ )
        {
            res[i] = ComplexNumber.ORIGIN;
        }
        
        return new Vector( res );
    }

    @Override
    public Matrix multiply ( Matrix a )
    {

        return super.multiply( MatrixOperations.transpose( a ) );
    }

    public ComplexNumber [] getScalars ()
    {
        return this.getMatrix()[0];
    }

    @Override
    public int size ()
    {
        return this.getColons();
    }

    @Override
    public Vector tensorProduct ( Vector a )
    {
        final Vector [] scaledVectors = new Vector[this.getColons()];
        final Vector res = new Vector( this.getColons() * a.getColons() );

        final ComplexNumber [] scalars = this.getScalars();

        for ( int i = 0 ; i < scaledVectors.length ; i++ )
        {
            scaledVectors[i] = new Vector( a.multiplyWithScalar( scalars[i] )
                                            .getMatrix()[0] );
        }

        for ( int i = 0 ; i < scaledVectors.length ; i++ )
        {
            for ( int j = 0 ; j < this.getColons() ; j++ )
            {
                res.setAt( 0 ,
                           this.getColons() * i + j ,
                           scaledVectors[i].getAt( 0 , j ) );
            }
        }

        return res;
    }

    @Override
    public Iterator< ComplexNumber > iterator ()
    {
        return Arrays.stream( this.getScalars() ).iterator();
    }

    @Override
    public Vector get ( int indexFrom , int indexTo )
    {
        if ( indexFrom < 0 ||
                indexTo < 0
                || indexTo < indexFrom
                || indexTo >= this.size() )
            throw new IndexOutOfBoundsException();

        ComplexNumber [] res = new ComplexNumber[indexTo - indexFrom + 1];
        int counter = 0;
        for ( int i = indexFrom ; i <= indexTo ; i++ )
        {
            res[counter++] = this.getScalars()[i];
        }

        return new Vector( res );
    }

    // public static void main ( String [] args )
    // {
    // Vector a = new Vector( new ComplexNumber[] {
    // ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT,
    // ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT,
    // ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT,
    // ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT,
    // ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT } );
    //
    // System.out.println( a.get( 0 , 1 ) );
    //
    // Vector b = new Vector( a.multiplyWithScalar( ComplexNumber.REAL_UNIT.add(
    // ComplexNumber.REAL_UNIT ) )
    // .getMatrix()[0] );
    //
    // System.out.println( a );
    // System.out.println( "X" );
    // System.out.println( b );
    // System.out.println( "-------------------" );
    // System.out.println( a.tensorProduct( b ) );
    //
    // }
}
