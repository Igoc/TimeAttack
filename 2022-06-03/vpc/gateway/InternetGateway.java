package vpc.gateway;

public final class InternetGateway extends Gateway {
    public InternetGateway() {
        super();
    }

    @Override
    public void send(String msg) {
        System.out.println(msg);
    }
}