package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Tangela extends Pokemon {
    private final PoisionPowder poisionPowder;
    private final Megadrain megadrain;
    private final Slam slam;
    private final Constrict constrict;

    public Tangela(String name, String type, int level, int health, int maxHealth, String status, PoisionPowder poisionPowder, Megadrain megadrain, Slam slam, Constrict constrict) {
        super(name, type, level, health, maxHealth, status);
        this.poisionPowder = poisionPowder;
        this.megadrain = megadrain;
        this.slam = slam;
        this.constrict = constrict;
    }

    public PoisionPowder getPoisionPowder() {
        return poisionPowder;
    }

    public Megadrain getMegadrain() {
        this.restoreHealth();
        return megadrain;
    }

    public Slam getSlam() {
        return slam;
    }

    public Constrict getConstrict() {
        return constrict;
    }

    public void restoreHealth(){
        this.setHealth(this.getHealth() + megadrain.getHeal());
    }

}

class PoisionPowder extends Attack{
    // initializes PoisonPowder attack
    int pokemonStatus = new PokemonStatus().PoisonChance();
    public PoisionPowder(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Has 20% chance of
        // poisoning opponent. Returns hashmap with damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 3){
            status = "Poisoned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), status);
        }
        return moveResult;
    }
}

class Megadrain extends Attack {
    private int heal;

    // initializes Megadrain attack
    public Megadrain(int damage, int remaining, int maxRemains, int heal) {
        super(damage, remaining, maxRemains);
        this.heal = heal;
    }


    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap of damage
        // and status

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            this.setHeal(this.getDamage());
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }

    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}

class Slam extends Attack {

    // initializes Slam attack
    public Slam(int damage, int remaining, int maxRemains) {
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

class Constrict extends Attack {

    // initializes Constrict attack
    public Constrict(int damage, int remaining, int maxRemains) {
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
