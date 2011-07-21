
package com.communitybans.exceptions;

/**
 *
 * @author Hretsam
 * 
 * This exception is used when the client/server gets a packet that is a request instead of a response.
 */
public class WrongPacketException extends ConnectionException {
    
    /**
     * Creates a new instance of <code>IllegalPacketException</code> without detail message.
     */
    public WrongPacketException() {
    }

    /**
     * Constructs an instance of <code>IllegalPacketException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public WrongPacketException(String msg) {
        super(msg);
    }
}
