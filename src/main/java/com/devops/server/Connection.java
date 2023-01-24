package com.devops.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements  Runnable{

    private final Server server;
    private final ServerSocket serverSocket;

    public Connection(Server server) throws IOException {
        this.server = server;
        this.serverSocket = new ServerSocket(server.getPort());
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket sockNewUser = serverSocket.accept();
                ConnectedUser newClient = new ConnectedUser(server, sockNewUser);
                newClient.setId(server.getNumClients());
                server.addClient(newClient);

                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
