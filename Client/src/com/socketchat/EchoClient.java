/** *
 * EchoClient
 * Example of a TCP client
 * Date: 10/01/04
 * Authors:
 */
package com.socketchat;

import java.io.*;
import java.net.*;

public class EchoClient {

    private static Object key = new Object();

    /**
     * main method accepts a connection, receives a message from client then
     * sends an echo to the client
     *
     */
    public static void recievedMessage(String message) {
        synchronized (key) {
            System.out.println("RECIEVED :" + message);

        }
    }

    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        BufferedReader stdIn = null;
        EmissionThread emissionThread = null;
        RecieveThread recieveThread = null;

        if (args.length != 2) {
            System.out.println("Usage: java EchoClient <EchoServer host> <EchoServer port>");
            System.exit(1);
        }

        try {
            // creation socket ==> connexion
            echoSocket = new Socket(args[0], Integer.parseInt(args[1]));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            recieveThread = new RecieveThread(echoSocket);
            recieveThread.start();
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to:" + args[0]);
            System.exit(1);
        }

        String line;
        while (true) {
            line = stdIn.readLine();
            if (line.equals(".")) {
                break;
            }
            
            emissionThread = new EmissionThread(echoSocket, line);
            emissionThread.start();

        }
        stdIn.close();
        echoSocket.close();
    }
}
