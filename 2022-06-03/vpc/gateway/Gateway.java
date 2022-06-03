package vpc.gateway;

public abstract class Gateway {
    private static int sequence = 1;

    protected int id;

    public Gateway() {
        id = ++sequence;
    }

    public abstract void send(String msg);
}