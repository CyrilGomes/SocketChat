/** *
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */
package com.socketchat;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe d'exécution principale du serveur
 */
public class EchoServerMultiThreaded {

    /**
     * Liste des salons
    */
    public static Map<String, Room> rooms;

    /**
     * Liste des threads actifs associés aux clients connectés
     */
    public static List<ClientHandler> connectedClients;

    /**
     * Chemin du fichier log où l'historique sera enregistré
     */
    private static String logFilePath = "./logs.txt";

    /**
     * sauvegarde l'historique des conversations des salons
     * @throws IOException
     */
    public static void saveRooms() throws IOException {
        //System.out.println("SAVING");

        File yourFile = new File(logFilePath);
        yourFile.createNewFile();
        FileOutputStream file = new FileOutputStream(logFilePath);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(rooms);

        out.close();
        file.close();

    }
    
    /**
     * restaure l'historique des conversations des salons
     * @throws IOException
     */
    public static void restoreRooms() throws IOException {
        FileInputStream file = new FileInputStream(logFilePath);
        ObjectInputStream in = new ObjectInputStream(file);

        try {
            rooms = (Map<String, Room>) in.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EchoServerMultiThreaded.class.getName()).log(Level.SEVERE, null, ex);
        }

        in.close();
        file.close();

    }

    /**
     * main method
     */
    public static void main(String args[]) {
        
        //ajoute une sorte d'evenement qui détecte la fin du serveur, appelle la sauvegarde de l'historique
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                try {
                    saveRooms();
                } catch (IOException ex) {
                    Logger.getLogger(EchoServerMultiThreaded.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        try {
            restoreRooms();
        } catch (IOException ex) {
            Logger.getLogger(EchoServerMultiThreaded.class.getName()).log(Level.SEVERE, null, ex);
        }

        ServerSocket listenSocket;
        connectedClients = new ArrayList<>();
        if (rooms == null) {
            rooms = new HashMap<>();
        }

        if (args.length != 1) {
            //System.out.println("Usage: java EchoServer <EchoServer port>");
            System.exit(1);
        }
        try {
            listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
            //System.out.println("Server ready...");
            
            while (true) {
                Socket clientSocket = listenSocket.accept();
                //System.out.println("Connexion from:" + clientSocket.getInetAddress());
                ClientHandler currClient = new ClientHandler(clientSocket); //Thread qui gère le client connecté
                connectedClients.add(currClient);
                Thread ct = new Thread(currClient);
                ct.start();
            }
        } catch (Exception e) {
            //System.err.println("Error in EchoServer:" + e);
        }
    }
}
