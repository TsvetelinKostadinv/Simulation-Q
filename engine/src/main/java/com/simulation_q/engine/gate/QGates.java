/*
 * 16/11/2020 17:03:28
 * QGates.java created by Tsvetelin
 */
package com.simulation_q.engine.gate;

import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import static com.simulation_q.math.complex_number.ComplexConstants.*;

/**
 * A collection of premade quantum gates
 * 
 * @author Tsvetelin
 */
public class QGates
{
    private QGates ()
    {
    }
    
    public static final QGate IDENTITY =
        new QGate(
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _0.value } ,
                    { _0.value , _1.value }
                } ).get() ,
            1 ,
            1 );
    
    public static final QGate HADAMARD =
        new QGate(
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _1.value } ,
                    { _1.value , _1.value.negate() }
                } )
                .get()
                .multiply( _1_OVER_SQRT_2.value ) ,
            1 ,
            2 );
    
    /**
     * Another name is Pauli-X gate, represents rotation about the X axis
     */
    public static final QGate NOT =
        new QGate(
            Matrix.of(
                new ComplexNumber[][] {
                    { _0.value , _1.value } ,
                    { _1.value , _0.value }
                } ).get() ,
            1 ,
            2 );
    
    /**
     * Represents rotation about the Y axis
     */
    public static final QGate PAULI_Y =
        new QGate(
            Matrix.of(
                new ComplexNumber[][] {
                    { _0.value , _i.value.negate() } ,
                    { _i.value , _0.value }
                } ).get() ,
            1 ,
            2 );
    
    /**
     * Represents rotation about the Z axis
     */
    public static final QGate PAULI_Z =
        new QGate(
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _0.value } ,
                    { _0.value , _1.value.negate() }
                } ).get() ,
            1 ,
            2 );
    
    /**
     * Represents a controlled not gate, if the upper qubit is turned on
     * then it negates the second.
     * TODO: extend the gate generation with availability of
     * controlled
     * calculations. Something in the form
     * <code>controlled(gate)</code>
     * Then this can be overriden with just
     * controlled(QGates.NOT)
     * 
     * @see QGates#NOT
     */
    public static final QGate CNOT =
        new QGate(
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _0.value , _0.value , _0.value } ,
                    { _0.value , _1.value , _0.value , _0.value } ,
                    { _0.value , _0.value , _0.value , _1.value } ,
                    { _0.value , _0.value , _1.value , _0.value }
                } ).get() ,
            2 ,
            2 );
    
    /**
     * Swaps 2 qubits
     * TODO: implement <code>sqrt(gate)</code> which calculates the matrix
     * of the sqrt variant of the gate
     */
    public static final QGate SWAP =
        new QGate(
            Matrix.of(
                new ComplexNumber[][] {
                    { _1.value , _0.value , _0.value , _0.value } ,
                    { _0.value , _0.value , _1.value , _0.value } ,
                    { _0.value , _1.value , _0.value , _0.value } ,
                    { _0.value , _0.value , _0.value , _1.value }
                } ).get() ,
            2 ,
            2 );
    
}
