package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Dugtrio extends Pokemon{
    private final TriAttack triAttack;
    private final MudSlap mudslap;
    private final Dig dig;
    private final EarthQuake earthQuake;
    private String visibility;

    public Dugtrio(String name, String type, int level, int health, int maxHealth, String status, TriAttack triAttack, MudSlap mudslap, Dig dig, EarthQuake earthQuake, String visibility) {
        super(name, type, level, health, maxHealth, status);
        this.triAttack = triAttack;
        this.mudslap = mudslap;
        this.dig = dig;
        this.earthQuake = earthQuake;
        this.visibility = visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getVisibility() {
        return visibility;
    }

    public TriAttack getTriAttack() {
        return triAttack;
    }

    public MudSlap getMudslap() {
        return mudslap;
    }

    public Dig getDig() {
        this.setVisibility("Underground");
        return dig;
    }

    public EarthQuake getEarthQuake() {
        return earthQuake;
    }
}

class TriAttack extends Attack {
    // Initializes TriAttack attack
    public TriAttack(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap of integer
        // damage and string status
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }
    }
}

class MudSlap extends Attack{
    // Initializes Mudslap attack
    public MudSlap(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap of integer
        // damage and string status
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}

class Dig extends Attack{
    // Initializes Dig attack
    public Dig(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        /*
        / Carries out attack. If type Rock or Fire, then damage is doubled. If type flying, damage is halved. Subtracts
        / 1. from remaining unless remaining is 0 then returns 0. Will set visibility to underground making opponents
        / attack miss
         */
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Rock") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective ");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Flying")){
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

class EarthQuake extends Attack{
    // Initializes EarthQuake attack
    public EarthQuake(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Rock or Fire, then damage is doubled. If type flying, damage is halved. Subtracts
        // 1. from remaining unless remaining is 0 then returns 0. Returns hashmap with integer damage and string status.
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Rock") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective ");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Flying")){
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