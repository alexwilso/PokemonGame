package com.wilson;

public class Rapidash extends Pokemon {
    private final Stomp stomp;
    private final FireSpin fireSpin;
    private final FireBlast fireBlast;
    private final Bounce bounce;
    public Rapidash(String name, String type, int level, int health, int maxHealth, String status, Stomp stomp,
                    FireSpin fireSpin, FireBlast fireBlast,Bounce bounce) {
        super(name, type, level, health, maxHealth, status);
        this.stomp = stomp;
        this.fireSpin = fireSpin;
        this.fireBlast = fireBlast;
        this.bounce = bounce;
    }

    public Stomp getStomp() {
        return stomp;
    }

    public FireSpin getFireSpin() {
        return fireSpin;
    }

    public FireBlast getFireBlast() {
        return fireBlast;
    }

    public Bounce getBounce() {
        return bounce;
    }
}

class Stomp extends Attack{
    public Stomp(int damage, int remaining, int maxRemains) {
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

class FireSpin extends Attack{
    public FireSpin(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, the
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

class FireBlast extends Attack{
    public FireBlast(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, the
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

class Bounce extends Attack {
    public Bounce(int damage, int remaining, int maxRemains) {
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