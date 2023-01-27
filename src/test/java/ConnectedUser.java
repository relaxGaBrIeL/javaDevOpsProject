import java.io.IOException;

public class ConnectedUser implements Runnable {
    private final Connection connection;
    private final String username;

    ConnectedUser(Connection connection, String username) {
        this.connection = connection;
        this.username = username;
    }

    void send(Object data) throws IOException {
        connection.send(data);
    }

    Object receive() throws IOException, ClassNotFoundException {
        return connection.receive();
    }

    void close() throws IOException {
        connection.close();
    }

    String getUsername() {
        return username;
    }

    @Override
    public void run() {

    }
}
