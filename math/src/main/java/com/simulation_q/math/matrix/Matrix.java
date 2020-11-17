/*
 * 30/10/2020 15:13:02
 * Matrix.java created by Tsvetelin
 */
package com.simulation_q.math.matrix;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

import com.simulation_q.math.Field;
import com.simulation_q.math.complex_number.ComplexNumber;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * Represents a matrix from linear algebra, whose components are
 * complex numbers
 * 
 * @author Tsvetelin
 */
@EqualsAndHashCode
public class Matrix implements Field< Matrix >
{
    @Getter
    private final int rows;
    
    @Getter
    private final int columns;
    
    private final ComplexNumber [] matrix;
    
    protected Matrix ( final ComplexNumber [] [] matrix )
    {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix =
            Arrays.stream( matrix )
                .flatMap( row -> Arrays.stream( row ) )
                .toArray( size -> new ComplexNumber[size] );
    }
    
    /**
     * Creates a new matrix from the supplied 2 dimensional array.
     * 
     * @param  matrix - the values
     * @return        a full optional if the values can be parsed to a
     *                    matrix, empty one otherwise
     */
    public static Optional< Matrix > of ( final ComplexNumber [] [] matrix )
    {
        for ( int i = 1 ; i < matrix.length ; i++ )
        {
            if ( matrix[i].length != matrix[i - 1].length )
            {
                return Optional.empty();
            }
        }
        return Optional.of( new Matrix( matrix ) );
    }
    
    /**
     * Gets the additive identity for given rows and columns
     * 
     * @param  rows
     * @param  columns
     * @return         an addition identity matrix with the specified rows
     *                     and columns
     */
    public static Matrix additiveIdentity ( int rows , int columns )
    {
        ComplexNumber [] [] matrix = allZeroes( rows , columns );
        
        return new Matrix( matrix );
    }
    
    /**
     * Gets the multiplicative identity for given rows and columns
     * 
     * @param  rows
     * @param  columns
     * @return         an multiplicative identity identity matrix with the
     *                     specified rows
     *                     and columns
     */
    public static Matrix multiplicativeIdentity ( int rows , int columns )
    {
        ComplexNumber [] [] matrix = allZeroes( rows , columns );
        
        for ( int i = 0 ; i < ( rows < columns ? rows : columns ) ; i++ )
        {
            matrix[i][i] = _1.value;
        }
        return new Matrix( matrix );
    }
    
    /**
     * Gets the element at the specified coordinates
     * 
     * @param  rowIndex
     * @param  columnIndex
     * @return                           the complex number at the
     *                                       coordinates in the
     *                                       matrix
     * @throws IndexOutOfBoundsException if the specified index is outside
     *                                       the matrix
     */
    public ComplexNumber getAt ( int rowIndex , int columnIndex )
        throws IndexOutOfBoundsException
    {
        if ( rowIndex >= this.rows || columnIndex >= this.columns )
        {
            throw new IndexOutOfBoundsException(
                "The index has to be in the matrix, (" + rowIndex + ", "
                    + columnIndex + ") is not in the array" );
        }
        return this.matrix[rowIndex * columns + columnIndex];
    }
    
    public ComplexNumber [] getRowVector ( int rowIndex )
    {
        return slice(
            this.matrix ,
            rowIndex * columns ,
            ( rowIndex + 1 ) * columns );
    }
    
    public Matrix map (
        Function< ComplexNumber , ComplexNumber > mapper )
    {
        Objects.requireNonNull( mapper );
        ComplexNumber [] [] res =
            new ComplexNumber[this.getRows()][this.getColumns()];
        for ( int i = 0 ; i < this.getRows() ; i++ )
        {
            for ( int j = 0 ; j < this.getColumns() ; j++ )
            {
                res[i][j] = mapper.apply( this.getAt( i , j ) );
            }
        }
        return new Matrix( res );
    }
    
    /**
     * @apiNote returns the additive identity for the given size of matrix
     */
    @Override
    public Matrix additiveIdentity ()
    {
        return additiveIdentity( this.rows , this.columns );
    }
    
    /**
     * @throws ArithmeticException if the other array is not the same
     *                                 dimensionality, i.e. have the same
     *                                 number of rows and columns
     */
    @Override
    public Matrix add ( Matrix other ) throws ArithmeticException
    {
        if ( this.rows != other.columns || this.columns != other.columns )
        {
            throw new ArithmeticException(
                "The matrices must be the same size(have to have the same number of rows and columns)" );
        }
        
        ComplexNumber [] [] res = new ComplexNumber[this.rows][this.columns];
        for ( int i = 0 ; i < res.length ; i++ )
        {
            for ( int j = 0 ; j < res[i].length ; j++ )
            {
                res[i][j] = this.getAt( i , j ).add( other.getAt( i , j ) );
            }
        }
        return new Matrix( res );
    }
    
    @Override
    public Matrix negate ()
    {
        ComplexNumber [] [] res = new ComplexNumber[this.rows][this.columns];
        for ( int i = 0 ; i < res.length ; i++ )
        {
            for ( int j = 0 ; j < res[i].length ; j++ )
            {
                res[i][j] = this.getAt( i , j ).negate();
            }
        }
        return new Matrix( res );
    }
    
