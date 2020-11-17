/*
 * 08/11/2020 19:18:45
 * QGateTest.java created by Tsvetelin
 */
package com.simulation_q.engine.gate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
class QGateTest
{
    
    @Test
    void validConstructionTest ()
    {
        Matrix test =
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _1.value } ,
                    { _1.value , _1.value.negate() } ,
                } )
                .map(
                    mat -> mat
                        .multiply( _1_OVER_SQRT_2.value ) )
                .get();
        
        assertDoesNotThrow( () -> new QGate( test , 1 , 2 ) );
    }
    
    @Test
    void constructorTest ()
    {
        Matrix notSquareMatrix =
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _1.value , _1.value } ,
                    { _1.value , _1.value , _1.value } ,
                } ).get();
        Matrix squareTooSmallFor4QubitsMatrix =
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _1.value } ,
                    { _1.value , _1.value } ,
                } ).get();
        
        Matrix squareMatrixNotUnary =
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _1.value } ,
                    { _1.value , _1.value } ,
                } ).get();
        
        Matrix validMatrix = Matrix.multiplicativeIdentity( 4 , 4 );
        
        assertThrows(
            IllegalArgumentException.class ,
            () -> new QGate( notSquareMatrix , 2 , 2 ) ,
            "Gates should only accept square matrices" );
        
        assertThrows(
            IllegalArgumentException.class ,
            () -> new QGate( squareTooSmallFor4QubitsMatrix , 4 , 2 ) ,
            "Matrix too small for input qubits" );
        
        assertThrows(
            IllegalArgumentException.class ,
            () -> new QGate( squareMatrixNotUnary , 2 , 2 ) ,
            "Matrix should be unary" );
        
        assertDoesNotThrow(
            () -> new QGate( validMatrix , 2 , 2 ) ,
            "The identity matrix is a valid operation" );
    }
    
    @Test
    void identityStretch ()
    {
        QGate identityStretched =
            new QGate( Matrix.multiplicativeIdentity( 2 , 2 ) , 1 , 1 )
                .stretchFor( 0 , 4 );
        assertEquals(
            identityStretched.getOperation() ,
            Matrix.multiplicativeIdentity( 16 , 16 ) );
    }
    
}
