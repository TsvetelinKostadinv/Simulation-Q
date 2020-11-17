/*
 * 24/12/2019 14:43:19
 * QGateApplier.java created by Tsvetelin
 */
package com.simulation_q.engine.gate;

import java.util.Objects;

import com.simulation_q.engine.qubit.QRegister;
import com.simulation_q.engine.qubit.Qubit;
import com.simulation_q.math.matrix.Matrix;
import com.simulation_q.math.matrix.vector.Vector;

/**
 * Utility class for gate application to quantum objects: registers
 * and qubits.
 * By application what is meant is that the matrix of the gate is
 * multiplied with the vector of the register/qubit
 * 
 * @author Tsvetelin
 */
public class QGateApplier
{
    private QGateApplier ()
    {
    }
    
    public static QRegister apply (
        final QGate gate ,
        final QRegister reg ,
        final int startingVerticalIndex )
        throws NullPointerException
    {
        Objects.requireNonNull( gate );
        Objects.requireNonNull( reg );
        
        int requiredOperationDimension = (int) Math.pow( 2 , reg.size() );
        
        if ( startingVerticalIndex
            + gate.getNumberOfInputBits() > requiredOperationDimension )
        {
            throw new IllegalArgumentException(
                "The starting index cannot be such that the operation cannot fit!" );
        }
        
        final Vector vectorOfReg = reg.castToVector();
        final Matrix extended =
            gate.stretchFor(
                startingVerticalIndex ,
                requiredOperationDimension ).getOperation();
        
        return new QRegister(
            new Vector(
                extended.multiply( vectorOfReg.transpose() )
                    .transpose()
                    .getRowVector( 0 ) ) );
    }
    
    public static Qubit apply (
        final QGate gate ,
        final Qubit qubit )
    {
        Objects.requireNonNull( gate );
        Objects.requireNonNull( qubit );
        
        final Matrix qubitVector = qubit.castToVector();
        final Matrix operation = gate.getOperation();
        
        final Vector res =
            new Vector(
                operation
                    .multiply( qubitVector.transpose() )
                    .transpose()
                    .getRowVector( 0 ) );
        
        return new Qubit( res.getAt( 0 ) , res.getAt( 1 ) );
    }
}
