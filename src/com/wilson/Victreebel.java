package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Victreebel extends Pokemon{
    private final RazorLeaf razorLeaf;
    private final VineWhip vineWhip;
    private final Sleep sleep;
    private final SpitUp spitUp;

    public Victreebel(String name, String type, int level, int health, int maxHealth, String status, RazorLeaf razorLeaf, VineWhip vineWhip, Sleep sleep, SpitUp spitUp) {
        super(name, type, level, health, maxHealth, status);
        this.razorLeaf = razorLeaf;
        this.vineWhip = vineWhip;
        this.sleep = sleep;
        this.spitUp = spitUp;
    }

    public RazorLeaf getRazorLeaf() {
        return razorLeaf;
    }

    public VineWhip getVineWhip() {
        return vineWhip;
    }

    public Sleep getSleep() {
        return sleep;
    }

    public SpitUp getSpitUp() {
        return spitUp;
    }

}

class SpitUp extends Attack{
    //initializes spitup attack
    public SpitUp(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // integer damage and string status
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}


