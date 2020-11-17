/*
 * 20/12/2019 10:59:26
 * CRegister.java created by Tsvetelin
 */
package com.simulation_q.engine.qubit;

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
    
    private static Qubit [] convertToVector ( String bits )
    {
        Qubit [] qubits = new Qubit[bits.length()];
        
        for ( int i = 0 ; i < bits.length() ; i++ )
        {
            qubits[i] =
                bits.charAt( i ) == '1' ? Qubit.ON : Qubit.OFF;
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
