package com.communitybans.communication.objects;

import com.communitybans.communication.JsonConvertion;
import com.communitybans.communication.Packet;

/**
 *
 * @author Hretsam
 * 
 * This the the superclass for all the response objects.
 * In addition this is also a basic response for requests.
 */
public class Response extends Packet {

    private ResponseType response;

    /**
     * Constructor used by the JSON Converter.
     * Do not make a packet with this constructor!
     */
    protected Response() {
        super(Type.RESPONSE_BASIC);
    }

    /**
     * Constructor for subclasses of BasicResponse
     * @param packetType
     * @param idKey 
     * @param Type of response
     */
    protected Response(Type type, ResponseType restype) {
        super(type);
        this.response = restype;
    }

    public Response(int response) {
        super(Type.RESPONSE_BASIC);
        this.response = ResponseType.getType(response);
    }

    @JsonConvertion(name = "response", type = "get")
    public int getResponseId() {
        return response.id;
    }

    public ResponseType getResponse() {
        return response;
    }

    @JsonConvertion(name = "response", type = "set")
    protected void setResponse(String response) {
        this.response = ResponseType.getType(Integer.parseInt(response));
    }
    
    public enum ResponseType {

        /** Server accepted the request */
        ACCEPT(1),
        /** This server is not allowed to do that */
        NOT_ALLOWED(2),
        /** idKey is missing */
        ID_KEY_MISSING(3),
        /** Blacklisted */
        BLACKLIST(4),
        /** premium function is not allowed */
        NOT_PREMIUM(5),
        /** Unknown reason */
        UNKNOWN(0);
        
        /** Id that is the response type as an int*/
        private int id;

        /**
         * Creates a new type with ID
         * @param id 
         */
        private ResponseType(int id) {
            this.id = id;
        }

        /**
         * Returns the Responsetype thats corresponds to the ID,
         * If ID is invalid, it will return UNKNOWN
         * @param id
         * @return ResponseType or null when id is invalid
         */
        public static ResponseType getType(int id) {
            for (ResponseType type : values()) {
                if (type.id == id) {
                    return type;
                }
            }
            return UNKNOWN;
        }
    }
}
