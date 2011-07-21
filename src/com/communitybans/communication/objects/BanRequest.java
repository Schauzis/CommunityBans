package com.communitybans.communication.objects;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import com.communitybans.communication.JsonConvertion;

/**
 *
 * @author Hretsam
 * 
 * This object handles a request to ban a player
 */
public class BanRequest extends Request {

    /** The username of the player that banned */
    private String username;
    /** IPlist of the player, this is a list so that there can be several added */
    private List<String> iplist;
    /** The username of the player that issued the ban */
    private String admin;
    /** The reason given by the player that issued the ban */
    private String reason;
    /** All reports that are added to the request */
    private List<String> reports;

    /**
     * Constructor used by the JSON Converter.
     * Do not make a packet with this constructor!
     */
    protected BanRequest() {
    }

    /**
     * Makes an empty banrequest
     * @param idKey 
     */
    public BanRequest(String idKey) {
        super(Type.REQUEST_BAN, idKey);
    }

    /**
     * Creates a banrequest object with all information filled in
     * @param idKey
     * @param username
     * @param ip
     * @param admin
     * @param reason
     * @param reports 
     */
    public BanRequest(String idKey, String username, List<String> ip, String admin, String reason, List<String> reports) {
        super(Type.REQUEST_BAN, idKey);
        this.username = username;
        this.iplist = ip;
        this.admin = admin;
        this.reason = reason;
        this.reports = reports;
    }

    @JsonConvertion(name = "adminname", type = "get")
    public String getAdmin() {
        return admin;
    }

    @JsonConvertion(name = "adminname", type = "set")
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @JsonConvertion(name = "iplist", type = "get")
    protected String getIpListJsonString() {
        return new JSONArray(iplist).toString();
    }

    @JsonConvertion(name = "iplist", type = "set")
    protected void setIpList(String string) throws JSONException {
        JSONArray array = new JSONArray(string);
        iplist = new ArrayList<String>();
        for (int i = 0; i < array.length(); i++) {
            iplist.add(array.getString(i));
        }
    }

    public List<String> getIpList() {
        return iplist;
    }

    public void setIpList(List<String> ip) {
        this.iplist = ip;
    }

    @JsonConvertion(name = "reason", type = "get")
    public String getReason() {
        return reason;
    }

    @JsonConvertion(name = "reason", type = "set")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonConvertion(name = "reportlist", type = "get")
    protected String getReportListJsonString() {
        return new JSONArray(reports).toString();
    }

    @JsonConvertion(name = "reportlist", type = "set")
    protected void setReportList(String string) throws JSONException {
        JSONArray array = new JSONArray(string);
        reports = new ArrayList<String>();
        for (int i = 0; i < array.length(); i++) {
            reports.add(array.getString(i));
        }
    }

    public List<String> getReportList() {
        return reports;
    }

    public void setReportList(List<String> reports) {
        this.reports = reports;
    }
    
    @JsonConvertion(name = "username", type = "get")
    public String getUsername() {
        return username;
    }

    @JsonConvertion(name = "username", type = "set")
    public void setUsername(String username) {
        this.username = username;
    }
}
