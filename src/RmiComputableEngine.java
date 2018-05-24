public class RmiComputableEngine implements RmiComputable {

    public RmiComputableEngine() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }
}