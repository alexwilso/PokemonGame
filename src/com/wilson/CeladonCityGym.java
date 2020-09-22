package com.wilson;
import java.util.Map;


public class CeladonCityGym extends Gym {
    ErikaAI erikaAI;


    public CeladonCityGym(ReturnMove returnMove, PlayerMove playerMove, PokemonStatus pokemonStatus, Battlemenu battlemenu, ErikaAI erikaAI) {
        super(returnMove, playerMove, pokemonStatus, battlemenu);
        this.erikaAI = erikaAI; }

    public boolean Welcome(Player user, Object[] pokemon){
        // Returns Boolean determined by result of battle function

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
        System.out.println("Leader Erika sent out Victreebell\nErika's pokémon: " +
                victreebel.getName() + ". Health is " + victreebel.getHealth() + ". Status is " + victreebel.getStatus());
        return Battle(leaderErika, user, vileplume, victreebel, tangela, pokemon); }


    public void VictrebellNotPlayable(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                      Object[] userPokemon){
        // Calls battle function when vicrebells is not playable(Health=0). Sets tangela to cpus pokemon.
        System.out.println("Leader Erika sent out Tangela");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon); }

    public void VictreebelAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                 Object[] userPokemon){
        /* Updates victrebell's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in erika's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            victreebel.loseHealth(getPlayerAttackDamage());
            if (victreebel.getStatus().equals("Normal")) {
                victreebel.setStatus(getPlayerAttackStatus()); }}

        if (!victreebel.isPlayable()){
            VictrebellNotPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        System.out.println("Erika's pokemon: Victreebel. Health is " + victreebel.getHealth() + ". Status is " +
                victreebel.getStatus() + ".");

        // Updates move and health based on current status
        if (!victreebel.getStatus().equals("Normal")){
            if (victreebel.VictrebellStatus(victreebel)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!victreebel.isPlayable()){
            VictrebellNotPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = erikaAI.CreateTreeVictreebel(leaderErika, victreebel, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + victreebel.getName() + " used " + victreebel.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        if (victreebel.getAttackName().equals("Max Potion")){
            System.out.println("Victreebell's health: " + victreebel.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void TangelaNotPlayable(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                   Object[] userPokemon){
        // Calls battle function when tangela is not playable(Health=0). Sets vileplume to cpus pokemon.
        System.out.println("Leader Erika sent out Vileplume");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
    }

    public void TangelaAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                              Object[] userPokemon){
       /* Updates tangela's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in erika's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            tangela.loseHealth(getPlayerAttackDamage());
            if (tangela.getStatus().equals("Normal")) {
                tangela.setStatus(getPlayerAttackStatus()); }}

        if (!tangela.isPlayable()){
            TangelaNotPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        System.out.println("Erika's pokemon: Tangela. Health is " + tangela.getHealth() + ". Status is " +
                tangela.getStatus() + ".");

        // Updates move and health based on current status
        if (!tangela.getStatus().equals("Normal")){
            if (tangela.TangelaStatus(tangela)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!tangela.isPlayable()){
            TangelaNotPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = erikaAI.CreateTreeTangela(leaderErika, tangela, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Erika's " + tangela.getName() + " used " + tangela.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        if (tangela.getAttackName().equals("Potion")){
            System.out.println("Tangela's health: " + tangela.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void VileplumeNotPlayable(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                     Object[] userPokemon){
        // Calls battle function when Vileplume is not playable(Health=0). Will call player won function.
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderErika, user, vileplume, victreebel, tangela, userPokemon); }

    public void VileplumeAttack(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                                Object[] userPokemon){
       /* Updates vileplume's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in erika's ai class. */

        //Updates status and health based on user move
        if (isCpuDamage()) {
            vileplume.loseHealth(getPlayerAttackDamage());
            if (vileplume.getStatus().equals("Normal")) {
                vileplume.setStatus(getPlayerAttackStatus()); }}

        if (!vileplume.isPlayable()){
            VileplumeNotPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        System.out.println("Erika's pokemon: Vileplume. Health is " + vileplume.getHealth() + ". Status is " +
                vileplume.getStatus() + ".");

        // Updates move and health based on current status
        if (!vileplume.getStatus().equals("Normal")){
            if (vileplume.VileplumeStatus(vileplume)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!vileplume.isPlayable()){
            VileplumeNotPlayable(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = erikaAI.CreateTreeVileplume(leaderErika, vileplume, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(erikaAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
        System.out.println("Erika's " + vileplume.getName() + " used " + vileplume.getAttackName() + ".");
        AttackStrength(leaderErika.getStrength());
        System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public boolean Battle(LeaderErika leaderErika, Player user, Vileplume vileplume, Victreebel victreebel, Tangela tangela,
                       Object[] userPokemon) {
        /*While user and computer have pokemon available, battle will continue. Alternates between player and computer
         move. If user runs out of playable pokemon, false is returned. If computer runs out, true is returned*/

        while (isBattleOn()) {
            // Sets player attack and health and status of user pokemon based on cpu move. Checks if player loses.
            while (isPlayerTurn()) {
                if (isFirstTurn()) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    setFirstTurn(false);
                    setPokemonTypeCpu(victreebel.getType());
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
                if (victreebel.isPlayable()) {
                    VictreebelAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
                    break; }
                else if (tangela.isPlayable()) {
                    TangelaAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
                    break; }
                else if (vileplume.isPlayable()) {
                    VileplumeAttack(leaderErika, user, vileplume, victreebel, tangela, userPokemon);
                    break; }
                else {
                    setBattleOn(false);
                    setCpuTurn(false);
                    setPlayerTurn(false);
                    setPlayerWins(PlayerWins(user));
                    break; } } }
        return isPlayerWins(); }

    public boolean PlayerWins(Player user){
        // Called if player wins. Adds 2000 to player money.
        System.out.println("You defeated Leader Erika");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Erika: Oh! I conceded defeat. You are remarkably strong. \n" +
                "I must confer on you the RAINBOWBADGE.\nPlayer got 2000 for winning.");
        user.setMoney(2000);
        return true; }

    public boolean PlayerLoses(){
        // Called when player loses.
        System.out.println("You were defeated by Leader Erika!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Erika: Looks like I am stronger than you! Please come back again. I enjoyed showing" +
                " you the strength of the grass type");
        battlemenu.pressAnyKeyToContinue();
        return false;
    }
}
