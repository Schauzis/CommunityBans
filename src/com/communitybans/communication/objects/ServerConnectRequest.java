package com.communitybans.communication.objects;

import com.communitybans.communication.JsonConvertion;

/**
 *
 * @author Hretsam
 * 
 * This object is used to make a connection request, where its checked if the permium service should be enabled.
 * Also this checks if the server is allowed to use this plugin.
 */
public class ServerConnectRequest extends Request {

    private String premiumPass;

    /**
     * Constructor used by the JSON Converter.
     * Do not make a packet with this constructor!
     */
    protected ServerConnectRequest() {
    }

    public ServerConnectRequest(String idKey) {
        super(Type.REQUEST_SERVER_CONNECT, idKey);
    }

    public ServerConnectRequest(String idKey, String premiumPass) {
        super(Type.REQUEST_SERVER_CONNECT, idKey);
        this.premiumPass = premiumPass;
    }

    @JsonConvertion(name = "premiumpass", type = "get")
    public String getPremiumPass() {
        return premiumPass;
    }

    @JsonConvertion(name = "premiumpass", type = "set")
    public void setPremiumPass(String permiumpass) {
        this.premiumPass = permiumpass;
    }
}
