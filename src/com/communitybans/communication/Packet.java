package com.communitybans.communication;

import com.communitybans.communication.objects.*;
import com.communitybans.exceptions.PacketConvertionException;
import com.communitybans.exceptions.UncompletePacketException;
import java.lang.reflect.Method;
import org.json.JSONObject;

/**
 *
 * @author Hretsam
 */
public abstract class Packet {

    /** The type of the packet */
    private Type packetType;

    /**
     * Constructor used by the JSON Converter.
     * Do not make a packet with this constructor!
     */
    protected Packet() {
    }

    /**
     * Constructor for subclasses, do not make a packet from the parent object.
     * @param packetType
     */
    protected Packet(Type packetType) {
        this.packetType = packetType;
    }
    
    /**
     * This method converts a packet to a json String that can be send or stored.
     * If there is something wrong with the convertion of the packet it will throw a PacketConvertionException.
     *
     * @param subclass of Packet
     * @return JSONString of the packet
     * @throws PacketConvertionException
     */
    public static String packetToString(Packet packet) throws PacketConvertionException {
        try {
            // Make a JSON object that will temporary hold the values
            JSONObject obj = new JSONObject();
            // Get the class of the packet
            Class clazz = packet.getClass();
            // Annotation object, used in the convertion;
            JsonConvertion annot;
            // Check the class his own methods
            for (Method method : clazz.getDeclaredMethods()) {
                // Get the annotation
                annot = method.getAnnotation(JsonConvertion.class);
                // Check if the annotation exists for this method, and if its a 'get' method
                if (annot != null && annot.type().equals("get")) {
                    // Run the method and set the value in the object
                    obj.put(annot.name(), method.invoke(packet, new Object[]{}));
                }
            }
            // Check all superclasses, Object class will be ignored
            while ((clazz = clazz.getSuperclass()) != null && clazz != Object.class) {
                // Check all methods of the superclass
                for (Method method : clazz.getDeclaredMethods()) {
                    // Get the annotation
                    annot = method.getAnnotation(JsonConvertion.class);
                    // Check if the annotation exists for this method, and if its a 'get' method
                    if (annot != null && annot.type().equals("get")) {
                        // Run the method and set the value in the object
                        obj.put(annot.name(), method.invoke(packet, new Object[]{}));
                    }
                }
            }
            // Return the String
            return obj.toString();
        } catch (Exception ex) {
            // If there is an exception the packet is not valid. Throw error
            throw new PacketConvertionException("Cannot compress packet. Packet error 002. " + ex);
        }
    }

    /**
     * This is a converted that converts the given json string to a subclass off Packet,.
     * If the json string is not properly given it will return an UncompletPacketException
     *
     * @param JSONString
     * @return Subclass of packet
     * @throws UncompletePacketException
     */
    public static Packet stringToPacket(String jsonString) throws UncompletePacketException {
        try {
            // Make a JSON object that will temporary hold the values
            JSONObject obj = new JSONObject(jsonString);
            // Make a new object that is a subclass of packet
            // Because type.getType will always return a type with a class
            // Its safe to ignorne all null checks and it will be safe for casting
            Packet packet = (Packet) Type.getType(obj.getInt("type")).getClazz().newInstance();
            // Get the class
            Class clazz = packet.getClass();
            // Annotation object, used in the convertion;
            JsonConvertion annot;
            // Check the class his own methods
            for (Method method : clazz.getDeclaredMethods()) {
                // Get the annotation
                annot = method.getAnnotation(JsonConvertion.class);
                // Check if the annotation exists for this method, and if its a 'set' method
                if (annot != null && annot.type().equals("set")) {
                    // Run the method to set the value
                    method.invoke(packet, new Object[]{obj.getString(annot.name())});
                }
            }
            // Check all superclasses, Object class will be ignored
            while ((clazz = clazz.getSuperclass()) != null && clazz != Object.class) {
                // Check methods of the superclas
                for (Method method : clazz.getDeclaredMethods()) {
                    // Get the annotation
                    annot = method.getAnnotation(JsonConvertion.class);
                    // Check if the annotation exists for this method, and if its a 'set' method
                    if (annot != null && annot.type().equals("set")) {
                        // Run the method to set the value
                        method.invoke(packet, new Object[]{obj.getString(annot.name())});
                    }
                }
            }
            // Return the packet
            return packet;
        } catch (Exception ex) {
            // If there is an exception the packet is not valid. Throw error
            throw new UncompletePacketException("Packet is not complete, Packet error 001. " + ex);
        }
    }

    /**
     * Returns the packettype
     * @return the type of the packet
     * @see Packed.Type
     */
    public Type getPacketType() {
        return packetType;
    }

    /**
     * This will return the type as an int
     * @return Type as int
     */
    @JsonConvertion(name = "type", type = "get")
    public int getPacketTypeInt() {
        return packetType.getId();
    }

    /**
     * Set method for the converter.
     * This method should not be called on other location.
     * The type of the packet should not be allowed to be adjusted.
     * @param type 
     */
    @JsonConvertion(name = "type", type = "set")
    private void setPacketType(String type) {
        this.packetType = Type.getType(Integer.parseInt(type));
    }

    /**
     * Enum with the types and classes of the connections
     */
    public enum Type {

        /** Connect request */
        REQUEST_SERVER_CONNECT(1, ServerConnectRequest.class),
        /** Ban requests */
        REQUEST_BAN(2, BanRequest.class),
        /** Looup requests */
        REQUEST_LOOKUP(3, LookupRequest.class),
        /** Response */
        RESPONSE_BASIC(4, Response.class),
        /** Response Lookup */
        RESPONSE_LOOPUP(5, LookupResponse.class),
        /** Unknown */
        UNKNOWN(0, Packet.class);
        
        /** The packet id, 0 = unknown */
        private int id;
        /** The class that handles this type */
        private Class clazz;

        /**
         * Constructor for type
         * @param The packet id, 0 = unknown 
         * @param The class that handles this type
         */
        private Type(int id, Class clazz) {
            this.id = id;
            this.clazz = clazz;
        }

        /**
         * Returns the id of the type, which represents the type as an int
         * @return type as an int
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the Class object that handles the type
         * @return Class
         */
        public Class getClazz() {
            return clazz;
        }

        /**
         * Returns the Type that got the given id.
         * If id is invalid, it will return unknown.
         * @param id
         * @return The type that correponds with the id, if id is not valid UNKNOWN
         */
        public static Type getType(int id) {
            for (Type t : values()) {
                if (t.getId() == id) {
                    return t;
                }
            }
            return UNKNOWN;
        }
    }
}
