package com.wilson;

public class Attack {
    private int damage;
    private int pp;
    private int maxRemains;
    private String status;

    public Attack(int damage, int remaining, int maxRemains) {
        this.damage = damage;
        this.pp = remaining;
        this.maxRemains = maxRemains;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getMaxRemains() {
        return maxRemains;
    }

    public void setMaxRemains(int maxRemains) {
        this.maxRemains = maxRemains;
    }

    public String useElixer(String item){
        if (item.equals("Elixer")){
            this.setPp(10);
            return("PP restored by 10. New pp " + this.getPp());
        }
        return("This item has no affect");
    }}
