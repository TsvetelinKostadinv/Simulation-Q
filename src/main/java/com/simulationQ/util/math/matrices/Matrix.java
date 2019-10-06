/*
 * 01/10/2019 11:14:53
 * Matrix.java created by Tsvetelin
 */
package com.simulationQ.util.math.matrices;


import java.math.BigDecimal;
import java.util.Arrays;


/**
 * 
 * Matrix implementation for BigDecimal
 * 
 * @author Tsvetelin
 *
 */
public class Matrix implements MatrixOperations
{

    /**
     * The number of rows on the matrix
     */
    private final int              rows;

    /**
     * The number of colons on the matrix
     */
    private final int              colons;

    /**
     * The data matrix
     */
    private final BigDecimal [] [] matrix;

    /**
     * 
     * @param matrix
     *            - actual values for the matrix
     */
    public Matrix ( final BigDecimal [] [] matrix )
    {
        super();
        if ( !MatrixOperations.isMatrixRectangular( matrix ) )
            throw new IllegalArgumentException( "The matrix HAS to be rectangular" );
        this.matrix = matrix;
        this.rows = matrix.length;
        this.colons = matrix[0].length;
    }

    /**
     * 
     * @param matrix
     *            - actual values for the matrix
     */
    public Matrix ( final Double [] [] matrix )
    {
        super();
        if ( !MatrixOperations.isMatrixRectangular( matrix ) )
            throw new IllegalArgumentException( "The matrix HAS to be rectangular" );
        this.rows = matrix.length;
        this.colons = matrix[0].length;
        this.matrix = convertDoubleMatrixToBigDecimal( matrix );
    }

    /**
     * 
     * @param rows
     * @param colons
     */
    public Matrix ( final int rows , final int colons )
    {
        super();
        this.rows = rows;
        this.colons = colons;
        this.matrix = additionIdentity( rows , colons );

    }

    /**
     * 
     * @param rows
     * @param colons
     * @return the additive identity.
     */
    public static final BigDecimal [] [] additionIdentity ( final int rows ,
                                                            final int colons )
    {
        final BigDecimal [] [] res = new BigDecimal[rows][colons];

        for ( int i = 0 ; i < rows ; i++ )
        {
            for ( int j = 0 ; j < colons ; j++ )
            {
                res[i][j] = BigDecimal.ZERO;
            }
        }
        return res;

    }

    /**
     * @return the rows
     */
    public int getRows ()
    {
        return rows;
    }

    /**
     * @return the colons
     */
    public int getColons ()
    {
        return colons;
    }

    /**
     * @return the matrix
     */
    public BigDecimal [] [] getMatrix ()
    {
        return matrix;
    }

    /**
     * 
     * @param row
     * @param colon
     * @return the data at the specified row and colon
     */
    public BigDecimal getAt ( final int row , final int colon )
    {
        return matrix[row][colon];
    }

    /**
     * 
     * Sets the data on the specified row and colon with the specified value
     * 
     * @param row
     * @param colon
     * @param value
     */
    public void setAt ( final int row ,
                        final int colon ,
                        final BigDecimal value )
    {
        this.matrix[row][colon] = value;
    }

    @Override
    public Matrix add ( final Matrix a )
    {
        if ( !MatrixOperations.areMatricesSameSize( a , this ) )
            throw new IllegalArgumentException( "Illegal matrix dimensions." );

        final Matrix res = new Matrix( a.getRows() , a.getColons() );
        for ( int i = 0 ; i < a.getRows() ; i++ )
        {
            for ( int j = 0 ; j < a.getColons() ; j++ )
            {
                res.getMatrix()[i][j] = a.getAt( i , j )
                                         .add( this.getAt( i , j ) );
            }
        }
        return res;
    }

    @Override
    public Matrix multiply ( final Matrix a )
    {
        if ( a.getColons() != this.getRows() )
            throw new IllegalArgumentException( "Illegal matrix dimensions." );

        final Matrix res = new Matrix( this.getRows() , a.getColons() );

        for ( int i = 0 ; i < res.getRows() ; i++ )
        {
            for ( int j = 0 ; j < res.getColons() ; j++ )
            {
                for ( int k = 0 ; k < this.getColons() ; k++ )
                {
                    res.setAt( i ,
                               j ,
                               res.getAt( i , j )
                                  .add( this.getAt( i , k )
                                            .multiply( a.getAt( k , j ) ) ) );
                }
            }
        }
        return res;
    }

