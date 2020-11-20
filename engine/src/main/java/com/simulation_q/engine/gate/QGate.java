/*
 * 25/11/2019 23:27:05
 * QGate.java created by Tsvetelin
 */
package com.simulation_q.engine.gate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.simulation_q.math.complex_number.ComplexNumber;
import com.simulation_q.math.matrix.Matrix;

import lombok.Data;

/**
 * This is the atomic operation performed on a qubit or a register of
 * qubits
 * 
 * @author Tsvetelin
 */
@Data
public class QGate
{
    private final Matrix operation;
    private final int periodOfOperation;
    private final int numberOfInputBits;
    
    /**
     * The operation should be a square matrix, with the appropriate size
     * for the input(2^input) and it should be unary with respect to the
     * period of operation
     * 
     * @param operation
     * @param numberInputBits
     * @param periodOfOperation
     */
    public QGate (
        Matrix operation ,
        int numberInputBits ,
        int periodOfOperation )
    {
        super();
        Objects.requireNonNull( operation );
        
        if ( !isMatrixSquare( operation ) )
        {
            throw new IllegalArgumentException(
                "The matrix should be square to be classified as a quantum operation" );
        }
        
        if ( !appropriateSizeForInputBits( operation , numberInputBits ) )
        {
            throw new IllegalArgumentException(
                "The matrix is not appropriate size for the number of qubits. You need: "
                    + Math.pow( 2 , numberInputBits ) + " rows and columns" );
        }
        
        if ( !isMatrixUnary( operation , periodOfOperation ) )
        {
            throw new IllegalArgumentException(
                "The matrix should be unary with respect to the period" );
        }
        
        this.operation = operation;
        this.periodOfOperation = periodOfOperation;
        this.numberOfInputBits = numberInputBits;
    }
    
    /**
     * Checks whether the matrix is large enough for the cpecified input
     * in the rule: operation rows and columns should be 2^input
     * 
     * @param  operation
     * @param  numberInputBits
     * @return
     */
    static final boolean appropriateSizeForInputBits (
        Matrix operation ,
        int numberInputBits )
    {
        return operation.getRows() == (int) Math.pow( 2 , numberInputBits );
    }
    
    /**
     * After applying the matrix the specified number of times, we should
     * end up with the identity matrix
     * 
     * @param   matrix
     * @param   period
     * @return         true if the matrix is unary in respect to the
     *                     period, false otherwise
     * @apinote        some loss in precision is accounted for while
     *                     performing this check
     */
    static boolean isMatrixUnary (
        Matrix matrix ,
        int period )
    {
        return matrix.pow( period ).map( old -> {
            BigDecimal oldReal = old.getReal();
            BigDecimal oldImg = old.getImaginary();
            
            BigDecimal realPart =
                oldReal.setScale(
                    1 ,
                    RoundingMode.HALF_UP )
                    .stripTrailingZeros();
            
            BigDecimal imgPart =
                oldImg.setScale(
                    1 ,
                    RoundingMode.HALF_UP )
                    .stripTrailingZeros();
            
            return ComplexNumber.Algebraic.of( realPart , imgPart );
        } )
            .equals(
                Matrix.multiplicativeIdentity(
                    matrix.getRows() ,
                    matrix.getColumns() ) );
    }
    
    /**
     * Stretches the matrix to fit for the size of the input qubits and at
     * the supplied starting index
     * 
     * @param  startingIndex - the index of the qubit that the operation
     *                           starts from
     * @param  registerSize  - the number of input qubits
     * @return               a stretched gate that can be applied to a
     *                           register of the given size
     */
    public QGate stretchFor ( int startingIndex , int registerSize )
    {
        int vectorSize = (int) Math.pow( 2 , registerSize );
        
        Objects.requireNonNull( operation );
        if ( startingIndex < 0 || startingIndex >= vectorSize )
        {
            throw new IllegalArgumentException(
                "The starting index has to be within the bouds of the register" );
        }
        if ( registerSize <= 0 )
        {
            throw new IllegalArgumentException(
                "The size of the register has to be greater than zero" );
        }
        
        QGate res = new QGate( Matrix.multiplicativeIdentity( 1 , 1 ) , 0 , 0 );
        QGate identity2x2 =
            new QGate( Matrix.multiplicativeIdentity( 2 , 2 ) , 1 , 0 );
        
        for ( int i = 0 ; i < startingIndex ; i++ )
        {
            res = res.above( identity2x2 );
        }
        
        res =
            res.above( this );
        
        while ( res.getNumberOfInputBits() < registerSize )
        {
            res = res.above( identity2x2 );
        }
        
        return res;
    }
    
    /**
     * Gives back a gate that is as if we have this above the other gate
     * in a schematic representation:
     * <br>
     * 
     * <pre>
     *            ___________
     * qubits1 => |=> this  | => | the same |
     * qubits2 => |=> other | => | register |
     *            -----------
     * </pre>
     * 
     * @param  other                - the other gate which should be
     *                                  applied
     * @return                      a composition of gates: this above
     *                                  other
     * @throws NullPointerException - if the other gate is null
     */
    public QGate above ( QGate other ) throws NullPointerException
    {
        Objects.requireNonNull( other );
        // TODO this formula always will work but is not efficient
        int period =
            this.periodOfOperation > other.periodOfOperation ?
                this.periodOfOperation :
                    other.periodOfOperation;
        int inputQubits = this.numberOfInputBits + other.numberOfInputBits;
        Matrix operation = other.operation.productKronecker( this.operation );
        return new QGate( operation , inputQubits , period );
    }
    
    /**
     * Gives back a gate that is as if we have this above the other gate
     * in a schematic representation:
     * <br>
     * 
     * <pre>
     *            ___________
     * qubits1 => |=> other | => | the same |
     * qubits2 => |=> this  | => | register |
     *            -----------
     * </pre>
     * 
     * @param  other - the other gate which should be applied
     * @return       a composition of gates: this below other
     *                   (other above this)
     */
    public QGate below ( QGate other )
    {
        return other.above( this );
    }
    
    /**
     * The serial application of this and the other gate
     * 
     * <pre>
     *              __________    ___________
     * register  => |=> this | => |=> other | => | the same |
     *              ----------    -----------
     * </pre>
     * 
     * @param  other
     * @return                      a composition of the 2 gates
     * @throws NullPointerException - if the other gate is null
     */
    public QGate andThen ( QGate other ) throws NullPointerException
    {
        Objects.requireNonNull( other );
        if ( other.numberOfInputBits != this.numberOfInputBits )
        {
            throw new IllegalArgumentException(
                "To compose 2 gates they have to have the same number of input qubits" );
        }
        int period =
            this.periodOfOperation > other.periodOfOperation ?
                this.periodOfOperation :
                    other.periodOfOperation;
        int inputQubits = this.numberOfInputBits;
        Matrix operation = other.operation.multiply( this.operation );
        return new QGate( operation , inputQubits , period );
    }
    
    /**
     * Self explanatory
     * 
     * @param  matrix
     * @return        true if the matrix is square, i.e rows == columns
     */
    static boolean isMatrixSquare ( Matrix matrix )
    {
        return matrix.getRows() == matrix.getColumns();
    }
}
