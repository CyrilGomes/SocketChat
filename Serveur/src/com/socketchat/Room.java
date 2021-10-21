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
 * Classe de gestion d'un salon de discussion
 */
public class Room implements Serializable {
    /**
     * Liste des threads correspondants aux clients connectés au salon
     */
    private transient List<ClientHandler> roomClients;
    
    /**
     * Nom du salon
     */
    private String roomName;
    
    /**
     * Liste des messages envoyés dans le salon
     */
    private List<Message> historic;

    /**
     * 
     * @return Nom du salon
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 
     * @return Liste des messages du salon
     */
    public List<Message> getHistoric() {
        return historic;
    }

    /**
     * permet de créer une array list lors de la désérialization
     * @return Un array list
    */
    private Object readResolve() {
        this.roomClients = new ArrayList<>();
        return this;
    }

    /**
     * 
     * @param roomName Nom du salon
     */
    public Room(String roomName) {
        this.roomName = roomName;
        roomClients = new ArrayList<>();
        historic = new ArrayList<>();
    }

    /**
     *  ajoute le client dans le salon
     * @param clientHandler Thread du client à ajouter
     */
    public void addClient(ClientHandler clientHandler) {
        //System.out.println(toString());
        roomClients.add(clientHandler);
        for (Message m : historic) {
            Response response = new Response("501", m);
            clientHandler.sendResponse(response);
        }
    }

    /**
     *  enleve le client du salon
     * @param client Thread du client à enlever
     */
    public void removeClient(ClientHandler client) {
        roomClients.remove(client);
    }

    /**
     * envoie un message à tous les clients connecté au salon
     * @param message Message à envoyer
     */
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
