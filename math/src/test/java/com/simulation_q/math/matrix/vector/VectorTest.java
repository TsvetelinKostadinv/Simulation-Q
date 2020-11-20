/*
 * 02/11/2020 13:52:24
 * VectorTest.java created by Tsvetelin
 */
package com.simulation_q.math.matrix.vector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * @author Tsvetelin
 */
class VectorTest
{
    
    @Test
    void tensorProduct ()
    {
        Vector a =
            new Vector(
                new ComplexNumber[] {
                    _1.value , _1.value ,
                    _1.value , _1.value , } );
        
        Vector b =
            new Vector(
                new ComplexNumber[] {
                    _1.value.add( _1.value ) ,
                    _0.value ,
                    _1.value.add( _1.value ) ,
                    _0.value , } );
        
        Vector tensorProd = a.tensorProduct( b );
        Matrix kroneckerProd = a.tensorProduct( b );
        
        assertEquals( new Vector(kroneckerProd.getRowVector( 0 )) , tensorProd );
    }
    
}