    /**
     * @apiNote returns the multiplicative identity for the given size of
     *              matrix
     */
    @Override
    public Matrix multiplicativeIdentity ()
    {
        return multiplicativeIdentity( this.rows , this.columns );
    }
    
    @Override
    public Matrix multiply ( Matrix other ) throws ArithmeticException
    {
        if ( this.columns != other.rows )
        {
            throw new ArithmeticException(
                "The first matrix's columns must be the same number as the seconds rows" );
        }
        
        ComplexNumber [] [] res = allZeroes( this.rows , other.columns );
        
        for ( int i = 0 ; i < this.rows ; i++ )
        {
            for ( int j = 0 ; j < other.columns ; j++ )
            {
                for ( int k = 0 ; k < this.columns ; k++ )
                {
                    res[i][j] =
                        res[i][j].add(
                            this.getAt( i , k )
                                .multiply( other.getAt( k , j ) ) );
                }
            }
        }
        return new Matrix( res );
    }
    
    public Matrix multiply ( ComplexNumber scalar )
    {
        ComplexNumber [] [] matrix =
            IntStream.range( 0 , rows )
                .mapToObj( rowIndex -> this.getRowVector( rowIndex ) )
                .map( row -> {
                    for ( int i = 0 ; i < row.length ; i++ )
                    {
                        row[i] = row[i].multiply( scalar );
                    }
                    return row;
                } )
                .toArray( __ -> new ComplexNumber[this.rows][this.columns] );
        return new Matrix( matrix );
    }
    
    @Override
    public Matrix invert () throws ArithmeticException
    {
        if ( this.rows != this.columns )
        {
            throw new IllegalArgumentException(
                "The matrix cannot be reversed" );
        }
        // TODO implement this at some point
        throw new UnsupportedOperationException(
            "The operation is not yet implemented" );
    }
    
    /**
     * Lifts the matrix to a power
     * 
     * @param  power
     * @return       this^power
     */
    public Matrix pow ( int power )
    {
        if ( power == 0 )
        {
            return this.multiplicativeIdentity();
        }
        
        Matrix res = power > 0 ? this : this.invert();
        
        for ( int i = 1 ; i < power ; i++ )
        {
            res = res.multiply( res );
        }
        
        return res;
    }
    
    /**
     * In simple words flips the matrix about it's main diagonal
     * 
     * @return this^T
     */
    public Matrix transpose ()
    {
        ComplexNumber [] [] transposed =
            new ComplexNumber[this.columns][this.rows];
        for ( int i = 0 ; i < this.getColumns() ; i++ )
            for ( int j = 0 ; j < this.getRows() ; j++ )
                transposed[i][j] = this.getAt( j , i );
        return new Matrix( transposed );
    }
    
    /**
     * Find the Kronecker product of the arguments.
     * 
     * @param  other
     *                   The second matrix to multiply.
     * @return       A new matrix: the Kronecker product of the arguments.
     * @see          {@link https://rosettacode.org/wiki/Kronecker_product#Java}
     */
    public Matrix productKronecker ( final Matrix other )
    {
        final ComplexNumber [] [] res =
            allZeroes(
                this.getRows() * other.getRows() ,
                this.getColumns() * other.getColumns() );
        
        for ( int thisRow = 0 ; thisRow < this.getRows() ; thisRow++ )
        {
            for (
                int thisColumn = 0 ;
                thisColumn < this.getColumns() ;
                thisColumn++ )
            {
                for (
                    int otherRow = 0 ;
                    otherRow < other.getRows() ;
                    otherRow++ )
                {
                    for (
                        int otherColumn = 0 ;
                        otherColumn < other.getColumns() ;
                        otherColumn++ )
                    {
                        res[other.getRows() * thisRow
                            + otherRow][other.getColumns() * thisColumn
                                + otherColumn] =
                                    this.getAt( thisRow , thisColumn )
                                        .multiply(
                                            other.getAt(
                                                otherRow ,
                                                otherColumn ) );
                    }
                }
            }
        }
        
        return new Matrix( res );
    }
    
    @Override
    public String toString ()
    {
        final StringBuilder sb = new StringBuilder();
        
        for ( int row = 0 ; row < this.rows ; row++ )
        {
            sb.append(
                Arrays.toString( this.getRowVector( row ) )
                    + System.lineSeparator() );
        }
        
        return sb.toString();
    }
    
    protected static ComplexNumber [] [] allZeroes ( int rows , int columns )
    {
        ComplexNumber [] [] matrix = new ComplexNumber[rows][columns];
        for ( int i = 0 ; i < rows ; i++ )
            for ( int j = 0 ; j < columns ; j++ )
                matrix[i][j] = _0.value;
        return matrix;
    }
    
    protected static ComplexNumber [] slice (
        ComplexNumber [] matrix ,
        int startIndex ,
        int endIndex )
    {
        if ( startIndex < 0 ||
            endIndex < 0
            || endIndex < startIndex
            || endIndex > matrix.length )
            throw new IndexOutOfBoundsException();
        
        ComplexNumber [] slice = new ComplexNumber[endIndex - startIndex];
        
        for ( int i = 0 ; i < slice.length ; i++ )
        {
            slice[i] = matrix[startIndex + i];
        }
        return slice;
    }
}
