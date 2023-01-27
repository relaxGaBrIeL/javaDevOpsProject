import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {
    private ArrayList<ConnectedUser> connectedUsers;
    private ServerSocket serverSocket;

   public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        connectedUsers = new ArrayList<>();
    }

    void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                String username = (String) connection.receive();
                ConnectedUser connectedUser = new ConnectedUser(connection, username);
                connectedUsers.add(connectedUser);
                new Thread(new HandleClient(connectedUser)).start();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public int getNbUsersConnected(){
       return this.connectedUsers.size();
    }
    void broadcast(Object data, ConnectedUser sender) {
        for (ConnectedUser connectedUser : connectedUsers) {
            if (!connectedUser.equals(sender)) {
                try {
                    connectedUser.send(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Disconnecte user
     * @param connectedUser
     */
    void removeUser(ConnectedUser connectedUser) {
        //envoyer un signal broadcast
        //Fermé le flux
        connectedUsers.remove(connectedUser);
    }

    class HandleClient implements Runnable {
        private final ConnectedUser connectedUser;

        HandleClient(ConnectedUser connectedUser) {
            this.connectedUser = connectedUser;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Object data = connectedUser.receive();
                    broadcast(data, connectedUser);
                } catch (IOException | ClassNotFoundException e) {
                    try {
                        connectedUser.close();
                        removeUser(connectedUser);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
