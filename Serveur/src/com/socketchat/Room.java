/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Cyril
 */
public class Room implements Serializable {

    private transient List<ClientHandler> roomClients;
    private String roomName;
    private List<Message> historic;

    public String getRoomName() {
        return roomName;
    }

    public List<Message> getHistoric() {
        return historic;
    }

    private Object readResolve() {
        this.roomClients = new ArrayList<>();
        return this;
    }

    public Room(String roomName) {
        this.roomName = roomName;
        roomClients = new ArrayList<>();
        historic = new ArrayList<>();
    }

    public void addClient(ClientHandler clientHandler) {
        System.out.println(toString());
        roomClients.add(clientHandler);
        for (Message m : historic) {
            Response response = new Response("501", m);
            clientHandler.sendResponse(response);
        }
    }

    public void removeClient(ClientHandler client) {
        roomClients.remove(client);
    }

    public void broadcastMessage(Message message) {
        historic.add(message);
        Response response = new Response("501", message);
        for (ClientHandler roomClient : roomClients) {
            roomClient.sendResponse(response);
        }

    }

    @Override
    public String toString() {
        return "Room{" + "roomName=" + roomName + ", historic=" + historic + '}';
    }

}
