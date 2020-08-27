package com.wilson;

import java.util.HashMap;
import java.util.Map;

public class CeladonCityGym {
    boolean playerTurn;
    boolean cpuTurn;
    boolean firstTurn;
    private String pokemonTypePLayer;
    private Integer pokemonHealthPlayer;
    private String pokemonTypeCpu;
    private String pokemonStatusPlayer;
    private int cpuAttackDamage;
    private String cpuAttackStatus;
    private int playerAttackDamage;
    private String playerAttackStatus;
    int pokemonPlayer;
    ErikaAI erikaAI = new ErikaAI();
    ReturnMove returnMove = new ReturnMove();

    public CeladonCityGym() {
        this.playerTurn = true;
        this.cpuTurn = false;
        this.firstTurn = true;
        this.cpuAttackStatus = "Normal";
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
        return playerAttackDamage;
    }

    public String getPlayerAttackStatus() {
        return playerAttackStatus;
    }

    public void setCpuAttackDamage(int cpuAttackDamage) {
        this.cpuAttackDamage = cpuAttackDamage;
    }

    public void setCpuAttackStatus(String cpuAttackStatus) {
        this.cpuAttackStatus = cpuAttackStatus;
    }

    public void setPlayerAttackDamage(int playerAttackDamage) {
        this.playerAttackDamage = playerAttackDamage;
    }

