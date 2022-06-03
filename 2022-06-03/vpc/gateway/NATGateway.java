package vpc.gateway;

import vpc.subnet.Subnet;

public final class NATGateway extends Gateway {
    private Subnet subnet;

    public NATGateway(Subnet subnet) {
        super();
        this.subnet = subnet;
    }

    @Override
    public void send(String msg) {
        subnet.transfer(msg);
    }
}