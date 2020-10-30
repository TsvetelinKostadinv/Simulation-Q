/*
 * 29/10/2020 01:05:59
 * AlgebraicComplexNumberArithmeticTest.java created by Tsvetelin
 */
package com.simulation_q.math.complex_numbers;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.simulation_q.math.complex_number.AlgebraicComplexNumber;
import com.simulation_q.math.complex_number.ComplexNumber;

/**
 * Tests the algebraic complex numbers for various errors, namely
 * precision loss and correctness of calculations
 * 
 * @author Tsvetelin
 */
class AlgebraicComplexNumberArithmeticTest
{
    
    private static final BigDecimal one = BigDecimal.ONE;
    private static final BigDecimal two = new BigDecimal( "2" );
    private static final BigDecimal three = one.add( two );
    
    private static final BigDecimal half = one.divide( two );
    private static final BigDecimal quarter = half.divide( two );
    
    @Test
    void constructorsTest ()
    {
        // assemble
        ComplexNumber bigDecimalConstructor =
            new AlgebraicComplexNumber( one , one );
        ComplexNumber stringConstructor =
            new AlgebraicComplexNumber( "1" , "1" );
        // act
        // assert
        assertEquals(
            bigDecimalConstructor ,
            stringConstructor ,
            "Different constructors provided with the same semantic data should produce the same complex number" );
    }
    
    @Test
    void equalsTest ()
    {
        // assemble
        ComplexNumber bigDecimalConstructor =
            new AlgebraicComplexNumber( one , one );
        ComplexNumber stringConstructor =
            new AlgebraicComplexNumber( "1" , "1" );
        ComplexNumber complex1and2 =
            new AlgebraicComplexNumber( one , two );
        ComplexNumber complex2and1 =
            new AlgebraicComplexNumber( two , one );
        // act
        // assert
        assertEquals(
            bigDecimalConstructor ,
            bigDecimalConstructor ,
            "Complex number is equal to itself" );
        assertEquals(
            bigDecimalConstructor ,
            stringConstructor ,
            "Different constructors provided with the same semantic data should produce the same complex number" );
        
        assertNotEquals(
            bigDecimalConstructor ,
            new Object() ,
            "A complex number should not be equal to a Object" );
        
        assertNotEquals(
            stringConstructor ,
            complex1and2 ,
            "The numbers have different imaginary parts" );
        
        assertNotEquals(
            stringConstructor ,
            complex2and1 ,
            "The numbers have different real parts" );
    }
    
    @Test
    void additionTest ()
    {
        // assembe
        ComplexNumber oneTwo = new AlgebraicComplexNumber( one , two );
        ComplexNumber twoOne = new AlgebraicComplexNumber( two , one );
        ComplexNumber threeThree = new AlgebraicComplexNumber( three , three );
        // act
        ComplexNumber res = oneTwo.add( twoOne );
        // assert
        assertEquals(
            threeThree ,
            res ,
            "Addition should just sum the components, maybe there is an error in the setting of the the precision" );
    }
    
    @Test
    void subtractionTest ()
    {
        // tests both negation and subtraction
        // assembe
        ComplexNumber oneTwo = new AlgebraicComplexNumber( one , two );
        ComplexNumber twoOne = new AlgebraicComplexNumber( two , one );
        ComplexNumber threeThree = new AlgebraicComplexNumber( three , three );
        // act
        // assert
        assertEquals(
            oneTwo ,
            threeThree.subtract( twoOne ) ,
            "Addition should just subtract the components, maybe there is an error in the setting of the the precision" );
        assertEquals(
            twoOne ,
            threeThree.subtract( oneTwo ) ,
            "Addition should just subtract the components, maybe there is an error in the setting of the the precision" );
        
    }
    
    @Test
    void additiveIdentityProperties ()
    {
        // assemble
        ComplexNumber complex11 = new AlgebraicComplexNumber( one , one );
        ComplexNumber addIdent = complex11.additiveIdentity();
        
        // act
        ComplexNumber complexPlusIdent = complex11.add( addIdent );
        ComplexNumber identPlusComplex = addIdent.add( complex11 );
        ComplexNumber identPlusIdent = addIdent.add( addIdent );
        
        ComplexNumber complexMinusIdent = complex11.subtract( addIdent );
        ComplexNumber identMinusIdent = addIdent.subtract( addIdent );
        
        // assert
        assertEquals(
            complex11 ,
            complexPlusIdent ,
            "Identity at the right hand side of + should not change the element" );
        
        assertEquals(
            complex11 ,
            identPlusComplex ,
            "Identity at the left hand side of + should not change the element" );
        
        assertEquals(
            complexPlusIdent ,
            identPlusComplex ,
            "Identity should not change the transitivity of equals" );
        
        assertEquals(
            addIdent ,
            identPlusIdent ,
            "Identity added to itself should still be the identity" );
        
        assertEquals(
            complex11 ,
            complexMinusIdent ,
            "Something - identity should equal the same thing" );
        
        assertEquals(
            addIdent ,
            identMinusIdent ,
            "identity - identity should still be the identity" );
    }
    
    @Test
    void multiplicativeIdentityProperties ()
    {
        // assemble
        ComplexNumber complex11 = new AlgebraicComplexNumber( two , two );
        ComplexNumber multIdent = complex11.multiplicativeIdentity();
        
        // act
        ComplexNumber complexTimesIdent = complex11.multiply( multIdent );
        ComplexNumber identTimesComplex = multIdent.multiply( complex11 );
        ComplexNumber identTimesIdent = multIdent.multiply( multIdent );
        
        ComplexNumber complexDividedIdent = complex11.divide( multIdent );
        ComplexNumber identDividedIdent = multIdent.divide( multIdent );
        
        // assert
        assertEquals(
            complex11 ,
            complexTimesIdent ,
            "Identity at the right hand side of * should not change the element" );
        
        assertEquals(
            complex11 ,
            identTimesComplex ,
            "Identity at the left hand side of * should not change the element" );
        
        assertEquals(
            complexTimesIdent ,
            identTimesComplex ,
            "Identity should not change the transitivity of equals" );
        
        assertEquals(
            multIdent ,
            identTimesIdent ,
            "Identity added to itself should still be the identity" );
        
        assertEquals(
            complex11 ,
            complexDividedIdent ,
            "Something / identity should equal the same thing" );
        
        assertEquals(
            multIdent ,
            identDividedIdent ,
            "Identity / identity should still be the identity" );
        
    }
    
    @Test
    void divisionTest ()
    {
        // assemble
        ComplexNumber realHalf = ComplexNumber.Algebraic.real( half );
        ComplexNumber realTwo = ComplexNumber.Algebraic.real( two );
        ComplexNumber realQuarter = ComplexNumber.Algebraic.real( quarter );
        
        // act
        ComplexNumber res = realHalf.divide( realTwo );
        
        // assert
        assertEquals( realQuarter , res , "The precision of division is off" );
    }
    
    @Test
    void multiplicationTest ()
    {
        // assemble
        ComplexNumber realHalf = ComplexNumber.Algebraic.real( half );
        ComplexNumber realTwo = ComplexNumber.Algebraic.real( two );
        ComplexNumber realOne = ComplexNumber.Algebraic.real( one );
        
        // act
        ComplexNumber res = realHalf.multiply( realTwo );
        
        // assert
        assertEquals(
            realOne ,
            res ,
            "The precision of multiplication is off" );
    }
    
}
