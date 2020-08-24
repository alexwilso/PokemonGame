package com.wilson;

public class Flareon extends Pokemon {
    private final FlameCharge flameCharge;
    private final SuperPower superPower;
    private final Toxic toxic;
    private final FirePunch firePunch;

    public Flareon(String name, String type, int level, int health, int maxHealth, String status, FlameCharge flameCharge, SuperPower superPower, Toxic toxic, FirePunch firePunch) {
        super(name, type, level, health, maxHealth, status);
        this.flameCharge = flameCharge;
        this.superPower = superPower;
        this.toxic = toxic;
        this.firePunch = firePunch;
    }
}

class FlameCharge extends Attack {
    // Initializes FlameCharge attack
    public FlameCharge(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. If type == Grass, then damage is doubled. If type == Rock, the
        // damage is halved. Subtracts 1 from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if (type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            return this.getDamage() / 2;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class SuperPower extends Attack {
    // Initializes SuperPower attack
    public SuperPower(int damage, int remaining, int maxRemains) {
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

class Toxic extends Attack{
    // Initializes Toxic attack
    public Toxic(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
//        } else if (num == 3){
//            this.setPp(this.getPp() - 1);
//            // Will set pokemon status to poisioned
//            return this.getDamage();
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class FirePunch extends Attack {
    // Initializes FirePunch attack
    public FirePunch(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. If type == Grass, then damage is doubled. If type == Rock, the
        // damage is halved. Subtracts 1 from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if (type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            return this.getDamage() / 2;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}