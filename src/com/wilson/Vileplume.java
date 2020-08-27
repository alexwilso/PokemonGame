package com.wilson;

import java.util.HashMap;
import java.util.Map;

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

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap of
        // damage and status

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            this.setHeal(this.getDamage());
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;

    }
}

class StunSpore extends Attack{
    int pokemonStatus = new PokemonStatus().ParalyzeChance();
    public StunSpore(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 1){
            status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
            return moveResult;

        }
    }
}

class HyperBeam extends Attack{

    public HyperBeam(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, damage is halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Rock") || type.equals("Water")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Flying") || type.equals("Fire")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }
    }
}

class Sleep extends Attack{
    int pokemonStatus = new PokemonStatus().SleepChance();
    public Sleep(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 1){
            status = "Asleep";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
        }
        return moveResult;
    }
}