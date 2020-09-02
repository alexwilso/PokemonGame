package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class Pikachu extends Pokemon {
    private final QuickAttack quickAttack;
    private final ThunderShock thunderShock;
    private final Thunder thunder;
    private final Growl growl;
    Battlemenu battlemenu;
    Scanner scanner = new Scanner(System.in);

    public Pikachu(String name, String type, int level, int health, int maxHealth, String status, QuickAttack quickAttack,
    ThunderShock thunderShock, Thunder thunder, Growl growl, Battlemenu battlemenu) {
        super(name, type, level, health, maxHealth, status);
        this.quickAttack = quickAttack;
        this.thunderShock = thunderShock;
        this.thunder = thunder;
        this.growl = growl;
        this.battlemenu = battlemenu;
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

    public String[] DisplayPikachu(Pikachu pikachu){
        return new String[]{"Type: " + pikachu.getType(),"Attacks: Growl damage: " + pikachu.getGrowl().getDamage() + " PP: " + pikachu.getGrowl().getPp(),
                "Quick Attack damage: " + pikachu.getQuickAttack().getDamage() + " PP: " + pikachu.getQuickAttack().getPp(),
                "Thunder damage: " + pikachu.getThunder().getDamage() + " PP: " + pikachu.getThunder().getPp(),
                "Thunder Shock Throw: " + pikachu.getThunderShock().getDamage() + " PP: " + pikachu.getThunderShock().getPp()};
    }

    public Map<Integer, String> PikachuBattle(Player user, Object[] userPokemon, String cpuType, int activePokemon){
        Map<Integer, String> move = new HashMap<>();
        int selection = battlemenu.Menu(getName());
        if (selection == 1){
            return PikachuAttacks(cpuType);}
        else if (selection == 2){
            return battlemenu.ChangePokemon(user, userPokemon, activePokemon); }
        else if(selection == 3){
            if (user.getBag().isEmpty()){
                System.out.println("You have no items to use.");
                PikachuBattle(user, userPokemon, cpuType, activePokemon);}
            return PikachuItems(battlemenu.UseItem(user), user); }
        else {
            System.out.println("Not a valid option");
            PikachuBattle(user, userPokemon, cpuType, activePokemon);}
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
                move = getQuickAttack().attack(cpuType);
                setAttackStrength(getQuickAttack().getStrength());
                break;
            case 2:
                setAttackName("Thunder Shock");
                move = getThunderShock().attack(cpuType);
                setAttackStrength(getThunderShock().getStrength());
                break;
            case 3:
                setAttackName("Thunder");
                move = getThunder().attack(cpuType);
                setAttackStrength(getThunder().getStrength());
                break;
            case 4:
                setAttackName("Growl");
                move = getGrowl().attack(cpuType);
                setAttackStrength(getGrowl().getStrength());
                break;
        }
        return move;
    }

    public Map<Integer, String> PikachuItems(String item, Player user){
        Map<Integer, String> itemMap = new HashMap<>();
        itemMap.put(0, item);
        if (item.equals("Elixer")) {
            System.out.println("Which attack would you like to user elixer on?");
            System.out.println("Enter number: 1. Quick Attack\n 2.Thunder Shock\n3. Thunder\n4. Growl");
            int restore = Integer.parseInt(scanner.nextLine());
            if (restore == 1){
                getQuickAttack().useElixer("Elixer");
            }else if (restore == 2){
                getThunderShock().useElixer("ELixer");
            } else if (restore == 3){
                getThunder().useElixer("Elixer");
            } else if (restore == 4){
                getGrowl().useElixer("Elixer");
            } } else {
                use_item(item);
            }
        user.useItem(item);
        setAttackName(item);
        setAttackStrength("Normal");
        return itemMap;
    }

    public boolean PikachuStatus(Pikachu pikachu){
        // If pokemon status anything other than normal, function is called. Returns true if pikachu cannot make move
        // and true if able to
        if (pikachu.getStatus().equals("Asleep")){
            if (pikachu.WakeUp()){
                pikachu.setStatus("Normal");
                System.out.println(pikachu.getName() + " woke up");
            } else {
                System.out.println(pikachu.getName() + " is asleep. Cannot make a move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }} else if (pikachu.getStatus().equals("Burned")){
            System.out.println("Pikachu is burned. Lost 10 health.");
            pikachu.Burn();
        } else if (pikachu.getStatus().equals("Poisoned")){
            System.out.println("Pikachu is poisoned. Lost 10 health.");
            pikachu.Poisioned();
        } else  if (pikachu.getStatus().equals("Paralyzed")){
            if (pikachu.Paralyzed()){
                System.out.println("Pikachu is paralyzed and cannot move");
                battlemenu.pressAnyKeyToContinue();
                return true;
            }
        } else if (pikachu.getStatus().equals("Confused")){
            if (pikachu.Confusion()){
                System.out.println("Pikachu is confused. Pikachu hurt itself and cannot make a move. Lost 10 health");
                battlemenu.pressAnyKeyToContinue();
                return true;
            } else {
                System.out.println("Pikachu snapped out of confusion");
                pikachu.setStatus("Normal");
            }
        }
        return false;
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
            moveResult.put(999, "Normal"); }
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal"); }
        return moveResult;
    }
}

class ThunderShock extends Attack{
    // Initializes ThunderShock attack
    PokemonStatus pokemonStatus;
    String status;
    public ThunderShock(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.
        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(this.pokemonStatus.ParalyzeChance() == 1){
            this.status = "Paralyzed";
        }
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(999, "Normal");
            return moveResult;}
        else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, this.status);
            return moveResult; }
        else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
            moveResult.put(this.getDamage() / 2, this.status);
            return moveResult;}
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), this.status);
            return moveResult;
        }
    }
}

class Thunder extends Attack{
    // Initializes Thunder attack
    PokemonStatus pokemonStatus;
    String status;
    public Thunder(int damage, int remaining, int maxRemains, PokemonStatus pokemonStatus) {
        super(damage, remaining, maxRemains);
        this.pokemonStatus = pokemonStatus;
    }

    public Map<Integer, String> attack(String type){
        // Carries out attack. If type Water, then damage is doubled. If type Rock, damage halved. Subtracts 1
        // from remaining unless remaining is 0 then returns 0. Returns hashmap with damage and status.

        Map<Integer, String> moveResult = new HashMap<>();
        this.status = "Normal";
        if(this.pokemonStatus.ParalyzeChance() == 1){
            this.status = "Paralyzed";
        }

        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            moveResult.put(999, "Normal");
            return moveResult; }
        else if (type.equals("Water")){
            this.setPp(this.getPp() - 1);
            setStrength("It's super effective");
            moveResult.put(this.getDamage() * 2, "Normal");
            return moveResult; }
        else if (type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            setStrength("It's not very effective");
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
            moveResult.put(999, "Normal");}
        else {
            this.setPp(this.getPp() - 1);
            setStrength("Normal");
            moveResult.put(this.getDamage(), "Normal"); }
        return moveResult;
    }
}
