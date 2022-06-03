package vpc.subnet;

import vpc.routingtable.RoutingTable;

public class Subnet {
    private static int sequence = 1;

    private int id;
    private String region;
    private String cidr;
    private RoutingTable routingTable;

    public Subnet(String region, String cidr) {
        id = ++sequence;
        this.region = region;
        this.cidr = cidr;
    }

    public void transfer(String msg) {
        routingTable.route(msg);
    }

    public void setRoutingTable(RoutingTable routingTable) {
        this.routingTable = routingTable;
    }
}