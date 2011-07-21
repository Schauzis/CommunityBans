package com.communitybans.communication.objects;

import com.communitybans.communication.JsonConvertion;

/**
 *
 * @author Hretsam
 * 
 * This is a placeholder for a lookup request of a player or ip address.
 */
public class LookupRequest extends Request {

    /** The item that the player wants lookedup, can be an IP address or username*/
    private String lookupItem;

    /**
     * Constructor used by the JSON Converter.
     * Do not make a packet with this constructor!
     */
    protected LookupRequest() {
    }

    /**
     * Makes an empty lookuprequest item
     * @param idKey 
     */
    public LookupRequest(String idKey) {
        super(Type.REQUEST_LOOKUP, idKey);
    }

    public LookupRequest(String idKey, String item) {
        super(Type.REQUEST_LOOKUP, idKey);
        this.lookupItem = item;
    }

    @JsonConvertion(name = "litem", type = "get")
    public String getLookupItem() {
        return lookupItem;
    }

    @JsonConvertion(name = "litem", type = "set")
    public void setLookupItem(String item) {
        this.lookupItem = item;
    }
}
