import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMICalcServer {

    public static final String STUB_ADRESS = "rmi://localhost/ABC";

    private RemoteIntegralCalulable _integralCalulable;

    public static void main(String[] args) throws RemoteException {

//        double res =  new RemoteIntegralCalculator().integrate(Math::sin, 0, 1);

//        System.out.println(res);

        try {
            new RMICalcServer().serve();
        } catch (Exception e) {
            System.err.println("RmiComputableEngine exception:");
            e.printStackTrace();
        }
    }

    public void serve() throws RemoteException, MalformedURLException {
        // add security manager

        System.setProperty("java.rmi.server.hostname", "localhost");

        RemoteIntegralCalulable calc_stub = new RemoteIntegralCalculator();

        Naming.rebind(STUB_ADRESS, calc_stub);
        System.out.println("RemoteIntegralCalculator stub registered");
    }
}
