import java.util.Scanner;
import java.util.function.Function;

public class UserInputRetriever {
    private Scanner _scanner;

    public UserInputRetriever() {
        _scanner = new Scanner(System.in);
    }

    /**
     * @param askPrompt - printed before we let user type input
     * @param parser - function that returns the end value depending on user input,
     *               or null if the input is not valid
     * @param wrongInputPrompt - printed every time user enters invalid input
     */
    public <TValue> TValue GetValueFromUser(String askPrompt,
                                    Function<String, TValue> parser,
                                    String wrongInputPrompt) {
        System.out.println(askPrompt);
        TValue res = parser.apply(_scanner.nextLine());

        while(res == null)
        {
            System.out.println(wrongInputPrompt);
            System.out.println(askPrompt);

            var line =  _scanner.nextLine();
            res = parser.apply(line);
        }

        return res;
    }
}
