package com.wilson;

import java.util.Hashtable;

public class LeaderSurge extends Leader {
    private final Pikachu pikachu;
    private final Electabuzz electabuzz;
    private Voltorb voltorb;

    public LeaderSurge(Hashtable<String, Integer> bag, Pikachu pikachu, Electabuzz electabuzz, Voltorb voltorb) {
        super(bag);
        this.pikachu = pikachu;
        this.electabuzz = electabuzz;
        this.voltorb = voltorb;
    }


    public Pikachu getPikachu() {
        return pikachu;
    }

    public Electabuzz getElectabuzz() {
        return electabuzz;
    }

    public Voltorb getVoltorb() {
        return voltorb;
    }
}
