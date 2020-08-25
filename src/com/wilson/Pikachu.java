package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class Pikachu extends Pokemon {
    private final QuickAttack quickAttack;
    private final ThunderShock thunderShock;
    private final Thunder thunder;
    private final Growl growl;

    public Pikachu(String name, String type, int level, int health, int maxHealth, String status, QuickAttack quickAttack,
    ThunderShock thunderShock, Thunder thunder, Growl growl) {
        super(name, type, level, health, maxHealth, status);
        this.quickAttack = quickAttack;
        this.thunderShock = thunderShock;
        this.thunder = thunder;
        this.growl = growl;
    }

    public QuickAttack getQuickAttack() {
        return quickAttack;
    }

    public ThunderShock getThunderShock() {
        return thunderShock;
    }

    public Thunder getThunder() {
        return thunder;
    }

    public Growl getGrowl() {
        return growl;
    }

    public String[] DisplayPikachu(Pikachu pikachu){
        return new String[]{"Type: " + pikachu.getType(),"Growl damage: " + String.valueOf(pikachu.getGrowl().getDamage()) + " PP: " + String.valueOf(pikachu.getGrowl().getPp()),
                "Quick Attack damage: " + String.valueOf(pikachu.getQuickAttack().getDamage()) + " PP: " + String.valueOf(pikachu.getQuickAttack().getPp()),
                "Thunder damage: " + String.valueOf(pikachu.getThunder().getDamage()) + " PP: " + String.valueOf(pikachu.getThunder().getPp()),
                "Thunder Shock Throw: " + String.valueOf(pikachu.getThunderShock().getDamage()) + " PP: " + String.valueOf(pikachu.getThunderShock().getPp())};
    }
}

class QuickAttack extends Attack{
    // Initializes QuickAttack attack

    public QuickAttack(int damage, int remaining, int maxRemains) {
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
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}

class ThunderShock extends Attack{
    // Initializes ThunderShock attack
    int pokemonStatus = new PokemonStatus().ParalyzeChance();
    public ThunderShock(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.
        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 1){
            status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, status);
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            moveResult.put(this.getDamage() / 2, status);
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
            return moveResult;
        }
    }
}

class Thunder extends Attack{
    // Initializes Thunder attack
    int pokemonStatus = new PokemonStatus().ParalyzeChance();
    public Thunder(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 1){
            status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            moveResult.put(this.getDamage() / 2, status);
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
            return moveResult;
        }
    }
}


class Growl extends Attack{
    // Initializes Growl attack
    public Growl(int damage, int remaining, int maxRemains) {
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
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}