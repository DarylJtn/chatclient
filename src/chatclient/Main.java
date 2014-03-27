/*
 * Main.java
 */

package chatclient;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        String host = "127.0.0.1";
        if (args.length == 1) 
            host = args[0];
        
        DatagramSocket socket = new DatagramSocket();

        byte[] buf = new byte[256];
        InetAddress address   = InetAddress.getByName(host);

        BufferedReader stdIn  = new BufferedReader(
                                new InputStreamReader(System.in));
        while (true) {
            System.out.println("ChatClient: Type in your request (Q=Exit): ");
                
            String request = stdIn.readLine();
            if (request.equals("Q")) {
                System.exit(0);
            }
            buf = request.getBytes();

            DatagramPacket packet = new DatagramPacket(buf, buf.length, 
                                    address, 4445);

            String packetData = new String(packet.getData());
            System.out.println("ChatClient: Sending:" + packetData);

            socket.send(packet);

            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length, 
                                    address, 4445);
            
            System.out.println("ChatClient: Receiving...");
            socket.receive(packet);

            String response = new String(packet.getData());
            System.out.println("ChatClient: Response from Server: " + response);
       }
   }
}