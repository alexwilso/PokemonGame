package com.wilson;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class CeladonCityGym {
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
    ErikaAI erikaAI;
    ReturnMove returnMove;
    PlayerMove playerMove;
    Battlemenu battlemenu;
    Scanner scanner = new Scanner(System.in);


    public CeladonCityGym(ReturnMove returnMove, PlayerMove playerMove, ErikaAI erikaAI, PokemonStatus pokemonStatus,
                          Battlemenu battlemenu) {
        this.returnMove = returnMove;
        this.playerMove = playerMove;
        this.erikaAI = erikaAI;
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

    public boolean Welcome(Player user, Object[] pokemon){
        Vileplume vileplume = new Vileplume("Vileplume", "Grass", 50, 120,120,"Normal",
                new Absorb(25,  10,10, 25), new StunSpore(15, 20,20, pokemonStatus),
                new HyperBeam(30,5,5), new Sleep(10, 10,10, pokemonStatus));

        Victreebel victreebel = new Victreebel("Victreebell", "Grass", 50, 120,120, "Normal",
                new RazorLeaf(25, 10,10), new VineWhip(30,10,10),
                new Sleep(10,10,10, pokemonStatus), new SpitUp(15,20,20));

        Tangela tangela = new Tangela("Tangela", "Grass", 50,120,120, "Normal",
                new PoisionPowder(15,15,15, pokemonStatus), new Megadrain(20, 20,20,20),
                new Slam(15,15,15), new Constrict(25,10,10));
        LeaderErika leaderErika = new LeaderErika(victreebel, vileplume, tangela);
        System.out.println("Leader Erica: Hello... Lovely weather, isn't it? It's so pleasant.\n...Oh, dear...\nI must" +
                " have dozed off... Welcome! My name is Erika. I am the leader of Celadon Gym.\nI am a student of the art" +
                " of flower arranging. My pokémon are solely of the grass type.\n... Oh, I'm sorry, I had no idea that you" +
                " wished to challenge me. Very well, but I shall not lose.");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Erika sent out Victreebell\nErika's pokemon: " +
                victreebel.getName() + ". Health is " + victreebel.getHealth() + ". Status is " + victreebel.getStatus());
//        setPokemon(user.getPokemon()[0], user.getPokemon()[1], user.getPokemon()[2], pokemon);
        return Battle(leaderErika, user, vileplume, victreebel, tangela, pokemon);
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

    // Need to fix when returning items! Also see if you can make user move its own class!!! So we don't have to type every time.

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

    public void AttackStrength(String strength){
        // Prints strength if
        if(!strength.equals("Normal")){
            System.out.println(strength);
        }
    }

    public void VictrebellPlayable(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                   Object[] userPokemon){
        // Determines if victrebell is playable
        System.out.println("Leader Erika sent out Tangela");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
    }

    public void VictreebelAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                 Object[] userPokemon){
        // Updates victrebells health and status based on player move.
        // Sets damage and status of opposing pokemon based on return from erika ai class.
        if (isCpuDamage()) {
            victreebel.loseHealth(getPlayerAttackDamage());
            if (victreebel.getStatus().equals("Normal")) {
                victreebel.setStatus(getPlayerAttackStatus());
            }}

        if (!victreebel.isPlayable()){
            VictrebellPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return;
        }

        System.out.println("Erika's pokemon: Victreebel. Health is " + victreebel.getHealth() + ". Status is " +
                victreebel.getStatus() + ".");

        if (!victreebel.getStatus().equals("Normal")){
            if (victreebel.VictrebellStatus(victreebel)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!victreebel.isPlayable()){
            VictrebellPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return;
        }
        Map<Integer, String> cpuMove = erikaAI.CreateTreeVictreebel(leaderErika, victreebel, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + victreebel.getName() + " used " + victreebel.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        if (victreebel.getAttackName().equals("Max Potion")){
            System.out.println("Victreebell's health: " + victreebel.getHealth());
        } else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public void TangelaPlayable(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                   Object[] userPokemon){
        // Determines if victrebell is playable
        System.out.println("Leader Erika sent out Vileplume");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
    }

    public void TangelaAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                              Object[] userPokemon){
        // Updates Tangelas health and status based on player move.
        // Sets damage and status of opposing pokemon based on returns from erika ai class.
        if (isCpuDamage()) {
            tangela.loseHealth(getPlayerAttackDamage());
            if (tangela.getStatus().equals("Normal")) {
                tangela.setStatus(getPlayerAttackStatus()); }}

        if (!tangela.isPlayable()){
            TangelaPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        System.out.println("Erika's pokemon: Tangela. Health is " + tangela.getHealth() + ". Status is " +
                tangela.getStatus() + ".");

        if (!tangela.getStatus().equals("Normal")){
            if (tangela.TangelaStatus(tangela)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!tangela.isPlayable()){
            TangelaPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        Map<Integer, String> cpuMove = erikaAI.CreateTreeTangela(leaderErika, tangela, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + tangela.getName() + " used " + tangela.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        if (victreebel.getAttackName().equals("Potion")){
            System.out.println("Tangela's health: " + victreebel.getHealth());
        } else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public void VileplumePlayable(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                Object[] userPokemon){
        // Determines if victrebell is playable
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
    }

    public void VileplumeAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                Object[] userPokemon){
        // Updates Vileplumes health and status based on player move.
        // Sets damage and status of opposing pokemon based on returns from erika ai class.
        if (isCpuDamage()) {
            vileplume.loseHealth(getPlayerAttackDamage());
            if (vileplume.getStatus().equals("Normal")) {
                vileplume.setStatus(getPlayerAttackStatus());
            }}
        if (!vileplume.isPlayable()){
            VileplumePlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }
        System.out.println("Erika's pokemon: Vileplume. Health is " + vileplume.getHealth() + ". Status is " +
                vileplume.getStatus() + ".");
        if (!vileplume.getStatus().equals("Normal")){
            if (vileplume.VileplumeStatus(vileplume)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }
        if (!vileplume.isPlayable()){
            VileplumePlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }
        Map<Integer, String> cpuMove = erikaAI.CreateTreeVileplume(leaderErika, vileplume, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + vileplume.getName() + " used " + vileplume.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true);
    }

// We will make this return true.
    public boolean Battle(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                       Object[] userPokemon) {
        while (isBattleOn()) {
            while (isPlayerTurn()) {
                if (firstTurn) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    this.firstTurn = false;
                    setPokemonTypeCpu(victreebel.getType());
                    setPlayerTurn(PlayerAttack(user, userPokemon, 0, false, false));
                    setCpuTurn(!isPlayerTurn());
                    setActivePokemon(0);
                } else {
                    setPlayerTurn(PlayerAttack(user, userPokemon, playerMove.getActivePokemon(), false, true));
                    setCpuTurn(!isPlayerTurn());
                    if (user.allFainted()) {
                        setBattleOn(false);
                        setPlayerTurn(false);
                        setCpuTurn(false);
                        this.playerWins = PlayerLoses(user);
                    }
                }
            }
            while (isCpuTurn()) {
                if (victreebel.isPlayable()) {
                    VictreebelAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
                    break;
                } else if (tangela.isPlayable()) {
                    TangelaAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
                    break;
                } else if (vileplume.isPlayable()) {
                    VileplumeAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
                    break;
                } else {
                    setBattleOn(false);
                    setCpuTurn(false);
                    setPlayerTurn(false);
                    this.playerWins = PlayerWins(user);
                    break;
                    }
                }
            }
        return this.playerWins;
    }

    public boolean PlayerWins(Player user){
        System.out.println("You defeated Leader Erika");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Erika: Oh! I conceded defeat. You are remarkably strong. \n" +
                "I must confer on you the RAINBOWBADGE. Player got 2900 for winning");
        user.setMoney(2900);
        return true;
    }

    public boolean PlayerLoses(Player user){
        System.out.println("You were defeated by Leader Erika!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Erika: Looks like I am stronger than you! Please come back again. I enjoyed showing" +
                " you the strength of the grass type");
        return false;
    }
}
