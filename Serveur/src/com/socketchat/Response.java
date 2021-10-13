/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import java.io.Serializable;

/**
 *
 * @author Cyril
 */
public class Response {
    public String status;
    public Serializable payload;

    public Response(String status, Serializable payload) {
        this.status = status;
        this.payload = payload;
    }


}
