package com.wilson;

public class Pidgey extends Pokemon{
    private Fly fly;
    private Gust gust;
    private WingAttack wingAttack;
    private Peck peck;

    public Pidgey(String name, String type, int level, int health, int maxHealth, String status, Fly fly, Gust gust, WingAttack wingAttack,
                  Peck peck) {
        super(name, type, level, health, maxHealth, status);
        this.fly = fly;
        this.gust = gust;
        this.wingAttack = wingAttack;
        this.peck = peck;
    }


    public Fly getFly() {
        return fly;
    }

    public Gust getGust() {
        return gust;
    }

    public WingAttack getWingAttack() {
        return wingAttack;
    }

    public Peck getPeck() {
        return peck;
    }

    public String[] DisplayPidgey(Pidgey pidgey){
        return new String[]{"Type: " + pidgey.getType(),"Fly damage: " + String.valueOf(pidgey.getFly().getDamage()) + " PP: " + String.valueOf(pidgey.getFly().getPp()),
                "Gust damage: " + String.valueOf(pidgey.getGust().getDamage()) + " PP: " + String.valueOf(pidgey.getGust().getPp()),
                "Peck damage: " + String.valueOf(pidgey.getPeck().getDamage()) + " PP: " + String.valueOf(pidgey.getPeck().getPp()),
                "Wing Attack Throw: " + String.valueOf(pidgey.getWingAttack().getDamage()) + " PP: " + String.valueOf(pidgey.getWingAttack().getPp())};
    }
}

class Fly extends Attack {
    // Initializes Fly attack
    public Fly(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Grass, damage is doubled. If type Rock or Electric, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if(type.equals("Rock") || type.equals("Electric")){
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

class Gust extends Attack{
    // Initializes Gust attack
    public Gust(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. If type Grass, damage is doubled. If type Rock or Electric, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if(type.equals("Rock") || type.equals("Electric")){
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

class WingAttack extends Attack{
    // Initializes WingAttack attack for Pidgey
    public WingAttack(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getPp();
        }
    }
}

class Peck extends Attack{
    // Initializes peck attack
    public Peck(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getPp();
        }
    }
}

