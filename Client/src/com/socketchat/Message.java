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

    private String type;
    private String content;
    private String author;
    private String dest;
    private Date date;

    public String getType() {
        return type;
    }

    private Message(String type, String content, String author, String dest, Date date) {
        this.type = type;
        this.content = content;
        this.author = author;
        this.dest = dest;
        this.date = date;
    }

    public static Message textMessage(String content, String author, String dest) {

        Message message = new Message("MSG", content, author, dest, new Date());

        return message;
    }

    public static Message joinRoomMessage(String author, String dest) {
        Message message = new Message("JROM", "", author, dest, new Date());
        return message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
