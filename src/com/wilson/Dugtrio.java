package com.wilson;

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

class MudSlap extends Attack{
    // Initializes Mudslap attack
    public MudSlap(int damage, int remaining, int maxRemains) {
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

class Dig extends Attack{
    // Initializes Dig attack
    public Dig(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Rock or Fire, then damage is doubled. If type flying, damage is halved. Subtracts
        // 1. from remaining unless remaining is 0 then returns 0. Will set visibility to underground making opponents
        // attack miss
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            return 0;
        }else if (type.equals("Rock") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective ");
            return this.getDamage() * 2;
        } else if (type.equals("Flying")){
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

class EarthQuake extends Attack{
    // Initializes EarthQuake attack
    public EarthQuake(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Rock or Fire, then damage is doubled. If type flying, damage is halved. Subtracts
        // 1. from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            return 0;
        }else if (type.equals("Rock") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective ");
            return this.getDamage() * 2;
        } else if (type.equals("Flying")){
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