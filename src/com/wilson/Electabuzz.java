package com.wilson;

public class Electabuzz extends Pokemon {
    private final QuickAttack quickAttack;
    private final ThunderPunch thunderPunch;
    private final Thunder thunder;
    private final LowKick lowKick;

    public Electabuzz(String name, String type, int level, int health, int maxHealth, String status, QuickAttack quickAttack, ThunderPunch thunderPunch, Thunder thunder, LowKick lowKick) {
        super(name, type, level, health, maxHealth, status);
        this.quickAttack = quickAttack;
        this.thunderPunch = thunderPunch;
        this.thunder = thunder;
        this.lowKick = lowKick;
    }

    public QuickAttack getQuickAttack() {
        return quickAttack;
    }

    public ThunderPunch getThunderPunch() {
        return thunderPunch;
    }

    public Thunder getThunder() {
        return thunder;
    }

    public LowKick getLowKick() {
        return lowKick;
    }
}

class ThunderPunch extends Attack{

    public ThunderPunch(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public int attack(String type){
        // Carries out attack. If type == Rock, then damage is doubled. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Water")){
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

class LowKick extends Attack{
    public LowKick(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}