    @Override
    public Matrix negate ()
    {
        final Matrix res = new Matrix( this.rows , this.colons );

        for ( int i = 0 ; i < this.rows ; i++ )
        {
            for ( int j = 0 ; j < this.colons ; j++ )
            {
                res.setAt( i , j , this.getAt( i , j ).negate() );
            }
        }

        return res;
    }

    @Override
    public Matrix pow ( final int n )
    {
        Matrix res = this.multiplicativeIdentity();
        for ( int i = 0 ; i < n ; i++ )
        {
            res = res.multiply( this );
        }
        return res;
    }

    @Override
    public Matrix multiplicativeIdentity ()
    {
        final Matrix res = additiveIdentity();
        for ( int i = 0 ; i < ( ( this.rows < this.colons ) ? this.rows
                : this.colons ) ; i++ )
        {
            res.setAt( i , i , BigDecimal.ONE );
        }
        return res;
    }

    @Override
    public Matrix additiveIdentity ()
    {
        final Matrix res = additiveIdentity();
        for ( int i = 0 ; i < ( ( this.rows < this.colons ) ? this.rows
                : this.colons ) ; i++ )
        {
            res.setAt( i , i , BigDecimal.ZERO );
        }
        return res;
    }

    /**
     * 
     * A utility method which converts a double matrix to a BigDecimal one
     * 
     * @param matrix
     * @return
     */
    private static final BigDecimal [] [] convertDoubleMatrixToBigDecimal ( final Double [] [] matrix )
    {
        final int rows = matrix.length;
        final int colons = matrix[0].length;
        final BigDecimal [] [] res = new BigDecimal[rows][colons];

        for ( int row = 0 ; row < rows ; row++ )
        {
            for ( int colomn = 0 ; colomn < colons ; colomn++ )
            {
                res[row][colomn] = new BigDecimal( matrix[row][colomn] );
            }
        }

        return res;

    }

    @Override
    public boolean equals ( final Object obj )
    {
        final Matrix casted = ( Matrix ) obj;
        final boolean sameSize = casted.rows == this.rows
                && casted.colons == this.colons;

        if ( !sameSize ) return false;

        for ( int row = 0 ; row < this.rows ; row++ )
        {
            for ( int colon = 0 ; colon < this.colons ; colon++ )
            {
                if ( !this.matrix[row][colon].equals( casted.matrix[row][colon] ) )
                { return false; }
            }
        }

        return true;
    }

    @Override
    public String toString ()
    {
        final StringBuilder sb = new StringBuilder();

        for ( int row = 0 ; row < this.rows - 1 ; row++ )
        {
            sb.append( Arrays.toString( this.matrix[row] )
                    + System.lineSeparator() );
        }

        sb.append( Arrays.toString( this.matrix[this.rows - 1] ) );

        return sb.toString();
    }

    // public static void main ( String [] args ) throws Exception
    // {
    // final Double [] [] matrix = {
    // { 1.0, 1.0 },
    // { 2.0, 2.0 }
    // };
    //
    // final Double [] [] matrix2 = {
    // { 1.0, 2.0 },
    // { 1.0, 2.0 }
    // };
    //
    // final Double [] [] triangular = {
    // { 1.0 },
    // { 1.0, 2.0 },
    // { 1.0, 2.0, 3.0 }
    // };
    //
    // Matrix m = new Matrix( matrix );
    // Matrix m2 = new Matrix( matrix2 );
    // try
    // {
    // Matrix notPossible = new Matrix( triangular );
    // System.out.println( "Vety bad if you see this: " + notPossible );
    // } catch ( IllegalArgumentException e )
    // {
    // System.out.println( "The exception was caught!" );
    // }
    // System.out.println( "toString():" );
    // System.out.println( m );
    //
    // System.out.println( "equals():" );
    // System.out.println( "Different: " + m.equals( m2 ) );
    // System.out.println( "Same: " + m.equals( m ) );
    //
    // System.out.println( "---------------------" );
    //
    // }

}
