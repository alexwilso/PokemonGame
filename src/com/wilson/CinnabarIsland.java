package com.wilson;

import java.util.Map;

public class CinnabarIsland extends Gym{
    BlaineAI blaineAI;

    public CinnabarIsland(ReturnMove returnMove, PlayerMove playerMove, PokemonStatus pokemonStatus, Battlemenu battlemenu, BlaineAI blaineAI) {
        super(returnMove, playerMove, pokemonStatus, battlemenu);
        this.blaineAI = blaineAI; }

    public boolean Welcome(Player user, Object[] pokemon){
        // Returns Boolean determined by result of battle function

        Flareon flareon = new Flareon("Flareon", "Fire", 50, 110, 110, "Normal",
                new FlameCharge(30, 8, 8, pokemonStatus), new SuperPower(25, 10, 10),
                new Toxic(15, 20, 20, pokemonStatus), new FirePunch(25, 10,10));
        Rapidash rapidash = new Rapidash("Rapidash", "Fire", 50, 110, 120, "Normal",
                new Stomp(15, 20, 20), new FireSpin(25, 10,10, pokemonStatus),
                new FireBlast(25, 10, 10, pokemonStatus), new Bounce(10, 20, 20));
        Growlithe growlithe = new Growlithe("Growlithe", "Fire", 50, 110, 120, "Normal",
                new FireFang(30, 5, 5), new Bite(20, 15, 15),
                new Roar(10, 20, 20), new FlareBlitz(20, 10, 10, pokemonStatus));
        LeaderBlaine leaderBlaine = new LeaderBlaine(flareon, rapidash, growlithe);
        System.out.println("Leader Blaine: I am Blaine, the red-hot leader of Cinnabar Gym! My fiery pokémon are all rough\n" +
                "and ready with intense heat! They incinerate all challengers! HAH! You better have burn heal!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Blaine sent out Flareon.\nBlaine's pokémon: " + flareon.getName() + ". Health is " +
                flareon.getHealth() + ". Status is: " + flareon.getStatus());
        return Battle(leaderBlaine, user, flareon, rapidash, growlithe, pokemon); }


    public void FlareonNotPlayable(LeaderBlaine leaderBlaine, Player user, Flareon flareon, Rapidash rapidash, Growlithe growlithe,
                                     Object[] userPokemon){
        // Calls battle function when Flareon is not playable(Health=0). Sets rapidash to cpus pokemon.
        System.out.println("Leader Blaine sent out Rapidash");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
    }

