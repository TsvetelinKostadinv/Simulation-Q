/*
 * 28/10/2019 13:53:19
 * MatrixOperationsTest.java created by Tsvetelin
 */
package com.util.math.matrices;

import org.junit.jupiter.api.Test;

import com.simulationQ.simulation.util.math.matrices.Matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.simulation_q.math.complex_numbers.ComplexNumber;

/**
 * @author Tsvetelin
 */
class MatrixOperationsTest
{
    
    /**
     * 1+0i
     */
    public static final ComplexNumber ONE = ComplexNumber.Algebraic.realUnit();
    
    /**
     * 2+0i
     */
    public static final ComplexNumber TWO = ONE.add( ONE );
    
    /**
     * 4+0i
     */
    public static final ComplexNumber FOUR = TWO.multiply( TWO );
    
    /**
     * 8+0i
     */
    public static final ComplexNumber EIGHT = FOUR.multiply( TWO );
    
    /**
     * [1 1]
     * [1 1]
     */
    public static final Matrix sample2x2matrix_ones =
        new Matrix(
            new ComplexNumber[][] {
                { ONE , ONE } ,
                { ONE , ONE }
            } );
    
    /**
     * [2 2]
     * [2 2]
     */
    public static final Matrix sample2x2matrix_twoes =
        new Matrix(
            new ComplexNumber[][] {
                { TWO , TWO } ,
                { TWO , TWO }
            } );
    
    /**
     * [4 4]
     * [4 4]
     */
    public static final Matrix sample2x2matrix_fours =
        new Matrix(
            new ComplexNumber[][] {
                { FOUR , FOUR } ,
                { FOUR , FOUR }
            } );
    
    /**
     * [4 4]
     * [4 4]
     */
    public static final Matrix sample2x2matrix_eights =
        new Matrix(
            new ComplexNumber[][] {
                { EIGHT , EIGHT } ,
                { EIGHT , EIGHT }
            } );
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#additionIdentity(int, int)}.
     */
    @Test
    final void testAdditionIdentity ()
    {
        final Matrix additionIdentity =
            new Matrix(
                Matrix.additionIdentity(
                    sample2x2matrix_ones.getRows() ,
                    sample2x2matrix_ones.getColons() ) );
        assertEquals(
            sample2x2matrix_ones.add( additionIdentity ) ,
            sample2x2matrix_ones ,
            "The addition identity should not change a matrix when applied with the addition operation in either way!" );
        
        assertEquals(
            sample2x2matrix_ones ,
            sample2x2matrix_ones.add( additionIdentity ) ,
            "The addition identity should not change a matrix when applied with the addition operation in either way!" );
        
    }
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#getAt(int, int)}.
     */
    @Test
    final void testGetAt ()
    {
        assertThrows(
            IndexOutOfBoundsException.class ,
            () -> {
                sample2x2matrix_ones.getAt( -1 , 0 );
            } );
        
        assertThrows(
            IndexOutOfBoundsException.class ,
            () -> {
                sample2x2matrix_ones.getAt( 0 , -1 );
            } );
        
        assertThrows(
            IndexOutOfBoundsException.class ,
            () -> {
                sample2x2matrix_ones.getAt(
                    sample2x2matrix_ones.getRows() ,
                    0 );
            } );
        
        assertThrows(
            IndexOutOfBoundsException.class ,
            () -> {
                sample2x2matrix_ones.getAt(
                    0 ,
                    sample2x2matrix_ones.getColons() );
            } );
    }
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#setAt(int, int, com.simulationQ.util.math.complexNumbers.ComplexNumber)}.
     */
    @Test
    final void testSetAt ()
    {
        assertThrows(
            IndexOutOfBoundsException.class ,
            () -> {
                sample2x2matrix_ones.setAt(
                    -1 ,
                    0 ,
                    ComplexNumber.Algebraic.origin() );
            } );
        
        assertThrows(
            IndexOutOfBoundsException.class ,
            () -> {
                sample2x2matrix_ones.setAt(
                    0 ,
                    -1 ,
                    ComplexNumber.Algebraic.origin() );
            } );
        
        assertThrows( IndexOutOfBoundsException.class , () -> {
            sample2x2matrix_ones.setAt(
                sample2x2matrix_ones.getRows() ,
                0 ,
                ComplexNumber.Algebraic.origin() );
        } );
        
        assertThrows( IndexOutOfBoundsException.class , () -> {
            sample2x2matrix_ones.setAt(
                0 ,
                sample2x2matrix_ones.getColons() ,
                ComplexNumber.Algebraic.origin() );
        } );
    }
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#add(com.simulationQ.util.math.matrices.Matrix)}.
     */
    @Test
    final void testAdd ()
    {
        assertEquals(
            sample2x2matrix_ones.add( sample2x2matrix_ones ) ,
            sample2x2matrix_twoes ,
            "The addition of a matrix filled with ones with the same matrix should be a matrix filled with twoes!" );
    }
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#multiply(com.simulationQ.util.math.matrices.Matrix)}.
     */
    @Test
    final void testMultiply ()
    {
        assertEquals(
            sample2x2matrix_ones.multiply( sample2x2matrix_twoes ) ,
            sample2x2matrix_fours ,
            "The provided matrix multiplication is not correct" );
    }
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#pow(int)}.
     */
    @Test
    final void testPow ()
    {
        assertEquals(
            sample2x2matrix_twoes.pow( 2 ) ,
            sample2x2matrix_eights ,
            "The power func is not working" );
    }
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#multiplyWithScalar(com.simulationQ.util.math.complexNumbers.ComplexNumber)}.
     */
    @Test
    final void testMultiplyWithScalar ()
    {
        assertEquals(
            sample2x2matrix_ones.multiplyWithScalar( TWO ) ,
            sample2x2matrix_twoes );
    }
    
}
