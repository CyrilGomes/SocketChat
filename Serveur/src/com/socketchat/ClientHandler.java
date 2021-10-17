/** *
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */
package com.socketchat;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler
        implements Runnable {

    private String username;
    private Socket clientSocket;
    private BufferedReader socIn;
    private PrintWriter socOut;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            socOut = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * receives a request from client then sends an echo to the client
     *
     * @param clientSocket the client socket
     *
     */
    public void run() {
        try {

            Boolean isRunning = true;
            while (isRunning) {
                String line = socIn.readLine();
                if (line == null) {
                    socIn.close();
                    socOut.close();
                    clientSocket.close();
                    System.out.println("Déconnexion d'un utilisateur");
                    isRunning = false;
                } else {
                    Message recievedMessage = new Gson().fromJson(line, Message.class);
                    username = recievedMessage.getAuthor();

                    if (EchoServerMultiThreaded.rooms.containsKey(recievedMessage.getDest())) {
                        Room room = EchoServerMultiThreaded.rooms.get(recievedMessage.getDest());
                        room.broadcastMessage(recievedMessage);
                    }

                }
            }
        } catch (Exception e) {
            System.err.println("Error in ClientHandler:" + e);
        }
    }

    public void sendResponse(Response response) {
        String serializedResponse = new Gson().toJson(response);
        EmissionThread emissionThread = new EmissionThread(socOut, serializedResponse);
        emissionThread.start();
    }

}
