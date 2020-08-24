package com.wilson;

public class Vileplume extends Pokemon {
    private final Absorb absorb;
    private final StunSpore stunSpore;
    private final HyperBeam hyperBeam;
    private final Sleep sleep;

    public Vileplume(String name, String type, int level, int health, int maxHealth, String status, Absorb absorb, StunSpore stunSpore, HyperBeam hyperBeam, Sleep sleep) {
        super(name, type, level, health, maxHealth, status);
        this.absorb = absorb;
        this.stunSpore = stunSpore;
        this.hyperBeam = hyperBeam;
        this.sleep = sleep;
    }

    public Absorb getAbsorb() {
        return absorb;
    }

    public StunSpore getStunSpore() {
        return stunSpore;
    }

    public HyperBeam getHyperBeam() {
        return hyperBeam;
    }

    public Sleep getSleep() {
        return sleep;
    }

    public void restoreHealth(){
        this.setHealth(this.getHealth() + absorb.getHeal());
    }
}

class Absorb extends Attack {
    // Initializes Absorb Attack
    private int heal;
    public Absorb(int damage, int remaining, int maxRemains, int heal) {
        super(damage, remaining, maxRemains);
        this.heal = heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            this.setHeal(this.getDamage());
            return this.getDamage();
        }

    }
}

class StunSpore extends Attack{

    public StunSpore(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            // Chance to stun opponent
            return this.getDamage();

        }
    }
}

class HyperBeam extends Attack{

    public HyperBeam(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, damage is halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Ground") || type.equals("Water")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if (type.equals("Flying") || type.equals("Fire")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            return this.getDamage() * 2;
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class Sleep extends Attack{

    public Sleep(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            // Chance to sleep opponent
            return this.getDamage();

        }
    }
}