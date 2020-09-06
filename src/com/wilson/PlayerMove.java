package com.wilson;

import java.util.Map;
import java.util.jar.JarOutputStream;

public class PlayerMove {
    private int playerAttackDamage;
    private String playerAttackStatus;
    private int activePokemon;
    ReturnMove returnMove = new ReturnMove();

    public void setActivePokemon(int activePokemon) {
        this.activePokemon = activePokemon;
    }

    public int getActivePokemon() {
        return activePokemon;
    }

    public int getPlayerAttackDamage() {
        return playerAttackDamage;
    }

    public String getPlayerAttackStatus() {
        return playerAttackStatus;
    }

    public void setPlayerAttackDamage(int playerAttackDamage) {
        this.playerAttackDamage = playerAttackDamage;
    }

    public void setPlayerAttackStatus(String playerAttackStatus) {
        this.playerAttackStatus = playerAttackStatus;
    }

    public void userItem(Map<Integer, String> item){
        System.out.println("Player used " + returnMove.MoveString(item, returnMove.MoveDamage(item)));
    }

    public void AttackStrength(String strength){
        // Prints strength if
        if(!strength.equals("Normal")){
            System.out.println(strength); } }

    public void RemovePokemonFromFainted(Player user, String pokemon){
        for(int x = 0; x < 3; x++){
            if (user.getFaintedPokemon()[x] == null){
                break;
            } else if (user.getFaintedPokemon()[x].equals(pokemon)){
                user.getFaintedPokemon()[x] = null; }
        }}

    public boolean RevivePokemon(Player user, Object[] userPokemon, int pokemon){
        // Revives pokemon chosen by user. If entered something other than pokemon, true is returned and function is called again.
        int revive = (pokemon * -1) - 1;
        switch (userPokemon[revive].toString()){
            case("Bulbasuar"):
                Bulbasaur bulbasaur = (Bulbasaur) user.getPokemon(revive);
                bulbasaur.revive();
                user.useItem("Revive");
                RemovePokemonFromFainted(user, bulbasaur.getName());
                return false;
            case("Charmander"):
                Charmander charmander = (Charmander) user.getPokemon(revive);
                charmander.revive();
                user.useItem("Revive");
                RemovePokemonFromFainted(user, charmander.getName());
                System.out.println(user.getBag());
                return false;
            case("Gengar"):
                Gengar gengar = (Gengar) user.getPokemon(revive);
                gengar.revive();
                user.useItem("Revive");
                RemovePokemonFromFainted(user, gengar.getName());
                return false;
            case("Onix"):
                Onix onix = (Onix) user.getPokemon(revive);
                onix.revive();
                user.useItem("Revive");
                RemovePokemonFromFainted(user, onix.getName());
                return false;
            case("Pidgey"):
                Pidgey pidgey = (Pidgey) user.getPokemon(revive);
                pidgey.revive();
                user.useItem("Revive");
                RemovePokemonFromFainted(user, pidgey.getName());
                return false;
            case("Pikacu"):
                Pikachu pikachu = (Pikachu) user.getPokemon(revive);
                pikachu.revive();
                user.useItem("Revive");
                RemovePokemonFromFainted(user, pikachu.getName());
                return false;
            case("Squirtle"):
                Squirtle squirtle = (Squirtle) user.getPokemon(revive);
                squirtle.revive();
                user.useItem("Revive");
                RemovePokemonFromFainted(user, squirtle.getName());
                return false; }
        return true; }

    public void NoDamageToEnemy(){
        setPlayerAttackDamage(0);
        setPlayerAttackStatus("Normal");
    }

