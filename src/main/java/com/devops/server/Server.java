package com.devops.server;

import com.devops.lbnum_project.Controllers.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {


    private final List<ConnectedUser> users;
    private final int port;

    public int getPort() {
        return port;
    }

    public Server(int port) throws IOException {
        this.users = new ArrayList<ConnectedUser>();
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
        Message msg = new Message(String.format("%s vient de se connecter", client.getId()));
        this.broadcastMessage(msg,client.getId());
    }

    /**
     * Allow to send a message for all clients
     */
    public void broadcastMessage(Message message, int id) throws IOException {
        for(ConnectedUser user : this.users){
            if(user.getId() != id){
                user.sendMessage(message);
            }
        }
    }

    /**
     * For disconnect user of the server
     */
    public void disconnectedClient(ConnectedUser user) throws IOException {
        user.closeClient();
        this.users.remove(user);
        Message msg = new Message(String.format("%s s'est déconnecté",user.getId()), String.valueOf(user.getId()));
        this.broadcastMessage(msg,user.getId());
    }


}
