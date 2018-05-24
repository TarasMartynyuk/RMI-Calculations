public class Worker implements Task<String> {
    @Override
    public String execute() {
        return "Now Your head's lying somewhere away from your shoulders!";
    }
}
