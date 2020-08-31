package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Rhyhorn extends Pokemon{
    private final HornAttack hornAttack;
    private final Stomp stomp;
    private final MegaHorn megaHorn;
    private final EarthQuake earthQuake;

    public Rhyhorn(String name, String type, int level, int health, int maxHealth, String status, HornAttack hornAttack, Stomp stomp, MegaHorn megaHorn, EarthQuake earthQuake) {
        super(name, type, level, health, maxHealth, status);
        this.hornAttack = hornAttack;
        this.stomp = stomp;
        this.megaHorn = megaHorn;
        this.earthQuake = earthQuake;
    }

    public HornAttack getHornAttack() {
        return hornAttack;
    }

    public Stomp getStomp() {
        return stomp;
    }

    public MegaHorn getMegaHorn() {
        return megaHorn;
    }

    public EarthQuake getEarthQuake() {
        return earthQuake;
    }
}

class HornAttack extends Attack{

    public HornAttack(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Grass") || type.equals("Ghost")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
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

class MegaHorn extends Attack{

    public MegaHorn(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Grass") || type.equals("Ghost")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
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
