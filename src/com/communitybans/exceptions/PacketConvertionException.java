package com.communitybans.exceptions;

/**
 * @author Hretsam
 * 
 * This exception is used to show that there was an error converting the packet.
 */
public class PacketConvertionException extends ConnectionException {

    /**
     * Creates a new instance of <code>IllegalPacketException</code> without detail message.
     */
    public PacketConvertionException() {
    }

    /**
     * Constructs an instance of <code>IllegalPacketException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public PacketConvertionException(String msg) {
        super(msg);
    }
}
