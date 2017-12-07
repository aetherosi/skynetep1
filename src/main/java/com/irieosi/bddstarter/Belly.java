package com.irieosi.bddstarter;

public class Belly {

    private int cukes;

    private int digestionTime;

    public void eat(int cukes) {
        this.cukes = cukes;
    }

    public void digest(Integer digestionTime) {
        this.digestionTime = digestionTime;
    }

    public String growl() {
        return "GRRRRR";
    }
}