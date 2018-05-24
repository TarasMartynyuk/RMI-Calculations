import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.function.Function;

public class RemoteIntegralCalculator extends UnicastRemoteObject implements RemoteIntegralCalulable {

    static final double EPS = 0.0001;

    //region UnicastRemoteObject ctors
    protected RemoteIntegralCalculator() throws RemoteException {
    }

    protected RemoteIntegralCalculator(int port) throws RemoteException {
        super(port);
    }

    protected RemoteIntegralCalculator(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }//endregion

    public double integrate(Function<Double, Double> f, double a, double b) {
        return slowSimpson(f, a, b, EPS);
    }

    private static double slowSimpson(Function<Double, Double> f,
                                      double a, double b, double eps) {
        int n = 2;
        double h = (b - a) * 0.5;
        double ss;

        double s = h * (f.apply(a) + f.apply(a + h) + f.apply(b));

        do {
            ss = s;
            n *= 2;
            h /= 2;
            s = f.apply(a) + 4 * f.apply(a + h) + f.apply(b);
            for (int i = 2; i < n; i = i + 2) {
                s = s + 2 * f.apply(a + i * h) + 4 * f.apply(a + (i + 1) * h);
            }
            s = s * h;
        } while (Math.abs(s - ss) > eps);

        return s / 3;
    }
}
