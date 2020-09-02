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

    public Absorb getAbsorb(boolean heal) {
        if (heal){
            restoreHealth();
        }
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
        this.gainHealth(absorb.getHeal());
    }
    public boolean VileplumeStatus(Vileplume vileplume){
        // If pokemon status anything other than normal, function is called. Returns true if vileplume cannot make move
        // and true if able to
        if (vileplume.getStatus().equals("Asleep")){
            if (vileplume.WakeUp()){
                vileplume.setStatus("Normal");
                System.out.println(vileplume.getName() + " woke up");
            } else {
                System.out.println(vileplume.getName() + " is asleep. Cannot make a move");
                return true;
            }} else if (vileplume.getStatus().equals("Burned")){
            System.out.println("Vileplume is burned. Lost 10 health.");
            vileplume.Burn();
        } else if (vileplume.getStatus().equals("Poisoned")){
            System.out.println("Vileplume is poisoned. Lost 10 health.");
            vileplume.Poisioned();
        } else  if (vileplume.getStatus().equals("Paralyzed")){
            if (vileplume.Paralyzed()){
                System.out.println("Vileplume is paralyzed and cannot move");
                return true;
            }
        } else if (vileplume.getStatus().equals("Confused")){
            if (vileplume.Confusion()){
                System.out.println("Vileplume is confused. Vileplume hurt itself and cannot make a move. Lost 10 health");
                return true;
            } else {
                System.out.println("Vileplume snapped out of confusion");
                vileplume.setStatus("Normal");
            }
        }
        return false;
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
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;

    }
}

class StunSpore extends Attack{
    PokemonStatus pokemonStatus;
    String status;

    public StunSpore(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(pokemonStatus.ParalyzeChance() == 1){
            this.status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
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
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Flying") || type.equals("Fire")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
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


class Sleep extends Attack{
    PokemonStatus pokemonStatus;
    String status;

    public Sleep(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(pokemonStatus.SleepChance() == 1){
            this.status = "Asleep";
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