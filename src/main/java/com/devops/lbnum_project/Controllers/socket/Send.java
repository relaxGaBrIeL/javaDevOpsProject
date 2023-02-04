package com.devops.lbnum_project.Controllers.socket;

import com.devops.lbnum_project.Controllers.Message;
import com.devops.lbnum_project.Controllers.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Send implements  Runnable{
    private final ObjectOutputStream out;
    private final Socket socket;
    public User getUser() {
        return user;
    }

    private final User user;

    public Send(Socket socket, ObjectOutputStream out, User user){
        this.socket = socket;
        this.out = out;
        this.user = user;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        while(isActive){
            try {
                System.out.println("Votre message >> ");
                String m= sc.nextLine();
                Message message = new Message(m, user.getFname());
                if(Objects.equals(m, "bye") || Objects.equals(m, "exit")){
                    System.exit(0);
                }
                else{
                    out.writeObject(message);
                    out.flush();
                }
            } catch(IOException exception){
                isActive = false;
            }
        }
    }
}
