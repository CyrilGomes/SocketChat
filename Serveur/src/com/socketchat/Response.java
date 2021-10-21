/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import java.io.Serializable;

/**
 * Informations d'une réponse
 */
public class Response {
    /**
     * Description du statut à communiquer
     */
    public String status;
    
    /**
     * Données à transmettre
     */
    public Serializable payload;

    /**
     * 
     * @param status Description du statut à communiquer
     * @param payload Données à transmettre
     */
    public Response(String status, Serializable payload) {
        this.status = status;
        this.payload = payload;
    }


}
