package com.wilson;

import java.util.Map;

public class ViridianCityGym extends Gym{
    GiovanniAI giovanniAI;

    public ViridianCityGym(ReturnMove returnMove, PlayerMove playerMove, PokemonStatus pokemonStatus, Battlemenu battlemenu, GiovanniAI giovanniAI) {
        super(returnMove, playerMove, pokemonStatus, battlemenu);
        this.giovanniAI = giovanniAI;
    }

    public boolean Welcome(Player user, Object[] pokemon){
        // Returns Boolean determined by result of battle function

        Dugtrio dugtrio = new Dugtrio("Dugtrio", "Ground", 100, 120, 120, "Normal",
                new TriAttack(15, 15, 15), new MudSlap(10, 20,20),
                new Dig(25, 10, 10), new EarthQuake(25, 10, 10),
                "Visible");
        Rhyhorn rhyhorn = new Rhyhorn("Rhyhorn", "Rock", 100, 130, 130, "Normal",
                new HornAttack(25,10,10), new Stomp(15, 20, 20),
                new MegaHorn(20, 12,12), new EarthQuake(25, 10, 10));
        Nidoqueen nidoqueen = new Nidoqueen("Nidoqueen", "Rock", 100, 140,140, "Normal",
                new DoubleKick(15, 15,15), new BodySlam(25, 10, 10),
                new PoisonSting(10, 20, 20, pokemonStatus), new FocusPunch(30, 5, 5));
        LeaderGiovanni leaderGiovanni = new LeaderGiovanni(rhyhorn, nidoqueen, dugtrio);

        System.out.println("Leader Giovanni: Fwahaha! Welcome to my hideout! It shall be so until I can restore Team\n" +
                "Rocket to its former glory. But, you have found me again. So be it.\nThis time I'm not holding back!" +
                " Once more, you shall face Giovanni, the greatest trainer!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Giovanni sent out Dugtrio\nLt. Surge's pokémon: " +dugtrio.getName() + ". Health is " +
                dugtrio.getHealth() + ". Status is: " + dugtrio.getStatus());
        return Battle(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, pokemon);
    }

    public void DugtrioNotPlayable(LeaderGiovanni leaderGiovanni, Player user, Dugtrio dugtrio, Rhyhorn rhyhorn, Nidoqueen nidoqueen,
                                      Object[] userPokemon){
        // Calls battle function when dugtrio is not playable(Health=0). Sets rhyhorn to cpus pokemon.
        System.out.println("Leader Giovanni sent out Rhyhorn");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
    }

