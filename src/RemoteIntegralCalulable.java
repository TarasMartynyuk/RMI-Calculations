import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Function;

public interface RemoteIntegralCalulable extends Remote {

    double integrate(Function<Double, Double> f, double a, double b) throws RemoteException;
}
