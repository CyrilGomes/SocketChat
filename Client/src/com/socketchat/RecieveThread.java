/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyril
 */
public class RecieveThread extends Thread {

    private Socket clientSocket;
    private BufferedReader socIn = null;

    RecieveThread(Socket s) {

        this.clientSocket = s;

        try {
            // creation socket ==> connexion
            socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * receives a request from client then sends an echo to the client
     *
     * @param recieveSocket the client socket
     *
     */
    public void run() {

        String line;
        while (true) {
            try {
                line = socIn.readLine();
                if (line.isEmpty()) {
                    break;
                }
                EchoClient.recievedMessage(line);
            } catch (IOException ex) {
                Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void close() throws IOException {
        socIn.close();

    }
}
