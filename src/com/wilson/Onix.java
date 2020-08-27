package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Onix extends Pokemon {
    private Rage rage;
    private RockThrow rockThrow;
    private RockSmash rockSmash;
    private Bind bind;
    Scanner scanner = new Scanner(System.in);

    public Onix(String name, String type, int level, int health, int maxHealth, String status, Rage rage,
                RockThrow rockThrow, RockSmash rockSmash, Bind bind) {
        super(name, type, level, health, maxHealth, status);
        this.rage = rage;
        this.rockThrow = rockThrow;
        this.rockSmash =rockSmash;
        this.bind = bind;
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
        return new String[]{"Type: " + onix.getType(),"Bind damage: " + String.valueOf(onix.getBind().getDamage()) + " PP: " + String.valueOf(onix.getBind().getPp()),
                "Rage damage: " + String.valueOf(onix.getRage().getDamage()) + " PP: " + String.valueOf(onix.getRage().getPp()),
                "Rock Smash damage: " + String.valueOf(onix.getRockSmash().getDamage()) + " PP: " + String.valueOf(onix.getRockSmash().getPp()),
                "Rock Throw: " + String.valueOf(onix.getRockThrow().getDamage()) + " PP: " + String.valueOf(onix.getRockThrow().getPp())};
    }

    public int OnixBattle(Player user, Object[] userPokemon){
        Battlemenu battlemenu = new Battlemenu();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            OnixAttacks();;
        } else if (selection == 2){
            battlemenu.ChangePokemon(user, userPokemon);
        } else if(selection == 3){
            OnixItems(battlemenu.UseItem(user));
        }
        return 1;
    }

    public void OnixAttacks(){
        System.out.println("1. Bind PP: " + getBind().getPp() + "/" +getBind().getMaxRemains() +
                "\n2. Rage PP: " + getRage().getPp() + "/" + getRage().getMaxRemains() +
                "\n3. Rock Smash PP: " + getRockSmash().getPp() + "/" + getRockSmash().getMaxRemains() +
                "\n4. Rock Throw PP: " + getRockThrow().getPp() + "/" + getRockThrow().getMaxRemains());
    }

    public String OnixItems(String item){
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Bind\n 2.Rage\n3. Rock Smash\n4. Rock Throw");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                return getBind().useElixer("Elixer");
            }else if (restore == 2){
                return getRage().useElixer("ELixer");
            } else if (restore == 3){
                return getRockSmash().useElixer("Elixer");
            } else if (restore == 4){
                return getRockThrow().useElixer("Elixer");
            }
        }
        return use_item(item);
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
            moveResult.put(0, "Normal");
        } else {
            this.setPp(this.getPp() - 1);
            moveResult.put(this.getDamage(), "Normal");
        }
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
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Flying") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
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
            moveResult.put(0, "Normal");
            return moveResult;
        }else if (type.equals("Flying") || type.equals("Fire")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very Effective");
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

class Bind extends Attack{
    // Initializes Bind attack
    public Bind(int damage, int remaining, int maxRemains) {
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

class OnixBattle{

    public String menu(Onix onix){
        return "Here you are";
    }

}