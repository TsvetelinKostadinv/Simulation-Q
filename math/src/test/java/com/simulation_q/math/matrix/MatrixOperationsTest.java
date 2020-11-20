/*
 * 28/10/2019 13:53:19
 * MatrixOperationsTest.java created by Tsvetelin
 */
package com.simulation_q.math.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.simulation_q.math.complex_number.ComplexNumber;

import java.util.Optional;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
public class MatrixOperationsTest
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
        Matrix.of(
            new ComplexNumber[][] {
                { ONE , ONE } ,
                { ONE , ONE }
            } ).get();
    
    /**
     * [2 2]
     * [2 2]
     */
    public static final Matrix sample2x2matrix_twoes =
        Matrix.of(
            new ComplexNumber[][] {
                { TWO , TWO } ,
                { TWO , TWO }
            } ).get();
    
    /**
     * [4 4]
     * [4 4]
     */
    public static final Matrix sample2x2matrix_fours =
        Matrix.of(
            new ComplexNumber[][] {
                { FOUR , FOUR } ,
                { FOUR , FOUR }
            } ).get();
    
    /**
     * [4 4]
     * [4 4]
     */
    public static final Matrix sample2x2matrix_eights =
        Matrix.of(
            new ComplexNumber[][] {
                { EIGHT , EIGHT } ,
                { EIGHT , EIGHT }
            } ).get();
    
    /**
     * Test method for
     * {@link com.simulationQ.util.math.matrices.Matrix#additionIdentity(int, int)}.
     */
    @Test
    final void testAdditionIdentity ()
    {
        final Matrix additionIdentity =
            Matrix.additiveIdentity(
                sample2x2matrix_ones.getRows() ,
                sample2x2matrix_ones.getColumns() );
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
                    sample2x2matrix_ones.getColumns() );
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
            sample2x2matrix_ones.multiply( TWO ) ,
            sample2x2matrix_twoes );
    }
    
    @Test
    void additiveIdentityTest ()
    {
        // assemble
        Matrix identity2x2 = sample2x2matrix_fours.additiveIdentity();
        Matrix inverse = sample2x2matrix_fours.negate();
        // act
        Matrix sameMinusSame =
            sample2x2matrix_fours.subtract( sample2x2matrix_fours );
        // assert
        assertEquals(
            identity2x2 ,
            sample2x2matrix_fours.add( inverse ) ,
            "Subtracting the matrix from itself should equal the identity" );
        
        assertEquals(
            identity2x2 ,
            sameMinusSame ,
            "Subtracting the matrix from itself should equal the identity" );
        
        assertEquals(
            sample2x2matrix_fours.add( identity2x2 ) ,
            sample2x2matrix_fours ,
            "Adding the identity should not change the matrix" );
        
        assertEquals(
            sample2x2matrix_fours.subtract( identity2x2 ) ,
            sample2x2matrix_fours ,
            "Subtracting the identity should not change the matrix" );
    }
    
    @Test
    void multiplicativeIdentityTest ()
    {
        // assemble
        Matrix identity2x2 = sample2x2matrix_fours.multiplicativeIdentity();
        // act
        Matrix matrixTimesIdentity =
            sample2x2matrix_fours.multiply( identity2x2 );
        // assert
        assertEquals(
            sample2x2matrix_fours ,
            matrixTimesIdentity ,
            "Multiplying with matrix should not change the matrix" );
    }
    
    @Test
    void kroneckerTest ()
    {
        Matrix addititiveIdent = Matrix.multiplicativeIdentity( 2 , 2 );
        Matrix sample =
            Matrix.of(
                new ComplexNumber[][] {
                    {
                        _1.value ,
                        _1.value.add( _1.value ) } ,
                    {
                        _1.value.add( _1.value ) ,
                        _1.value } ,
                } ).get();
        
        Matrix kroneckerProd = addititiveIdent.productKronecker( sample );
        Matrix expected =
            Matrix.of(
                new ComplexNumber[][] {
                    {
                        _1.value ,
                        _1.value.add( _1.value ) ,
                        _0.value ,
                        _0.value ,
                    } ,
                    {
                        _1.value.add( _1.value ) ,
                        _1.value ,
                        _0.value ,
                        _0.value ,
                    } ,
                    {
                        _0.value ,
                        _0.value ,
                        _1.value ,
                        _1.value.add( _1.value ) ,
                    } ,
                    {
                        _0.value ,
                        _0.value ,
                        _1.value.add( _1.value ) ,
                        _1.value ,
                    } ,
                } ).get();
        
        assertEquals(
            expected ,
            kroneckerProd ,
            "The kronecker product is not as expected" );
    }
    
    @Test
    void notParsableTest ()
    {
        // assemble
        Optional< Matrix > valid =
            Matrix.of(
                new ComplexNumber[][] {
                    {
                        _1.value ,
                        _1.value.add( _1.value ) } ,
                    {
                        _1.value.add( _1.value ) ,
                        _1.value } ,
                } );
        
        Optional< Matrix > invalid =
            Matrix.of(
                new ComplexNumber[][] {
                    {
                        _1.value ,
                        _1.value.add( _1.value ) } ,
                    {
                        _1.value.add( _1.value ) ,
                        _1.value } ,
                    {
                        _1.value.add( _1.value ) ,
                        _1.value ,
                        _1.value } ,
                } );
        // act
        // assert
        assertTrue( valid.isPresent() , "Could not parse a valid matrix" );
        assertTrue( invalid.isEmpty() , "This matrix should not be parsed" );
    }
    
}
