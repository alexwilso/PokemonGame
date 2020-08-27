package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Gengar extends Pokemon {
    private final PoisonJab poisonJab;
    private final ConfusionRay confusionRay;
    private final Lick lick;
    private final ShadowBall shadowBall;
    private String attackName;
    Scanner scanner = new Scanner(System.in);
    Battlemenu battlemenu = new Battlemenu();

    public Gengar(String name, String type, int level, int health, int maxHealth, String status,  PoisonJab poisonJab,
                  ConfusionRay confusionRay, Lick lick, ShadowBall shadowBall) {
        super(name, type, level, health, maxHealth, status);
        this.poisonJab = poisonJab;
        this.confusionRay = confusionRay;
        this.lick = lick;
        this.shadowBall = shadowBall;
    }

    public PoisonJab getPoisonJab() {
        return poisonJab;
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

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public String[] DisplayGengar(Gengar gengar){
        return new String[]{"Type: " + gengar.getType(),"Poison jab damage: " + gengar.getPoisonJab().getDamage() + " PP: " + gengar.getPoisonJab().getPp(),
                "Shadowball damage: " + gengar.getShadowBall().getDamage() + " PP: " + gengar.getShadowBall().getPp(),
                "Confusion Ray damage: " + gengar.getConfusionRay().getDamage() + " PP: " + gengar.getConfusionRay().getPp(),
                "Lick damage: " + gengar.getLick().getDamage() + " PP: " + gengar.getLick().getPp()};
    }

    public Map<Integer, String> GengarBattle(Player user, Object[] userPokemon, String cpuType){
        Map<Integer, String> move = new HashMap<>();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return GengarAttacks(cpuType);
        } else if (selection == 2){
            battlemenu.ChangePokemon(user, userPokemon);
        } else if(selection == 3){
            GengarItems(battlemenu.UseItem(user));
        }
        return move;
    }

    public Map<Integer, String> GengarAttacks(String cpuType){
        Map<Integer, String> move = new HashMap<>();
        System.out.println("Enter number of attack...");
        System.out.println("1. Confusion Ray PP: " + getConfusionRay().getPp() + "/" +getConfusionRay().getMaxRemains() +
                "\n2. Lick PP: " + getLick().getPp() + "/" + getLick().getMaxRemains() +
                "\n3. Poison Jab PP: " + getPoisonJab().getPp() + "/" + getPoisonJab().getMaxRemains() +
                "\n4. Shadow Ball PP: " + getShadowBall().getPp() + "/" + getShadowBall().getMaxRemains());
        int attack = Integer.parseInt(scanner.nextLine());
        switch (attack){
            case 1:
                setAttackName("Confusion Ray");
                return getConfusionRay().attack();
            case 2:
                setAttackName("Lick");
                return getLick().attack();
            case 3:
                setAttackName("Poison Jab");
                return getPoisonJab().attack(cpuType);
            case 4:
                setAttackName("Shadow Ball");
                return getShadowBall().attack(cpuType);
        }
        return move;
    }

    public String GengarItems(String item){
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Confusion Ray\n 2.Lick\n3. Poison Jab \n4. Shadow Ball");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                return getConfusionRay().useElixer("Elixer");
            }else if (restore == 2){
                return getLick().useElixer("ELixer");
            } else if (restore == 3){
                return getPoisonJab().useElixer("Elixer");
            } else if (restore == 4){
                return getShadowBall().useElixer("Elixer");
            }
        }
        return use_item(item);
    }
}

class PoisonJab extends Attack{
    // Initializes PoisonJab
    int pokemonStatus = new PokemonStatus().PoisonChance();
    public PoisonJab(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Rock or Ghost, then damage is doubled. Subtract 1 from remaining unless remaining
        // is 0 then returns 0. Has 20% chance of poisoning opponent. Returns hashmap of damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 3){
            status = "Poisoned";
        }
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if(type.equals("Rock") || type.equals("Ghost")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, status);
            return moveResult;
        } else {
            this.setPp(this.getPp() -1);
            moveResult.put(this.getDamage(), status);
            return moveResult;
        }
    }
}

class ConfusionRay extends Attack{
    //  Initializes ConfusionRay attack
    int pokemonStatus = new PokemonStatus().ConfusionChance();
    public ConfusionRay(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Has a 20% chance of
        // confusing enemy. Returns hashmap with damage and status

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 2){
            status = "Confused";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
        }
        return moveResult;
    }
}

class Lick extends Attack{
    //  Initializes Lick attack for Gengar
    public Lick(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }
    public Map<Integer, String> attack(){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}

class ShadowBall extends Attack{
    //  Initializes ShadowBall attack for Gengar
    public ShadowBall(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type == Rock or Ghost, then damage is doubled. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if(type.equals("Rock") || type.equals("Ghost")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else {
            this.setPp(this.getPp() -1);
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }
    }
}


