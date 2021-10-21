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

    private Room room;
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
        room = null;
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
     * logique de réception de message, et de choix de salon
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
                    room.removeClient(this);
                    //System.out.println("Déconnexion d'un utilisateur");
                    isRunning = false;
                } else {
                    Message recievedMessage = new Gson().fromJson(line, Message.class);

                    String type = recievedMessage.getType();
                    username = recievedMessage.getAuthor();
                    if (type.equals("MSG")) {
                        room.broadcastMessage(recievedMessage);
                    } else if (type.equals("JROM")) {
                        joinRoom(recievedMessage.getDest());
                    }
                    else if (type.equals("JCLI")) {
                        joinRoom(recievedMessage.getDest());
                    }

                }
            }
        } catch (Exception e) {
            //System.err.println("Error in ClientHandler:" + e);
        }
    }

    synchronized void joinRoom(String roomName) {
        if (room != null) {
            room.removeClient(this);
        }
        if (EchoServerMultiThreaded.rooms.containsKey(roomName)) { //si le salon existe déja, on place le client à l'interieur
            room = EchoServerMultiThreaded.rooms.get(roomName);
            room.addClient(this);
        } else { //sinon on créer un nouveau salon, et on le place dedans
            Room newRoom = new Room(roomName);
            newRoom.addClient(this);
            room = newRoom;
            EchoServerMultiThreaded.rooms.put(roomName, room);
        }

    }

    /**
     *  sérialize la réponse et l'envoie au client
     * @param response
     */
    public void sendResponse(Response response) {
        String serializedResponse = new Gson().toJson(response);
        EmissionThread emissionThread = new EmissionThread(socOut, serializedResponse);
        emissionThread.start();
    }

}
