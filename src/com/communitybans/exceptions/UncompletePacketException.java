package com.communitybans.exceptions;

/**
 *
 * @author Hretsam
 * 
 * This exception is used to show that there is a problem with an recieved packet.
 * The packet that throws the error is missing information and cannot be converted back into a packet.
 */
public class UncompletePacketException extends ConnectionException {

    /**
     * Creates a new instance of <code>IllegalPacketException</code> without detail message.
     */
    public UncompletePacketException() {
    }

    /**
     * Constructs an instance of <code>IllegalPacketException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UncompletePacketException(String msg) {
        super(msg);
    }
}
