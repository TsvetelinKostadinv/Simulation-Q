/*
 * 04/03/2020 14:30:17
 * Presenting.java created by Tsvetelin
 */

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
 * This class demonstrates how you would set up a register, a program
 * and collapse it a number of times. Also it would be useful if you
 * just wanted to verify some results. However, this class works with
 * limited amount of gates.
 * 
 * @author Tsvetelin
 */
public abstract class SimpleInterpreter
{
    
    // Allowed symbols 0 and 1
    // Otherwise an exception is raised
    public static final String REGISTER = "010";
    
    // Allowed symbols H, X, Y, Z
    // spaces and undescores are ignored
    // an error will be generated for all other inputs
    // the array should contain as many elements as the register
    // in all other circumstances an exception is raised
    public static final String [] PROGRAM =
        new String[] {
            "" ,
            "" ,
            "" ,
        };
    
    // Pretty much self explanatory
    public static final long COLLAPSES = 1_000_000L;
    
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
        
        final QRegister finalState =
            QFinalStateCalculator.calculateFinalState( program , register );
        System.out.println( "Final state: " + finalState );
        
        long start = System.currentTimeMillis();
        
        final CRegister result = QCollapser.collapse( finalState , COLLAPSES );
        
        long end = System.currentTimeMillis();
        
        System.out
            .println( "Result(after " + COLLAPSES + " collapses) : " + result );
        System.out.println( "Took: " + ( end - start ) + "ms" );
        
    }
    
    /**
     * @return
     */
    private static final QGate [] [] parseProgram ()
    {
        final String registerAndProgramNotTheSameSizeMessage =
            "The length of the register and the elements in the array should have the same size";
        final String unexpectedSymbolMessage =
            "Unexpected symbol on line%s - %s";
        if ( PROGRAM.length != REGISTER.length() )
            throw new IllegalArgumentException(
                registerAndProgramNotTheSameSizeMessage );
        
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
                        throw new IllegalArgumentException(
                            String.format(
                                unexpectedSymbolMessage ,
                                row ,
                                symbol ) );
                }
            }
            programRows
                .add( currentRow.toArray( new QGate[currentRow.size()] ) );
        }
        
        return programRows.toArray( new QGate[programRows.size()][] );
        
    }
    
    /**
     * @return
     */
    private static final Qubit [] parseRegister ()
    {
        final String unexpectedSymbolMessage =
            "UnexpectedSymbol: the register should contain only 0s and 1s";
        
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
                    throw new IllegalArgumentException(
                        unexpectedSymbolMessage );
            }
        }
        return reg.toArray( new Qubit[reg.size()] );
    }
    
}
