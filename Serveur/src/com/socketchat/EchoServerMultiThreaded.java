/** *
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */
package com.socketchat;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServerMultiThreaded {

    public static Map<String, ClientHandler> clientsMap;
    public static Map<String, Room> rooms;
    public static List<ClientHandler> connectedClients;
    private static String logFilePath = "./logs.json";

    /**
     * main method
     *
     * @param EchoServer port
     *
     *
     */
    public static void saveRooms() throws IOException {

        File yourFile = new File(logFilePath);
        yourFile.createNewFile();
        System.out.println("SAVINGS");
        FileOutputStream file = new FileOutputStream(logFilePath);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(rooms);

        out.close();
        file.close();

    }

    public static void restoreRooms() throws IOException {
        FileInputStream file = new FileInputStream(logFilePath);
        ObjectInputStream in = new ObjectInputStream(file);

        try {
            rooms = (Map<String, Room>) in.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EchoServerMultiThreaded.class.getName()).log(Level.SEVERE, null, ex);
        }

        in.close();
        file.close();
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            String key = entry.getKey();
            Room value = entry.getValue();
            System.out.println(key + " " + value.toString());
        }

    }

    public static void main(String args[]) {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                try {
                    saveRooms();
                } catch (IOException ex) {
                    Logger.getLogger(EchoServerMultiThreaded.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        try {
            restoreRooms();
        } catch (IOException ex) {
            Logger.getLogger(EchoServerMultiThreaded.class.getName()).log(Level.SEVERE, null, ex);
        }

        ServerSocket listenSocket;
        connectedClients = new ArrayList<>();
        clientsMap = new HashMap<>();
        if (rooms == null) {
            rooms = new HashMap<>();
        }

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
                Thread ct = new Thread(currClient);
                ct.start();
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
