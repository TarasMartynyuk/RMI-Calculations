import java.io.Serializable;

public class Worker implements Task<String>, Serializable {
    @Override
    public String execute() {
        return "Now Your head's lying somewhere away from your shoulders!";
    }
}