    public boolean BulbasaurMove(Player user, Bulbasaur bulbasaur, boolean firstTurn, boolean playerDamage, int damage, String status,
                                              Object[] userPokemon, String cpuType, int activePokemon){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates bulbasaur health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            bulbasaur.loseHealth(damage);
            if (bulbasaur.getStatus().equals("Normal")){
            bulbasaur.setStatus(status); }}
        if (!bulbasaur.isPlayable()){
            return true; }
        System.out.println("Player pokémon: " + bulbasaur.getName() + ". Health is " + bulbasaur.getHealth() + ". Status " +
                "is " + bulbasaur.getStatus() + ".");
        if (!bulbasaur.getStatus().equals("Normal") && playerDamage){
            if (bulbasaur.BulbasaurStatus(bulbasaur)) {
                NoDamageToEnemy();
                return false; } }
        if (!bulbasaur.isPlayable()){
            return true; }
        Map<Integer, String> bulbasaurMove = bulbasaur.BulbasuarBattle(user, userPokemon, cpuType, activePokemon);
        if (returnMove.MoveDamage(bulbasaurMove) < 0){
            userItem(bulbasaurMove);
            NoDamageToEnemy();
            return RevivePokemon(user, userPokemon, returnMove.MoveDamage(bulbasaurMove)); }
        if (returnMove.MoveDamage(bulbasaurMove) == 0){
            userItem(bulbasaurMove);
            NoDamageToEnemy();
            System.out.println(bulbasaur.getName() + " health: " + bulbasaur.getHealth());
            return false; }
        else if (returnMove.MoveDamage(bulbasaurMove) <= 3){
            NoDamageToEnemy();
            setActivePokemon(returnMove.MoveDamage(bulbasaurMove) - 1);
            return false; }
        else if (returnMove.MoveDamage(bulbasaurMove) == 999){
            return BulbasaurMove(user, bulbasaur, false, false, damage, status, userPokemon, cpuType, activePokemon);}
        setPlayerAttackDamage(returnMove.MoveDamage(bulbasaurMove));
        setPlayerAttackStatus(returnMove.MoveString(bulbasaurMove, returnMove.MoveDamage(bulbasaurMove)));
        System.out.println("Player's " + bulbasaur.getName() + " used " + bulbasaur.getAttackName() + ".");
        AttackStrength(bulbasaur.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        if (bulbasaur.getAttackName().equals("Leech Seed")){
            System.out.println("Bulbasuar's health was restored by " + bulbasaur.getLeechSeed(false).getHeal() + "."
            + " Bulbasuar's health: " + bulbasaur.getHealth()); }
        return false; }

    public boolean CharmanderMove(Player user, Charmander charmander, boolean firstTurn, boolean playerDamage, int damage, String status,
                                              Object[] userPokemon, String cpuType, int activePokemon){
       /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates charmander health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            charmander.loseHealth(damage);
            if (charmander.getStatus().equals("Normal")){
                charmander.setStatus(status); }}
        if (!charmander.isPlayable()){
            return true; }
        System.out.println("Player pokémon: " + charmander.getName() + ". Health is " + charmander.getHealth() + ". Status " +
                "is " + charmander.getStatus() + ".");
        if (!charmander.getStatus().equals("Normal") && playerDamage){
            if (charmander.CharmanderStatus(charmander)) {
                setPlayerAttackStatus("Normal");
                setPlayerAttackDamage(0);
                return false; } }
        if (!charmander.isPlayable()){
            return true; }
        Map<Integer, String> charmanderMove = charmander.CharmanderBattle(user, userPokemon, cpuType, activePokemon);
        if (returnMove.MoveDamage(charmanderMove) < 0){
            NoDamageToEnemy();
            setPlayerAttackStatus("Normal");
            return RevivePokemon(user, userPokemon, returnMove.MoveDamage(charmanderMove)); }
        if (returnMove.MoveDamage(charmanderMove) == 0){
            NoDamageToEnemy();
            setPlayerAttackStatus("Normal");
            System.out.println(charmander.getName() + " health: " + charmander.getHealth());
            return false; }
        else if (returnMove.MoveDamage(charmanderMove) <= 3){
            NoDamageToEnemy();
            setActivePokemon(returnMove.MoveDamage(charmanderMove) - 1);
            return false; }
        else if (returnMove.MoveDamage(charmanderMove) == 999){
            return CharmanderMove(user, charmander, false, false, damage, status, userPokemon, cpuType, activePokemon);}
        setPlayerAttackDamage(returnMove.MoveDamage(charmanderMove));
        setPlayerAttackStatus(returnMove.MoveString(charmanderMove, returnMove.MoveDamage(charmanderMove)));
        System.out.println("Player's " + charmander.getName() + " used " + charmander.getAttackName() + ".");
        AttackStrength(charmander.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }

    public boolean GengarMove(Player user, Gengar gengar, boolean firstTurn, boolean playerDamage, int damage, String status,
                                               Object[] userPokemon, String cpuType, int activePokemon){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates gengar health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            gengar.loseHealth(damage);
            if (gengar.getStatus().equals("Normal")){
                gengar.setStatus(status); }}
        if (!gengar.isPlayable()){
            return true; }
        System.out.println("Player pokémon: " + gengar.getName() + ". Health is " + gengar.getHealth() + ". Status " +
                "is " + gengar.getStatus() + ".");
        if (!gengar.getStatus().equals("Normal") && playerDamage){
            if (gengar.GengarStatus(gengar)) {
                setPlayerAttackStatus("Normal");
                setPlayerAttackDamage(0);
                return false; } }
        if (!gengar.isPlayable()){
            return true; }
        Map<Integer, String> gengarMove = gengar.GengarBattle(user, userPokemon, cpuType, activePokemon);
        if (returnMove.MoveDamage(gengarMove) < 0){
            userItem(gengarMove);
            NoDamageToEnemy();
            return RevivePokemon(user, userPokemon, returnMove.MoveDamage(gengarMove)); }
        if (returnMove.MoveDamage(gengarMove) == 0){
            userItem(gengarMove);
            NoDamageToEnemy();
            System.out.println(gengar.getName() + " health: " + gengar.getHealth());
            return false; }
        else if (returnMove.MoveDamage(gengarMove) <= 3){
            NoDamageToEnemy();
            setActivePokemon(returnMove.MoveDamage(gengarMove) - 1);
            return false; }
        else if (returnMove.MoveDamage(gengarMove) == 999){
            return GengarMove(user, gengar, false, false, damage, status, userPokemon, cpuType, activePokemon);}
        setPlayerAttackDamage(returnMove.MoveDamage(gengarMove));
        setPlayerAttackStatus(returnMove.MoveString(gengarMove, returnMove.MoveDamage(gengarMove)));
        System.out.println("Player's " + gengar.getName() + " used " + gengar.getAttackName() + ".");
        AttackStrength(gengar.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false; }

