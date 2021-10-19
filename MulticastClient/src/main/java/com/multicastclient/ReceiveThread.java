/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicastclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author creep
 */
public class ReceiveThread extends Thread {
    private final MulticastSocket socket;

    public ReceiveThread(MulticastSocket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        while(!this.isInterrupted()){
            try {
                byte[] buf = new byte[256];
                DatagramPacket datagram = new DatagramPacket(buf, 256);
                socket.receive(datagram);
                String msg = new String(buf);
                System.out.println(datagram.getAddress().getHostName() + " [" + new Date().toString() + "] " + msg);
            } catch (IOException ex) {
                Logger.getLogger(ReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
