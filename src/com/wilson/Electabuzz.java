package com.wilson;

import java.util.HashMap;
import java.util.Map;

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
    public Map<Integer, String> attack(String type){
        // Carries out attack. If type is water, then damage is doubled. If type is rock, damage is halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with integer damage and string status.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }
    }
}

class LowKick extends Attack{
    public LowKick(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // integer damage and string status

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}