    public void FlareonAttack(LeaderBlaine leaderBlaine, Player user, Flareon flareon, Rapidash rapidash, Growlithe growlithe,
                                Object[] userPokemon){
       /* Updates flareon's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in blaines's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            flareon.loseHealth(getPlayerAttackDamage());
            if (flareon.getStatus().equals("Normal")) {
                flareon.setStatus(getPlayerAttackStatus()); }}

        if (!flareon.isPlayable()){
            FlareonNotPlayable(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
            return; }

        System.out.println("Blaine's pokemon: Flareon. Health is " + flareon.getHealth() + ". Status is " +
                flareon.getStatus() + ".");

        // Updates move and health based on current status
        if (!flareon.getStatus().equals("Normal")){
            if (flareon.FlareonStatus(flareon)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!flareon.isPlayable()){
            FlareonNotPlayable(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = blaineAI.CreateTreeFlareon(leaderBlaine, flareon, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(blaineAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
        System.out.println("Blaine's " + flareon.getName() + " used " + flareon.getAttackName() + ".");
        AttackStrength(leaderBlaine.getStrength());
        System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public void RapidashNotPlayable(LeaderBlaine leaderBlaine, Player user, Flareon flareon, Rapidash rapidash, Growlithe growlithe,
                                   Object[] userPokemon){
        // Calls battle function when Flareon is not playable(Health=0). Sets rapidash to cpus pokemon.
        System.out.println("Leader Blaine sent out Growlithe");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon); }

    public void RapidashAttack(LeaderBlaine leaderBlaine, Player user, Flareon flareon, Rapidash rapidash, Growlithe growlithe,
                              Object[] userPokemon){
        /* Updates rapidash's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in blaines's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            rapidash.loseHealth(getPlayerAttackDamage());
            if (rapidash.getStatus().equals("Normal")) {
                rapidash.setStatus(getPlayerAttackStatus()); } }

        if (!rapidash.isPlayable()){
            RapidashNotPlayable(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
            return; }

        System.out.println("Erika's pokemon: Rapidash. Health is " + rapidash.getHealth() + ". Status is " +
                rapidash.getStatus() + ".");

        // Updates move and health based on current status
        if (!rapidash.getStatus().equals("Normal")){
            if (rapidash.RapidashStatus(rapidash)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!rapidash.isPlayable()){
            RapidashNotPlayable(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
            return; }
        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = blaineAI.CreateTreeRapidash(leaderBlaine, rapidash, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(blaineAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
        System.out.println("Blaine's " + rapidash.getName() + " used " + rapidash.getAttackName() + ".");
        AttackStrength(leaderBlaine.getStrength());
        System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void GrowlitheNotPlayable(LeaderBlaine leaderBlaine, Player user, Flareon flareon, Rapidash rapidash, Growlithe growlithe,
                                    Object[] userPokemon){
        // Calls battle function when growlithe is not playable(Health=0). Will call player won function.
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon); }

    public void GrowlitheAttack(LeaderBlaine leaderBlaine, Player user, Flareon flareon, Rapidash rapidash, Growlithe growlithe,
                               Object[] userPokemon){
     /* Updates rapidash's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in blaines's ai class. */

        //Updates status and health based on user move
        if (isCpuDamage()) {
            growlithe.loseHealth(getPlayerAttackDamage());
            if (growlithe.getStatus().equals("Normal")) {
                growlithe.setStatus(getPlayerAttackStatus()); }}

        if (!growlithe.isPlayable()){
            GrowlitheNotPlayable(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
            return; }

        System.out.println("Erika's pokemon: Growlithe. Health is " + growlithe.getHealth() + ". Status is " +
                growlithe.getStatus() + ".");

        // Updates move and health based on current status
        if (!growlithe.getStatus().equals("Normal")){
            if (growlithe.GrowlitheStatus(growlithe)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!growlithe.isPlayable()){
            GrowlitheNotPlayable(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
            return; }

        Map<Integer, String> cpuMove = blaineAI.CreateTreeGrowlithe(leaderBlaine, growlithe, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(blaineAI.getOpponentStatus());}
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
        System.out.println("Blaine's " + growlithe.getName() + " used " + growlithe.getAttackName() + ".");
        AttackStrength(leaderBlaine.getStrength());
        System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public boolean Battle(LeaderBlaine leaderBlaine, Player user, Flareon flareon, Rapidash rapidash, Growlithe growlithe,
                          Object[] userPokemon) {
        /*While user and computer have pokemon available, battle will continue. Alternates between player and computer
         move. If user runs out of playable pokemon, false is returned. If computer runs out, true is returned*/

        while (isBattleOn()) {
            // Sets player attack and health and status of user pokemon based on cpu move. Checks if player loses.
            while (isPlayerTurn()) {
                if (isFirstTurn()) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    setFirstTurn(false);
                    setPokemonTypeCpu(flareon.getType());
                    setPlayerTurn(PlayerAttack(user, userPokemon, 0, false, false));
                    setCpuTurn(!isPlayerTurn());
                    setActivePokemon(0); }
                else {
                    setPlayerTurn(PlayerAttack(user, userPokemon, playerMove.getActivePokemon(), false, true));
                    setCpuTurn(!isPlayerTurn());
                    if (user.allFainted()) {
                        setBattleOn(false);
                        setPlayerTurn(false);
                        setCpuTurn(false);
                        setPlayerWins(PlayerLoses()); } } }
            // Sets Cpu attack and health and status of cpu pokemon based on player move. Checks if cpu  loses.
            while (isCpuTurn()) {
                if (flareon.isPlayable()) {
                    FlareonAttack(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
                    break; }
                else if (rapidash.isPlayable()) {
                    RapidashAttack(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
                    break; }
                else if (growlithe.isPlayable()) {
                    GrowlitheAttack(leaderBlaine, user, flareon, rapidash, growlithe, userPokemon);
                    break; }
                else {
                    setBattleOn(false);
                    setCpuTurn(false);
                    setPlayerTurn(false);
                    setPlayerWins(PlayerWins(user));
                    break; } } }
        return isPlayerWins(); }

    public boolean PlayerWins(Player user){
        System.out.println("You defeated Leader Blaine");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Blaine: I have burned down to nothing! Not even ashes remain\n" +
                "You have earned the VOLCANOBADGE.\nPlayer got 2000 for winning.");
        user.setMoney(2000);
        return true; }

    public boolean PlayerLoses(){
        System.out.println("You were defeated by Leader Blaine!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Blaine: I am much to strong for you! I have reduced you to nothing but a pile of ashes! \n" +
                "Looks like you will never be as strong as me!");
        battlemenu.pressAnyKeyToContinue();
        return false; }
}

