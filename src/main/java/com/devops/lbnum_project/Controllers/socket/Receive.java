package com.devops.lbnum_project.Controllers.socket;



import com.devops.lbnum_project.Controllers.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Receive implements Runnable{
    private final SocketConnection client;
    private final Socket socket;

    public Receive(SocketConnection client, Socket socket){
        this.client = client;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
            boolean isActive = true;
            while(isActive){
                Message mess = (Message) in.readObject();
                if(mess != null){
                   // this.client.messageReceived(mess);
                }
                else{
                    isActive = false;
                }
            }
            this.client.disconnectedServer();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
