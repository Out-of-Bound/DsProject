package Vertices;

import Graph.DirectedGraph;
public class Account extends DirectedGraph.Vertex {
    private String ownerSsn, bank_name ,account_id ; // id = iBan;

    public Account(String ownerSsn, String bank_name, String iBan, String account_id) {
        super(iBan);
        this.ownerSsn = ownerSsn;
        this.bank_name = bank_name;
        this.account_id = account_id;
    }

    public String getOwnerSsn() {
        return ownerSsn;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getAccount_id() {
        return account_id;
    }
}
