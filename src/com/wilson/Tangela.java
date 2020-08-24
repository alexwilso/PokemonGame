package com.wilson;

public class Tangela extends Pokemon {
    private final PoisionPowder poisionPowder;
    private final Megadrain megadrain;
    private final Slam slam;
    private final Constrict constrict;

    public Tangela(String name, String type, int level, int health, int maxHealth, String status, PoisionPowder poisionPowder, Megadrain megadrain, Slam slam, Constrict constrict) {
        super(name, type, level, health, maxHealth, status);
        this.poisionPowder = poisionPowder;
        this.megadrain = megadrain;
        this.slam = slam;
        this.constrict = constrict;
    }

    public PoisionPowder getPoisionPowder() {
        return poisionPowder;
    }

    public Megadrain getMegadrain() {
        this.restoreHealth();
        return megadrain;
    }

    public Slam getSlam() {
        return slam;
    }

    public Constrict getConstrict() {
        return constrict;
    }

    public void restoreHealth(){
        this.setHealth(this.getHealth() + megadrain.getHeal());
    }

}

class PoisionPowder extends Attack{
    // initializes PoisonPowder attack
    public PoisionPowder(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            // Chance to stun opponent
            return this.getDamage();

        }
    }
}

class Megadrain extends Attack {
    private int heal;

    // initializes Megadrain attack
    public Megadrain(int damage, int remaining, int maxRemains, int heal) {
        super(damage, remaining, maxRemains);
        this.heal = heal;
    }


    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            this.setHeal(this.getDamage());
            return this.getDamage();
        }

    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}

class Slam extends Attack {

    // initializes Slam attack
    public Slam(int damage, int remaining, int maxRemains) {
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

class Constrict extends Attack {

    // initializes Constrict attack
    public Constrict(int damage, int remaining, int maxRemains) {
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
