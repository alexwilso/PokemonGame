package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Onix extends Pokemon {
    private final Rage rage;
    private final RockThrow rockThrow;
    private final RockSmash rockSmash;
    private final Bind bind;
    private final Battlemenu battlemenu;
    Scanner scanner = new Scanner(System.in);

    public Onix(String name, String type, int level, int health, int maxHealth, String status, Rage rage,
                RockThrow rockThrow, RockSmash rockSmash, Bind bind, Battlemenu battlemenu) {
        super(name, type, level, health, maxHealth, status);
        this.rage = rage;
        this.rockThrow = rockThrow;
        this.rockSmash =rockSmash;
        this.bind = bind;
        this.battlemenu = battlemenu;
    }

    public Rage getRage() {
        return rage;
    }

    public RockThrow getRockThrow() {
        return rockThrow;
    }

    public RockSmash getRockSmash() {
        return rockSmash;
    }

    public Bind getBind() {
        return bind;
    }

    public String[] DisplayOnix(Onix onix){
        return new String[]{"Type: " + onix.getType(),"Attacks: Bind damage: " + onix.getBind().getDamage() + " PP: " + onix.getBind().getPp(),
                "Rage damage: " + onix.getRage().getDamage() + " PP: " + onix.getRage().getPp(),
                "Rock Smash damage: " + onix.getRockSmash().getDamage() + " PP: " + onix.getRockSmash().getPp(),
                "Rock Throw: " + onix.getRockThrow().getDamage() + " PP: " + onix.getRockThrow().getPp()};
    }

    public Map<Integer, String> OnixBattle(Player user, Object[] userPokemon, String cpuType, int activePokemon){
        Map<Integer, String> move = new HashMap<>();
        boolean noneFainted = true;
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return OnixAttacks(cpuType);}
        else if (selection == 2){
            return battlemenu.ChangePokemon(user, userPokemon, activePokemon);}
        else if(selection == 3){
            if (user.getBag().isEmpty()){
                System.out.println("You have no items to use.");
                return OnixBattle(user, userPokemon, cpuType, activePokemon); }
            String item = battlemenu.UseItem(user);
            if (item.equals("Revive")) {
                for (int x = 0; x<3; x++){
                    if (user.getFaintedPokemon()[x] != null){
                        noneFainted = false; } }
                if (noneFainted){
                    System.out.println("All pokemon are playable");
                    return OnixBattle(user, userPokemon, cpuType, activePokemon); } }
            return OnixItems(item, user); }
        else {
            System.out.println("Not a valid option");
            OnixBattle(user, userPokemon, cpuType, activePokemon);
        }
        return move;
    }

    public  Map<Integer, String> OnixAttacks(String cpuType){
        Map<Integer, String> move = new HashMap<>();
        System.out.println("1. Bind PP: " + getBind().getPp() + "/" +getBind().getMaxRemains() +
                "\n2. Rage PP: " + getRage().getPp() + "/" + getRage().getMaxRemains() +
                "\n3. Rock Smash PP: " + getRockSmash().getPp() + "/" + getRockSmash().getMaxRemains() +
                "\n4. Rock Throw PP: " + getRockThrow().getPp() + "/" + getRockThrow().getMaxRemains());
        int attack = Integer.parseInt(scanner.nextLine());
        switch (attack){
            case 1:
                setAttackName("Bind");
                move = getBind().attack(cpuType);
                setAttackStrength(getBind().getStrength());
                break;
            case 2:
                setAttackName("Rage");
                move = getRage().attack(cpuType);
                setAttackStrength(getRage().getStrength());
                break;
            case 3:
                setAttackName("Rock Smash");
                move = getRockSmash().attack(cpuType);
                setAttackStrength(getRockSmash().getStrength());
                break;
            case 4:
                setAttackName("Rock Throw");
                move = getRockThrow().attack(cpuType);
                setAttackStrength(getRockThrow().getStrength());
                break;
        }
        return move;
    }

    public Map<Integer, String> OnixItems(String item, Player user){
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
            System.out.println("Enter number: 1. Bind\n 2.Rage\n3. Rock Smash\n4. Rock Throw");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getBind().useElixer("Elixer");
            }else if (restore == 2){
                getRage().useElixer("Elixer");
            } else if (restore == 3){
                getRockSmash().useElixer("Elixer");
            } else if (restore == 4){
                getRockThrow().useElixer("Elixer");
            } } else {
            use_item(item);
        }
        user.useItem(item);
        setAttackName(item);
        setAttackStrength("Normal");
        return itemMap;
    }

    public boolean OnixStatus(Onix onix) {
        // If pokemon status anything other than normal, function is called. Returns true if onix cannot make move
        // and true if able to
        if (onix.getStatus().equals("Asleep")) {
            if (onix.WakeUp()) {
                onix.setStatus("Normal");
                System.out.println(onix.getName() + " woke up");
            } else {
                System.out.println(onix.getName() + " is asleep. Cannot make a move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }
        } else if (onix.getStatus().equals("Burned")) {
            System.out.println("Onix is burned. Lost 10 health.");
            onix.Burn();
        } else if (onix.getStatus().equals("Poisoned")) {
            System.out.println("Onix is poisoned. Lost 10 health.");
            onix.Poisioned();
        } else if (onix.getStatus().equals("Paralyzed")) {
            if (onix.Paralyzed()) {
                System.out.println("Onix is paralyzed and cannot move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }
        } else if (onix.getStatus().equals("Confused")) {
            if (onix.Confusion()) {
                System.out.println("Onix is confused. Onix hurt itself and cannot make a move. Lost 10 health");
                battlemenu.pressAnyKeyToContinue();
                return true;
            } else {
                System.out.println("Onix snapped out of confusion");
                onix.setStatus("Normal");
            }
        }
        return false;
    }
}

class Rage extends Attack{
    // Initializes rage attack
    public Rage(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status

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

class RockThrow extends Attack{
    // Initializes RockThrow attack
    public RockThrow(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Flying or Fire, then damage is doubled. If type Rock, damage is halved. Subtracts
        // 1 from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(999, "Normal");}
        else if (type.equals("Flying") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");}
        else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very Effective");
            moveResult.put(this.getDamage() / 2, "Normal");}
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal");}
        return moveResult;
    }
}

class RockSmash extends Attack{
    // Initializes RockSmash attack
    public RockSmash(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Flying or Fire, then damage is doubled. If type Rock, damage is halved. Subtracts
        // 1 from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status
        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack Remaining");
            moveResult.put(999, "Normal");
            return moveResult;}
        else if (type.equals("Flying") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult; }
        else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very Effective");
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

class Bind extends Attack {
    // Initializes Bind attack
    public Bind(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type) {
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // damage and status

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
