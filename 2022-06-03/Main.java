import vpc.gateway.Gateway;
import vpc.gateway.InternetGateway;
import vpc.gateway.NATGateway;
import vpc.routingtable.RoutingTable;
import vpc.subnet.Subnet;

public class Main {
    public static void main(String[] args) {
        Subnet subnetA = new Subnet("ap-northeast-2a", "172.31.0.0/20");
        Subnet subnetB = new Subnet("ap-northeast-2b", "172.31.16.0/20");
        Subnet subnetC = new Subnet("ap-northeast-2c", "172.31.32.0/20");
        Subnet subnetD = new Subnet("ap-northeast-2d", "172.31.48.0/20");

        Gateway internetGateway = new InternetGateway();
        RoutingTable publicRoutingTable = new RoutingTable();

        publicRoutingTable.setGateway(internetGateway);
        publicRoutingTable.addSubnet(subnetA);
        publicRoutingTable.addSubnet(subnetB);
        publicRoutingTable.addSubnet(subnetC);

        Gateway natGateway = new NATGateway(subnetD);
        RoutingTable privateRoutingTable = new RoutingTable();

        privateRoutingTable.setGateway(natGateway);
        privateRoutingTable.addSubnet(subnetD);

        subnetA.transfer("Send message from subnet A");
        subnetD.transfer("Send message from subnet D");
    }
}