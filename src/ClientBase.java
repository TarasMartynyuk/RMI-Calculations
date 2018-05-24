import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public abstract class ClientBase {

    protected Socket _serverSocket;
    protected BufferedReader _consoleIn;

    protected ClientBase(int serverPort, String serverAddress) throws IOException {

        _serverSocket = new Socket(serverAddress, serverPort);
        _consoleIn = new BufferedReader(new InputStreamReader(System.in));
    }
}
