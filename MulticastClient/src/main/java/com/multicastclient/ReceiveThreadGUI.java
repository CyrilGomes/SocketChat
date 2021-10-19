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
public class ReceiveThreadGUI extends Thread {
    private final MulticastSocket socket;
    private final MulticastClientGUI gui;

    public ReceiveThreadGUI(MulticastSocket socket, MulticastClientGUI gui) {
        this.socket = socket;
        this.gui = gui;
    }
    
    @Override
    public void run(){
        while(!this.isInterrupted()){
            try {
                byte[] buf = new byte[256];
                DatagramPacket datagram = new DatagramPacket(buf, 256);
                socket.receive(datagram);
                String msg = new String(buf);
                gui.addMessage(msg);
            } catch (IOException ex) {
                Logger.getLogger(ReceiveThreadGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
