/*
 * 05/10/2019 15:47:43
 * Operations.java created by Tsvetelin
 */
package com.simulationQ.simulation.util.math.matrices;

import com.simulationQ.simulation.util.math.ArithmeticOperations;
import com.simulationQ.simulation.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.simulation.util.math.functional.TriFunction;
import com.simulationQ.simulation.util.math.matrices.vectors.Vector;

/**
 * 
 * An extension to arithmetic operations for matrices
 * 
 * @author Tsvetelin
 *
 */
public interface MatrixOperations extends ArithmeticOperations< Matrix >
{

    /**
     * Multiplies the scalar with the matrix
     * 
     * @param a
     *            - the scalar
     * @return this*a
     */
    public Matrix multiplyWithScalar ( final ComplexNumber a );

    /**
     * Multiplies the scalar with the matrix
     * 
     * @param a
     *            - the scalar
     * @param b
     *            - the matrix
     * @return a*b
     */
    public static Matrix multiplyWithScalar ( final ComplexNumber a ,
                                              final Matrix b )
    {
        return b.multiplyWithScalar( a );
    }
    
    /**
     * Applies the trifunction to each element of the matrix
     * @param mapper - a trifunction receiving the x, y and value and producing the new value
     * @return
     */
    public Matrix map ( final TriFunction< Integer , Integer , ComplexNumber , ComplexNumber > mapper );

    /**
     * Applies the trifunction to each element of the matrix
     * @param mapper - a trifunction receiving the x, y and value and producing the new value
     * @return
     */
    public static Matrix map ( final TriFunction< Integer , Integer , ComplexNumber , ComplexNumber > mapper ,
                               final Matrix a )
    {
        return a.map( mapper );
    }

    /**
     * 
     * @param matrix
     * @return true if the number of rows equals the number of colons, false
     *         otherwise
     */
    public static < T > boolean isMatrixSquare ( final T [] [] matrix )
    {
        final int checkAgainst = matrix[0].length;

        for ( int row = 1 ; row < matrix.length ; row++ )
        {
            if ( matrix[row].length != checkAgainst ) return false;
        }

        return true;
    }

    /**
     * 
     * In simple words flips the matrix about it's main diagonal
     * 
     * @param a
     * @return a^T
     */
    public static Matrix transpose ( final Matrix a )
    {
        final Matrix transpose = new Matrix( a.getColons() , a.getRows() );
        for ( int i = 0 ; i < a.getColons() ; i++ )
            for ( int j = 0 ; j < a.getRows() ; j++ )
                transpose.setAt( i , j , a.getAt( j , i ) );
        return transpose;
    }

    /**
     * 
     * @param a
     * @param b
     * @return true if the number of colons of matrix a equal the number of rows
     *         of matrix b and number of colons of matrix a equal the number of
     *         colons on matrix b
     */
    public static boolean areMatricesSameSize ( final Matrix a ,
                                                final Matrix b )
    {
        return a.getRows() == b.getRows() && a.getColons() == b.getColons();
    }

    public static Vector multiply ( final Matrix a , final Vector b )
    {
        if ( a.getColons() != b.size() )
        {
            throw new IllegalArgumentException( "The number of cols in the matrix should be equal to the number of elements in the vector" );
        }

        final Vector res = new Vector( a.getRows() );

        for ( int i = 0 ; i < a.getRows() ; i++ )
        {
            ComplexNumber [] row = a.getMatrix()[i];

            ComplexNumber sumOfRow = ComplexNumber.ORIGIN;

            for ( int j = 0 ; j < b.size() ; j++ )
            {
                sumOfRow = sumOfRow.add( row[j].multiply( b.getAt( j ) ) );
            }

            res.setAt( i , sumOfRow );

        }

        return res;

    }

    // public static void main ( String [] args )
    // {
    // Matrix m = new Matrix( new ComplexNumber[][] {
    // { ComplexNumber.REAL_UNIT, ComplexNumber.REAL_UNIT.negate(),
    // ComplexNumber.REAL_UNIT.add( ComplexNumber.REAL_UNIT ) }, // 1,
    // // -1,
    // // 2
    // { ComplexNumber.ORIGIN,
    // ComplexNumber.REAL_UNIT.add( ComplexNumber.REAL_UNIT.add(
    // ComplexNumber.REAL_UNIT ) )
    // .negate(),
    // ComplexNumber.REAL_UNIT } // 0, -3, 1
    // } );
    //
    // System.out.println( m );
    //
    // Vector v = new Vector( new ComplexNumber[] {
    // ComplexNumber.REAL_UNIT.add( ComplexNumber.REAL_UNIT ),
    // ComplexNumber.REAL_UNIT,
    // ComplexNumber.ORIGIN
    // } );
    //
    // System.out.println( );
    //
    // System.out.println( v );
    //
    // System.out.println( );
    //
    // System.out.println( MatrixOperations.multiply( m , v ) );
    //
    // }

}
