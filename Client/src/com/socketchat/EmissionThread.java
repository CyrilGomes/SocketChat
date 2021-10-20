/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socketchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyril
 */
public class EmissionThread extends Thread {

    private Socket clientSocket = null;
    PrintWriter socOut = null;
    String message;


    EmissionThread(PrintWriter socOut, String message) {

        this.message = message;

        try {
            this.socOut = socOut;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        socOut.println(message);
        socOut.flush();
    }

}
