package com.wilson;

public class Gengar extends Pokemon {
    private final PoisionJab poisionJab;
    private final ConfusionRay confusionRay;
    private final Lick lick;
    private final ShadowBall shadowBall;

    public Gengar(String name, String type, int level, int health, int maxHealth, String status,  PoisionJab poisionJab,
                  ConfusionRay confusionRay, Lick lick, ShadowBall shadowBall) {
        super(name, type, level, health, maxHealth, status);
        this.poisionJab = poisionJab;
        this.confusionRay = confusionRay;
        this.lick = lick;
        this.shadowBall = shadowBall;
    }

    public PoisionJab getPoisionJab() {
        return poisionJab;
    }

    public ConfusionRay getConfusionRay() {
        return confusionRay;
    }

    public Lick getLick() {
        return lick;
    }

    public ShadowBall getShadowBall() {
        return shadowBall;
    }

    public String[] DisplayGengar(Gengar gengar){
        return new String[]{"Type: " + gengar.getType(),"Poison jab damage: " + String.valueOf(gengar.getPoisionJab().getDamage()) + " PP: " + String.valueOf(gengar.getPoisionJab().getPp()),
                "Shadowball damage: " + String.valueOf(gengar.getShadowBall().getDamage()) + " PP: " + String.valueOf(gengar.getShadowBall().getPp()),
                "Confusion Ray damage: " + String.valueOf(gengar.getConfusionRay().getDamage()) + " PP: " + String.valueOf(gengar.getConfusionRay().getPp()),
                "Lick damage: " + String.valueOf(gengar.getLick().getDamage()) + " PP: " + String.valueOf(gengar.getLick().getPp())};
    }
}


class PoisionJab extends Attack{
    // Initializes PoisionJab used by Gengar
    public PoisionJab(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type Rock or Ghost, then damage is doubled. Subtract 1 from remaining unless remaining
        // is 0 then returns 0.
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            return 0;
        } else if(type.equals("Rock") || type.equals("Ghost")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else {
            this.setPp(this.getPp() -1);
            return this.getDamage();
        }
    }
}

class ConfusionRay extends Attack{
    //  Initializes ConfusionRay attack for Gengar
    public ConfusionRay(int damage, int remaining, int maxRemains) {
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

class Lick extends Attack{
    //  Initializes Lick attack for Gengar
    public Lick(int damage, int remaining, int maxRemains) {
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

class ShadowBall extends Attack{
    //  Initializes ShadowBall attack for Gengar
    public ShadowBall(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. If type == Rock or Ghost, then damage is doubled. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            return 0;
        } else if(type.equals("Rock") || type.equals("Ghost")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else {
            this.setPp(this.getPp() -1);
            return this.getDamage();
        }
    }
}

