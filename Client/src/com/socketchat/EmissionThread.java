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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyril
 */
public class EmissionThread extends Thread {

    private Socket clientSocket;
    PrintStream socOut = null;
    String message;

    EmissionThread(Socket s, String message) {

        this.clientSocket = s;
        this.message = message;

        try {
            socOut = new PrintStream(clientSocket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * receives a request from client then sends an echo to the client
     *
     * @param emissionSocket the client socket
     *
     */
    public void run() {
        socOut.println(message);
    }

    public void close() throws IOException {
        socOut.close();

    }
}
