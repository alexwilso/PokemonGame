package com.wilson;

public class Rhyhorn extends Pokemon{
    private final HornAttack hornAttack;
    private final Stomp stomp;
    private MegaHorn megaHorn;
    private EarthQuake earthQuake;

    public Rhyhorn(String name, String type, int level, int health, int maxHealth, String status, HornAttack hornAttack, Stomp stomp, MegaHorn megaHorn, EarthQuake earthQuake) {
        super(name, type, level, health, maxHealth, status);
        this.hornAttack = hornAttack;
        this.stomp = stomp;
        this.megaHorn = megaHorn;
        this.earthQuake = earthQuake;
    }

    public HornAttack getHornAttack() {
        return hornAttack;
    }

    public Stomp getStomp() {
        return stomp;
    }

    public MegaHorn getMegaHorn() {
        return megaHorn;
    }

    public EarthQuake getEarthQuake() {
        return earthQuake;
    }
}

class HornAttack extends Attack{

    public HornAttack(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            return 0;
        }else if (type.equals("Grass") || type.equals("Ghost")) {
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

class MegaHorn extends Attack{

    public MegaHorn(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. If type Grass, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            return 0;
        }else if (type.equals("Grass") || type.equals("Ghost")) {
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
