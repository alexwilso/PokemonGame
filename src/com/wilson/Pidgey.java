package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Pidgey extends Pokemon{
    private Fly fly;
    private Gust gust;
    private WingAttack wingAttack;
    private Peck peck;

    public Pidgey(String name, String type, int level, int health, int maxHealth, String status, Fly fly, Gust gust, WingAttack wingAttack,
                  Peck peck) {
        super(name, type, level, health, maxHealth, status);
        this.fly = fly;
        this.gust = gust;
        this.wingAttack = wingAttack;
        this.peck = peck;
    }


    public Fly getFly() {
        return fly;
    }

    public Gust getGust() {
        return gust;
    }

    public WingAttack getWingAttack() {
        return wingAttack;
    }

    public Peck getPeck() {
        return peck;
    }

    public String[] DisplayPidgey(Pidgey pidgey){
        return new String[]{"Type: " + pidgey.getType(),"Fly damage: " + String.valueOf(pidgey.getFly().getDamage()) + " PP: " + String.valueOf(pidgey.getFly().getPp()),
                "Gust damage: " + String.valueOf(pidgey.getGust().getDamage()) + " PP: " + String.valueOf(pidgey.getGust().getPp()),
                "Peck damage: " + String.valueOf(pidgey.getPeck().getDamage()) + " PP: " + String.valueOf(pidgey.getPeck().getPp()),
                "Wing Attack Throw: " + String.valueOf(pidgey.getWingAttack().getDamage()) + " PP: " + String.valueOf(pidgey.getWingAttack().getPp())};
    }
}

class Fly extends Attack {
    // Initializes Fly attack
    public Fly(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Grass, damage is doubled. If type Rock or Electric, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if(type.equals("Rock") || type.equals("Electric")){
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

class Gust extends Attack{
    // Initializes Gust attack
    public Gust(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Grass, damage is doubled. If type Rock or Electric, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if(type.equals("Rock") || type.equals("Electric")){
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

class WingAttack extends Attack{
    // Initializes WingAttack attack for Pidgey
    public WingAttack(int damage, int remaining, int maxRemains) {
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
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}

class Peck extends Attack{
    // Initializes peck attack
    public Peck(int damage, int remaining, int maxRemains) {
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
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}

