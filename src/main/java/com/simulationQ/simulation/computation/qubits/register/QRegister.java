/*
 * 13/11/2019 15:14:50
 * QRegister.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.qubits.register;

import com.simulationQ.simulation.computation.qubits.Qubit;
import com.simulationQ.simulation.util.math.QMath;
import com.simulation_q.math.matrix.vector.Vector;

/**
 * @author Tsvetelin
 */
public class QRegister
{
    
    private final Vector computationalVector;
    
    public QRegister ( Qubit... qubits )
    {
        this.computationalVector = getAsComputationalVector( qubits );
    }
    
    /**
     * This should be used with caution as it does not perform checking as
     * the vector might be entangled
     * 
     * @param vector
     */
    public QRegister ( Vector vector )
    {
        this.computationalVector = vector;
    }
    
    public Qubit head ()
    {
        return this.computationalVector.size() == 2 ?
            new Qubit(
                this.computationalVector.getAt( 0 ) ,
                this.computationalVector.getAt( 1 ) ) :
                null;
    }
    
    public final int size ()
    {
        return QMath.log2( this.computationalVector.size() );
    }
    
    /**
     * @return the computationalVector
     */
    public Vector getComputationalVector ()
    {
        return this.computationalVector;
    }
    
    private static Vector getAsComputationalVector ( Qubit [] qubits )
    {
        Vector res = qubits[0].getAsVector();
        
        for ( int i = 1 ; i < qubits.length ; i++ )
        {
            res = res.tensorProduct( qubits[i].getAsVector() );
        }
        return res;
        
    }
    
    @Override
    public boolean equals ( Object obj )
    {
        if ( obj instanceof QRegister )
        {
            QRegister reg = (QRegister) obj;
            
            return reg.getComputationalVector()
                .equals( this.getComputationalVector() );
        }
        return false;
    }
    
    @Override
    public String toString ()
    {
        return this.computationalVector.toString();
    }
    
}
