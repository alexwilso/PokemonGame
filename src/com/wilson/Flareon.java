package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Flareon extends Pokemon {
    private final FlameCharge flameCharge;
    private final SuperPower superPower;
    private final Toxic toxic;
    private final FirePunch firePunch;

    public Flareon(String name, String type, int level, int health, int maxHealth, String status, FlameCharge flameCharge, SuperPower superPower, Toxic toxic, FirePunch firePunch) {
        super(name, type, level, health, maxHealth, status);
        this.flameCharge = flameCharge;
        this.superPower = superPower;
        this.toxic = toxic;
        this.firePunch = firePunch;
    }
}

class FlameCharge extends Attack {
    // Initializes FlameCharge attack
    PokemonStatus pokemonStatus;
    String status;

    public FlameCharge(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        /*
        / Carries out attack. If type Grass, damage is doubled. If type Water or Rock, damage is halved. Subtracts 1
        / from remaining unless remaining is 0 then returns 0. Returns hashmap containing damage and status. Has a 1/5
        / chance of burning enemy.
         */

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(pokemonStatus.BurnChance() == 3){
            this.status = "Burned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult;
        } else if (type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
            moveResult.put(this.getDamage() / 2,this.status);
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
            return moveResult;
        }
    }
}

class SuperPower extends Attack {
    // Initializes SuperPower attack
    public SuperPower(int damage, int remaining, int maxRemains) {
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

class Toxic extends Attack{
    // Initializes Toxic attack
    PokemonStatus pokemonStatus;
    String status;

    public Toxic(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public  Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Has 20% chance of
        // poisoning opponent. Returns hashmap with damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(pokemonStatus.PoisonChance() == 3){
            this.status = "Poisoned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
        }
        return moveResult;
    }
}

class FirePunch extends Attack {
    // Initializes FirePunch attack
    public FirePunch(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public Map<Integer, String> attack(String type){
        // Carries out attack. If type == Grass, then damage is doubled. If type == Rock, the
        // damage is halved. Subtracts 1 from remaining unless remaining is 0 then returns 0. Returns hashmap containing
        // damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }
    }
}