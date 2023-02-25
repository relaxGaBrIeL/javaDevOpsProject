package com.devops.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private final List<ConnectedUser> users;
    private final int port;

    public int getPort() {
        return port;
    }

    public Server(int port) throws IOException {
        this.users = new ArrayList<>();
        this.port = port;

        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }

    /**
     * Return the number of connected clients
     * */
    public int getNumClients(){
        return this.users.size();
    }

    /**
     * Allow to add client to the list connected clients
     * */
    public void addClient(ConnectedUser client) throws IOException {
        this.users.add(client);
        String msg =  client.getId() +" vient de se connecter";
        this.broadcastMessage(msg,client.getId());
    }

    /**
     * Allow to send a message for all clients
     */
    public void broadcastMessage(String message, int id) throws IOException {
        for(ConnectedUser user : this.users){
            if(user.getId() != id){
                user.sendMessageToClient(message);
            }
        }
    }

    /**
     * For disconnect user of the server
     */
    public void disconnectedClient(ConnectedUser user) throws IOException {
        user.closeClient();
        this.users.remove(user);
        String msg =user.getId()+ " s'est déconnecté";
        this.broadcastMessage(msg,user.getId());
    }


}
