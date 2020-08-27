package com.wilson;

import java.util.Map;

public class CeladonCityGym {
    boolean playerTurn;
    boolean cpuTurn;
    boolean firstTurn;
    private String pokemonTypePLayer;
    private Integer pokemonHealthPlayer;
    private String pokemonStatusPlayer;
    private int cpuAttackDamage;
    private String cpuAttackStatus;
    private int playerAttackDamage;
    private String playerAttackStatus;
    ErikaAI erikaAI = new ErikaAI();
    ReturnMove returnMove = new ReturnMove();

    public CeladonCityGym() {
        this.playerTurn = true;
        this.cpuTurn = false;
        this.firstTurn = true;
        this.cpuAttackStatus = "Normal";
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
        switch (userPokemon[x].toString()){
            case("Bulbasuar"):
                Bulbasaur bulbasaur = (Bulbasaur) user.getPokemon(x);
                if (!firstTurn){
                    bulbasaur.loseHealth(getCpuAttackDamage());
                    bulbasaur.setStatus(getCpuAttackStatus());
                }
                SetActiveBulbasaur(bulbasaur);
                System.out.println(bulbasaur.BulbasuarBattle(user, userPokemon));
                return false;
            case("Charmander"):
                Charmander charmander = (Charmander) user.getPokemon(x);
                if (!firstTurn){
                    charmander.loseHealth(getCpuAttackDamage());
                    charmander.setStatus(getCpuAttackStatus());
                }
                SetActiveCharmander(charmander);
                System.out.println(charmander.CharmanderBattle(user, userPokemon));
                return false;
            case("Gengar"):
                Gengar gengar = (Gengar) user.getPokemon(x);
                if (!firstTurn){
                    gengar.loseHealth(getCpuAttackDamage());
                    gengar.setStatus(getCpuAttackStatus());
                }
                SetActiveGengar(gengar);
                System.out.println(gengar.GengarBattle(user, userPokemon));
                return false;
            case("Onix"):
                Onix onix = (Onix) user.getPokemon(x);
                if (!firstTurn){
                    onix.loseHealth(getCpuAttackDamage());
                    onix.setStatus(getCpuAttackStatus());
                }
                SetActiveOnix(onix);
                System.out.println(onix.OnixBattle(user, userPokemon));
                return false;
            case("Pidgey"):
                Pidgey pidgey = (Pidgey) user.getPokemon(x);
                if (!firstTurn){
                    pidgey.loseHealth(getCpuAttackDamage());
                    pidgey.setStatus(getCpuAttackStatus());
                }
                SetActivePidgey(pidgey);
                System.out.println(pidgey.PidgeyBattle(user, userPokemon));
                return false;
            case("Pikacu"):
                Pikachu pikachu = (Pikachu) user.getPokemon(x);
                if (!firstTurn){
                    pikachu.loseHealth(getCpuAttackDamage());
                    pikachu.setStatus(getCpuAttackStatus());
                }
                SetActivePikachu(pikachu);
                System.out.println(pikachu.PikachuBattle(user, userPokemon));
                return false;
            case("Squirtle"):
                Squirtle squirtle = (Squirtle) user.getPokemon(x);
                if (!firstTurn){
                    squirtle.loseHealth(getCpuAttackDamage());
                    squirtle.setStatus(getCpuAttackStatus());
                }
                SetActiveSquirtle(squirtle);
                System.out.println(squirtle.SquirtleBattle(user, userPokemon));
                return false;
        }
        return true;
    }

    public void Battle(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela, Object[] userPokemon) {
        while (true) {
            while (playerTurn) {
                if (firstTurn) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    playerTurn = userMove(user, userPokemon, 0, firstTurn);
                    cpuTurn = !playerTurn;
                    firstTurn = false;
                } else {
                    playerTurn = userMove(user, userPokemon, 0, false);
                    cpuTurn = !playerTurn;
                }
            }
            while (cpuTurn) {
                if (victreebel.isPlayable()) {
                    System.out.println("Erika's pokemon: Victreebel. Health is " + victreebel.getHealth() + " Status is " +
                            victreebel.getStatus());
                    Map<String, Integer> cpuMove = erikaAI.CreateTreeVictreebel(leaderErika, victreebel, getPokemonHealthPlayer(),
                            getPokemonTypePLayer(), getPokemonStatusPlayer());
                    if (!getPokemonStatusPlayer().equals("Normal")) {
                        setCpuAttackStatus(erikaAI.getOpponentStatus());
                    }
                    setCpuAttackDamage(returnMove.moveDamage(cpuMove, returnMove.MoveString(cpuMove)));
                    System.out.println("Erika's " + victreebel.getName() + " used " + returnMove.MoveString(cpuMove) + ". Dealt " +
                            getCpuAttackDamage() + " to opponent");
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
        System.out.println("Player pokémon: " + bulbasaur.getName() + ". Health is " + bulbasaur.getHealth() + " Status " +
                "is " + bulbasaur.getStatus());
        setPokemonHealthPlayer(bulbasaur.getHealth());
        setPokemonTypePLayer(bulbasaur.getType());
        setPokemonStatusPlayer(bulbasaur.getStatus());
    }

    private void SetActiveCharmander(Charmander charmander){
        System.out.println("Player pokémon: " + charmander.getName() + ". Health is " + charmander.getHealth() + ". Status " +
                "is " + charmander.getStatus());
        setPokemonHealthPlayer(charmander.getHealth());
        setPokemonTypePLayer(charmander.getType());
        setPokemonStatusPlayer(charmander.getStatus());
    }

    private void SetActiveGengar(Gengar gengar){
        System.out.println("Player pokémon: " + gengar.getName() + ". Health is " + gengar.getHealth() + ". Status " +
                "is " + gengar.getStatus());
        setPokemonHealthPlayer(gengar.getHealth());
        setPokemonTypePLayer(gengar.getType());
        setPokemonStatusPlayer(gengar.getStatus());
    }

    private void SetActiveOnix(Onix onix){
        System.out.println("Player pokémon: " + onix.getName() + ". Health is " + onix.getHealth() + ". Status " +
                "is " + onix.getStatus());
        setPokemonHealthPlayer(onix.getHealth());
        setPokemonTypePLayer(onix.getType());
        setPokemonStatusPlayer(onix.getStatus());
    }

    private void SetActivePidgey(Pidgey pidgey){
        System.out.println("Player pokémon: " + pidgey.getName() + ". Health is " + pidgey.getHealth() + ". Status " +
                "is " + pidgey.getStatus());
        setPokemonHealthPlayer(pidgey.getHealth());
        setPokemonTypePLayer(pidgey.getType());
        setPokemonStatusPlayer(pidgey.getStatus());
    }

    private void SetActivePikachu(Pikachu pikachu){
        System.out.println("Player pokémon: " + pikachu.getName() + ". Health is " + pikachu.getHealth() + " Status " +
                "is " + pikachu.getStatus());
        setPokemonHealthPlayer(pikachu.getHealth());
        setPokemonTypePLayer(pikachu.getType());
        setPokemonStatusPlayer(pikachu.getStatus());
    }

    private void SetActiveSquirtle(Squirtle squirtle){
        System.out.println("Player pokémon: " + squirtle.getName() + ". Health is " + squirtle.getHealth() + " Status " +
                "is " + squirtle.getStatus());
        setPokemonHealthPlayer(squirtle.getHealth());
        setPokemonTypePLayer(squirtle.getType());
        setPokemonStatusPlayer(squirtle.getStatus());
    }

}



