package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Charmander extends Pokemon{
    private final Scratch scratch;
    private final Ember ember;
    private final Flamethrower flamethrower;
    private final Tailwhip tailwhip;
    private String attackName;
    Scanner scanner = new Scanner(System.in);
    Battlemenu battlemenu = new Battlemenu();

    public Charmander(String name, String type, int level, int health, int maxHealth, String status, Scratch scratch, Ember ember,
                      Flamethrower flamethrower, Tailwhip tailwhip) {
        super(name, type, level, health, maxHealth, status);
        this.scratch = scratch;
        this.ember = ember;
        this.flamethrower = flamethrower;
        this.tailwhip = tailwhip;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public Scratch getScratch() {
        return scratch;
    }

    public Ember getEmber() {
        return ember;
    }

    public Flamethrower getFlamethrower() {
        return flamethrower;
    }

    public Tailwhip getTailwhip() {
        return tailwhip;
    }

    public String[] DisplayCharmander(Charmander charmander){
        return new String[]{"Type: " + charmander.getType(),"Ember damage: " + charmander.getEmber().getDamage() + " PP: " + charmander.getEmber().getPp(),
                "Flamethrower damage: " + charmander.getFlamethrower().getDamage() + " PP: " + charmander.getFlamethrower().getPp(),
                "Scratch damage: " + charmander.getScratch().getDamage() + " PP: " + charmander.getScratch().getPp(),
                "Tailwhip damage: " + charmander.getTailwhip().getDamage() + " PP: " + charmander.getTailwhip().getPp()};
    }

    public Map<Integer, String> CharmanderBattle(Player user, Object[] userPokemon, String cpuType){
        int selection = battlemenu.Menu(getName());
        Map<Integer, String> move = new HashMap<>();
        if (selection == 1){
            return CharmanderAttacks(cpuType);
        } else if (selection == 2){
            battlemenu.ChangePokemon(user, userPokemon);
        } else if(selection == 3){
            CharmanderItems(battlemenu.UseItem(user));
        }
        return move;
    }

    public Map<Integer, String> CharmanderAttacks(String cpuType){
        Map<Integer, String> move = new HashMap<>();
        System.out.println("Enter number of attack...");
        System.out.println("1. Ember PP: " + getEmber().getPp() + "/" +getEmber().getMaxRemains() +
                "\n2. Scratch PP: " + getScratch().getPp() + "/" + getScratch().getMaxRemains() +
                "\n3. Tailwhip PP: " + getTailwhip().getPp() + "/" + getTailwhip().getMaxRemains() +
                "\n4. Flamethrower PP: " + getFlamethrower().getPp() + "/" + getFlamethrower().getMaxRemains());
        int attack = Integer.parseInt(scanner.nextLine());
        switch (attack){
            case 1:
                setAttackName("Ember");
                return getEmber().attack(cpuType);
            case 2:
                setAttackName("Scratch");
                return getScratch().attack(cpuType);
            case 3:
                setAttackName("Tail whip");
                return getTailwhip().attack(cpuType);
            case 4:
                setAttackName("Flamethrower");
                return getFlamethrower().attack(cpuType);
        }
        return move;
    }

    public void CharmanderItems(String item){
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Ember\n 2. Scratch\n3. Tailwhip\n4. Flamethrower");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getEmber().useElixer("Elixer");
                return;
            }else if (restore == 2){
                getScratch().useElixer("ELixer");
                return;
            } else if (restore == 3){
                getTailwhip().useElixer("Elixer");
                return;
            } else if (restore == 4){
                getFlamethrower().useElixer("Elixer");
                return;
            }
        }
        use_item(item);
    }
}

class Scratch extends Attack{
    // Initializes Scratch
    public Scratch(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap containing damage and status
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

class Ember extends Attack{
    // Initializes ember
    int pokemonStatus = new PokemonStatus().BurnChance();
    public Ember(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        /*
        / Carries out attack. If type Grass, damage is doubled. If type Water or Rock, damage is halved. Subtracts 1
        / from remaining unless remaining is 0 then returns 0. Returns hashmap containing damage and status. Has a 1/5
        / chance of burning enemy.
         */
        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 3){
            status = "Burned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super Effective");
            moveResult.put(this.getDamage() * 2, status);
            return moveResult;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
            moveResult.put(this.getDamage() / 2, status);
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
            return moveResult;
        }
    }
}

class Flamethrower extends Attack{
    // initializes Flamethrower attack
    int pokemonStatus = new PokemonStatus().BurnChance();

    public Flamethrower(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        /*
        / Carries out attack. If type Grass, damage is doubled. If type Water or Rock, damage is halved. Subtracts 1
        / from remaining unless remaining is 0 then returns 0. Returns hashmap containing damage and status. Has a 1/5
        / chance of burning enemy.
         */

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 3){
            status = "Burned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super Effective");
            moveResult.put(this.getDamage() * 2, status);
            return moveResult;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
            moveResult.put(this.getDamage() / 2, status);
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), status);
            return moveResult;
        }
    }
}

class Tailwhip extends Attack{
    // Initializes Tailwhip attack
    public Tailwhip(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status
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
