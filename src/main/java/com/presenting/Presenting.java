/*
 * 04/03/2020 14:30:17
 * Presenting.java created by Tsvetelin
 */
package com.presenting;


import java.util.LinkedList;
import java.util.List;

import com.simulationQ.simulation.computation.QCollapser;
import com.simulationQ.simulation.computation.QFinalStateCalculator;
import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.computation.gates.impl.Hadamard;
import com.simulationQ.simulation.computation.gates.impl.NOT;
import com.simulationQ.simulation.computation.gates.impl.PauliY;
import com.simulationQ.simulation.computation.gates.impl.PauliZ;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.Qubit;
import com.simulationQ.simulation.computation.qubits.register.CRegister;
import com.simulationQ.simulation.computation.qubits.register.QRegister;


/**
 * @author Tsvetelin
 *
 */
public abstract class Presenting
{

    // ��������� ������� 1 � 0
    // �� ������ �������� ����������� ������� �� �������� ������

    public static final String    REGISTER = "010";

    
    
    // ��������� ������� H, X, Y, Z
    // ���������� � ����� ����� �� ���������,
    // �� ������ �������� ����������� ������� �� �������� ������

    public static final String [] PROGRAM  = new String[] {
            "",
            "",
            ""
    };
    
    
    public static final long COLLAPSES = 1_000_000L;

    /**
     * @param args
     */
    public static void main ( String [] args )
    {
        final QRegister register = new QRegister( parseRegister() );
        System.out.println( "Start: " + register );

        final QProgram program = new QProgram();

        final QGate [] [] gates = parseProgram();

        for ( int index = 0 ; index < gates.length ; index++ )
        {
            for ( int j = 0 ; j < gates[index].length ; j++ )
            {
                program.addPart( gates[index][j] , index );
            }
        }
        
        final QRegister finalState = QFinalStateCalculator.calculateFinalState( program , register );
        System.out.println( "Final state: " + finalState );
        
        long start = System.currentTimeMillis();
        
        final CRegister result = QCollapser.collapse( finalState , COLLAPSES );
        
        long end = System.currentTimeMillis();
        
        System.out.println( "Result(after "+COLLAPSES+" collapses) : " + result );
        System.out.println( "Took: " + (end-start) + "ms" );
        
    }

    /**
     * @return
     */
    private static final QGate [] [] parseProgram ()
    {
        final String registerAndProgramNotTheSameSizeMessage = "������������ �� ���������� � �� ������� ��������� �� ���������� ������ �� ��������";
        final String unexpectedSymbolMessage = "���������� ������ �� ��� %s - %s";
        if ( PROGRAM.length != REGISTER.length() )
            throw new IllegalArgumentException( registerAndProgramNotTheSameSizeMessage );

        List< QGate [] > programRows = new LinkedList<>();

        for ( String row : PROGRAM )
        {
            List< QGate > currentRow = new LinkedList<>();
            for ( char symbol : row.toCharArray() )
            {
                switch ( symbol )
                    {
                        case 'H' :
                        case 'h' :
                            currentRow.add( new Hadamard() );
                            break;
                        case 'X' :
                        case 'x' :
                            currentRow.add( new NOT() );
                            break;
                        case 'Y' :
                        case 'y' :
                            currentRow.add( new PauliY() );
                            break;
                        case 'Z' :
                        case 'z' :
                            currentRow.add( new PauliZ() );
                            break;
                        case ' ' :
                        case '_' :
                            break;
                        default :
                            throw new IllegalArgumentException( String.format( unexpectedSymbolMessage ,
                                                                               row ,
                                                                               symbol ) );
                    }
            }
            programRows.add( currentRow.toArray( new QGate[currentRow.size()] ) );
        }

        return programRows.toArray( new QGate[programRows.size()][] );

    }

    /**
     * @return
     */
    private static final Qubit [] parseRegister ()
    {
        final String unexpectedSymbolMessage = "���������� ������ �� ������� ���� ������� 1 ��� 0";

        final List< Qubit > reg = new LinkedList< Qubit >();
        for ( char bit : REGISTER.toCharArray() )
        {
            switch ( bit )
                {
                    case '0' :
                        reg.add( Qubit.QUBIT_OFF );
                        break;
                    case '1' :
                        reg.add( Qubit.QUBIT_ON );
                        break;
                    default :
                        throw new IllegalArgumentException( unexpectedSymbolMessage );
                }
        }
        return reg.toArray( new Qubit[reg.size()] );
    }

}
