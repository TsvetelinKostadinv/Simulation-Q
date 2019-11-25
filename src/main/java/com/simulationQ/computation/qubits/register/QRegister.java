/*
 * 13/11/2019 15:14:50
 * QRegister.java created by Tsvetelin
 */
package com.simulationQ.computation.qubits.register;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.simulationQ.computation.qubits.Qubit;
import com.simulationQ.util.math.matrices.vectors.Vector;


/**
 * @author Tsvetelin
 *
 */
public class QRegister
{

    private final List< Qubit > register = new ArrayList<>();

    public QRegister ( Qubit [] qubits )
    {
        Arrays.stream( qubits ).forEach( this.register::add );
    }

    public Vector getComputationalVector ()
    {
        Vector res = this.register.get( 0 ).getAsVector();
        
        for( int i=1;i<this.register.size();i++ )
        {
            System.out.println( res );
            res = res.tensorProduct( this.register.get( i ).getAsVector() );
        }
        System.out.println( res );

        return res;

    }

    // public static void main ( String [] args )
    // {
    // final ComplexNumber ONE_OVER_SQRT_2 = ComplexNumber.real(
    // QMath.Constants.ONE_OVER_SQRT_2.value );
    //
    // final Qubit bit = new Qubit( ONE_OVER_SQRT_2 , ONE_OVER_SQRT_2 );
    //
    // final QRegister r = new QRegister( new Qubit[] { bit, bit } );
    //
    // System.out.println( r.getComputationalVector() );
    //
    // }

}
