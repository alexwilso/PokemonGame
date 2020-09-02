package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class Gengar extends Pokemon {
    private final PoisonJab poisonJab;
    private final ConfusionRay confusionRay;
    private final Lick lick;
    private final ShadowBall shadowBall;
    private final Battlemenu battlemenu;
    Scanner scanner = new Scanner(System.in);

    public Gengar(String name, String type, int level, int health, int maxHealth, String status,  PoisonJab poisonJab,
                  ConfusionRay confusionRay, Lick lick, ShadowBall shadowBall, Battlemenu battlemenu) {
        super(name, type, level, health, maxHealth, status);
        this.poisonJab = poisonJab;
        this.confusionRay = confusionRay;
        this.lick = lick;
        this.shadowBall = shadowBall;
        this.battlemenu = battlemenu;
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

    public String[] DisplayGengar(Gengar gengar){
        return new String[]{"Type: " + gengar.getType(),"Attacks: Poison jab damage: " + gengar.getPoisonJab().getDamage() + " PP: " + gengar.getPoisonJab().getPp(),
                "Shadowball damage: " + gengar.getShadowBall().getDamage() + " PP: " + gengar.getShadowBall().getPp(),
                "Confusion Ray damage: " + gengar.getConfusionRay().getDamage() + " PP: " + gengar.getConfusionRay().getPp(),
                "Lick damage: " + gengar.getLick().getDamage() + " PP: " + gengar.getLick().getPp()};
    }

    public Map<Integer, String> GengarBattle(Player user, Object[] userPokemon, String cpuType, int activePokemon){
        Map<Integer, String> move = new HashMap<>();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return GengarAttacks(cpuType);}
        else if (selection == 2){
            return battlemenu.ChangePokemon(user, userPokemon, activePokemon); }
        else if(selection == 3){
            if (user.getBag().isEmpty()){
                System.out.println("You have no items to use.");
                GengarBattle(user, userPokemon, cpuType, activePokemon);}
            return GengarItems(battlemenu.UseItem(user), user);}
        else {
            System.out.println("Not a valid option");
            GengarBattle(user, userPokemon, cpuType, activePokemon);
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
                move = getConfusionRay().attack();
                setAttackStrength(getConfusionRay().getStrength());
                break;
            case 2:
                setAttackName("Lick");
                move = getLick().attack();
                setAttackStrength(getLick().getStrength());
                break;
            case 3:
                setAttackName("Poison Jab");
                move = getPoisonJab().attack(cpuType);
                setAttackStrength(getPoisonJab().getStrength());
                break;
            case 4:
                setAttackName("Shadow Ball");
                move = getShadowBall().attack(cpuType);
                setAttackStrength(getShadowBall().getStrength());
                break;
        }
        return move;
    }

    public Map<Integer, String> GengarItems(String item, Player user){
        Map<Integer, String> itemMap = new HashMap<>();
        itemMap.put(0, item);
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Confusion Ray\n 2.Lick\n3. Poison Jab \n4. Shadow Ball");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getConfusionRay().useElixer("Elixer");
            }else if (restore == 2){
                getLick().useElixer("Elixer");
            } else if (restore == 3){
                getPoisonJab().useElixer("Elixer");
            } else if (restore == 4){
                getShadowBall().useElixer("Elixer");
            } } else {
            use_item(item);
        }
        user.useItem(item);
        setAttackName(item);
        setAttackStrength("Normal");
        return itemMap;
    }

    public boolean GengarStatus(Gengar gengar){
        // If pokemon status anything other than normal, function is called. Returns true if gengar cannot make move
        // and true if able to
        if (gengar.getStatus().equals("Asleep")){
            if (gengar.WakeUp()){
                gengar.setStatus("Normal");
                System.out.println(gengar.getName() + " woke up");
            } else {
                System.out.println(gengar.getName() + " is asleep. Cannot make a move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }} else if (gengar.getStatus().equals("Burned")){
            System.out.println("Gengar is burned. Lost 10 health.");
            gengar.Burn();
        } else if (gengar.getStatus().equals("Poisoned")){
            System.out.println("Gengar is poisoned. Lost 10 health.");
            gengar.Poisioned();
        } else  if (gengar.getStatus().equals("Paralyzed")){
            if (gengar.Paralyzed()){
                System.out.println("Gengar is paralzyed and cannot move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }
        } else if (gengar.getStatus().equals("Confused")){
            if (gengar.Confusion()){
                System.out.println("Gengar is confused. Gengar hurt itself and cannot make a move. Lost 10 health");
                battlemenu.pressAnyKeyToContinue();
                return true;
            } else {
                System.out.println("Gengar snapped out of confusion");
                gengar.setStatus("Normal");
            }
        }
        return false;
    }
}

class PoisonJab extends Attack{
    // Initializes PoisonJab
    PokemonStatus pokemonStatus;
    String status;
    public PoisonJab(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Rock or Ghost, then damage is doubled. Subtract 1 from remaining unless remaining
        // is 0 then returns 0. Has 20% chance of poisoning opponent. Returns hashmap of damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(this.pokemonStatus.PoisonChance() == 3){
            this.status = "Poisoned";
        }
        if (this.getPp() == 0){
            System.out.println("No attack remaining");
            moveResult.put(999, "Normal");
            return moveResult; }
        else if(type.equals("Rock") || type.equals("Ghost")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult; }
        else {
            this.setPp(this.getPp() -1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
            return moveResult;
        }
    }
}

class ConfusionRay extends Attack{
    //  Initializes ConfusionRay attack
    PokemonStatus pokemonStatus;
    String status;
    public ConfusionRay(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Has a 20% chance of
        // confusing enemy. Returns hashmap with damage and status

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(pokemonStatus.ConfusionChance() == 2){
            this.status = "Confused"; }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(999, "Normal");}
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status); }
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
            moveResult.put(999, "Normal");}
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");}
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
            moveResult.put(999, "Normal");}
        else if(type.equals("Rock") || type.equals("Ghost")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");}
        else {
            this.setPp(this.getPp() -1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");}
        return moveResult;
    }
}


