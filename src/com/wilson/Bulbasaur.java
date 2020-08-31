package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bulbasaur extends Pokemon {
    private final VineWhip vineWhip;
    private final SludgeBomb sludgeBomb;
    private final RazorLeaf razorLeaf;
    private final LeechSeed leechSeed;
    Battlemenu battlemenu = new Battlemenu();
    Scanner scanner = new Scanner(System.in);

    public Bulbasaur(String name, String type, int level, int health, int maxHealth, String status, VineWhip vineWhip,
                     SludgeBomb sludgeBomb, RazorLeaf razorLeaf, LeechSeed leechSeed) {
        super(name, type, level, health, maxHealth, status);
        this.vineWhip = vineWhip;
        this.sludgeBomb = sludgeBomb;
        this.razorLeaf = razorLeaf;
        this.leechSeed = leechSeed;
    }

    public VineWhip getVineWhip() {
        return vineWhip;
    }

    public SludgeBomb getSludgeBomb() {
        return sludgeBomb;
    }

    public RazorLeaf getRazorLeaf() {
        return razorLeaf;
    }

    public LeechSeed getLeechSeed() {
        return leechSeed;

    }

    public void restoreHealth(){
        this.setHealth(this.getHealth() + leechSeed.getHeal());
    }

    public String[] DisplayBulbasaur(Bulbasaur bulbasaur){
        return new String[]{"Type: " + bulbasaur.getType(),"Leech Seed damage: " + bulbasaur.getLeechSeed().getDamage() + " PP: " + bulbasaur.getLeechSeed().getPp(),
                "Razor Leaf damage: " + bulbasaur.getRazorLeaf().getDamage() + " PP: " + bulbasaur.getRazorLeaf().getPp(),
                "Sludge Bomb damage: " + bulbasaur.getSludgeBomb().getDamage() + " PP: " + bulbasaur.getSludgeBomb().getPp(),
                "Vine Whip damage: " + bulbasaur.getVineWhip().getDamage() + " PP: " + bulbasaur.getVineWhip().getPp()};

    }

    public Map<Integer, String> BulbasuarBattle(Player user, Object[] userPokemon, String cpuType){
        Map<Integer, String> move = new HashMap<>();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return BulbasuarAttacks(cpuType);
        } else if (selection == 2){
            return battlemenu.ChangePokemon(user, userPokemon);
        } else if(selection == 3){
            if (user.getBag().isEmpty()){
                System.out.println("You have no items to use.");
                BulbasuarBattle(user, userPokemon, cpuType);
            } else {
                return BulbasuarItems(battlemenu.UseItem(user));
            }
        }
        return move;
    }

    public Map<Integer, String> BulbasuarAttacks(String cpuType){
        Map<Integer, String> move = new HashMap<>();
        System.out.println("Enter number of attack...");
        System.out.println("1. Razor Leaf PP: " + getRazorLeaf().getPp() + "/" +getRazorLeaf().getMaxRemains() +
                "\n2. Leech Seed PP: " + getLeechSeed().getPp() + "/" + getLeechSeed().getMaxRemains() +
                "\n3. Sludge bomb PP: " + getSludgeBomb().getPp() + "/" + getSludgeBomb().getMaxRemains() +
                "\n4. Vine whip PP: " + getVineWhip().getPp() + "/" + getVineWhip().getMaxRemains());
        int attack = Integer.parseInt(scanner.nextLine());
        switch (attack){
            case 1:
                setAttackName("Razor Leaf");
                move = getRazorLeaf().attack(cpuType);
                setAttackStrength(getRazorLeaf().getStrength());
                break;
            case 2:
                setAttackName("Leech Seed");
                move = getLeechSeed().attack();
                setAttackStrength(getLeechSeed().getStrength());
                break;
            case 3:
                setAttackName("Sludge Bomb");
                move = getSludgeBomb().attack();
                setAttackStrength(getSludgeBomb().getStrength());
                break;
            case 4:
                setAttackName("Vine whip");
                move = getVineWhip().attack(cpuType);
                setAttackStrength(getVineWhip().getStrength());
                break;
        }
        return move;
    }

    public Map<Integer, String> BulbasuarItems(String item){
        Map<Integer, String> itemMap = new HashMap<>();
        itemMap.put(0, item);
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Razor Leaf\n 2. Leech Seed\n3. Sludge bomb\n4. Vine whip");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getLeechSeed().useElixer("Elixer");
            }else if (restore == 2){
                getLeechSeed().useElixer("Eixer");
            } else if (restore == 3){
                getSludgeBomb().useElixer("Elixer");
            } else if (restore == 4){
                getVineWhip().useElixer("Elixer");
            }
        }
        use_item(item);
        return itemMap;
    }
}

class VineWhip extends Attack{
    // Initializes VineWhip attack
    public VineWhip(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Ground or Water, damage is doubled. If type Flying or Fire, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "False");
            return moveResult;
        } else if (type.equals("Rock") || (type.equals("Water"))){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Flying") || type.equals("Fire")){
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

class SludgeBomb extends Attack{
    // Initializes SludgeBomb attack
    public SludgeBomb(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Sludge bomb");
        } else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }
}

class RazorLeaf extends Attack{
    // Initializes RazorLeaf attack
    public RazorLeaf(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Ground or Water, damage is doubled. If type Flying or Fire, damage halved.
        // Subtracts 1 from remaining unless remaining is 0 then returns 0.
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Razor leaf");
            return moveResult;
        } else if (type.equals("Rock") || (type.equals("Water"))){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            this.setStatus("Normal");
            return moveResult;
        } else if (type.equals("Flying") || type.equals("Fire")){
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

class LeechSeed extends Attack{
    // Initializes LeechSeed attack
    private int heal;

    public LeechSeed(int damage, int remaining, int maxRemains, int heal) {
        super(damage, remaining, maxRemains);
        this.heal = heal;
    }

    public Map<Integer, String> attack(){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Bulbasaur is health is
        // restored by the amount of damage done to the enemy
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "False");
        } else {
            this.setPp(this.getPp() - 1);
            this.setHeal(this.getDamage());
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");
        }
        return moveResult;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}
