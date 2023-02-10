package com.devops.server;

public class MainServer {


    public static void main(String[] args) {
        int port = Integer.parseInt("1025");
        try {
            new Server(port);

            System.out.println("running on port " + port);
        } catch (Exception e) {
            System.out.println("java server.Server <port>");
            System.out.println("\t<port>: server's port");
        }
    }


}
