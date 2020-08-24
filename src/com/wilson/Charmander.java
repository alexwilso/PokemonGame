package com.wilson;

public class Charmander extends Pokemon{
    private final Scratch scratch;
    private final Ember ember;
    private final Flamethrower flamethrower;
    private final Tailwhip tailwhip;
//    static String status = "Normal";

    public Charmander(String name, String type, int level, int health, int maxHealth, String status, Scratch scratch, Ember ember,
                      Flamethrower flamethrower, Tailwhip tailwhip) {
        super(name, type, level, health, maxHealth, status);
        this.scratch = scratch;
        this.ember = ember;
        this.flamethrower = flamethrower;
        this.tailwhip = tailwhip;
    }

    public Scratch getScratch() {
        return scratch;
    }

    public Ember getEmber() {
        return ember;
    }

    public Flamethrower getFlamethrower() {
        return flamethrower;
    }

    public Tailwhip getTailwhip() {
        return tailwhip;
    }

    public String[] DisplayCharmander(Charmander charmander){
        return new String[]{"Type: " + charmander.getType(),"Ember damage: " + String.valueOf(charmander.getEmber().getDamage()) + " PP: " + String.valueOf(charmander.getEmber().getPp()),
                "Flamethrower damage: " + String.valueOf(charmander.getFlamethrower().getDamage()) + " PP: " + String.valueOf(charmander.getFlamethrower().getPp()),
                "Scratch damage: " + String.valueOf(charmander.getScratch().getDamage()) + " PP: " + String.valueOf(charmander.getScratch().getPp()),
                "Tailwhip damage: " + String.valueOf(charmander.getTailwhip().getDamage()) + " PP: " + String.valueOf(charmander.getTailwhip().getPp())};
    }
}

class Scratch extends Attack{
    // Initializes Scratch
    public Scratch(int damage, int remaining, int maxRemains) {
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

class Ember extends Attack{
    // Initializes ember
    public Ember(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Grass, damage is doubled. If type Water or Rock, damage is halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super Effective");
            return this.getDamage() * 2;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
            return this.getDamage() / 2;
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class Flamethrower extends Attack{
    // initializes Flamethrower attack
    public Flamethrower(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }


    public int attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Water or Rock, damage is halved. Subtracts
        // 1 from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super Effective");
            return this.getDamage() * 2;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
            return this.getDamage() / 2;
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class Tailwhip extends Attack{
    // Initializes Tailwhip attack
    public Tailwhip(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
//        this.setDamage(15);
//        this.setPp(15);
//        this.setMaxRemains(15);
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
