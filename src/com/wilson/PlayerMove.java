package com.wilson;

import java.util.Map;

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

    public boolean userItem(Map<Integer, String> item){
        System.out.println("Player's used " + returnMove.MoveString(item, returnMove.MoveDamage(item)));
        return false;
    }

    public void AttackStrength(String strength){
        // Prints strength if
        if(!strength.equals("Normal")){
            System.out.println(strength);
        }
    }

    public boolean BulbasaurMove(Player user, Bulbasaur bulbasaur, boolean firstTurn, boolean playerDamage, int damage, String status,
                                              Object[] userPokemon, String cpuType){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates bulbasaur health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            bulbasaur.loseHealth(damage);
            bulbasaur.setStatus(status);
        }
        System.out.println("Player pokémon: " + bulbasaur.getName() + ". Health is " + bulbasaur.getHealth() + ". Status " +
                "is " + bulbasaur.getStatus() + ".");
        Map<Integer, String> bulbasaurMove = bulbasaur.BulbasuarBattle(user, userPokemon, cpuType);
        if (returnMove.MoveDamage(bulbasaurMove) == 0){
            return userItem(bulbasaurMove);
        } else if (returnMove.MoveDamage(bulbasaurMove) <= 3){
            setPlayerAttackDamage(0);
            setPlayerAttackStatus("Normal");
            setActivePokemon(returnMove.MoveDamage(bulbasaurMove) - 1);
            return false;
        }
        setPlayerAttackDamage(returnMove.MoveDamage(bulbasaurMove));
        setPlayerAttackStatus(returnMove.MoveString(bulbasaurMove, returnMove.MoveDamage(bulbasaurMove)));
        System.out.println("Player's " + bulbasaur.getName() + " used " + bulbasaur.getAttackName() + ".");
        AttackStrength(bulbasaur.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }

    public boolean CharmanderMove(Player user, Charmander charmander, boolean firstTurn, boolean playerDamage, int damage, String status,
                                              Object[] userPokemon, String cpuType){
       /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates charmander health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            charmander.loseHealth(damage);
            charmander.setStatus(status);
        }
        System.out.println("Player pokémon: " + charmander.getName() + ". Health is " + charmander.getHealth() + ". Status " +
                "is " + charmander.getStatus() + ".");
        Map<Integer, String> charmanderMove = charmander.CharmanderBattle(user, userPokemon, cpuType);
        if (returnMove.MoveDamage(charmanderMove) == 0){
            return userItem(charmanderMove);
        } else if (returnMove.MoveDamage(charmanderMove) <= 3){
            setPlayerAttackDamage(0);
            setPlayerAttackStatus("Normal");
            setActivePokemon(returnMove.MoveDamage(charmanderMove) - 1);
            return false;
        }
        setPlayerAttackDamage(returnMove.MoveDamage(charmanderMove));
        setPlayerAttackStatus(returnMove.MoveString(charmanderMove, returnMove.MoveDamage(charmanderMove)));
        System.out.println("Player's " + charmander.getName() + " used " + charmander.getAttackName() + ".");
        AttackStrength(charmander.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }

    public boolean GengarMove(Player user, Gengar gengar, boolean firstTurn, boolean playerDamage, int damage, String status,
                                               Object[] userPokemon, String cpuType){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates gengar health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            gengar.loseHealth(damage);
            gengar.setStatus(status);
        }
        System.out.println("Player pokémon: " + gengar.getName() + ". Health is " + gengar.getHealth() + ". Status " +
                "is " + gengar.getStatus() + ".");
        Map<Integer, String> gengarMove = gengar.GengarBattle(user, userPokemon, cpuType);
        if (returnMove.MoveDamage(gengarMove) == 0){
            return userItem(gengarMove);
        } else if (returnMove.MoveDamage(gengarMove) <= 3){
            setPlayerAttackDamage(0);
            setPlayerAttackStatus("Normal");
            setActivePokemon(returnMove.MoveDamage(gengarMove) - 1);
            return false;
        }
        setPlayerAttackDamage(returnMove.MoveDamage(gengarMove));
        setPlayerAttackStatus(returnMove.MoveString(gengarMove, returnMove.MoveDamage(gengarMove)));
        System.out.println("Player's " + gengar.getName() + " used " + gengar.getAttackName() + ".");
        AttackStrength(gengar.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }

    public boolean OnixMove(Player user, Onix onix, boolean firstTurn, boolean playerDamage, int damage, String status,
                                           Object[] userPokemon, String cpuType){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Onix health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            onix.loseHealth(damage);
            onix.setStatus(status);
        }
        System.out.println("Player pokémon: " + onix.getName() + ". Health is " + onix.getHealth() + ". Status " +
                "is " + onix.getStatus() + ".");
        Map<Integer, String> onixMove = onix.OnixBattle(user, userPokemon, cpuType);
        if (returnMove.MoveDamage(onixMove) == 0){
            return userItem(onixMove);
        } else if (returnMove.MoveDamage(onixMove) <= 3){
            setPlayerAttackDamage(0);
            setPlayerAttackStatus("Normal");
            setActivePokemon(returnMove.MoveDamage(onixMove) - 1);
            return false;
        }
        setPlayerAttackDamage(returnMove.MoveDamage(onixMove));
        setPlayerAttackStatus(returnMove.MoveString(onixMove, returnMove.MoveDamage(onixMove)));
        System.out.println("Player's " + onix.getName() + " used " + onix.getAttackName() + ".");
        AttackStrength(onix.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }
    public boolean PidgeyMove(Player user, Pidgey pidgey, boolean firstTurn, boolean playerDamage, int damage, String status,
                                         Object[] userPokemon, String cpuType){
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Pidgey health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            pidgey.loseHealth(damage);
            pidgey.setStatus(status);
        }
        System.out.println("Player pokémon: " + pidgey.getName() + ". Health is " + pidgey.getHealth() + ". Status " +
                "is " + pidgey.getStatus() + ".");
        Map<Integer, String> pidgeyMove = pidgey.PidgeyBattle(user, userPokemon, cpuType);
        if (returnMove.MoveDamage(pidgeyMove) == 0){
            return userItem(pidgeyMove);
        } else if (returnMove.MoveDamage(pidgeyMove) <= 3){
            setPlayerAttackDamage(0);
            setPlayerAttackStatus("Normal");
            setActivePokemon(returnMove.MoveDamage(pidgeyMove) - 1);
            return false;
        }
        setPlayerAttackDamage(returnMove.MoveDamage(pidgeyMove));
        setPlayerAttackStatus(returnMove.MoveString(pidgeyMove, returnMove.MoveDamage(pidgeyMove)));
        System.out.println("Player's " + pidgey.getName() + " used " + pidgey.getAttackName() + ".");
        AttackStrength(pidgey.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }

    public boolean PikachuMove(Player user, Pikachu pikachu, boolean firstTurn, boolean playerDamage, int damage, String status,
                                           Object[] userPokemon, String cpuType){
       /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Pikachu health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            pikachu.loseHealth(damage);
            pikachu.setStatus(status);
        }
        System.out.println("Player pokémon: " + pikachu.getName() + ". Health is " + pikachu.getHealth() + ". Status " +
                "is " + pikachu.getStatus() + ".");
        Map<Integer, String> pikachuMove = pikachu.PikachuBattle(user, userPokemon, cpuType);
        if (returnMove.MoveDamage(pikachuMove) == 0){
            return userItem(pikachuMove);
        } else if (returnMove.MoveDamage(pikachuMove) <= 3){
            setPlayerAttackDamage(0);
            setPlayerAttackStatus("Normal");
            setActivePokemon(returnMove.MoveDamage(pikachuMove) - 1);
            return false;
        }
        setPlayerAttackDamage(returnMove.MoveDamage(pikachuMove));
        setPlayerAttackStatus(returnMove.MoveString(pikachuMove, returnMove.MoveDamage(pikachuMove)));
        System.out.println("Player's " + pikachu.getName() + " used " + pikachu.getAttackName() + ".");
        AttackStrength(pikachu.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }


    public boolean SquirtleMove(Player user, Squirtle squirtle, boolean firstTurn, boolean playerDamage, int damage, String status,
                                            Object[] userPokemon, String cpuType) {
        /*
        / Returns false to set users move in gym class to false allowing the cpu to move. Sets player attack damage and
        / enemy status after attack. If item is used, item is used and turn is ended. Updates Squirtle health and status
        / based on cpu attack
         */
        if (!firstTurn && playerDamage){
            squirtle.loseHealth(damage);
            squirtle.setStatus(status);
        }
        if (!squirtle.isPlayable()){
            return true;
        }
        System.out.println("Player pokémon: " + squirtle.getName() + ". Health is " + squirtle.getHealth() + ". Status " +
                "is " + squirtle.getStatus() + ".");
        Map<Integer, String> squirtleMove = squirtle.SquirtleBattle(user, userPokemon, cpuType);
        if (returnMove.MoveDamage(squirtleMove) == 0) {
            return userItem(squirtleMove);
        } else if (returnMove.MoveDamage(squirtleMove) <= 3){
            setPlayerAttackDamage(0);
            setPlayerAttackStatus("Normal");
            setActivePokemon(returnMove.MoveDamage(squirtleMove) - 1);
            return false;
        }
        setPlayerAttackDamage(returnMove.MoveDamage(squirtleMove));
        setPlayerAttackStatus(returnMove.MoveString(squirtleMove, returnMove.MoveDamage(squirtleMove)));
        System.out.println("Player's " + squirtle.getName() + " used " + squirtle.getAttackName() + ".");
        AttackStrength(squirtle.getAttackStrength());
        System.out.println("Dealt " + getPlayerAttackDamage() + " damage to opponent.");
        return false;
    }
}

