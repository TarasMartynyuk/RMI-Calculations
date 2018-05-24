import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMICalcClient {
    static final int PORT = 6666;
    static final String SERVER_ADRESS = "127.0.0.1";

    private RmiComputable _computeStub;


    public static void main(String[] ar) throws IOException {
        try {
            new RMICalcClient(PORT, SERVER_ADRESS).beARMICalcClient();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    protected RMICalcClient(int serverPort, String serverAddress) throws IOException {
    }

    private void beARMICalcClient() throws RemoteException, NotBoundException, MalformedURLException {
        doCalcOnRemote();
    }

    private void doCalcOnRemote() throws RemoteException, NotBoundException, MalformedURLException {
        // add security manager

//        var registry = LocateRegistry.getRegistry(SERVER_ADRESS, PORT);
        // gets stub only
        var comp = (RmiComputable) Naming.lookup(RMICalcServer.STUB_ADRESS);

        var task = new Worker();
        String pi = comp.executeTask(task);
        System.out.println(pi);
    }
}
