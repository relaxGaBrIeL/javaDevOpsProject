import com.devops.lbnum_project.Controllers.socket.SocketConnection;

import java.io.IOException;

public class TestClient {
    public static void main(String[] args) {
        try {

            String address = "127.0.0.1";

            int port = Integer.parseInt("1025");
            SocketConnection c = new SocketConnection();
            System.out.println("java client.Client <address> <port>");
            System.out.println("\t<address>: server's ip address");
            System.out.println("\t<port>: server's port");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
