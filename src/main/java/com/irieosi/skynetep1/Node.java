package com.irieosi.skynetep1;

public class Node {
    private final int id;

    private boolean isGateway;

    public Node(int id) {
        this.isGateway = false;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public boolean isGateway() {
        return isGateway;
    }

    public void setGateway(boolean gateway) {
        isGateway = gateway;
    }
}
