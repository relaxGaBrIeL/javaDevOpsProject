import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;

    Connection(Socket socket) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    void send(Object data) throws IOException {
        output.writeObject(data);
    }

    Object receive() throws IOException, ClassNotFoundException {
        return input.readObject();
    }

    void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

}
