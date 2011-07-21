package com.communitybans.communication;

import com.communitybans.communication.objects.Request;
import com.communitybans.communication.objects.Response;
import com.communitybans.exceptions.ConnectionException;
import com.communitybans.exceptions.PacketConvertionException;
import com.communitybans.exceptions.UncompletePacketException;
import com.communitybans.exceptions.WrongPacketException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Hretsam
 * 
 * This is the handler for the communication with the server.
 * 
 */
public class CommunicationHandler {

    /** The url of the server */
    private static String remoteUrl;

    /**
     * This method will send a request to the server.
     * The method itself is not threaded (yet) so it will use the same thread as where the request came from.
     * This can slow down or halt the Thread its in!
     * 
     * @param a request subclass of packet
     * @return subclass of packet that the response
     * 
     * @throws PacketConvertionException
     * This is thrown when the requestpacket cannot be converted.
     * @throws UncompletePacketException
     * This is thrown when the requestPacket is empty.
     * @throws WrongPacketException
     * This is thrown when the packet that is recieved is not a response.
     * @throws ConnectionException 
     * This exception is thrown when there is something wrong with the connection.
     * The ConnectionException is also the superclass off the other Exceptions.
     */
    public static Response sendRequest(Request requestPacket) throws PacketConvertionException, UncompletePacketException, WrongPacketException, ConnectionException {
        // Check for a packet
        if (requestPacket == null) {
            throw new UncompletePacketException("Packet is empty! Communication error 001. ");
        }
        // Check if a location is set!
        if (remoteUrl == null) {
            throw new ConnectionException("No location! Communication error 002. ");
        }

        try {
            // Make connection
            URL url = new URL(remoteUrl + requestPacket.getIdKey());
            URLConnection conn = url.openConnection();
            // connection Settings
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(15000);
            conn.setDoOutput(true);

            // Send object
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(Packet.packetToString(requestPacket));
            wr.flush();

            // Get object
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder buf = new StringBuilder();
            String line;

            // Read incomming string
            while ((line = rd.readLine()) != null) {
                buf.append(line);
            }

            // Close reader & writer
            try {
                rd.close();
            } catch (IOException e) {
                // Ignore
            }
            try {
                wr.close();
            } catch (IOException e) {
                // Ignore
            }

            // Make packet object
            Packet responsePacket = Packet.stringToPacket(buf.toString());

            // Check if packet is a response
            if (!(responsePacket instanceof Response)) {
                // Packet is not a response, throw an exception!
                throw new WrongPacketException("Wrong packet recieved! Communication error 005 ");
            }

            // Return the downcasted packet
            return (Response) responsePacket;
        } catch (MalformedURLException ex) {
            throw new ConnectionException("Url invalid! Communication error 003 " + ex.getMessage());
        } catch (IOException ex) {
            throw new ConnectionException("Connection failed, communication error 004 " + ex.getMessage());
        }
    }

    /**
     * This will set the remote location of the server, this is used in the other methods of this class
     * @param remoteUrl 
     * 
     * @todo add a check to see if the url is ok
     */
    public static void setRemoteUrl(String remoteUrl) {
        CommunicationHandler.remoteUrl = remoteUrl;
    }

    /**
     * This will return the remote location of the server
     * @return remote server location
     */
    public static String getRemoteUrl() {
        return remoteUrl;
    }
}
