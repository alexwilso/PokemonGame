package com.wilson;

import java.util.Hashtable;

public class LeaderGiovanni extends Leader{
    private final Rhyhorn rhyhorn;
    private final Nidoqueen nidoqueen;
    private final Dugtrio dugtrio;

    public LeaderGiovanni(Hashtable<String, Integer> bag, Rhyhorn rhyhorn, Nidoqueen nidoqueen, Dugtrio dugtrio) {
        super(bag);
        this.rhyhorn = rhyhorn;
        this.nidoqueen = nidoqueen;
        this.dugtrio = dugtrio;
    }

    public Rhyhorn getRhyhorn() {
        return rhyhorn;
    }

    public Nidoqueen getNidoqueen() {
        return nidoqueen;
    }

    public Dugtrio getDugtrio() {
        return dugtrio;
    }
}