    public void DugtrioAttack(LeaderGiovanni leaderGiovanni, Player user, Dugtrio dugtrio, Rhyhorn rhyhorn, Nidoqueen nidoqueen,
                                 Object[] userPokemon){
       /* Updates dugtrio's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in giovanni's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            dugtrio.loseHealth(getPlayerAttackDamage());
            if (dugtrio.getStatus().equals("Normal")) {
                dugtrio.setStatus(getPlayerAttackStatus()); }}

        if (!dugtrio.isPlayable()){
            DugtrioNotPlayable(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
            return; }

        System.out.println("Leader Giovanni's pokemon: Dugtrio. Health is " + dugtrio.getHealth() + ". Status is " +
                dugtrio.getStatus() + ".");

        // Updates move and health based on current status
        if (!dugtrio.getStatus().equals("Normal")){
            if (dugtrio.DugtrioStatus(dugtrio)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!dugtrio.isPlayable()){
            DugtrioNotPlayable(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = giovanniAI.CreateTreeDugtrio(leaderGiovanni, dugtrio, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(giovanniAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
        System.out.println("Leader Giovanni's " + dugtrio.getName() + " used " + dugtrio.getAttackName() + ".");
        AttackStrength(leaderGiovanni.getStrength());
        if (dugtrio.getAttackName().equals("Max Potion")){
            System.out.println("Dugtrio's health: " + dugtrio.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void RhyhornNotPlayable(LeaderGiovanni leaderGiovanni, Player user, Dugtrio dugtrio, Rhyhorn rhyhorn, Nidoqueen nidoqueen,
                                   Object[] userPokemon){
        // Calls battle function when Rhyhorn is not playable(Health=0). Sets Nidoqueen to cpus pokemon.
        System.out.println("Leader Giovanni sent out Nidoqueen");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon); }

    public void RhyhornAttack(LeaderGiovanni leaderGiovanni, Player user, Dugtrio dugtrio, Rhyhorn rhyhorn, Nidoqueen nidoqueen,
                              Object[] userPokemon){
       /* Updates rhyhorn's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in giovanni's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            rhyhorn.loseHealth(getPlayerAttackDamage());
            if (rhyhorn.getStatus().equals("Normal")) {
                rhyhorn.setStatus(getPlayerAttackStatus()); }}

        if (!rhyhorn.isPlayable()){
            RhyhornNotPlayable(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
            return; }

        System.out.println("Leader Giovanni pokemon: Rhyhorn. Health is " + rhyhorn.getHealth() + ". Status is " +
                rhyhorn.getStatus() + ".");

        // Updates move and health based on current status
        if (!rhyhorn.getStatus().equals("Normal")){
            if (rhyhorn.RhyhornStatus(rhyhorn)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!rhyhorn.isPlayable()){
            RhyhornNotPlayable(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = giovanniAI.CreateTreeRhyhorn(leaderGiovanni, rhyhorn, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(giovanniAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
        System.out.println("Leader Giovanni's " + rhyhorn.getName() + " used " + rhyhorn.getAttackName() + ".");
        AttackStrength(leaderGiovanni.getStrength());
        if (rhyhorn.getAttackName().equals("Max Potion")){
            System.out.println("Ryhorn's health: " + rhyhorn.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void NidoqueenNotPLayable(LeaderGiovanni leaderGiovanni, Player user, Dugtrio dugtrio, Rhyhorn rhyhorn, Nidoqueen nidoqueen,
                                   Object[] userPokemon){
        // Sets attack based on return of AI class. Updates players status and health based on result.
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon); }

    public void NidoqueenAttack(LeaderGiovanni leaderGiovanni, Player user, Dugtrio dugtrio, Rhyhorn rhyhorn, Nidoqueen nidoqueen,
                              Object[] userPokemon){
        /* Updates nidoqueen's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in giovanni's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            nidoqueen.loseHealth(getPlayerAttackDamage());
            if (nidoqueen.getStatus().equals("Normal")) {
                nidoqueen.setStatus(getPlayerAttackStatus()); }}

        if (!nidoqueen.isPlayable()){
            NidoqueenNotPLayable(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
            return; }

        System.out.println("Leader Giovanni's pokemon: Nidoqueen. Health is " + nidoqueen.getHealth() + ". Status is " +
                nidoqueen.getStatus() + ".");

        // Updates move and health based on current status
        if (!nidoqueen.getStatus().equals("Normal")){
            if (nidoqueen.NidoqueenStatus(nidoqueen)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!nidoqueen.isPlayable()){
            NidoqueenAttack(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = giovanniAI.CreateTreeNidoqueen(leaderGiovanni, nidoqueen, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(giovanniAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
        System.out.println("Leader Giovanni's " + nidoqueen.getName() + " used " + nidoqueen.getAttackName() + ".");
        AttackStrength(leaderGiovanni.getStrength());
        if (nidoqueen.getAttackName().equals("Max Potion")){
            System.out.println("Nidoqueen's health: " + nidoqueen.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public boolean Battle(LeaderGiovanni leaderGiovanni, Player user, Dugtrio dugtrio, Rhyhorn rhyhorn, Nidoqueen nidoqueen,
                          Object[] userPokemon) {
        /*While user and computer have pokemon available, battle will continue. Alternates between player and computer
         move. If user runs out of playable pokemon, false is returned. If computer runs out, true is returned*/

        while (isBattleOn()) {
            // Sets player attack and health and status of user pokemon based on cpu move. Checks if player loses.
            while (isPlayerTurn()) {
                if (isFirstTurn()) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    setFirstTurn(false);
                    setPokemonTypeCpu(dugtrio.getType());
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
            // Sets Cpu attack and health and status of cpu pokemon based on player move. Checks if cpu loses.
            while (isCpuTurn()) {
                if (dugtrio.isPlayable()) {
                    DugtrioAttack(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
                    break; }
                else if (rhyhorn.isPlayable()) {
                    RhyhornAttack(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
                    break; }
                else if (nidoqueen.isPlayable()) {
                    NidoqueenAttack(leaderGiovanni, user, dugtrio, rhyhorn, nidoqueen, userPokemon);
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
        System.out.println("You defeated Leader Giovanni!.");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Giovanni: Ha! That was a truly intense fight!\n" +
                "You've won! As proof here it is the EARTHBADGE!\nPlayer got 2000 for winning.");
        user.setMoney(2000);
        return true; }

    public boolean PlayerLoses(){
        // Called when player loses.
        System.out.println("You were defeated by Leader Giovanni!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Leader Giovanni: Mwhaha! I knew you were no match for me kid! You tried hard,\nbut you'll " +
                "never be good enough to beat me!");
        battlemenu.pressAnyKeyToContinue();
        return false; }
}
