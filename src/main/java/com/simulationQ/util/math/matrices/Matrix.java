/*
 * 01/10/2019 11:14:53
 * Matrix.java created by Tsvetelin
 */
package com.simulationQ.util.math.matrices;


import java.util.Arrays;
import com.simulationQ.util.math.complexNumbers.ComplexNumber;
import com.simulationQ.util.math.functional.TriFunction;


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
    private final int                 rows;

    /**
     * The number of colons on the matrix
     */
    private final int                 colons;

    /**
     * The data matrix
     */
    private final ComplexNumber [] [] matrix;

    /**
     * 
     * @param matrix
     *            - actual values for the matrix
     */
    public Matrix ( final ComplexNumber [] [] matrix )
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
    public static final ComplexNumber [] [] additionIdentity ( final int rows ,
                                                               final int colons )
    {
        final ComplexNumber [] [] res = new ComplexNumber[rows][colons];

        for ( int i = 0 ; i < rows ; i++ )
        {
            for ( int j = 0 ; j < colons ; j++ )
            {
                res[i][j] = ComplexNumber.ORIGIN;
            }
        }
        return res;

    }
    
    public static final ComplexNumber [] [] multiplicativeIdentity ( final int rows ,
                                                               final int colons )
    {
        final ComplexNumber [] [] res = new ComplexNumber[rows][colons];

        for ( int i = 0 ; i < rows ; i++ )
        {
            for ( int j = 0 ; j < colons ; j++ )
            {
                if( i==j )
                {
                    res[i][j] = ComplexNumber.REAL_UNIT;
                }else {
                    res[i][j] = ComplexNumber.ORIGIN;
                }
                
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
    public ComplexNumber [] [] getMatrix ()
    {
        return matrix;
    }

    /**
     * 
     * @param row
     * @param colon
     * @return the data at the specified row and colon
     */
    public ComplexNumber getAt ( final int row , final int colon )
    {
        if ( row < 0 || row >= this.rows )
            throw new IndexOutOfBoundsException( row );
        if ( colon < 0 || colon >= this.colons )
            throw new IndexOutOfBoundsException( colon );

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
                        final ComplexNumber value )
    {
        if ( row < 0 || row >= this.rows )
            throw new IndexOutOfBoundsException( row );
        if ( colon < 0 || colon >= this.colons )
            throw new IndexOutOfBoundsException( colon );

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
        
        System.out.println( "Multiplication." );
        
        final Matrix res = new Matrix( this.getRows() , a.getColons() );
        
        System.out.println( "THIS: " + this );
        System.out.println( this.rows );
        System.out.println( "* by : "+ a );
        System.out.println( a.getColons() );
        
        if ( a.getColons() != this.getRows() )
        {
            throw new IllegalArgumentException( "Illegal matrix dimensions." );
        }
            
        System.out.println( res );

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
            res.setAt( i , i , ComplexNumber.REAL_UNIT );
        }
        return res;
    }

    @Override
    public Matrix additiveIdentity ()
    {
        return new Matrix( this.rows , this.colons );
    }

    @Override
    public Matrix multiplyWithScalar ( ComplexNumber a )
    {
        Matrix res = new Matrix( this.getRows() , this.getColons() );

        for ( int i = 0 ; i < this.getRows() ; i++ )
        {
            for ( int j = 0 ; j < this.getColons() ; j++ )
            {
                res.setAt( i , j ,
                           this.getAt( i , j )
                           .multiply( a ) );
            }
        }
        return res;
    }

    @Override
    public Matrix map ( TriFunction< Integer , Integer , ComplexNumber , ComplexNumber > mapper )
    {
        final Matrix res = new Matrix( this.rows , this.colons );

        for ( int i = 0 ; i < this.rows ; i++ )
        {
            for ( int j = 0 ; j < this.colons ; j++ )
            {
                res.setAt( i ,
                           j ,
                           mapper.apply( i , j , this.getAt( i , j ) ) );
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

}
