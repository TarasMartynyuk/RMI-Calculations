import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.function.Function;

public class RMICalcClient {

    private UserInputRetriever _userInputRetriever;

    public static void main(String[] ar) throws IOException {
        try {
            new RMICalcClient().beARMICalcClient();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public RMICalcClient() {
        _userInputRetriever = new UserInputRetriever();
    }

    public void beARMICalcClient() throws IOException, NotBoundException {

        var func = getFunctionFromUser();
        int a = 0;
        int b = 1;

        double res = calculateIntegralRemotely(func, a, b);
        System.out.println("and the result is: " + res);
    }

    private double calculateIntegralRemotely(
            SerializableFunction<Double, Double> f, double a, double b)
            throws RemoteException, NotBoundException, MalformedURLException {
        // add security manager

//        Runnable lambda = f;
        // gets stub only
        var integralCalulable = (RemoteIntegralCalulable) Naming.lookup(RMICalcServer.STUB_ADRESS);

        return integralCalulable.integrate(f, a, b);
    }

    private SerializableFunction<Double, Double> getFunctionFromUser() throws IOException {

        return _userInputRetriever.GetValueFromUser(
                "Quick! Sine or Cosine?!",
                this::parseUserInputAsFunctionName,
                "Didn't get that. Try again:"
                );
    }

    private SerializableFunction<Double, Double> parseUserInputAsFunctionName(String userLine) {

        if(userLine.equals("s") || userLine.toLowerCase().contains("sin")) {
            return (Math::sin);

        } else if (userLine.equals("c") || userLine.toLowerCase().contains("cos")) {
            return Math::cos;

        } else {
            return null;
        }
    }
}
