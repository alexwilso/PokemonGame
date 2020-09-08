package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Voltorb extends Pokemon{
    private final Spark spark;
    private final Tackle tackle;
    private final SonicBoom sonicBoom;
    private final SelfDestruct selfDestruct;

    public Voltorb(String name, String type, int level, int health, int maxHealth, String status, Spark spark, Tackle tackle, SonicBoom sonicBoom, SelfDestruct selfDestruct) {
        super(name, type, level, health, maxHealth, status);
        this.spark = spark;
        this.tackle = tackle;
        this.sonicBoom = sonicBoom;
        this.selfDestruct = selfDestruct;
    }

    public Spark getSpark() {
        return spark;
    }

    public Tackle getTackle() {
        return tackle;
    }

    public SonicBoom getSonicBoom() {
        return sonicBoom;
    }

    public SelfDestruct getSelfDestruct() {
        this.setHealth(0);
        return selfDestruct;
    }
    public boolean VoltorbStatus(Voltorb voltorb){
        // If pokemon status anything other than normal, function is called. Returns true if voltorb cannot make move
        // and true if able to
        if (voltorb.getStatus().equals("Asleep")){
            if (voltorb.WakeUp()){
                voltorb.setStatus("Normal");
                System.out.println(voltorb.getName() + " woke up");
            } else {
                System.out.println(voltorb.getName() + " is asleep. Cannot make a move");
                return true;
            }} else if (voltorb.getStatus().equals("Burned")){
            System.out.println(voltorb.getName() + " is burned. Lost 10 health.");
            voltorb.Burn();
        } else if (voltorb.getStatus().equals("Poisoned")){
            System.out.println(voltorb.getName() + " is poisoned. Lost 10 health.");
            voltorb.Poisioned();
        } else  if (voltorb.getStatus().equals("Paralyzed")){
            if (voltorb.Paralyzed()){
                System.out.println(voltorb.getName() + " is paralyzed and cannot move");
                return true;
            }
        } else if (voltorb.getStatus().equals("Confused")){
            if (voltorb.Confusion()){
                System.out.println(voltorb.getName() + " is confused. Voltorb hurt itself and cannot make a move. Lost 10 health");
                return true;
            } else {
                System.out.println(voltorb.getName() + " snapped out of confusion");
                voltorb.setStatus("Normal");
            }
        }
        return false;
    }
}

class Spark extends Attack{
    PokemonStatus pokemonStatus;
    String status;
    public Spark(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type) {
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.
        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if (this.pokemonStatus.ParalyzeChance() == 1) {
            this.status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Water")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult;
        } else if (type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
            moveResult.put(this.getDamage() / 2, this.status);
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
            return moveResult;
        }
    }
}

class SonicBoom extends Attack{
    public SonicBoom(int damage, int remaining, int maxRemains) {
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

class SelfDestruct extends Attack{
    public SelfDestruct(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap of damage
        // and status
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
