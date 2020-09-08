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

    public boolean RhyhornStatus(Rhyhorn rhyhorn){
        // If pokemon status anything other than normal, function is called. Returns true if rhyhorn cannot make move
        // and true if able to
        if (rhyhorn.getStatus().equals("Asleep")){
            if (rhyhorn.WakeUp()){
                rhyhorn.setStatus("Normal");
                System.out.println(rhyhorn.getName() + " woke up");
            } else {
                System.out.println(rhyhorn.getName() + " is asleep. Cannot make a move");
                return true;
            }} else if (rhyhorn.getStatus().equals("Burned")){
            System.out.println("Rhyhorn is burned. Lost 10 health.");
            rhyhorn.Burn();
        } else if (rhyhorn.getStatus().equals("Poisoned")){
            System.out.println("Rhyhorn is poisoned. Lost 10 health.");
            rhyhorn.Poisioned();
        } else  if (rhyhorn.getStatus().equals("Paralyzed")){
            if (rhyhorn.Paralyzed()){
                System.out.println("Rhyhorn is paralyzed and cannot move");
                return true;
            }
        } else if (rhyhorn.getStatus().equals("Confused")){
            if (rhyhorn.Confusion()){
                System.out.println("Rhyhorn is confused. Rhyhorn hurt itself and cannot make a move. Lost 10 health");
                return true;
            } else {
                System.out.println("Rhyhorn snapped out of confusion");
                rhyhorn.setStatus("Normal");
            }
        }
        return false;
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
