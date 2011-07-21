package com.communitybans.communication.objects;

import com.communitybans.communication.JsonConvertion;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Hretsam
 * 
 * Response for the lookuprequest, acts for now as a placeholder
 */
public class LookupResponse extends Response {

    private List<String> banreasons;
    private int reputation;

    /**
     * Constructor used by the JSON Converter.
     * Do not make a packet with this constructor!
     */
    protected LookupResponse() {
    }

    /**
     * An empty lookup resposne
     * @param restype 
     */
    public LookupResponse(ResponseType restype) {
        super(Type.RESPONSE_LOOPUP, restype);
    }

    /**
     * A loopup seponse object.
     * @param restype
     * @param banreasons
     * @param reputation 
     */
    public LookupResponse(ResponseType restype, List<String> banreasons, int reputation) {
        super(Type.RESPONSE_LOOPUP, restype);
        this.banreasons = banreasons;
        this.reputation = reputation;
    }

    public List<String> getBanreasons() {
        return banreasons;
    }

    public void setBanreasons(List<String> banreasons) {
        this.banreasons = banreasons;
    }

    @JsonConvertion(name = "reputation", type = "get")
    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    @JsonConvertion(name = "reputation", type = "set")
    protected void setReputation(String reputation) throws NumberFormatException {
        this.reputation = Integer.parseInt(reputation);
    }

    @JsonConvertion(name = "banreasons", type = "get")
    protected String getBanReasonString() {
        return new JSONArray(banreasons).toString();
    }

    @JsonConvertion(name = "banreasons", type = "set")
    protected void setBanReason(String string) throws JSONException {
        JSONArray array = new JSONArray(string);
        banreasons = new ArrayList<String>();
        for (int i = 0; i < array.length(); i++) {
            banreasons.add(array.getString(i));
        }
    }
}