    public boolean OnixMove(Player user, Onix onix, boolean firstTurn, boolean playerDamage, int damage, String status,
                                           Object[] userPokemon, String cpuType, int activePokemon){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Onix health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            onix.loseHealth(damage);
            if (onix.getStatus().equals("Normal")){
                onix.setStatus(status); }}
        if (!onix.isPlayable()){
            return true; }
        System.out.println("Player pokémon: " + onix.getName() + ". Health is " + onix.getHealth() + ". Status " +
                "is " + onix.getStatus() + ".");
        if (!onix.getStatus().equals("Normal") && playerDamage){
            if (onix.OnixStatus(onix)) {
                NoDamageToEnemy();
                return false; } }
        if (!onix.isPlayable()){
            return true; }
        Map<Integer, String> onixMove = onix.OnixBattle(user, userPokemon, cpuType, activePokemon);
        if (returnMove.MoveDamage(onixMove) < 0){
            userItem(onixMove);
            NoDamageToEnemy();
            return RevivePokemon(user, userPokemon, returnMove.MoveDamage(onixMove)); }
        if (returnMove.MoveDamage(onixMove) == 0){
            userItem(onixMove);
            NoDamageToEnemy();
            System.out.println(onix.getName() + " health: " + onix.getHealth());
            return false; }
        else if (returnMove.MoveDamage(onixMove) <= 3){
            NoDamageToEnemy();
            setActivePokemon(returnMove.MoveDamage(onixMove) - 1);
            return false; }
        else if (returnMove.MoveDamage(onixMove) == 999){
            return OnixMove(user, onix, false, false, damage, status, userPokemon, cpuType, activePokemon);}
        setPlayerAttackDamage(returnMove.MoveDamage(onixMove));
        setPlayerAttackStatus(returnMove.MoveString(onixMove, returnMove.MoveDamage(onixMove)));
        System.out.println("Player's " + onix.getName() + " used " + onix.getAttackName() + ".");
        AttackStrength(onix.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false; }

    public boolean PidgeyMove(Player user, Pidgey pidgey, boolean firstTurn, boolean playerDamage, int damage, String status,
                                         Object[] userPokemon, String cpuType, int activePokemon){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Pidgey health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            pidgey.loseHealth(damage);
            if (pidgey.getStatus().equals("Normal")){
                pidgey.setStatus(status); }}
        if (!pidgey.isPlayable()){
            return true; }
        System.out.println("Player pokémon: " + pidgey.getName() + ". Health is " + pidgey.getHealth() + ". Status " +
                "is " + pidgey.getStatus() + ".");
        if (!pidgey.getStatus().equals("Normal") && playerDamage){
            if (pidgey.PidgeyStatus(pidgey)) {
                setPlayerAttackStatus("Normal");
                setPlayerAttackDamage(0);
                return false; } }
        if (!pidgey.isPlayable()){
            return true; }
        Map<Integer, String> pidgeyMove = pidgey.PidgeyBattle(user, userPokemon, cpuType, activePokemon);
        if (returnMove.MoveDamage(pidgeyMove) < 0){
            userItem(pidgeyMove);
            NoDamageToEnemy();
            return RevivePokemon(user, userPokemon, returnMove.MoveDamage(pidgeyMove)); }
        if (returnMove.MoveDamage(pidgeyMove) == 0){
            userItem(pidgeyMove);
            NoDamageToEnemy();
            System.out.println(pidgey.getName() + " health: " + pidgey.getHealth());
            return false; }
        else if (returnMove.MoveDamage(pidgeyMove) <= 3){
            NoDamageToEnemy();
            setActivePokemon(returnMove.MoveDamage(pidgeyMove) - 1);
            return false; }
        else if (returnMove.MoveDamage(pidgeyMove) == 999){
            return PidgeyMove(user, pidgey, false, false, damage, status, userPokemon, cpuType, activePokemon);}
        setPlayerAttackDamage(returnMove.MoveDamage(pidgeyMove));
        setPlayerAttackStatus(returnMove.MoveString(pidgeyMove, returnMove.MoveDamage(pidgeyMove)));
        System.out.println("Player's " + pidgey.getName() + " used " + pidgey.getAttackName() + ".");
        AttackStrength(pidgey.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false; }

