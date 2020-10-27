/*
 * 25/12/2019 15:54:55
 * RealNumber.java created by Tsvetelin
 */
package com.simulationQ.simulation.util.math.complexNumbers;

import java.math.BigDecimal;

/**
 * @author Tsvetelin
 */
public class RealNumber extends ComplexNumber
{
    
    /**
     * 
     */
    public RealNumber ( BigDecimal value )
    {
        super( value , BigDecimal.ZERO );
    }
    
    /**
     * 
     */
    public RealNumber ( String value )
    {
        super( value , "0" );
    }
    
    @Override
    public String toString ()
    {
        return this.getReal().toPlainString();
    }
    
}
