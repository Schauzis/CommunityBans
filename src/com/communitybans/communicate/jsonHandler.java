package com.communitybans.communicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.communitybans.main.CommunityBans;
import com.communitybans.main.Settings;

@SuppressWarnings("unchecked")
public class jsonHandler{
	private String IDKey = null;
	public CommunityBans CB;
	public jsonHandler( CommunityBans p){
		CB = p;
		IDKey = Settings.getString("IDKey");
	}
	public JSONObject get_data(String json_text){
	    try {
			JSONObject json = new JSONObject(json_text);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	public HashMap<String,String> mainRequest(HashMap<String,String> items){
		HashMap<String,String> out= new HashMap<String,String>();
		String url_req=this.urlparse(items);
		String json_text=this.request_from_api(url_req);
		JSONObject output=this.get_data(json_text);
		if(output!=null){
			
			Iterator<String> i = output.keys();
			if(i!=null){
				while(i.hasNext())
				{
				    String next = i.next();
				    try {
						out.put(next, output.getString(next));
					} catch (JSONException e) {
							System.out.println("Error refers to the jsonHadler 001");
							e.printStackTrace();
					}
				}
			}
		}
		return out;
	}
	public JSONObject hdl_jobj(HashMap<String,String> items){
		String urlReq = urlparse(items);
		String jsonText = request_from_api(urlReq);
		JSONObject output = get_data(jsonText);
		return output;
	}
	public String request_from_api(String data){
		try {
			URL url = new URL("http://127.0.0.1/v2/"+this.IDKey);
    	    URLConnection conn = url.openConnection();
    	    conn.setConnectTimeout(4000);
    	    conn.setReadTimeout(6000);
    	    conn.setDoOutput(true);
    	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    	    wr.write(data);
    	    wr.flush();
    	    StringBuilder buf = new StringBuilder();
    	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	    String line;
    	    while ((line = rd.readLine()) != null) {
    	    	buf.append(line);
    	    }
    	    String result = buf.toString();
    	    wr.close();
    	    rd.close();
			return result;
		} catch (Exception e) {
			return "";
    	}
	}
	public String urlparse(HashMap<String,String> items){
		String data = "";
		try {
			for ( Map.Entry<String, String> entry : items.entrySet() ){
				String key = entry.getKey();
				String val = entry.getValue();
				if(data.equals("")){
					data = URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
				}else{
					data += "&" + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
				}
			}
		} catch (UnsupportedEncodingException e) {}
		return data;
	}
}