package com.irieosi.skynetep1;

import java.io.Serializable;

public class Link implements Serializable {

    private final int origin;

    private final int destination;

    public Link(int origin, int destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return origin + " " + destination;
    }
}
