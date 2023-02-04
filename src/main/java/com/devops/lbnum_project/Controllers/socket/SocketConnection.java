package com.devops.lbnum_project.Controllers.socket;

import com.devops.lbnum_project.Controllers.Message;
import com.devops.lbnum_project.Controllers.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection {

    private final User user;
    //region Attributes
    private int port;
    private String address;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    //endregion

    //region Getter / Setter
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    //endregion

    public SocketConnection(User user) throws IOException {

        this.port = 1025;
        this.address = "127.0.0.1";
        this.socket = new Socket(address,port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.user = user;

        Thread threadClientReceive = new Thread(new Receive(this,this.socket));
        threadClientReceive.start();

        Thread threadClientSend = new Thread(new Send(this.socket,this.out, user));
        threadClientSend.start();

        System.out.println(user.getFname() +" Vous êtes Connected sur l'adresse : "+ this.address+"\n Sur le port : "+ this.port);
    }

    /**
     * Disconnected from the server
     * */
    public void disconnectedServer() throws IOException {
        if(this.in != null && this.out != null && this.socket != null){
            this.in.close();
            this.out.close();
            this.socket.close();
            System.exit(0);
        }
        else {
            System.out.println("disconnectedServer : Erreur les attributs in, out ou socket sont null");
        }
    }

    /**
     * Show message received
     * */
    public void messageReceived(Message message){
        System.out.println(message.toString());
    }
}
