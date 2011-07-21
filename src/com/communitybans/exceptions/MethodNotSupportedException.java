package com.communitybans.exceptions;

/**
 *
 * @author Hretsam
 * 
 * Exception used for the reporting hooks in MCBans.
 * If the given plugin doesn't support the method that is called, this exception will be thrown.
 * 
 */
public class MethodNotSupportedException extends ConnectionException {

    /**
     * Creates a new instance of <code>IllegalPacketException</code> without detail message.
     */
    public MethodNotSupportedException() {
    }

    /**
     * Constructs an instance of <code>IllegalPacketException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MethodNotSupportedException(String msg) {
        super(msg);
    }
    
}
