package vpc.routingtable;

import vpc.gateway.Gateway;
import vpc.subnet.Subnet;

import java.util.ArrayList;
import java.util.List;

public class RoutingTable {
    private static int sequence = 1;

    private int id;
    private Gateway gateway;
    private List<Subnet> subnetList = new ArrayList<>();

    public RoutingTable() {
        id = ++sequence;
    }

    public void addSubnet(Subnet subnet) {
        subnetList.add(subnet);
        subnet.setRoutingTable(this);
    }

    public void route(String msg) {
        gateway.send(msg);
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
}