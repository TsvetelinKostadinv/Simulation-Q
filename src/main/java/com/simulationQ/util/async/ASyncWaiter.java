/*
 * 08/12/2019 16:02:51
 * ASyncWaiter.java created by Tsvetelin
 */
package com.simulationQ.util.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Tsvetelin
 */
public interface ASyncWaiter
{
    public static < T > T awaitRes ( final Future< T > f )
        throws InterruptedException ,
        ExecutionException
    {
        while ( !f.isDone() )
            ;
        return f.get();
    }
}
