package com.wilson;

public class Onix extends Pokemon {
    private Rage rage;
    private RockThrow rockThrow;
    private RockSmash rockSmash;
    private Bind bind;

    public Onix(String name, String type, int level, int health, int maxHealth, String status, Rage rage,
                RockThrow rockThrow, RockSmash rockSmash, Bind bind) {
        super(name, type, level, health, maxHealth, status);
        this.rage = rage;
        this.rockThrow = rockThrow;
        this.rockSmash =rockSmash;
        this.bind = bind;
    }

    public Rage getRage() {
        return rage;
    }

    public RockThrow getRockThrow() {
        return rockThrow;
    }

    public RockSmash getRockSmash() {
        return rockSmash;
    }

    public Bind getBind() {
        return bind;
    }

    public String[] DisplayOnix(Onix onix){
        return new String[]{"Type: " + onix.getType(),"Bind damage: " + String.valueOf(onix.getBind().getDamage()) + " PP: " + String.valueOf(onix.getBind().getPp()),
                "Rage damage: " + String.valueOf(onix.getRage().getDamage()) + " PP: " + String.valueOf(onix.getRage().getPp()),
                "Rock Smash damage: " + String.valueOf(onix.getRockSmash().getDamage()) + " PP: " + String.valueOf(onix.getRockSmash().getPp()),
                "Rock Throw: " + String.valueOf(onix.getRockThrow().getDamage()) + " PP: " + String.valueOf(onix.getRockThrow().getPp())};
    }
}

class Rage extends Attack{
    // Initializes rage attack
    public Rage(int damage, int remaining, int maxRemains) {
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

class RockThrow extends Attack{
    // Initializes RockThrow attack
    public RockThrow(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Flying or Fire, then damage is doubled. If type Rock, damage is halved. Subtracts
        // 1 from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            return 0;
        }else if (type.equals("Flying") || type.equals("Fire")) {
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

class RockSmash extends Attack{
    // Initializes RockSmash attack
    public RockSmash(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Flying or Fire, then damage is doubled. If type Rock, damage is halved. Subtracts
        // 1 from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            return 0;
        }else if (type.equals("Flying") || type.equals("Fire")) {
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

class Bind extends Attack{
    // Initializes Bind attack
    public Bind(int damage, int remaining, int maxRemains) {
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