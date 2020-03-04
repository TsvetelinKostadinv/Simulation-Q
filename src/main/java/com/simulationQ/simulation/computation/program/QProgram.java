/*
 * 03/03/2020 16:28:07
 * Program.java created by Tsvetelin
 */
package com.simulationQ.simulation.computation.program;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.simulationQ.simulation.computation.gates.QGate;


/**
 * @author Tsvetelin
 *
 */
public class QProgram implements Iterable< QProgram.QProgramPart >
{

    private final List< QProgramPart > program = new LinkedList<>();

    /**
     * 
     */
    public QProgram ()
    {}
    
    
    public void addPart( QGate gate , int index )
    {
        program.add( new QProgramPart( gate , index ) );
    }

    
    
    @Override
    public Iterator< QProgramPart > iterator ()
    {
        return program.iterator();
    }
    
    public Stream< QProgramPart > stream()
    {
        return StreamSupport.stream(spliterator(), false);
    }



    public static final class QProgramPart
    {

        private final QGate oper;

        private final int   startIndexInRegister;

        /**
         * @param oper
         * @param startIndexInRegister
         */
        public QProgramPart ( QGate oper , int startIndexInRegister )
        {
            super();
            this.oper = oper;
            this.startIndexInRegister = startIndexInRegister;
        }

        
        /**
         * @return the oper
         */
        public QGate getOper ()
        {
            return oper;
        }

        
        /**
         * @return the startIndexInRegister
         */
        public int getStartIndexInRegister ()
        {
            return startIndexInRegister;
        }

    }
}
