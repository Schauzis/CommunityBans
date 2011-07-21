package com.communitybans.communication.objects;

import com.communitybans.communication.JsonConvertion;
import com.communitybans.communication.Packet;
import com.communitybans.communication.Packet.Type;

/**
 *
 * @author Hretsam
 * 
 * This the the superclass for all the request objects.
 */
public class Request extends Packet {

    /** The idkey of the server, this must always be included with the packet, 
     * If its not set, the packet will instantly fail. */
    private String idKey;

    /**
     * Constructor used by the JSON Converter.
     * Do not make a packet with this constructor!
     */
    protected Request() {
    }

    /**
     * Constructor for subclasses, do not make a packet from the parent object.
     * @param packetType
     * @param idKey 
     */
    protected Request(Type packetType, String idKey) {
        super(packetType);
        this.idKey = idKey;
    }

    /**
     * Returns the IDkey that is set.
     * @return the IDkey
     */
    @JsonConvertion(name = "idkey", type = "get")
    public String getIdKey() {
        return idKey;
    }

    /**
     * Set method for the converter.
     * This method should not be called on other location.
     * The idkey of the packet should always be set, as it doesn't change this method should not be needed.
     * @param IDkey 
     */
    @JsonConvertion(name = "idkey", type = "set")
    private void setIdKey(String key) {
        this.idKey = key;
    }
}
