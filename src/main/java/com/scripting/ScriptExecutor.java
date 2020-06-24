/*
 * 23/06/2020 23:33:56
 * ScriptExecutor.java created by Tsvetelin
 */
package com.scripting;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.simulationQ.simulation.computation.QFinalStateCalculator;
import com.simulationQ.simulation.computation.gates.QGates;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.register.CRegister;
import com.simulationQ.simulation.computation.qubits.register.QRegister;


/**
 * @author Tsvetelin
 *
 */
public interface ScriptExecutor
{

    public static Optional<QRegister> executeScript ( final String path )
    {
        File file = new File( path );
        if ( !file.exists() )
        {
            System.err.println( "You are trying to execute an unexisting file!" );
            return Optional.empty();
        }

        List< String > rawProgram = null;

        try ( final BufferedReader reader = new BufferedReader( new FileReader( file ) ) )
        {
            rawProgram = reader.lines()
                               .collect( Collectors.toList() );
        } catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        } catch ( IOException e )
        {
            e.printStackTrace();
        }

        final String registerRegex = "^(\\[(0|1)+\\])$";

        CRegister reg = null;
        QProgram program = new QProgram();

        for ( int i = 0 ; i < rawProgram.size() ; i++ )
        {
            final String line = rawProgram.get( i );
            if ( line.startsWith( "--" )
                    || line.isBlank() )
                continue;

            if ( line.matches( registerRegex ) )
            {
                // when the user is setting the register
                reg = new CRegister( line.substring( 1 , line.length()-1 ) );
            } else
            {
                // normal line with instructions

                String [] instructions = line.split( "->" );
                try
                {
                    final int index = Integer.parseInt( instructions[0].trim() );
                    final int rowNumber = i;
                    for ( int j = 1 ; j < instructions.length ; j++ )
                    {
                        QGates.getGateByNameIgnoreCase( instructions[j].trim() )
                              .ifPresentOrElse(
                                                gate -> program.addPart( gate ,
                                                                         index ) ,
                                                () -> {
                                                    printError( rowNumber ,
                                                                line ,
                                                                0 ,
                                                                line.length() ,
                                                                "Invalid gate identifier!" );
                                                } );
                    }

                } catch ( NumberFormatException e )
                {
                    printError( i ,
                                line ,
                                0 ,
                                instructions[0].length() ,
                                "invalid index of bit" );
                }

            }
        }
        return Optional.of( QFinalStateCalculator.calculateFinalState( program , reg ) );

    }

    public static void printError ( int line ,
                                    String input ,
                                    int index ,
                                    int len ,
                                    String desc )
    {
        System.err.println( "Error" );
        System.err.println( desc );
        System.err.println( repeat( " " , numberOfDigits( line ) ) + "|" );

        System.err.println( (line+1) + " | " + input );
        System.err.print( repeat( " " , numberOfDigits( line )+1 ) + repeat( " " , index ) );
        System.err.print( repeat( " " , numberOfDigits( line ) ) + repeat( "^" , len ) );

        System.err.println();

        System.err.println( repeat( " " , numberOfDigits( line ) ) + "|" );
    }

    public static int numberOfDigits ( int num )
    {
        int count = 0;

        while ( num != 0 )
        {
            num /= 10;
            count++;
        }
        return count;
    }

    public static String repeat ( String str , int n )
    {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0 ; i < n ; i++ )
        {
            sb.append( str );
        }
        return sb.toString();
    }
}
