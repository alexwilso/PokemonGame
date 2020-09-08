package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Rapidash extends Pokemon {
    private final Stomp stomp;
    private final FireSpin fireSpin;
    private final FireBlast fireBlast;
    private final Bounce bounce;
    public Rapidash(String name, String type, int level, int health, int maxHealth, String status, Stomp stomp,
                    FireSpin fireSpin, FireBlast fireBlast,Bounce bounce) {
        super(name, type, level, health, maxHealth, status);
        this.stomp = stomp;
        this.fireSpin = fireSpin;
        this.fireBlast = fireBlast;
        this.bounce = bounce;
    }

    public Stomp getStomp() {
        return stomp;
    }

    public FireSpin getFireSpin() {
        return fireSpin;
    }

    public FireBlast getFireBlast() {
        return fireBlast;
    }

    public Bounce getBounce() {
        return bounce;
    }

    public boolean RapidashStatus(Rapidash rapidash){
        // If pokemon status anything other than normal, function is called. Returns true if rapidash cannot make move
        // and true if able to
        if (rapidash.getStatus().equals("Asleep")){
            if (rapidash.WakeUp()){
                rapidash.setStatus("Normal");
                System.out.println(rapidash.getName() + " woke up");
            } else {
                System.out.println(rapidash.getName() + " is asleep. Cannot make a move");
                return true;
            }} else if (rapidash.getStatus().equals("Burned")){
            System.out.println(rapidash.getName() + " is burned. Lost 10 health.");
            rapidash.Burn();
        } else if (rapidash.getStatus().equals("Poisoned")){
            System.out.println(rapidash.getName() + " is poisoned. Lost 10 health.");
            rapidash.Poisioned();
        } else  if (rapidash.getStatus().equals("Paralyzed")){
            if (rapidash.Paralyzed()){
                System.out.println(rapidash.getName() + " is paralyzed and cannot move");
                return true;
            }
        } else if (rapidash.getStatus().equals("Confused")){
            if (rapidash.Confusion()){
                System.out.println(rapidash.getName() + " is confused. Rapidash hurt itself and cannot make a move. Lost 10 health");
                return true;
            } else {
                System.out.println("Rapidash snapped out of confusion");
                rapidash.setStatus("Normal");
            }
        }
        return false;
    }
}

class Stomp extends Attack{
    public Stomp(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // integer damage and string status

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0){
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

class FireSpin extends Attack{
    PokemonStatus pokemonStatus;
    String status;
    public FireSpin(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
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
        if(this.pokemonStatus.BurnChance() == 3){
            this.status = "Burned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super Effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very Effective");
            moveResult.put(this.getDamage() / 2, this.status);
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
            return moveResult;
        }
    }
}

class FireBlast extends Attack{
    PokemonStatus pokemonStatus;
    String status;
    public FireBlast(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
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
        if(this.pokemonStatus.BurnChance() == 3){
            this.status = "Burned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super Effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very Effective");
            moveResult.put(this.getDamage() / 2, this.status);
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
            return moveResult;
        }
    }
}

class Bounce extends Attack {
    public Bounce(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // integer damage and string status

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0){
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