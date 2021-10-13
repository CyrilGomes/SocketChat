/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cyril
 */
public class Message implements Serializable {
    public String content;
    public String author;
    public Date date;
}
