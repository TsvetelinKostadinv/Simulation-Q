/*
 * 13/11/2019 15:14:50
 * QRegister.java created by Tsvetelin
 */
package com.simulation_q.engine.qubit;

import java.util.Arrays;

import com.simulation_q.math.QMath;
import com.simulation_q.math.matrix.vector.Vector;

import lombok.EqualsAndHashCode;

/**
 * Represents a quantum register, i.e a collection of qubits that can
 * be manipulated in unison. This is needed because of the nature of
 * the qubit that can be entangled with others. As we do not want to
 * entangle them referentially and lose track of the entanglement.
 * This object will be the central place
 * 
 * @author Tsvetelin
 */
@EqualsAndHashCode
public class QRegister
{
    private final Vector computationalVector;
    private final int size;
    
    public QRegister ( Qubit... qubits )
    {
        this.computationalVector = castToRegisterVector( qubits );
        this.size = qubits.length;
    }
    
    /**
     * This should be used with caution as it does not perform checking
     * as
     * the vector might be entangled
     *
     * @param vector
     */
    public QRegister ( Vector vector )
    {
        this.size = (int) QMath.log2( vector.size() );
        this.computationalVector = vector;
    }
    
// public Qubit head ()
// {
// return this.computationalVector.size() == 2 ?
// new Qubit(
// this.computationalVector.getAt( 0 ) ,
// this.computationalVector.getAt( 1 ) ) :
// null;
// }
    
    public int size ()
    {
        return size;
    }
    
    /**
     * @return precision that is guaranteed to not interfere with any
     *             calculations
     */
    public int getRegisterPrecision ()
    {
        return Arrays.stream( computationalVector.getScalars() )
            .mapToInt( complex -> {
                int realScale = complex.getReal().scale();
                int imaginaryScale = complex.getImaginary().scale();
                return realScale > imaginaryScale ? realScale : imaginaryScale;
            } )
            .max()
            .orElse( 1 );
    }
    
    public Vector castToVector ()
    {
        return this.computationalVector;
    }
    
    /**
     * Converts the array of standalone qubits to the vector that can be
     * used for computation
     * 
     * @param  qubits - the array of the qubits
     * @return        the vector that represents the state of all the
     *                    qubits
     */
    static Vector castToRegisterVector ( Qubit [] qubits )
    {
        Vector res = qubits[0].castToVector();
        
        for ( int i = 1 ; i < qubits.length ; i++ )
        {
            res = res.tensorProduct( qubits[i].castToVector() );
        }
        
        return res;
    }
    
    @Override
    public String toString ()
    {
        return this.computationalVector.toString();
    }
    
}
