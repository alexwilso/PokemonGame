package com.wilson;

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
    public Spark(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            return this.getDamage() / 2;
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class SonicBoom extends Attack{
    public SonicBoom(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }

    }
}

class SelfDestruct extends Attack{
    public SelfDestruct(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }

    }
}
