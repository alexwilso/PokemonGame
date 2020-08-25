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
}

class Spark extends Attack{
    int pokemonStatus = new PokemonStatus().ParalyzeChance();
    public Spark(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type) {
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.
        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if (pokemonStatus == 1) {
            status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Water")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, status);
            return moveResult;
        } else if (type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            moveResult.put(this.getDamage() / 2, status);
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
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
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}
