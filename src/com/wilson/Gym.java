package com.wilson;

import java.util.Scanner;

public class Gym {
    private boolean playerTurn;
    private boolean cpuTurn;
    boolean firstTurn;
    private boolean cpuDamage;
    private String pokemonTypePLayer;
    private Integer pokemonHealthPlayer;
    private String pokemonTypeCpu;
    private String pokemonStatusPlayer;
    private int cpuAttackDamage;
    private String cpuAttackStatus;
    private int playerAttackDamage;
    private String playerAttackStatus;
    private int activePokemon;
    boolean playerWins;
    private boolean battleOn;
    PokemonStatus pokemonStatus;
    ReturnMove returnMove;
    PlayerMove playerMove;
    Battlemenu battlemenu;
    Scanner scanner = new Scanner(System.in);

    public Gym(ReturnMove returnMove, PlayerMove playerMove, PokemonStatus pokemonStatus,
                             Battlemenu battlemenu) {
        this.returnMove = returnMove;
        this.playerMove = playerMove;
        this.pokemonStatus = pokemonStatus;
        this.battlemenu = battlemenu;
        this.playerTurn = true;
        this.cpuTurn = false;
        this.firstTurn = true;
        this.cpuDamage = true;
        this.playerWins = true;
        this.battleOn = true;
        this.cpuAttackStatus = "Normal";
        this.playerAttackStatus = "Normal";
    }
    public boolean isBattleOn() {
        return battleOn;
    }

    public void setBattleOn(boolean battleOn) {
        this.battleOn = battleOn;
    }

    public boolean isCpuDamage() {
        return cpuDamage;
    }

    public void setCpuDamage(boolean cpuDamage) {
        this.cpuDamage = cpuDamage;
    }

    public void setPlayerAttackDamage(int playerAttackDamage) {
        this.playerAttackDamage = playerAttackDamage;
    }

    public void setPlayerAttackStatus(String playerAttackStatus) {
        this.playerAttackStatus = playerAttackStatus;
    }

    public int getActivePokemon() {
        return activePokemon;
    }

