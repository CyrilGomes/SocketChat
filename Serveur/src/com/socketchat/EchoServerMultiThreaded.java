/** *
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */
package com.socketchat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EchoServerMultiThreaded {

    public static Map<String, ClientHandler> clientsMap;
    public static Map<String, Room> rooms;
    public static List<ClientHandler> connectedClients;

    /**
     * main method
     *
     * @param EchoServer port
     *
     *
     */
    public static void main(String args[]) {
        ServerSocket listenSocket;
        connectedClients = new ArrayList<>();
        clientsMap = new HashMap<>();
        rooms = new HashMap<>();
        Room room = new Room("Michel");
        rooms.put("Michel", room);
        if (args.length != 1) {
            System.out.println("Usage: java EchoServer <EchoServer port>");
            System.exit(1);
        }
        try {
            listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
            System.out.println("Server ready...");
            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Connexion from:" + clientSocket.getInetAddress());
                ClientHandler currClient = new ClientHandler(clientSocket);
                connectedClients.add(currClient);
                room.addClient(currClient);
                Thread ct = new Thread(currClient);
                ct.start();
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
