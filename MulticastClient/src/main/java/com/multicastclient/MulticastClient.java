/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicastclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author creep
 */
public class MulticastClient {

    static MulticastSocket socket;
    static int port;
    static InetAddress host;
    static ReceiveThread receiveThread;
    public static String nomUtilisateur;

    public static void main(String args[]) {
        if (args.length < 1 || args.length > 1) {
            System.out.println("MulticastClient <port>");
        } else {
            try {
                port = Integer.parseInt(args[0]);
                Boolean isRunning = true;
                Scanner s = new Scanner(System.in);
                System.out.println("Entrez un nom d'utilisateur:");
                nomUtilisateur = s.nextLine();
                while (isRunning) {
                    if (socket == null || socket.isClosed()) {
                        System.out.println("Entrez l'adresse du groupe à rejoindre:");
                        String hostName = s.nextLine();
                        host = InetAddress.getByName(hostName);
                        socket = new MulticastSocket(port);
                        socket.joinGroup(host);
                        receiveThread = new ReceiveThread(socket);
                        receiveThread.start();
                        System.out.println("---------------------------");
                        System.out.println("VOTRE CHAT ICI");
                        System.out.println("---------------------------");
                    } else {
                        System.out.print(">");
                        String msg = s.nextLine();
                        if (msg.equals("/stop")) {
                            socket.leaveGroup(host);
                            receiveThread.interrupt();
                            receiveThread.join();
                            socket.close();
                        } else {
                            byte[] byteArray = msg.getBytes();
                            DatagramPacket datagram = new DatagramPacket(byteArray, byteArray.length, host ,port);
                            socket.send(datagram);
                        }
                    }
                }
                s.close();
            } catch (UnknownHostException ex) {
                Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
