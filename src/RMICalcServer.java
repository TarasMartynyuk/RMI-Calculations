import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMICalcServer {

    public static final String STUB_ADRESS = "rmi://localhost/ABC";

    static final String LOCALHOST = "127.0.0.1";
    public static final int PORT = 6666;

//    private Registry _registry;

    public static void main(String[] args) {
        try {
            new RMICalcServer().serve();
        } catch (Exception e) {
            System.err.println("RmiComputableEngine exception:");
            e.printStackTrace();
        }
    }

    public void serve() throws RemoteException, MalformedURLException {
        // add security security manager

        System.setProperty("java.rmi.server.hostname", "localhost");

        var engine = new RmiComputableEngine();
        var stub = (RmiComputable) UnicastRemoteObject.exportObject(engine, 0);

//         _registry = LocateRegistry.getRegistry();

//         _registry.rebind(STUB_ADRESS, stub);
        Naming.rebind(STUB_ADRESS, stub);
        System.out.println("RmiComputableEngine bound");
    }
}
