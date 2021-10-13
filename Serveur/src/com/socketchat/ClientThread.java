/** *
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */
package com.socketchat;

import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.Date;

public class ClientThread
        extends Thread {

    private Socket clientSocket;

    ClientThread(Socket s) {
        this.clientSocket = s;
    }

    /**
     * receives a request from client then sends an echo to the client
     *
     * @param clientSocket the client socket
  	*
     */
    public void run() {
        try {
            BufferedReader socIn = null;
            socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
            Boolean isRunning = true;
            while (isRunning) {
                String line = socIn.readLine();
                System.out.println("LLALALALA");
                if(line == null){
                    socIn.close();
                    socOut.close();
                    clientSocket.close();
                    System.out.println("Déconnexion d'un utilisateur");
                    isRunning = false;
                }
                else{
                    System.out.println(line);
                    Message recievedMessage = new Gson().fromJson(line, Message.class );
                    Message message = new Message(recievedMessage.content,"SERVEUR",new Date());
                    Response response = new Response("501",message);
                    
                    String serializedResponse = new Gson().toJson(response);
                    socOut.println(serializedResponse);
                    
                }
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }

}
