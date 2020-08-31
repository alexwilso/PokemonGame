package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CeladonCityGym {
    private boolean playerTurn;
    private boolean cpuTurn;
    boolean firstTurn;
    private String pokemonTypePLayer;
    private Integer pokemonHealthPlayer;
    private String pokemonTypeCpu;
    private String pokemonStatusPlayer;
    private int cpuAttackDamage;
    private String cpuAttackStatus;
    private int playerAttackDamage;
    private String playerAttackStatus;
    private int activePokemon;
    boolean playerwins;
    private int[] faintedPokemon;
    ErikaAI erikaAI = new ErikaAI();
    ReturnMove returnMove = new ReturnMove();
    PlayerMove playerMove = new PlayerMove();
    Scanner scanner = new Scanner(System.in);


    public CeladonCityGym() {
        this.playerTurn = true;
        this.cpuTurn = false;
        this.firstTurn = true;
        this.cpuAttackStatus = "Normal";
        this.playerAttackStatus = "Normal";
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

    public void Welcome(Player user, Object[] pokemon){
        Vileplume vileplume = new Vileplume("Vileplume", "Grass", 50, 120,120,"Normal",
                new Absorb(25,  10,10, 25), new StunSpore(15, 20,20),
                new HyperBeam(30,5,5), new Sleep(10, 10,10));

        Victreebel victreebel = new Victreebel("Victreebell", "Grass", 50, 120,120, "Normal",
                new RazorLeaf(25, 10,10), new VineWhip(30,10,10),
                new Sleep(10,10,10), new SpitUp(15,20,20));

        Tangela tangela = new Tangela("Tangela", "Grass", 50,120,120, "Normal",
                new PoisionPowder(15,15,15), new Megadrain(20, 20,20,20),
                new Slam(15,15,15), new Constrict(25,10,10));
        LeaderErika leaderErika = new LeaderErika(victreebel, vileplume, tangela);
        System.out.println("Leader Erica: Hello... Lovely weather, isn't it? It's so pleasant.\n...Oh, dear...\nI must" +
                " have dozed off... Welcome! My name is Erika. I am the leader of Celadon Gym.\nI am a student of the art" +
                "of flower arranging. My pokémon are solely of the grass type.\n... Oh, I'm sorry, I had no idea that you" +
                " wished to challenge me. Very well, but I shall not lose.");
//        setPokemon(user.getPokemon()[0], user.getPokemon()[1], user.getPokemon()[2], pokemon);

        Battle(leaderErika, user, vileplume, victreebel, tangela, pokemon, true);
    }

    public boolean SwitchPokemon(Player user, Object[] userPokemon){
        // usermove function with pokemon user chooses to change to.
        for(int x = 0; x < 3; x++){
            System.out.println(Integer.toString(x+1) + " " + userPokemon[x]);
        }
        boolean playable = false;
        int pokemon;
        do {
            System.out.println("Enter number of pokémon you wish to play");
            pokemon = Integer.parseInt(scanner.nextLine());
            if (pokemon >= 0 && pokemon <= 3){
                playable = true;
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

        switch (userPokemon[pokemon].toString()){
            case("Bulbasuar"):
                Bulbasaur bulbasaur = (Bulbasaur) user.getPokemon(pokemon);
                if (!bulbasaur.isPlayable()){
                    System.out.println("Bulbasuar can no longer be used in battle");
                    return SwitchPokemon(user, userPokemon);
                }
                SetActiveBulbasaur(bulbasaur);
                return playerMove.BulbasaurMove(user, bulbasaur, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu());
            case("Charmander"):
                Charmander charmander = (Charmander) user.getPokemon(pokemon);
                if (!charmander.isPlayable()){
                    System.out.println("Charmander can no longer be used in battle");
                    return SwitchPokemon(user, userPokemon);
                }
                SetActiveCharmander(charmander);
                return playerMove.CharmanderMove(user, charmander, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu());
            case("Gengar"):
                Gengar gengar = (Gengar) user.getPokemon(pokemon);
                if (!gengar.isPlayable()){
                    System.out.println("Gengar can no longer be used in battle");
                    return SwitchPokemon(user, userPokemon);
                }
                SetActiveGengar(gengar);
                return playerMove.GengarMove(user, gengar, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu());
            case("Onix"):
                Onix onix = (Onix) user.getPokemon(pokemon);
                if (!onix.isPlayable()){
                    System.out.println("Onix can no longer be used in battle");
                    return SwitchPokemon(user, userPokemon);
                }
                SetActiveOnix(onix);
                return playerMove.OnixMove(user, onix, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu());
            case("Pidgey"):
                Pidgey pidgey = (Pidgey) user.getPokemon(pokemon);
                if (!pidgey.isPlayable()){
                    System.out.println("Pidgey can no longer be used in battle");
                    return SwitchPokemon(user, userPokemon);
                }
                SetActivePidgey(pidgey);
                return playerMove.PidgeyMove(user, pidgey, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu());
            case("Pikacu"):
                Pikachu pikachu = (Pikachu) user.getPokemon(pokemon);
                if (!pikachu.isPlayable()){
                    System.out.println("Pikachu can no longer be used in battle");
                    return SwitchPokemon(user, userPokemon);
                }
                SetActivePikachu(pikachu);
                return playerMove.PikachuMove(user, pikachu, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu());
            case("Squirtle"):
                Squirtle squirtle = (Squirtle) user.getPokemon(pokemon);
                if (!squirtle.isPlayable()){
                    System.out.println("Squirtle can no longer be used in battle");
                    return SwitchPokemon(user, userPokemon);
                }
                SetActiveSquirtle(squirtle);
                return playerMove.SquirtleMove(user, squirtle, firstTurn, playerDamage, getCpuAttackDamage(), getCpuAttackStatus(),
                        userPokemon, getPokemonTypeCpu());
        }
        return true;
    }

    public void AttackStrength(String strength){
        // Prints strength if
        if(!strength.equals("Normal")){
            System.out.println(strength);
        }
    }

    public void VictreebelAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                 Object[] userPokemon, boolean cpuDamage){
        // Updates victrebells health and status based on player move.
        // Sets damage and status of oppossing pokemon based on return from erika ai class.
        if (cpuDamage) {
            victreebel.loseHealth(getPlayerAttackDamage());
            victreebel.setStatus(getPlayerAttackStatus());
        }
        if (!victreebel.isPlayable()){
            System.out.println("Leader Erika sent out Tangela");
            Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon, false);
        }
        System.out.println("Erika's pokemon: Victreebel. Health is " + victreebel.getHealth() + ". Status is " +
                victreebel.getStatus() + ".");
        Map<Integer, String> cpuMove = erikaAI.CreateTreeVictreebel(leaderErika, victreebel, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (!getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + victreebel.getName() + " used " + victreebel.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public void TangelaAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                              Object[] userPokemon, boolean cpuDamage){
        // Updates Tangelas health and status based on player move.
        // Sets damage and status of oppossing pokemon based on returns from erika ai class.
        if (cpuDamage) {
            tangela.loseHealth(getPlayerAttackDamage());
            tangela.setStatus(getPlayerAttackStatus());
        }
        if (!tangela.isPlayable()){
            System.out.println("Leader Erika sent out Tangela");
            Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon, false);
        }
        System.out.println("Erika's pokemon: Victreebel. Health is " + tangela.getHealth() + ". Status is " +
                tangela.getStatus() + ".");
        Map<Integer, String> cpuMove = erikaAI.CreateTreeTangela(leaderErika, tangela, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (!getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + tangela.getName() + " used " + tangela.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public void VileplumeAttack(LeaderErika leaderErika, Vileplume vileplume){
        // Updates Tangelas health and status based on player move.
        // Sets damage and status of oppossing pokemon based on returns from erika ai class.
        vileplume.setHealth(vileplume.getHealth() - getPlayerAttackDamage());
        vileplume.setStatus(getPlayerAttackStatus());
        System.out.println("Erika's pokemon: Tangela. Health is " + vileplume.getHealth() + ". Status is " +
                vileplume.getStatus() + ".");
        Map<Integer, String> cpuMove = erikaAI.CreateTreeVileplume(leaderErika, vileplume, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (!getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + vileplume.getName() + " used " + vileplume.getAttackName() + ". Dealt " +
                getCpuAttackDamage() + " damage to opponent");
        setCpuTurn(false);
        setPlayerTurn(true);
    }


    public void Battle(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                       Object[] userPokemon, boolean cpuDamage) {
        while (true) {
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
                }
            }
            while (isCpuTurn()) {
                if (victreebel.isPlayable()) {
                    VictreebelAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon, cpuDamage);
                    break;
                } else if (tangela.isPlayable()) {
                  TangelaAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon, cpuDamage);
                    break;
                } else if (vileplume.isPlayable()) {
                    VileplumeAttack(leaderErika, vileplume);
                    break;
                } else {
                    PlayerWins(user);
                }
            }
        }
    }

    public void PlayerWins(Player user){
        System.out.println("You defeated Leader Erika");
        System.out.println("Leader Erika: Oh! I conceded defeat. You are remarkably strong. \n" +
                "I must confer on you the RAINBOWBADGE. Player got 2900 for winning");
        user.setMoney(2900);
        this.playerwins = true;
    }
}



