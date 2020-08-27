package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Squirtle extends Pokemon {
    private HydroPump HydroPump;
    private Tackle tackle;
    private Surf surf;
    private ShellAttack shellAttack;
    private String attackName;
    Battlemenu battlemenu = new Battlemenu();
    Scanner scanner = new Scanner(System.in);

    public Squirtle(String name, String type, int level, int health, int maxHealth, String status, HydroPump hydroPump, Tackle tackle,
                    Surf surf, ShellAttack shellAttack) {
        super(name, type, level, health, maxHealth, status);
        this.HydroPump = hydroPump;
        this.tackle = tackle;
        this.surf = surf;
        this.shellAttack = shellAttack;
    }

    public HydroPump getHydroPump() {
        return HydroPump;
    }

    public Tackle getTackle() {
        return tackle;
    }

    public Surf getSurf() {
        return surf;
    }

    public ShellAttack getShellAttack() {
        return shellAttack;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public String[] DisplaySquirtle(Squirtle squirtle) {
        return new String[]{"Type: " + squirtle.getType(), "Growl damage: " + squirtle.getSurf().getDamage() + " PP: " + squirtle.getSurf().getPp(),
                "Quick Attack damage: " + squirtle.getHydroPump().getDamage() + " PP: " + squirtle.getHydroPump().getPp(),
                "Thunder damage: " + squirtle.getShellAttack().getDamage() + " PP: " + squirtle.getShellAttack().getPp(),
                "Thunder Shock Throw: " + squirtle.getTackle().getDamage() + " PP: " + squirtle.getTackle().getPp()};
    }

    public Map<Integer, String> SquirtleBattle(Player user, Object[] userPokemon, String cpuType){
        Map<Integer, String> move = new HashMap<>();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return SquirtleAttacks(cpuType);
        } else if (selection == 2){
            battlemenu.ChangePokemon(user, userPokemon);
        } else if(selection == 3){
            SquirtleItems(battlemenu.UseItem(user));
        }
        return move;
    }

    public Map<Integer, String> SquirtleAttacks(String cpuType){
        Map<Integer, String> move = new HashMap<>();
        System.out.println("1. Tackle PP: " + getTackle().getPp() + "/" +getTackle().getMaxRemains() +
                "\n2. Shell Attack PP: " + getShellAttack().getPp() + "/" + getShellAttack().getMaxRemains() +
                "\n3. Hydro pump PP: " + getHydroPump().getPp() + "/" + getHydroPump().getMaxRemains() +
                "\n4. Surf PP: " + getSurf().getPp() + "/" + getSurf().getMaxRemains());
        int attack = Integer.parseInt(scanner.nextLine());
        switch (attack){
            case 1:
                setAttackName("Tackle");
                return getTackle().attack(cpuType);
            case 2:
                setAttackName("Shell Attack");
                return getShellAttack().attack(cpuType);
            case 3:
                setAttackName("Hydro Pump");
                return getHydroPump().attack(cpuType);
            case 4:
                setAttackName("Surf");
                return getSurf().attack(cpuType);
        }
        return move;
    }

    public String SquirtleItems(String item){
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Tackle\n 2.Shell Attack\n3. Hydro Pump\n4. Surf");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                return getTackle().useElixer("Elixer");
            }else if (restore == 2){
                return getShellAttack().useElixer("ELixer");
            } else if (restore == 3){
                return getHydroPump().useElixer("Elixer");
            } else if (restore == 4){
                return getSurf().useElixer("Elixer");
            }
        }
        return use_item(item);
    }
}

class HydroPump extends Attack{
    // Initializes HydroPump attack
    public HydroPump(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Fire or Rock, then damage is doubled. If type Water or Grass damage halved.
        // Subtracts 1 from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Fire") || type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Water") || type.equals("Grass")) {
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

class Tackle extends Attack{
    // Initializes Tackle attack
    public Tackle(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
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

class Surf extends Attack {
    // Initializes Surf attack
    public Surf(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Fire or Rock, then damage is doubled. If type Water or Grass damage halved.
        // Subtracts 1 from remaining unless remaining is 0 then returns 0. Returns hashmap of damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Fire") || type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Water") || type.equals("Grass")) {
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

class ShellAttack extends Attack{
    public ShellAttack(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
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
