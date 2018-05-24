import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICalcClient extends ClientBase {
    static final int port = 6666;
    static final String serverAddress = "127.0.0.1";


    public static void main(String[] ar) throws IOException {
        new RMICalcClient(port, serverAddress).beARMICalcClient();

    }

    protected RMICalcClient(int serverPort, String serverAddress) throws IOException {
        super(serverPort, serverAddress);
    }

    private void beARMICalcClient() {
    }

    private void doCalcOnRemote() throws RemoteException, NotBoundException {

        // add security security manager

        var registry = LocateRegistry.getRegistry(RMICalcServer.PORT);
        var comp = (Compute) registry.lookup(RMICalcServer.REGISTRY_NAME);

        var task = new Worker();
        String pi = comp.executeTask(task);
        System.out.println(pi);
    }

}
