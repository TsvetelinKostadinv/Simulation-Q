/*
 * 10/11/2019 17:15:52
 * QCollapser.java created by Tsvetelin
 */
package com.simulationQ.computation.quibits;

//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//import com.simulationQ.util.math.QMath;

/**
 * @author Tsvetelin
 *
 */
public interface QCollapser
{
    public static Qubit collapse( Qubit q )
    {
//        final BigDecimal onChance = q.getKetON().abs()
//                .pow( 2 )
//                .setScale( QMath.PRECISION ,
//                           RoundingMode.FLOOR );
//
//        final BigDecimal offChance = q.getKetOFF().abs()
//                  .pow( 2 )
//                  .setScale( QMath.PRECISION ,
//                             RoundingMode.FLOOR );
//        
//        final BigDecimal distance = onChance.subtract( offChance ).abs();
//        
//        final BigDecimal lower = onChance.compareTo( offChance ) ==-1 ? onChance : offChance;
//        
//        final BigDecimal decision = new BigDecimal( Math.random() ).multiply( distance ).subtract( lower );
//        
//        final BigDecimal distanceToON = decision.subtract( onChance ).abs();
//        final BigDecimal distanceToOFF = decision.subtract( offChance ).abs();
//        return distanceToON.compareTo( distanceToOFF ) == -1 ? Qubit.QUBIT_ON : Qubit.QUBIT_OFF;
        
        //TODO Needed to sort out the collapsing!!!!!!
        throw new UnsupportedOperationException( "The collapse of a quibit is not yet supported" );
        
        
    }
}
