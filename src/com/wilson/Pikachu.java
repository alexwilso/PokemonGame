package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pikachu extends Pokemon {
    private final QuickAttack quickAttack;
    private final ThunderShock thunderShock;
    private final Thunder thunder;
    private final Growl growl;
    private String attackName;
    Battlemenu battlemenu = new Battlemenu();
    Scanner scanner = new Scanner(System.in);

    public Pikachu(String name, String type, int level, int health, int maxHealth, String status, QuickAttack quickAttack,
    ThunderShock thunderShock, Thunder thunder, Growl growl) {
        super(name, type, level, health, maxHealth, status);
        this.quickAttack = quickAttack;
        this.thunderShock = thunderShock;
        this.thunder = thunder;
        this.growl = growl;
    }

    public QuickAttack getQuickAttack() {
        return quickAttack;
    }

    public ThunderShock getThunderShock() {
        return thunderShock;
    }

    public Thunder getThunder() {
        return thunder;
    }

    public Growl getGrowl() {
        return growl;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public String[] DisplayPikachu(Pikachu pikachu){
        return new String[]{"Type: " + pikachu.getType(),"Growl damage: " + pikachu.getGrowl().getDamage() + " PP: " + pikachu.getGrowl().getPp(),
                "Quick Attack damage: " + pikachu.getQuickAttack().getDamage() + " PP: " + pikachu.getQuickAttack().getPp(),
                "Thunder damage: " + pikachu.getThunder().getDamage() + " PP: " + pikachu.getThunder().getPp(),
                "Thunder Shock Throw: " + pikachu.getThunderShock().getDamage() + " PP: " + pikachu.getThunderShock().getPp()};
    }

    public Map<Integer, String> PikachuBattle(Player user, Object[] userPokemon, String cpuType){
        Map<Integer, String> move = new HashMap<>();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return PikachuAttacks(cpuType);
        } else if (selection == 2){
            battlemenu.ChangePokemon(user, userPokemon);
        } else if(selection == 3){
            PikachuItems(battlemenu.UseItem(user));
        }
        return move;
    }

    public Map<Integer, String> PikachuAttacks(String cpuType){
        Map<Integer, String> move = new HashMap<>();
        System.out.println("1. Quick Attack PP: " + getQuickAttack().getPp() + "/" +getQuickAttack().getMaxRemains() +
                "\n2. Thunder Shock PP: " + getThunderShock().getPp() + "/" + getThunderShock().getMaxRemains() +
                "\n3. Thunder PP: " + getThunder().getPp() + "/" + getThunder().getMaxRemains() +
                "\n4. Growl PP: " + getGrowl().getPp() + "/" + getGrowl().getMaxRemains());
        int attack = Integer.parseInt(scanner.nextLine());
        switch (attack){
            case 1:
                setAttackName("Quick Attack");
                return getQuickAttack().attack(cpuType);
            case 2:
                setAttackName("Thunder Shock");
                return getThunderShock().attack(cpuType);
            case 3:
                setAttackName("Thunder");
                return getThunder().attack(cpuType);
            case 4:
                setAttackName("Growl");
                return getGrowl().attack(cpuType);
        }
        return move;
    }

    public void PikachuItems(String item){
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Quick Attack\n 2.Thunder Shock\n3. Thunder\n4. Growl");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getQuickAttack().useElixer("Elixer");
                return;
            }else if (restore == 2){
                getThunderShock().useElixer("ELixer");
                return;
            } else if (restore == 3){
                getThunder().useElixer("Elixer");
                return;
            } else if (restore == 4){
                getGrowl().useElixer("Elixer");
                return;
            }
        }
        use_item(item);
    }
}

class QuickAttack extends Attack{
    // Initializes QuickAttack attack

    public QuickAttack(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // integer damage and string status
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

class ThunderShock extends Attack{
    // Initializes ThunderShock attack
    int pokemonStatus = new PokemonStatus().ParalyzeChance();
    public ThunderShock(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.
        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 1){
            status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, status);
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
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

class Thunder extends Attack{
    // Initializes Thunder attack
    int pokemonStatus = new PokemonStatus().ParalyzeChance();
    public Thunder(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        String status = "Normal";
        if(pokemonStatus == 1){
            status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(0, "Normal");
            return moveResult;
        } else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult;
        } else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
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


class Growl extends Attack{
    // Initializes Growl attack
    public Growl(int damage, int remaining, int maxRemains) {
        super(damage, remaining, maxRemains);
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0. Returns hashmap with
        // integer damage and string status
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

class PikcahuBattle{

    public String menu(Pikachu pikachu){
        return "Here you are";
    }

}