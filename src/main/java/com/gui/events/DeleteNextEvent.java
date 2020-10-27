/*
 * 22/03/2020 13:51:18
 * DeleteNextEvent.java created by Tsvetelin
 */
package com.gui.events;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * @author Tsvetelin
 */
public class DeleteNextEvent extends Event
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public static final DeleteNextEvent inst = new DeleteNextEvent();
    
    /**
     * @param eventType
     */
    private DeleteNextEvent ()
    {
        super( new EventType<>( DeleteNextEvent.class.getName() ) );
    }
    
    /**
     * @param source
     * @param target
     * @param eventType
     */
    public DeleteNextEvent (
        Object source ,
        EventTarget target ,
        EventType< ? extends Event > eventType )
    {
        super( source , target , eventType );
    }
    
}
