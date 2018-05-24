import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMICalcServer {

    public static final String REGISTRY_NAME = "Compute";
    public static final int PORT = 6666;

    private ServerSocket _serverSocket;

    public static void main(String[] args) {
        try {
            new RMICalcServer().serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public RMICalcServer() throws IOException {
        _serverSocket = new ServerSocket(PORT);
    }

    public void serve()
    {
        // add security security manager

        try {
            var engine = new ComputeEngine();
            var stub =
                    (Compute) UnicastRemoteObject.exportObject(engine, 0);

            var registry = LocateRegistry.getRegistry();
            registry.rebind(REGISTRY_NAME, stub);
            System.out.println("ComputeEngine bound");

        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
