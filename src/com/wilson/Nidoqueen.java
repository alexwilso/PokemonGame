package com.wilson;

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
}

class DoubleKick extends Attack{

    public DoubleKick(int damage, int remaining, int maxRemains) {
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

class BodySlam extends Attack{

    public BodySlam(int damage, int remaining, int maxRemains) {
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
            System.out.println("It's not very Effective");
            return this.getDamage() / 2;
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class PoisonSting extends Attack{

    public PoisonSting(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
}

class FocusPunch extends Attack {

    public FocusPunch(int damage, int remaining, int maxRemains) {
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


