package com.devops.server;

import com.devops.lbnum_project.Controllers.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ConnectedUser implements Runnable {

    private static int idCounter;
    private int id;
    private final Socket socket;
    private final ObjectOutputStream out;
    private ObjectInputStream in;
    private final Server server;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConnectedUser(Server server, Socket socket) throws IOException {
        this.id = idCounter;
        this.server = server;
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("Nouvelle connexion, id = " + this.getId());
        idCounter++;
    }

    /**
     * Allow to send a message
     * */
    public Message sendMessage(Message mess) throws IOException {
        this.out.writeObject(mess);
        this.out.flush();
        return mess;
    }

    /**
     * Close all input and output and Socket
     * */
    public void closeClient() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }

    @Override
    public void run() {
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            boolean isActive = true;

            while(isActive){
                Message mess = (Message)in.readObject();
                if(mess != null){
                    mess.setSender(String.valueOf(getId()));
                    server.broadcastMessage(mess,getId());
                }
                else{
                    isActive = false;
                    server.disconnectedClient(this);
                }
            }
        } catch (SocketException | EOFException exception){
            try {
                server.disconnectedClient(this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