    public void setActivePokemon(int activePokemon) {
        this.activePokemon = activePokemon;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setCpuTurn(boolean cpuTurn) {
        this.cpuTurn = cpuTurn;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public boolean isCpuTurn() {
        return cpuTurn;
    }

    public void setPokemonTypeCpu(String pokemonTypeCpu) {
        this.pokemonTypeCpu = pokemonTypeCpu;
    }

    public String getPokemonTypeCpu() {
        return pokemonTypeCpu;
    }

    public int getCpuAttackDamage() {
        return cpuAttackDamage;
    }

    public String getCpuAttackStatus() {
        return cpuAttackStatus;
    }

    public int getPlayerAttackDamage() {
        return playerMove.getPlayerAttackDamage();
    }

    public String getPlayerAttackStatus() {
        return playerMove.getPlayerAttackStatus();
    }

    public void setCpuAttackDamage(int cpuAttackDamage) {
        this.cpuAttackDamage = cpuAttackDamage;
    }

    public void setCpuAttackStatus(String cpuAttackStatus) {
        this.cpuAttackStatus = cpuAttackStatus;
    }

    public void setPokemonTypePLayer(String pokemonTypePLayer) {
        this.pokemonTypePLayer = pokemonTypePLayer;
    }

    public void setPokemonHealthPlayer(Integer pokemonHealthPlayer) {
        this.pokemonHealthPlayer = pokemonHealthPlayer;
    }

    public void setPokemonStatusPlayer(String pokemonStatusPlayer) {
        this.pokemonStatusPlayer = pokemonStatusPlayer;
    }

    public String getPokemonTypePLayer() {
        return pokemonTypePLayer;
    }

    public Integer getPokemonHealthPlayer() {
        return pokemonHealthPlayer;
    }

    public String getPokemonStatusPlayer() {
        return pokemonStatusPlayer;
    }

    private void SetActiveBulbasaur(Bulbasaur bulbasaur){
        setPokemonHealthPlayer(bulbasaur.getHealth());
        setPokemonTypePLayer(bulbasaur.getType());
        setPokemonStatusPlayer(bulbasaur.getStatus());
    }

    private void SetActiveCharmander(Charmander charmander){
        setPokemonHealthPlayer(charmander.getHealth());
        setPokemonTypePLayer(charmander.getType());
        setPokemonStatusPlayer(charmander.getStatus());
    }

    private void SetActiveGengar(Gengar gengar){
        setPokemonHealthPlayer(gengar.getHealth());
        setPokemonTypePLayer(gengar.getType());
        setPokemonStatusPlayer(gengar.getStatus());
    }

    private void SetActiveOnix(Onix onix){
        setPokemonHealthPlayer(onix.getHealth());
        setPokemonTypePLayer(onix.getType());
        setPokemonStatusPlayer(onix.getStatus());
    }

    private void SetActivePidgey(Pidgey pidgey){
        setPokemonHealthPlayer(pidgey.getHealth());
        setPokemonTypePLayer(pidgey.getType());
        setPokemonStatusPlayer(pidgey.getStatus());
    }

    private void SetActivePikachu(Pikachu pikachu){
        setPokemonHealthPlayer(pikachu.getHealth());
        setPokemonTypePLayer(pikachu.getType());
        setPokemonStatusPlayer(pikachu.getStatus());
    }

    private void SetActiveSquirtle(Squirtle squirtle){
        setPokemonHealthPlayer(squirtle.getHealth());
        setPokemonTypePLayer(squirtle.getType());
        setPokemonStatusPlayer(squirtle.getStatus());
    }
    public boolean SwitchPokemon(Player user, Object[] userPokemon){
        // usermove function with pokemon user chooses to change to.
        boolean gameOver = true;
        for (int x = 0; x<3; x++){
            if (user.getFaintedPokemon()[x] == null){
                gameOver = false;
            }} if (gameOver){
            return false;
        }
        for(int x = 0; x < 3; x++){
            System.out.println(Integer.toString(x+1) + " " + userPokemon[x]);
        }
        boolean playable = false;
        int pokemon;
        do {
            System.out.println("Enter number of pokémon you wish to play");
            pokemon = Integer.parseInt(scanner.nextLine());
            if (pokemon >= 0 && pokemon <= 3){
                if (user.searchFainted(pokemon-1)){
                    System.out.println("That pokemon fainted and is no longer playable. You must revive them to use them");
                    playable = false;} else {
                    playable = true;}
            } else {
                System.out.println("Not a valid option");
            }
        } while (! playable);
        System.out.println("Go " + userPokemon[pokemon-1]);
        playerMove.setActivePokemon(pokemon -1);
        PlayerAttack(user, userPokemon, pokemon -1, false, false);
        return false;
    }

    public boolean PlayerAttack(Player user, Object[] userPokemon, int pokemon, boolean firstTurn, boolean playerDamage){
        boolean result;
        switch (userPokemon[pokemon].toString()){
            case("Bulbasuar"):
                Bulbasaur bulbasaur = (Bulbasaur) user.getPokemon(pokemon);
                if (!bulbasaur.isPlayable()){
                    System.out.println("Bulbasuar can no longer be used in battle");
                    user.addFaintedPokemon(pokemon,userPokemon[pokemon].toString());
                    return SwitchPokemon(user, userPokemon);
                }
                result = playerMove.BulbasaurMove(user, bulbasaur, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu(), getActivePokemon());
                SetActiveBulbasaur(bulbasaur);
                return result;
            case("Charmander"):
                Charmander charmander = (Charmander) user.getPokemon(pokemon);
                if (!charmander.isPlayable()){
                    System.out.println("Charmander can no longer be used in battle");
                    user.addFaintedPokemon(pokemon,userPokemon[pokemon].toString());
                    return SwitchPokemon(user, userPokemon);
                }
                result = playerMove.CharmanderMove(user, charmander, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu(), getActivePokemon());
                SetActiveCharmander(charmander);
                return result;
            case("Gengar"):
                Gengar gengar = (Gengar) user.getPokemon(pokemon);
                if (!gengar.isPlayable()){
                    System.out.println("Gengar can no longer be used in battle");
                    user.addFaintedPokemon(pokemon,userPokemon[pokemon].toString());
                    return SwitchPokemon(user, userPokemon);
                }
                result = playerMove.GengarMove(user, gengar, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu(), getActivePokemon());
                SetActiveGengar(gengar);
                return result;
            case("Onix"):
                Onix onix = (Onix) user.getPokemon(pokemon);
                if (!onix.isPlayable()){
                    System.out.println("Onix can no longer be used in battle");
                    user.addFaintedPokemon(pokemon,userPokemon[pokemon].toString());
                    return SwitchPokemon(user, userPokemon);
                }
                result = playerMove.OnixMove(user, onix, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu(), getActivePokemon());
                SetActiveOnix(onix);
                return result;
            case("Pidgey"):
                Pidgey pidgey = (Pidgey) user.getPokemon(pokemon);
                if (!pidgey.isPlayable()){
                    System.out.println("Pidgey can no longer be used in battle");
                    user.addFaintedPokemon(pokemon,userPokemon[pokemon].toString());
                    return SwitchPokemon(user, userPokemon);
                }
                result = playerMove.PidgeyMove(user, pidgey, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu(), getActivePokemon());
                SetActivePidgey(pidgey);
                return result;
            case("Pikacu"):
                Pikachu pikachu = (Pikachu) user.getPokemon(pokemon);
                if (!pikachu.isPlayable()){
                    System.out.println("Pikachu can no longer be used in battle");
                    user.addFaintedPokemon(pokemon,userPokemon[pokemon].toString());
                    return SwitchPokemon(user, userPokemon);
                }
                result = playerMove.PikachuMove(user, pikachu, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu(), getActivePokemon());
                SetActivePikachu(pikachu);
                return result;
            case("Squirtle"):
                Squirtle squirtle = (Squirtle) user.getPokemon(pokemon);
                if (!squirtle.isPlayable()){
                    System.out.println("Squirtle can no longer be used in battle");
                    user.addFaintedPokemon(pokemon,userPokemon[pokemon].toString());
                    return SwitchPokemon(user, userPokemon);
                }
                result = playerMove.SquirtleMove(user, squirtle, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu(), getActivePokemon());
                SetActiveSquirtle(squirtle);
                return result;
        }
        return true;
    }


}

