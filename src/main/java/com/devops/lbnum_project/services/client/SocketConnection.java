package com.devops.lbnum_project.services.client;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class SocketConnection {

    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    //region Getter / Setter

    public SocketConnection() throws IOException {
        //region Attributes
        int port = 1025;
        String address = "127.0.0.1";
        this.socket = new Socket(address, port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }
    public void sendMessageToServer(String messageToSend) {
        try {
            out.writeObject(messageToSend);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erreur d'envoi de message au client");
            closeEverything(socket,out,in);
        }
    }
    public void receiveMessageFromServer(VBox vbox) {
        new Thread(() -> {
            while (socket.isConnected()){
                try {
                    String  messageFromServer = (String) in.readObject();
                    ClientController.addLabel(messageFromServer,vbox);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erreur de reception du serveur ");
                    closeEverything(socket,out,in);
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
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
    public void closeEverything(Socket socket, OutputStream out, InputStream in) {
        try{
            if (socket != null)
                socket.close();
            if (out != null)
                out.close();
            if (in != null)
                in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
