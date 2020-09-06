package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Charmander extends Pokemon{
    private final Scratch scratch;
    private final Ember ember;
    private final Flamethrower flamethrower;
    private final Tailwhip tailwhip;
    private final Battlemenu battlemenu;
    Scanner scanner = new Scanner(System.in);

    public Charmander(String name, String type, int level, int health, int maxHealth, String status, Scratch scratch, Ember ember,
                      Flamethrower flamethrower, Tailwhip tailwhip, Battlemenu battlemenu) {
        super(name, type, level, health, maxHealth, status);
        this.scratch = scratch;
        this.ember = ember;
        this.flamethrower = flamethrower;
        this.tailwhip = tailwhip;
        this.battlemenu = battlemenu;
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
        return new String[]{"Type: " + charmander.getType(),"Attacks: Ember damage: " + charmander.getEmber().getDamage() + " PP: " + charmander.getEmber().getPp(),
                "Flamethrower damage: " + charmander.getFlamethrower().getDamage() + " PP: " + charmander.getFlamethrower().getPp(),
                "Scratch damage: " + charmander.getScratch().getDamage() + " PP: " + charmander.getScratch().getPp(),
                "Tailwhip damage: " + charmander.getTailwhip().getDamage() + " PP: " + charmander.getTailwhip().getPp()};
    }

    public Map<Integer, String> CharmanderBattle(Player user, Object[] userPokemon, String cpuType, int activePokemon){
        int selection = battlemenu.Menu(getName());
        boolean noneFainted = true;
        Map<Integer, String> move = new HashMap<>();
        if (selection == 1){
            return CharmanderAttacks(cpuType); }

        else if (selection == 2){
            return battlemenu.ChangePokemon(user, userPokemon, activePokemon); }

        else if(selection == 3){
            if (user.getBag().isEmpty()){
                System.out.println("You have no items to use.");
                return CharmanderBattle(user, userPokemon, cpuType, activePokemon); }
            String item = battlemenu.UseItem(user);
            if (item.equals("Revive")) {
                for (int x = 0; x<3; x++){
                    if (user.getFaintedPokemon()[x] != null){
                        noneFainted = false; } }
                if (noneFainted){
                    System.out.println("All pokemon are playable");
                    return CharmanderBattle(user, userPokemon, cpuType, activePokemon); } }
            return CharmanderItems(item, user); }
        else {
            System.out.println("Not a valid option");
            CharmanderBattle(user, userPokemon, cpuType, activePokemon);}
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
                move = getEmber().attack(cpuType);
                setAttackStrength(getEmber().getStrength());
                break;
            case 2:
                setAttackName("Scratch");
                move = getScratch().attack(cpuType);
                setAttackStrength(getScratch().getStrength());
                break;
            case 3:
                setAttackName("Tail whip");
                move = getTailwhip().attack(cpuType);
                setAttackStrength(getTailwhip().getStrength());
                break;
            case 4:
                setAttackName("Flamethrower");
                move = getFlamethrower().attack(cpuType);
                setAttackStrength(getFlamethrower().getStrength());
                break;
        }
        return move;
    }

    public Map<Integer, String> CharmanderItems(String item, Player user){
        Map<Integer, String> itemMap = new HashMap<>();
        if (item.equals("Revive")){
            boolean noFainted = true;
            System.out.println("Fainted Pokemon:");
            for (int x = 0; x<3; x++){
                if (user.getFaintedPokemon()[x] != null){
                    noFainted = false;
                    System.out.println((x+ 1) + ". " +user.getFaintedPokemon()[x]); } }
            if (noFainted){
                System.out.println("All pokemon are playable");
            }
            System.out.println("Enter the number of the pokemon you would like to revive?");
            Integer revive = Integer.parseInt(scanner.nextLine()) * -1;
            itemMap.put(revive, item);
            return itemMap; }
        itemMap.put(0, item);
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Ember\n 2. Scratch\n3. Tailwhip\n4. Flamethrower");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getEmber().useElixer("Elixer");
            }else if (restore == 2){
                getScratch().useElixer("Elixer");
            } else if (restore == 3){
                getTailwhip().useElixer("Elixer");
            } else if (restore == 4){
                getFlamethrower().useElixer("Elixer");
            }
        } else {
            use_item(item);
        }
        user.useItem(item);
        setAttackName(item);
        setAttackStrength("Normal");
        return itemMap;
    }

    public boolean CharmanderStatus(Charmander charmander){
        // If pokemon status anything other than normal, function is called. Returns true if charmander cannot make move
        // and true if able to
        if (charmander.getStatus().equals("Asleep")){
            if (charmander.WakeUp()){
                charmander.setStatus("Normal");
                System.out.println(charmander.getName() + " woke up");
            } else {
                System.out.println(charmander.getName() + " is asleep. Cannot make a move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }} else if (charmander.getStatus().equals("Burned")){
            System.out.println("Charmander is burned. Lost 10 health.");
            charmander.Burn();
        } else if (charmander.getStatus().equals("Poisoned")){
            System.out.println("Chamander is poisoned. Lost 10 health.");
            charmander.Poisioned();
        } else  if (charmander.getStatus().equals("Paralyzed")){
            if (charmander.Paralyzed()){
                System.out.println("Charmander is paralzyed and cannot move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }
        } else if (charmander.getStatus().equals("Confused")){
            if (charmander.Confusion()){
                System.out.println("Charmander is confused. Charmander hurt itself and cannot make a move. Lost 10 health");
                battlemenu.pressAnyKeyToContinue();
                return true;
            } else {
                System.out.println("Carmander snapped out of confusion");
                charmander.setStatus("Normal");
            }
        }
        return false;
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
            moveResult.put(999, "Normal"); }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}

class Ember extends Attack{
    // Initializes ember
    PokemonStatus pokemonStatus;
    String status;

    public Ember(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        /*
        / Carries out attack. If type Grass, damage is doubled. If type Water or Rock, damage is halved. Subtracts 1
        / from remaining unless remaining is 0 then returns 0. Returns hashmap containing damage and status. Has a 1/5
        / chance of burning enemy.
         */
        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(this.pokemonStatus.BurnChance() == 3){
            this.status = "Burned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(999, "Normal");
            return moveResult; }
        else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super Effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult; }
        else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very Effective");
            moveResult.put(this.getDamage() / 2, this.status);
            return moveResult; }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
            return moveResult;
        }
    }
}

class Flamethrower extends Attack{
    // initializes Flamethrower attack
    PokemonStatus pokemonStatus;
    String status;

    public Flamethrower(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        /*
        / Carries out attack. If type Grass, damage is doubled. If type Water or Rock, damage is halved. Subtracts 1
        / from remaining unless remaining is 0 then returns 0. Returns hashmap containing damage and status. Has a 1/5
        / chance of burning enemy.
         */

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(this.pokemonStatus.BurnChance() == 3){
            this.status = "Burned";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(999, "Normal");
            return moveResult;
        } else if (type.equals("Grass")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super Effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult;
        } else if (type.equals("Water") || type.equals("Rock")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's not very Effective");
            moveResult.put(this.getDamage() / 2, this.status);
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
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
            moveResult.put(999, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }

}
