/*
 * 05/10/2019 15:47:43
 * Operations.java created by Tsvetelin
 */
package com.simulationQ.util.math.matrices;


import com.simulationQ.util.math.ArithmeticOperations;


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
     * 
     * @param matrix
     * @return true if the number of rows equals the number of colons, false
     *         otherwise
     */
    public static < T > boolean isMatrixRectangular ( final T [] [] matrix )
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

    // public static void main ( String [] args )
    // {
    // final Double [] [] m1 = {
    // { 1.0, 2.0 },
    // { 3.0, 4.0 }
    // };
    //
    // final Double [] [] m2 = {
    // { 4.0, 3.0 },
    // { 2.0, 1.0 }
    // };
    //
    // Matrix a = new Matrix( m1 );
    // Matrix b = new Matrix( m2 );
    //
    // System.out.println( ArithmeticOperations.multiply( a , b ) );
    // System.out.println();
    // System.out.println( ArithmeticOperations.multiply( b , a ) );
    // }

}
