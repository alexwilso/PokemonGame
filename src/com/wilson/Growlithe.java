package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Growlithe extends Pokemon {
    private final FireFang fireFang;
    private final Bite bite;
    private final Roar roar;
    private final FlareBlitz flareBlitz;

    public Growlithe(String name, String type, int level, int health, int maxHealth, String status, FireFang fireFang,
                     Bite bite, Roar roar, FlareBlitz flareBlitz) {
        super(name, type, level, health, maxHealth, status);
        this.fireFang = fireFang;
        this.bite = bite;
        this.roar = roar;
        this.flareBlitz = flareBlitz;
    }

    public FireFang getFireFang() {
        return fireFang;
    }

    public Bite getBite() {
        return bite;
    }

    public Roar getRoar() {
        return roar;
    }

    public FlareBlitz getFlareBlitz() {
        return flareBlitz;
    }

    public boolean GrowlitheStatus(Growlithe growlithe){
        // If pokemon status anything other than normal, function is called. Returns true if growlithe cannot make move
        // and true if able to
        if (growlithe.getStatus().equals("Asleep")){
            if (growlithe.WakeUp()){
                growlithe.setStatus("Normal");
                System.out.println(growlithe.getName() + " woke up");
            } else {
                System.out.println(growlithe.getName() + " is asleep. Cannot make a move");
                return true;
            }} else if (growlithe.getStatus().equals("Burned")){
            System.out.println("Growlithe is burned. Lost 10 health.");
            growlithe.Burn();
        } else if (growlithe.getStatus().equals("Poisoned")){
            System.out.println("Growlithe is poisoned. Lost 10 health.");
            growlithe.Poisioned();
        } else  if (growlithe.getStatus().equals("Paralyzed")){
            if (growlithe.Paralyzed()){
                System.out.println("Growlithe is paralyzed and cannot move");
                return true;
            }
        } else if (growlithe.getStatus().equals("Confused")){
            if (growlithe.Confusion()){
                System.out.println("Growlithe is confused. Growlithe hurt itself and cannot make a move. Lost 10 health");
                return true;
            } else {
                System.out.println("Growlithe snapped out of confusion");
                growlithe.setStatus("Normal");
            }
        }
        return false;
    }

}

class FireFang extends Attack{

    public FireFang(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        /*
        / Carries out attack. If type Grass, damage is doubled. If type Water or Rock, damage is halved. Subtracts 1
        / from remaining unless remaining is 0 then returns 0. Returns hashmap containing damage and status. Has a 1/5
        / chance of burning enemy.
         */

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super Effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very Effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }
    }
}

class Bite extends Attack {
    public Bite(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status

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

class Roar extends Attack {
    public Roar(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap of
        // damage and status.

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

class FlareBlitz extends Attack{
    PokemonStatus pokemonStatus;
    String status;
    public FlareBlitz(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
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