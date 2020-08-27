package com.wilson;

import javax.xml.stream.FactoryConfigurationError;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bulbasaur extends Pokemon {
    private final VineWhip vineWhip;
    private final SludgeBomb sludgeBomb;
    private final RazorLeaf razorLeaf;
    private final LeechSeed leechSeed;
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
        this.restoreHealth();
        return leechSeed;
    }

    public void restoreHealth(){
        this.setHealth(this.getHealth() + leechSeed.getHeal());
    }

    public String[] DisplayBulbasaur(Bulbasaur bulbasaur){
        return new String[]{"Type: " + bulbasaur.getType(),"Leech Seed damage: " + String.valueOf(bulbasaur.getLeechSeed().getDamage()) + " PP: " + String.valueOf(bulbasaur.getLeechSeed().getPp()),
                "Razor Leaf damage: " + String.valueOf(bulbasaur.getRazorLeaf().getDamage()) + " PP: " + String.valueOf(bulbasaur.getRazorLeaf().getPp()),
                "Sludge Bomb damage: " + String.valueOf(bulbasaur.getSludgeBomb().getDamage()) + " PP: " + String.valueOf(bulbasaur.getSludgeBomb().getPp()),
                "Vine Whip damage: " + String.valueOf(bulbasaur.getVineWhip().getDamage()) + " PP: " + String.valueOf(bulbasaur.getVineWhip().getPp())};

    }

    public int BulbasuarBattle(Player user, Object[] userPokemon){
        Battlemenu battlemenu = new Battlemenu();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            BulbasuarAttacks();;
        } else if (selection == 2){
            battlemenu.ChangePokemon(user, userPokemon);
        } else if(selection == 3){
            BulbasuarItems(battlemenu.UseItem(user));
        }
        return 1;
    }

    public void BulbasuarAttacks(){
        System.out.println("1. Razor Leaf PP: " + getRazorLeaf().getPp() + "/" +getRazorLeaf().getMaxRemains() +
                "\n2. Leech Seed PP: " + getLeechSeed().getPp() + "/" + getLeechSeed().getMaxRemains() +
                "\n3. Sludge bomb PP: " + getSludgeBomb().getPp() + "/" + getSludgeBomb().getMaxRemains() +
                "\n4. Vine whip PP: " + getVineWhip().getPp() + "/" + getVineWhip().getMaxRemains());
    }

    public String BulbasuarItems(String item){
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Razor Leaf\n 2. Leech Seed\n3. Sludge bomb\n4. Vine whip");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                return getLeechSeed().useElixer("Elixer");
            }else if (restore == 2){
                return getLeechSeed().useElixer("ELixer");
            } else if (restore == 3){
                return getSludgeBomb().useElixer("Elixer");
            } else if (restore == 4){
                return getVineWhip().useElixer("Elixer");
            }
        }
        return use_item(item);
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
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Rock") || (type.equals("Water"))){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Flying") || type.equals("Fire")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
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

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), "Normal");
            return moveResult;
        }
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
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Rock") || (type.equals("Water"))){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Flying") || type.equals("Fire")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult;
        }
        else {
            this.setPp(this.getPp() - 1);
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

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Bulbasaur is health is
        // restored by the amount of damage done to the enemy
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            this.setHeal(this.getDamage());
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
