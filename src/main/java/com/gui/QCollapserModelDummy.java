/*
 * 20/03/2020 13:17:14
 * QCollapserModel.java created by Tsvetelin
 */
package com.gui;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Tsvetelin
 *
 */
public interface QCollapserModelDummy
{
    
    //TODO this should take in a Register and a program which will be constructed in the controller
    static Map< String , Number > generateCollapseData( String starting )
    {
        final Map<String,Number> res = new LinkedHashMap<>();
        if( starting.length() == 0 ) return res;
        
        for( String possibility : generateBinaryStringValues( starting.length() ) )
        {
            res.putIfAbsent( possibility , -1 );
        }
        
        res.put( starting , 1 );
        
        return res;
    }
    
    public static List< String > generateBinaryStringValues ( final int bits )
    {
        return IntStream.range( 0 , (int) Math.pow( 2 , bits ) )
                        .mapToObj( Integer::toBinaryString )
                        .map( ( String x ) -> x.length() < bits
                                ? "0".repeat( bits - x.length() ) + x
                                : x )
                        .collect( Collectors.toList() );
    }
}
