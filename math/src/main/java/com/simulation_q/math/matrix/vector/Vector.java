/*
 * 06/10/2019 22:57:31
 * Vector.java created by Tsvetelin
 */
package com.simulation_q.math.matrix.vector;

import java.util.Arrays;
import java.util.Iterator;

import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

/**
 * Vector implementation with integration with matrices
 * 
 * @author Tsvetelin
 */
public class Vector extends Matrix
                    implements
                    Iterable< ComplexNumber >
{
    
    /**
     * @param size
     *                 - the dimensions of the vector
     */
    public Vector ( final int size )
    {
        super( allZeroes( 1 , size ) );
    }
    
    public Vector ( final ComplexNumber [] data )
    {
        super( new ComplexNumber[][] { data } );
    }
    
    @Override
    public Vector multiply ( Matrix a )
    {
        return new Vector( super.multiply( a.transpose() ).getRowVector( 0 ) );
    }
    
    /**
     * Returns the raw scalars of this vector
     * 
     * @return
     */
    public ComplexNumber [] getScalars ()
    {
        return this.getRowVector( 0 );
    }
    
    public ComplexNumber getAt ( final int index )
    {
        return this.getAt( 0 , index );
    }
    
    /**
     * @return size of the vector
     */
    public int size ()
    {
        return this.getColumns();
    }
    
    /**
     * Makes the tensor product of this and a(just a kronecker product but
     * for vectors)
     * 
     * @param  a
     * @return   the product by coordinates of the 2 vectors, by the rule:
     *               the first scalar is taken and multiplied with the
     *               whole second vector, then to that is appended the
     *               result of the second scalar multiplied with the
     *               vector and so on.(like the kronecker product for
     *               matrices
     */
    public Vector tensorProduct ( Vector a )
    {
        final Vector [] scaledVectors = new Vector[this.getColumns()];
        final ComplexNumber [] res =
            new ComplexNumber[this.getColumns() * a.getColumns()];
        
        final ComplexNumber [] scalars = this.getScalars();
        
        for ( int i = 0 ; i < scaledVectors.length ; i++ )
        {
            scaledVectors[i] =
                new Vector(
                    a.multiply( scalars[i] )
                        .getRowVector( 0 ) );
        }
        
        for ( int i = 0 ; i < scaledVectors.length ; i++ )
        {
            for ( int j = 0 ; j < scaledVectors[i].getColumns() ; j++ )
            {
                res[scaledVectors[i].getColumns() * i + j] =
                    scaledVectors[i].getAt( j );
            }
        }
        
        return new Vector( res );
    }
    
    @Override
    public Iterator< ComplexNumber > iterator ()
    {
        return Arrays.stream( this.getScalars() ).iterator();
    }
    
    /**
     * Pretty much like List.sublist
     * 
     * @param  indexFrom
     * @param  indexTo
     * @return
     */
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
}
