package com.irieosi.skynetep1;

import java.io.Serializable;

public class Link implements Serializable {

    private final int node1;

    private final int node2;

    public Link(int node1, int node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    @Override
    public String toString() {
        return node1 + " " + node2;
    }
}
