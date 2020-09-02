package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bulbasaur extends Pokemon {
    private final VineWhip vineWhip;
    private final SludgeBomb sludgeBomb;
    private final RazorLeaf razorLeaf;
    private final LeechSeed leechSeed;
    private final Battlemenu battlemenu;
    Scanner scanner = new Scanner(System.in);

    public Bulbasaur(String name, String type, int level, int health, int maxHealth, String status, VineWhip vineWhip,
                     SludgeBomb sludgeBomb, RazorLeaf razorLeaf, LeechSeed leechSeed, Battlemenu battlemenu) {
        super(name, type, level, health, maxHealth, status);
        this.vineWhip = vineWhip;
        this.sludgeBomb = sludgeBomb;
        this.razorLeaf = razorLeaf;
        this.leechSeed = leechSeed;
        this.battlemenu = battlemenu;
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

    public LeechSeed getLeechSeed(boolean healing) {
        if (healing){
            restoreHealth();
        }
        return leechSeed;

    }

    public void restoreHealth(){
        this.gainHealth(leechSeed.getHeal());
    }

    public String[] DisplayBulbasaur(Bulbasaur bulbasaur){
        return new String[]{"Type: " + bulbasaur.getType(),"Attacks: Leech Seed damage: " + bulbasaur.getLeechSeed(false).getDamage() + " PP: " + bulbasaur.getLeechSeed(false).getPp(),
                "Razor Leaf damage: " + bulbasaur.getRazorLeaf().getDamage() + " PP: " + bulbasaur.getRazorLeaf().getPp(),
                "Sludge Bomb damage: " + bulbasaur.getSludgeBomb().getDamage() + " PP: " + bulbasaur.getSludgeBomb().getPp(),
                "Vine Whip damage: " + bulbasaur.getVineWhip().getDamage() + " PP: " + bulbasaur.getVineWhip().getPp()};

    }

    public Map<Integer, String> BulbasuarBattle(Player user, Object[] userPokemon, String cpuType, int activePokemon){
        Map<Integer, String> move = new HashMap<>();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return BulbasuarAttacks(cpuType); }
        else if (selection == 2){
            return battlemenu.ChangePokemon(user, userPokemon, activePokemon); }
        else if(selection == 3){
            if (user.getBag().isEmpty()){
                System.out.println("You have no items to use.");
                BulbasuarBattle(user, userPokemon, cpuType, activePokemon); }
            return BulbasuarItems(battlemenu.UseItem(user), user); }
        else {
            System.out.println("Not a valid option");
            BulbasuarBattle(user, userPokemon, cpuType, activePokemon); }
        return move;
    }

    public Map<Integer, String> BulbasuarAttacks(String cpuType){
        Map<Integer, String> move = new HashMap<>();
        System.out.println("Enter number of attack...");
        System.out.println("1. Razor Leaf PP: " + getRazorLeaf().getPp() + "/" +getRazorLeaf().getMaxRemains() +
                "\n2. Leech Seed PP: " + getLeechSeed(false).getPp() + "/" + getLeechSeed(false).getMaxRemains() +
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
                move = getLeechSeed(true).attack();
                setAttackStrength(getLeechSeed(false).getStrength());
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

    public Map<Integer, String> BulbasuarItems(String item, Player user){
        Map<Integer, String> itemMap = new HashMap<>();
        itemMap.put(0, item);
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Razor Leaf\n 2. Leech Seed\n3. Sludge bomb\n4. Vine whip");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getLeechSeed(false).useElixer("Elixer");
            }else if (restore == 2){
                getLeechSeed(false).useElixer("Eixer");
            } else if (restore == 3){
                getSludgeBomb().useElixer("Elixer");
            } else if (restore == 4){
                getVineWhip().useElixer("Elixer");
            } } else {
            use_item(item);
        }
        user.useItem(item);
        setAttackName(item);
        setAttackStrength("Normal");
        return itemMap;
    }

    public boolean BulbasaurStatus(Bulbasaur bulbasaur){
        // If pokemon status anything other than normal, function is called. Returns true if bulbausaur cannot make move
        // and true if able to
        if (bulbasaur.getStatus().equals("Asleep")){
            if (bulbasaur.WakeUp()){
                bulbasaur.setStatus("Normal");
                System.out.println(bulbasaur.getName() + " woke up");
            } else {
                System.out.println(bulbasaur.getName() + " is asleep. Cannot make a move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }} else if (bulbasaur.getStatus().equals("Burned")){
            System.out.println("Bulbasuar is burned. Lost 10 health.");
            bulbasaur.Burn();
        } else if (bulbasaur.getStatus().equals("Poisoned")){
            System.out.println("Bulbasuar is poisoned. Lost 10 health.");
            bulbasaur.Poisioned();
        } else  if (bulbasaur.getStatus().equals("Paralyzed")){
            if (bulbasaur.Paralyzed()){
                System.out.println("Bulbasuar is paralyzed and cannot move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }
        } else if (bulbasaur.getStatus().equals("Confused")){
            if (bulbasaur.Confusion()){
                System.out.println("Bulbasuar is confused. Bulbasuar hurt itself and cannot make a move. Lost 10 health");
                battlemenu.pressAnyKeyToContinue();
                return true;
            } else {
                System.out.println("Bulbasuar snapped out of confusion");
                bulbasaur.setStatus("Normal");
            }
        }
        return false;
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
            moveResult.put(999, "Normal");
            return moveResult; }
        else if (type.equals("Rock") || (type.equals("Water"))){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult; }
        else if (type.equals("Flying") || type.equals("Fire")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult; }
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
            moveResult.put(999, "Normal"); }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal"); }
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
            moveResult.put(999, "Normal");
            return moveResult; }
        else if (type.equals("Rock") || (type.equals("Water"))){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            this.setStatus("Normal");
            return moveResult; }
        else if (type.equals("Flying") || type.equals("Fire")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
            moveResult.put(this.getDamage() / 2, "Normal");
            return moveResult; }
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
            moveResult.put(999, "Normal"); }
        else {
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
