package com.devops.lbnum_project.Controllers.socket;

import com.devops.lbnum_project.Controllers.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Send implements  Runnable{
    private final ObjectOutputStream out;

    public Send(Socket socket, ObjectOutputStream out){
        this.out = out;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        while(isActive){
            try {
                System.out.println("Votre message >> ");
                String m= sc.nextLine();
                Message message = new Message(m,"client");
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
