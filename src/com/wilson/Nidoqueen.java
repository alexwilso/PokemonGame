package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Nidoqueen extends Pokemon {
    private final DoubleKick doubleKick;
    private final BodySlam bodySlam;
    private final PoisonSting poisonSting;
    private final FocusPunch focusPunch;

    public Nidoqueen(String name, String type, int level, int health, int maxHealth, String status, DoubleKick doubleKick, BodySlam bodySlam, PoisonSting poisonSting, FocusPunch focusPunch) {
        super(name, type, level, health, maxHealth, status);
        this.doubleKick = doubleKick;
        this.bodySlam = bodySlam;
        this.poisonSting = poisonSting;
        this.focusPunch = focusPunch;
    }

    public DoubleKick getDoubleKick() {
        return doubleKick;
    }

    public BodySlam getBodySlam() {
        return bodySlam;
    }

    public PoisonSting getPoisonSting() {
        return poisonSting;
    }

    public FocusPunch getFocusPunch() {
        return focusPunch;
    }

    public boolean NidoqueenStatus(Nidoqueen nidoqueen){
        // If pokemon status anything other than normal, function is called. Returns true if nidoqueen cannot make move
        // and true if able to
        if (nidoqueen.getStatus().equals("Asleep")){
            if (nidoqueen.WakeUp()){
                nidoqueen.setStatus("Normal");
                System.out.println(nidoqueen.getName() + " woke up");
            } else {
                System.out.println(nidoqueen.getName() + " is asleep. Cannot make a move");
                return true;
            }} else if (nidoqueen.getStatus().equals("Burned")){
            System.out.println("Nidoqueen is burned. Lost 10 health.");
            nidoqueen.Burn();
        } else if (nidoqueen.getStatus().equals("Poisoned")){
            System.out.println("Nidoqueen is poisoned. Lost 10 health.");
            nidoqueen.Poisioned();
        } else  if (nidoqueen.getStatus().equals("Paralyzed")){
            if (nidoqueen.Paralyzed()){
                System.out.println("Nidoqueen is paralyzed and cannot move");
                return true;
            }
        } else if (nidoqueen.getStatus().equals("Confused")){
            if (nidoqueen.Confusion()){
                System.out.println("Nidoqueen is confused. Nidoqueen hurt itself and cannot make a move. Lost 10 health");
                return true;
            } else {
                System.out.println("Nidoqueen snapped out of confusion");
                nidoqueen.setStatus("Normal");
            }
        }
        return false;
    }
}

class DoubleKick extends Attack{

    public DoubleKick(int damage, int remaining, int maxRemains) {
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

class BodySlam extends Attack{

    public BodySlam(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Flying or Fire, then damage is doubled. If type Rock, damage is halved. Subtracts
        // 1 from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Flying") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
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

class PoisonSting extends Attack{
    // Initializes poison sting
    PokemonStatus pokemonStatus;
    String status;
    public PoisonSting(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public  Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(this.pokemonStatus.PoisonChance() == 3){
            this.status = "Poisoned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), this.status);
        }
        return moveResult;
    }
}

class FocusPunch extends Attack {

    public FocusPunch(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Flying or Fire, then damage is doubled. If type Rock, damage is halved. Subtracts
        // 1 from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Flying") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
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


