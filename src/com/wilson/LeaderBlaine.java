package com.wilson;

import java.util.Hashtable;

public class LeaderBlaine extends Leader {
    private final Flareon flareon;
    private final Rapidash rapidash;
    private final Growlithe growlithe;

    public LeaderBlaine(Hashtable<String, Integer> bag, Flareon flareon, Rapidash rapidash, Growlithe growlithe) {
        super(bag);
        this.flareon = flareon;
        this.rapidash = rapidash;
        this.growlithe = growlithe;
    }

    public Flareon getFlareon() {
        return flareon;
    }

    public Rapidash getRapidash() {
        return rapidash;
    }

    public Growlithe getGrowlithe() {
        return growlithe;
    }
}
