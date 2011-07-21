package com.communitybans.report;

/**
 *
 * @author Hretsam
 * 
 * Holder object for blocks. Converts to a simple string and back.
 * Reason for this is so that blocks with data values are also accepted.
 * Blocks like wool, steps, logs...
 */
public class BlockType {

    private int id;
    private Byte data;

    /**
     * Converts a string to an itemid,
     * if there is a data value it gets seperated
     * @param blockString (id:data)
     * @throws NumberFormatException 
     */
    public BlockType(String blockString) throws NumberFormatException {
        if (blockString.contains(":")) {
            String[] split = blockString.split(":");
            id = Integer.parseInt(split[0]);
            if (split.length == 2) {
                data = Byte.parseByte(String.valueOf(Integer.parseInt(split[1]) % 16));
            } else {
                data = new Byte("0");
            }
        } else {
            id = Integer.parseInt(blockString);
            data = new Byte("0");
        }
    }

    /**
     * Makes a blocktype object with the given id & data values
     * @param id
     * @param data 
     */
    public BlockType(int id, Byte data) {
        this.id = id;
        this.data = data;
    }

    /**
     * Returns the data of the block.
     * @return 
     */
    public Byte getData() {
        return data;
    }

    /**
     * Returns the id of the block
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Returns this object in a form of "id:data" string.
     * @return "id:data"string
     */
    public String getBlockString() {
        if (data != 0) {
            return id + ":" + data;
        } else {
            return id + "";
        }
    }
}
