/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de gestion du thread de réception des messages du serveur
 */
public class RecieveThread extends Thread {

    private Socket clientSocket;
    private BufferedReader socIn = null;
    EchoClientGUI clientGui;

    RecieveThread(BufferedReader socIn, EchoClientGUI clientGui) {

        try {
            this.socIn = socIn;
            this.clientGui = clientGui;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * logique de réception des messages avec désérialsation
     *
     */
    public void run() {

        String line;
        while (!this.isInterrupted()) {
            try {

                line = socIn.readLine();

                if (line.isEmpty()) {
                    break;
                }
                Gson gson = new Gson();
                JsonObject convertedObject = new Gson().fromJson(line, JsonObject.class);

                Message message = gson.fromJson(convertedObject.get("payload"), Message.class);
                clientGui.addMessage(message.getAuthor(), message.getDate(), message.getContent());
            } catch (IOException ex) {
                //Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
    }

}