    public boolean PikachuMove(Player user, Pikachu pikachu, boolean firstTurn, boolean playerDamage, int damage, String status,
                                           Object[] userPokemon, String cpuType, int activePokemon){
       /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Pikachu health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            pikachu.loseHealth(damage);
            if (!pikachu.getStatus().equals("Normal")){
                pikachu.setStatus(status); }}
        if (!pikachu.isPlayable()){
            return true; }
        System.out.println("Player pokémon: " + pikachu.getName() + ". Health is " + pikachu.getHealth() + ". Status " +
                "is " + pikachu.getStatus() + ".");
        if (!pikachu.getStatus().equals("Normal") && playerDamage){
            if (pikachu.PikachuStatus(pikachu)) {
                NoDamageToEnemy();
                return false; } }
        if (!pikachu.isPlayable()){
            return true; }
        Map<Integer, String> pikachuMove = pikachu.PikachuBattle(user, userPokemon, cpuType, activePokemon);
        if (returnMove.MoveDamage(pikachuMove) < 0){
            userItem(pikachuMove);
            NoDamageToEnemy();
            return RevivePokemon(user, userPokemon, returnMove.MoveDamage(pikachuMove)); }
        if (returnMove.MoveDamage(pikachuMove) == 0){
            userItem(pikachuMove);
            NoDamageToEnemy();
            System.out.println(pikachu.getName() + " health: " + pikachu.getHealth());
            return false; }
        else if (returnMove.MoveDamage(pikachuMove) <= 3){
            NoDamageToEnemy();
            setActivePokemon(returnMove.MoveDamage(pikachuMove) - 1);
            return false; }
        else if (returnMove.MoveDamage(pikachuMove) == 999){
            return PikachuMove(user, pikachu, false, false, damage, status, userPokemon, cpuType, activePokemon);}
        setPlayerAttackDamage(returnMove.MoveDamage(pikachuMove));
        setPlayerAttackStatus(returnMove.MoveString(pikachuMove, returnMove.MoveDamage(pikachuMove)));
        System.out.println("Player's " + pikachu.getName() + " used " + pikachu.getAttackName() + ".");
        AttackStrength(pikachu.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }


    public boolean SquirtleMove(Player user, Squirtle squirtle, boolean firstTurn, boolean playerDamage, int damage, String status,
                                            Object[] userPokemon, String cpuType, int activePokemon) {
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Squirtle health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            squirtle.loseHealth(damage);
            if (squirtle.getStatus().equals("Normal")){
                squirtle.setStatus(status); }}
        if (!squirtle.isPlayable()){
            return true; }
        System.out.println("Player pokémon: " + squirtle.getName() + ". Health is " + squirtle.getHealth() + ". Status " +
                "is " + squirtle.getStatus() + ".");
        if (!squirtle.getStatus().equals("Normal") && playerDamage){
            if (squirtle.SquirtleStatus(squirtle)) {
                NoDamageToEnemy();
                return false; } }
        if (!squirtle.isPlayable()){
            return true; }
        Map<Integer, String> squirtleMove = squirtle.SquirtleBattle(user, userPokemon, cpuType, activePokemon);
        if (returnMove.MoveDamage(squirtleMove) < 0){
            userItem(squirtleMove);
            NoDamageToEnemy();
            return RevivePokemon(user, userPokemon, returnMove.MoveDamage(squirtleMove)); }
        if (returnMove.MoveDamage(squirtleMove) == 0) {
            userItem(squirtleMove);
            NoDamageToEnemy();
            System.out.println(squirtle.getName() + " health: " + squirtle.getHealth());
            return false; }
        else if (returnMove.MoveDamage(squirtleMove) <= 3){
            NoDamageToEnemy();
            setActivePokemon(returnMove.MoveDamage(squirtleMove) - 1);
            return false; }
        else if (returnMove.MoveDamage(squirtleMove) == 999){
            return SquirtleMove(user, squirtle, false, false, damage, status, userPokemon, cpuType, activePokemon);}
        setPlayerAttackDamage(returnMove.MoveDamage(squirtleMove));
        setPlayerAttackStatus(returnMove.MoveString(squirtleMove, returnMove.MoveDamage(squirtleMove)));
        System.out.println("Player's " + squirtle.getName() + " used " + squirtle.getAttackName() + ".");
        AttackStrength(squirtle.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }
}