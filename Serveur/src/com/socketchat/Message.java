/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import java.io.Serializable;
import java.util.Date;

/**
 * Informations d'un message
 */
public class Message implements Serializable {
    /**
     * Type de message
     */
    private String type;
    
    /**
     * Contenu du message
     */
    private String content;
    
    /**
     * Auteur du message
     */
    private String author;
    
    /**
     * Destinataire du message
     */
    private String dest;
    
    /**
     * Date de génération du message
     */
    private Date date;

    /**
     * 
     * @return Type du message
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type Type du message
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return Nom de l'utilisateur destinataire
     */
    public String getDest() {
        return dest;
    }

    /**
     * 
     * @param dest Nom de l'utilisateur destinataire
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

    /**
     * 
     * @return Contenu du message
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content Contenu du message
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return Auteur du message
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author Auteur du message
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return Date de génération du message
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date Date de génération du message
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     * @param content Contenu du message
     * @param author Nom de l'utilisateur qui envoie le message
     * @param date Date de génération du message
     */
    public Message(String content, String author, Date date) {
        this.content = content;
        this.author = author;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" + "type=" + type + ", content=" + content + ", author=" + author + ", dest=" + dest + ", date=" + date + '}';
    }
    
}
