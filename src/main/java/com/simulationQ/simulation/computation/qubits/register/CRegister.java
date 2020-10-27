/*
 * 20/12/2019 10:59:26
 * CRegister.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.qubits.register;

import com.simulationQ.simulation.computation.qubits.Qubit;

/**
 * @author Tsvetelin
 */
public class CRegister extends QRegister
{
    
    private final String state;
    
    public CRegister ( final String bits )
    {
        super( convertToVector( bits ) );
        
        this.state = bits;
        
    }
    
    /**
     * @param  bits
     * @return
     */
    private static Qubit [] convertToVector ( String bits )
    {
        Qubit [] qubits = new Qubit[bits.length()];
        
        for ( int i = 0 ; i < bits.length() ; i++ )
        {
            qubits[i] =
                bits.charAt( i ) == '1' ? Qubit.QUBIT_ON : Qubit.QUBIT_OFF;
        }
        return qubits;
    }
    
    public int [] getState ()
    {
        final int [] res = new int[this.size()];
        
        for ( int i = 0 ; i < res.length ; i++ )
        {
            res[i] = state.charAt( i ) == '1' ? 1 : 0;
        }
        
        return res;
    }
    
    @Override
    public String toString ()
    {
        return this.state;
    }
    
}
