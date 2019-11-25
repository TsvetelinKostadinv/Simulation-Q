/*
 * 10/11/2019 17:15:52
 * QCollapser.java created by Tsvetelin
 */
package com.simulationQ.computation.qubits;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import com.simulationQ.util.math.QMath;


/**
 * @author Tsvetelin
 *
 */
public interface QCollapser
{

    public static Qubit collapse ( Qubit q )
    {
        final BigDecimal onChance = q.getKetON()
                                     .abs()
                                     .pow( 2 )
                                     .setScale( QMath.PRECISION ,
                                                RoundingMode.FLOOR );

        final BigDecimal decision = new BigDecimal( Math.random() );

        return decision.compareTo( onChance ) == -1 ? Qubit.QUBIT_ON
                : Qubit.QUBIT_OFF;

        // TODO Needed to sort out the collapsing in a better way!!!!!!
        // throw new UnsupportedOperationException( "The collapse of a quibit is
        // not yet supported" );
    }

    public static Qubit collapse ( long n , Qubit q )
    {
        long on = 0 , off = 0;

        for ( long i = 0 ; i < n ; i++ )
        {
            if ( q.collapseSuperposition().equals( Qubit.QUBIT_ON ) )
                on++;
            else
                off++;
        }

        return on < off ? Qubit.QUBIT_OFF : Qubit.QUBIT_ON;

        // TODO Needed to sort out the collapsing in a better way!!!!!!
        // throw new UnsupportedOperationException( "The collapse of a quibit is
        // not yet supported" );
    }

    /**
     * 
     * collapses n times q and generates logs
     * 
     * @param n
     * @param q
     * @return
     */
    public static List< String > generateCollapseData ( long n , Qubit q )
    {
        final List< String > res = new LinkedList<>();
        
        final String format = "N %d -> %s";
        
        for ( long i = 0 ; i < n ; i++ )
        {
//            System.out.println( "Collapsed -> " + q.collapseSuperposition()  );
//            System.out.println( String.format( format , i, q.collapseSuperposition() ) );
            res.add( String.format( format , n, q.collapseSuperposition() ) );
        }
        return res;
    }
    
//    public static void main ( String [] args )
//    {
//        final String res = QCollapser.generateCollapseData( 100 , Qubit.QUBIT_HALF_HALF )
//                .toString().replaceAll( "," , System.lineSeparator() );
//        
//        System.out.println( res );
//    }

    /*
     * Scrapped v1.0
     * final BigDecimal onChance = q.getKetON().abs()
     * .pow( 2 )
     * .setScale( QMath.PRECISION ,
     * RoundingMode.FLOOR );
     * 
     * final BigDecimal offChance = q.getKetOFF().abs()
     * .pow( 2 )
     * .setScale( QMath.PRECISION ,
     * RoundingMode.FLOOR );
     * 
     * final BigDecimal distance = onChance.subtract( offChance ).abs();
     * 
     * final BigDecimal lower = onChance.compareTo( offChance ) ==-1 ? onChance
     * : offChance;
     * 
     * final BigDecimal decision = new BigDecimal( Math.random() ).multiply(
     * distance ).subtract( lower );
     * 
     * final BigDecimal distanceToON = decision.subtract( onChance ).abs();
     * final BigDecimal distanceToOFF = decision.subtract( offChance ).abs();
     * return distanceToON.compareTo( distanceToOFF ) == -1 ? Qubit.QUBIT_ON :
     * Qubit.QUBIT_OFF;
     * -------------------------------------------------------------------------
     */

}