    public void setPlayerAttackStatus(String playerAttackStatus) {
        this.playerAttackStatus = playerAttackStatus;
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



    public void Welcome(Player user, Object[] pokemon){
        Vileplume vileplume = new Vileplume("Vileplume", "Grass", 50, 120,120,"Normal",
                new Absorb(25,  10,10, 25), new StunSpore(15, 20,20),
                new HyperBeam(30,5,5), new Sleep(10, 10,10));

        Victreebel victreebel = new Victreebel("Victreebell", "Grass", 50, 120,120, "Normal",
                new RazorLeaf(25, 10,10), new VineWhip(30,5,5),
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

        Battle(leaderErika, user, vileplume, victreebel, tangela, pokemon);
    }

    public boolean userMove(Player user, Object[] userPokemon, int x, boolean firstTurn){
        this.pokemonPlayer = x;
        switch (userPokemon[x].toString()){
            case("Bulbasuar"):
                Bulbasaur bulbasaur = (Bulbasaur) user.getPokemon(x);
                SetActiveBulbasaur(bulbasaur);
                if (!firstTurn){
                    bulbasaur.loseHealth(getCpuAttackDamage());
                    bulbasaur.setStatus(getCpuAttackStatus());
                }
                System.out.println("Player pokémon: " + bulbasaur.getName() + ". Health is " + bulbasaur.getHealth() + " Status " +
                        "is " + bulbasaur.getStatus() + ".");
                Map<Integer, String> bulbasaurMove = bulbasaur.BulbasuarBattle(user, userPokemon, getPokemonTypeCpu());
                setPlayerAttackDamage(returnMove.MoveDamage(bulbasaurMove));
                setPlayerAttackStatus(returnMove.MoveString(bulbasaurMove, returnMove.MoveDamage(bulbasaurMove)));
                System.out.println("Player's " + bulbasaur.getName() + " used " + bulbasaur.getAttackName() + ". Dealt " +
                        getPlayerAttackDamage() + " damage to opponent.");
                return false;
            case("Charmander"):
                Charmander charmander = (Charmander) user.getPokemon(x);
                SetActiveCharmander(charmander);
                if (!firstTurn){
                    charmander.loseHealth(getCpuAttackDamage());
                    charmander.setStatus(getCpuAttackStatus());
                }
                System.out.println("Player pokémon: " + charmander.getName() + ". Health is " + charmander.getHealth() + ". Status " +
                        "is " + charmander.getStatus() + ".");
                Map<Integer, String> charmanderMove = charmander.CharmanderBattle(user, userPokemon, getPokemonTypeCpu());
                setPlayerAttackDamage(returnMove.MoveDamage(charmanderMove));
                setPlayerAttackStatus(returnMove.MoveString(charmanderMove, returnMove.MoveDamage(charmanderMove)));
                System.out.println("Player's " + charmander.getName() + " used " + charmander.getAttackName() + ". Dealt " +
                        getPlayerAttackDamage() + " damage to opponent.");
                return false;
            case("Gengar"):
                Gengar gengar = (Gengar) user.getPokemon(x);
                SetActiveGengar(gengar);
                if (!firstTurn){
                    gengar.loseHealth(getCpuAttackDamage());
                    gengar.setStatus(getCpuAttackStatus());
                }
                System.out.println("Player pokémon: " + gengar.getName() + ". Health is " + gengar.getHealth() + ". Status " +
                        "is " + gengar.getStatus() + ".");
                Map<Integer, String> gengarMove = gengar.GengarBattle(user, userPokemon, getPokemonTypeCpu());
                setPlayerAttackDamage(returnMove.MoveDamage(gengarMove));
                setPlayerAttackStatus(returnMove.MoveString(gengarMove, returnMove.MoveDamage(gengarMove)));
                System.out.println("Player's " + gengar.getName() + " used " + gengar.getAttackName() + ". Dealt " +
                        getPlayerAttackDamage() + " damage to opponent.");
                return false;
            case("Onix"):
                Onix onix = (Onix) user.getPokemon(x);
                SetActiveOnix(onix);
                if (!firstTurn){
                    onix.loseHealth(getCpuAttackDamage());
                    onix.setStatus(getCpuAttackStatus());
                }
                System.out.println("Player pokémon: " + onix.getName() + ". Health is " + onix.getHealth() + ". Status " +
                        "is " + onix.getStatus() + ".");
                Map<Integer, String> onixMove = onix.OnixBattle(user, userPokemon, getPokemonTypeCpu());
                setPlayerAttackDamage(returnMove.MoveDamage(onixMove));
                setPlayerAttackStatus(returnMove.MoveString(onixMove, returnMove.MoveDamage(onixMove)));
                System.out.println("Player's " + onix.getName() + " used " + onix.getAttackName() + ". Dealt " +
                        getPlayerAttackDamage() + " damage to opponent.");
                return false;
            case("Pidgey"):
                Pidgey pidgey = (Pidgey) user.getPokemon(x);
                SetActivePidgey(pidgey);
                if (!firstTurn){
                    pidgey.loseHealth(getCpuAttackDamage());
                    pidgey.setStatus(getCpuAttackStatus());
                }
                System.out.println("Player pokémon: " + pidgey.getName() + ". Health is " + pidgey.getHealth() + ". Status " +
                        "is " + pidgey.getStatus() + ".");
                Map<Integer, String> pidgeyMove = pidgey.PidgeyBattle(user, userPokemon, getPokemonTypeCpu());
                setPlayerAttackDamage(returnMove.MoveDamage(pidgeyMove));
                setPlayerAttackStatus(returnMove.MoveString(pidgeyMove, returnMove.MoveDamage(pidgeyMove)));
                System.out.println("Player's " + pidgey.getName() + " used " + pidgey.getAttackName() + ". Dealt " +
                        getPlayerAttackDamage() + " damage to opponent.");
                return false;
            case("Pikacu"):
                Pikachu pikachu = (Pikachu) user.getPokemon(x);
                SetActivePikachu(pikachu);
                if (!firstTurn){
                    pikachu.loseHealth(getCpuAttackDamage());
                    pikachu.setStatus(getCpuAttackStatus());
                }
                System.out.println("Player pokémon: " + pikachu.getName() + ". Health is " + pikachu.getHealth() + ". Status " +
                        "is " + pikachu.getStatus() + ".");
                Map<Integer, String> pikachuMove = pikachu.PikachuBattle(user, userPokemon, getPokemonTypeCpu());
                setPlayerAttackDamage(returnMove.MoveDamage(pikachuMove));
                setPlayerAttackStatus(returnMove.MoveString(pikachuMove, returnMove.MoveDamage(pikachuMove)));
                System.out.println("Player's " + pikachu.getName() + " used " + pikachu.getAttackName() + ". Dealt " +
                        getPlayerAttackDamage() + " damage to opponent.");
                return false;
            case("Squirtle"):
                Squirtle squirtle = (Squirtle) user.getPokemon(x);
                SetActiveSquirtle(squirtle);
                if (!firstTurn){
                    squirtle.loseHealth(getCpuAttackDamage());
                    squirtle.setStatus(getCpuAttackStatus());
                }
                System.out.println("Player pokémon: " + squirtle.getName() + ". Health is " + squirtle.getHealth() + ". Status " +
                        "is " + squirtle.getStatus() + ".");
                Map<Integer, String> squirtleMove = squirtle.SquirtleBattle(user, userPokemon, getPokemonTypeCpu());
                setPlayerAttackDamage(returnMove.MoveDamage(squirtleMove));
                setPlayerAttackStatus(returnMove.MoveString(squirtleMove, returnMove.MoveDamage(squirtleMove)));
                System.out.println("Player's " + squirtle.getName() + " used " + squirtle.getAttackName() + ". Dealt " +
                        getPlayerAttackDamage() + " damage to opponent.");
                return false;
        }
        return true;
    }

    public void Battle(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela, Object[] userPokemon) {
        while (true) {
            while (playerTurn) {
                if (firstTurn) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    setPokemonTypeCpu(victreebel.getType());
                    playerTurn = userMove(user, userPokemon, 0, firstTurn);
                    cpuTurn = !playerTurn;
                    this.firstTurn = false;
                    this.pokemonPlayer = 0;
                } else {
                    playerTurn = userMove(user, userPokemon, pokemonPlayer, false);
                    cpuTurn = !playerTurn;
                }
            }
            while (cpuTurn) {
                if (victreebel.isPlayable()) {
                    victreebel.setHealth(victreebel.getHealth() - getPlayerAttackDamage());
                    victreebel.setStatus(getPlayerAttackStatus());
                    System.out.println("Erika's pokemon: Victreebel. Health is " + victreebel.getHealth() + " Status is " +
                            victreebel.getStatus() + ".");
                    Map<Integer, String> cpuMove = erikaAI.CreateTreeVictreebel(leaderErika, victreebel, getPokemonHealthPlayer(),
                            getPokemonTypePLayer(), getPokemonStatusPlayer());
                    if (!getPokemonStatusPlayer().equals("Normal")) {
                        setCpuAttackStatus(erikaAI.getOpponentStatus());
                    }
                    setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
                    System.out.println("Erika's " + victreebel.getName() + " used " + returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove)) + ". Dealt " +
                            getCpuAttackDamage() + " damage to opponent");
                    this.cpuTurn = false;
                    this.playerTurn = true;
                    break;
                } else if (tangela.isPlayable()) {
                    System.out.println("Erika's pokemon: Tangela. Health is " + tangela.getHealth() + "Status is " +
                            tangela.getStatus());
                    erikaAI.CreateTreeTangela(leaderErika, tangela, getPokemonHealthPlayer(), getPokemonTypePLayer(),
                            getPokemonStatusPlayer());
                    this.cpuTurn = false;
                    this.playerTurn = true;
                    break;
                } else if (vileplume.isPlayable()) {
                    System.out.println("Erika's pokémon: Vileplume. Health is " + vileplume.getHealth() + "Status is " +
                            vileplume.getStatus());
                    erikaAI.CreateTreeVileplume(leaderErika, vileplume, getPokemonHealthPlayer(), getPokemonTypePLayer(),
                            getPokemonStatusPlayer());
                    this.cpuTurn = false;
                    this.playerTurn = true;
                    break;
                } else {
                    break;
                    //will call user won here!
                }
            }
        }
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

}



