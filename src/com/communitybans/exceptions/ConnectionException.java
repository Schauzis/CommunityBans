package com.communitybans.exceptions;


/**
 *
 * @author Hretsam
 * 
 * This is an exception that is used in the handling of connections, its also acts 
 * as a superclass for the detailed exceptions classes.
 * 
 */
public class ConnectionException extends Exception {

    /**
     * Creates a new instance of <code>IllegalPacketException</code> without detail message.
     */
    public ConnectionException() {
    }

    /**
     * Constructs an instance of <code>IllegalPacketException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ConnectionException(String msg) {
        super(msg);
    }
}
