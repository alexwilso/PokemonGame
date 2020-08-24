package com.wilson;

public class Growlithe extends Pokemon {
    private final FireFang fireFang;
    private final Bite bite;
    private final Roar roar;
    private final FlareBlitz flareBlitz;

    public Growlithe(String name, String type, int level, int health, int maxHealth, String status, FireFang fireFang,
                     Bite bite, Roar roar, FlareBlitz flareBlitz) {
        super(name, type, level, health, maxHealth, status);
        this.fireFang = fireFang;
        this.bite = bite;
        this.roar = roar;
        this.flareBlitz = flareBlitz;
    }

    public FireFang getFireFang() {
        return fireFang;
    }

    public Bite getBite() {
        return bite;
    }

    public Roar getRoar() {
        return roar;
    }

    public FlareBlitz getFlareBlitz() {
        return flareBlitz;
    }
}

class FireFang extends Attack{

    public FireFang(int damage, int remaining, int maxRemains) {
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

class Bite extends Attack {
    public Bite(int damage, int remaining, int maxRemains) {
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

class Roar extends Attack {
    public Roar(int damage, int remaining, int maxRemains) {
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

class FlareBlitz extends Attack{
    public FlareBlitz(int damage, int remaining, int maxRemains) {
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
