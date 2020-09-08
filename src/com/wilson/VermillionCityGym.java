package com.wilson;

import java.util.Map;

public class VermillionCityGym extends Gym{
    SurgeAI surgeAI;

    public VermillionCityGym(ReturnMove returnMove, PlayerMove playerMove, PokemonStatus pokemonStatus, Battlemenu battlemenu, SurgeAI surgeAI) {
        super(returnMove, playerMove, pokemonStatus, battlemenu);
        this.surgeAI = surgeAI; }

    public boolean Welcome(Player user, Object[] pokemon){
        Electabuzz electabuzz = new Electabuzz("Electabuzz", "Electric", 50, 110, 110, "Normal",
                new QuickAttack(10, 25, 25), new ThunderPunch(30, 10, 10),
                new Thunder(25, 15, 15, pokemonStatus), new LowKick(15, 20, 20));
        Pikachu pikachu = new Pikachu("Pikacu", "Electric", 50, 140, 140, "Normal",
                new QuickAttack(15,25,25), new ThunderShock(25,20,20, pokemonStatus),
                new Thunder(40,5,5, pokemonStatus), new Growl(15,20,20), battlemenu);
        Voltorb voltorb = new Voltorb("Voltorb", "Electric", 50, 110, 110, "Normal",
                new Spark(25, 10,10, pokemonStatus), new Tackle(10, 25, 25),
                new SonicBoom(20, 20, 20), new SelfDestruct(200, 1, 1));
        LeaderSurge leaderSurge = new LeaderSurge(pikachu, electabuzz, voltorb);
        System.out.println("Lt Surge: Hey kid, what do you think you're doing here? You won't live long in combat!\nNot" +
                " with your puny power! I tell you, kid, electric pokémon saved me during the war! They zapped my enemies" +
                " into paralysis! The same as I'll do yo you!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Lt. Surge sent out voltorb\nLt. Surge's pokémon: " +voltorb.getName() + ". Health is  " +
                voltorb.getHealth() + ". Status is: " + voltorb.getStatus());
        return Battle(leaderSurge, user, voltorb, electabuzz, pikachu, pokemon); }

    public void VoltorbNotPlayable(LeaderSurge leaderSurge, Player user, Voltorb voltorb, Electabuzz electabuzz, Pikachu pikachu,
                                   Object[] userPokemon){
        // Determines if voltorb is playable
        System.out.println("Lt Surge sent out Electabuzz");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
    }

    public void VoltorbAttack(LeaderSurge leaderSurge, Player user, Voltorb voltorb, Electabuzz electabuzz, Pikachu pikachu,
                                 Object[] userPokemon){
        // Updates voltorbs health and status based on player move.
        // Sets damage and status of opposing pokemon based on return from erika ai class.
        if (isCpuDamage()) {
            voltorb.loseHealth(getPlayerAttackDamage());
            if (voltorb.getStatus().equals("Normal")) {
                voltorb.setStatus(getPlayerAttackStatus());
            }}

        if (!voltorb.isPlayable()){
            VoltorbNotPlayable(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
            return;
        }

        System.out.println("Lt Surge's pokemon: Voltorb. Health is " + voltorb.getHealth() + ". Status is " +
                voltorb.getStatus() + ".");

        if (!voltorb.getStatus().equals("Normal")){
            if (voltorb.VoltorbStatus(voltorb)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!voltorb.isPlayable()){
            VoltorbNotPlayable(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
            return;
        }
        Map<Integer, String> cpuMove = surgeAI.CreateTreeVoltorb(leaderSurge, voltorb, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(surgeAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Lt Surge's " + voltorb.getName() + " used " + voltorb.getAttackName() + ".");
        AttackStrength(leaderSurge.getStrength());
        if (voltorb.getAttackName().equals("Max Potion")){
            System.out.println("Voltorb's health: " + voltorb.getHealth());
        } else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true);
    }

    public void ElectabuzzNotPlayable(LeaderSurge leaderSurge, Player user, Voltorb voltorb, Electabuzz electabuzz, Pikachu pikachu,
                                      Object[] userPokemon){
        // Determines if pikashu is playable
        System.out.println("Lt. Surge sent out Pikachu");
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
    }

    public void ElectabuzzAttack(LeaderSurge leaderSurge, Player user, Voltorb voltorb, Electabuzz electabuzz, Pikachu pikachu,
                              Object[] userPokemon){
        // Updates Electabuzz health and status based on player move.
        // Sets damage and status of opposing pokemon based on return from LeaderSurge ai class.
        if (isCpuDamage()) {
            electabuzz.loseHealth(getPlayerAttackDamage());
            if (electabuzz.getStatus().equals("Normal")) {
                electabuzz.setStatus(getPlayerAttackStatus());
            }}

        if (!electabuzz.isPlayable()){
            ElectabuzzNotPlayable(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
            return; }

        System.out.println("Lt Surge's pokemon: Electabuzz. Health is " + electabuzz.getHealth() + ". Status is " +
                electabuzz.getStatus() + ".");

        if (!electabuzz.getStatus().equals("Normal")){
            if (electabuzz.ElectabuzzStatus(electabuzz)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!electabuzz.isPlayable()){
            VoltorbNotPlayable(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
            return;
        }
        Map<Integer, String> cpuMove = surgeAI.CreateTreeElectabuzz(leaderSurge, electabuzz, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(surgeAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Lt Surge's " + electabuzz.getName() + " used " + electabuzz.getAttackName() + ".");
        AttackStrength(leaderSurge.getStrength());
        if (electabuzz.getAttackName().equals("Max Potion")){
            System.out.println("Electabuzz's health: " + electabuzz.getHealth());
        } else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void PikachuNotPLayable(LeaderSurge leaderSurge, Player user, Voltorb voltorb, Electabuzz electabuzz, Pikachu pikachu,
                                      Object[] userPokemon){
        // Determines if LeaderSurge is playable
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        Battle(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
    }

    public void PikachuAttack(LeaderSurge leaderSurge, Player user, Voltorb voltorb, Electabuzz electabuzz, Pikachu pikachu,
                                 Object[] userPokemon){
        // Updates Electabuzz health and status based on player move.
        // Sets damage and status of opposing pokemon based on return from LeaderSurge ai class.
        if (isCpuDamage()) {
            pikachu.loseHealth(getPlayerAttackDamage());
            if (pikachu.getStatus().equals("Normal")) {
                pikachu.setStatus(getPlayerAttackStatus());
            }}

        if (!pikachu.isPlayable()){
            PikachuNotPLayable(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
            return; }

        System.out.println("Lt Surge's pokemon: Pikachu. Health is " + pikachu.getHealth() + ". Status is " +
                pikachu.getStatus() + ".");

        if (!pikachu.getStatus().equals("Normal")){
            if (pikachu.PikachuStatus(pikachu)) {
                setCpuAttackStatus("Normal");
                setCpuAttackDamage(0);
                setCpuDamage(true);
                setCpuTurn(false);
                setPlayerTurn(true);
                return;} }

        if (!pikachu.isPlayable()){
            PikachuNotPLayable(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
            return;
        }
        Map<Integer, String> cpuMove = surgeAI.CreateTreePikachu(leaderSurge, pikachu, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(surgeAI.getOpponentStatus());
        }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println("Lt Surge's " + pikachu.getName() + " used " + pikachu.getAttackName() + ".");
        AttackStrength(leaderSurge.getStrength());
        if (pikachu.getAttackName().equals("Max Potion")){
            System.out.println("Pikachu's health: " + pikachu.getHealth());
        } else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent.");
        }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }



    public boolean Battle(LeaderSurge leaderSurge, Player user, Voltorb voltorb, Electabuzz electabuzz, Pikachu pikachu,
                          Object[] userPokemon) {
        while (isBattleOn()) {
            while (isPlayerTurn()) {
                if (isFirstTurn()) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    setFirstTurn(false);
                    setPokemonTypeCpu(voltorb.getType());
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
                        setPlayerWins(PlayerLoses(user));
                    }
                }
            }
            while (isCpuTurn()) {
                if (voltorb.isPlayable()) {
                    VoltorbAttack(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
                    break;
                } else if (electabuzz.isPlayable()) {
                    ElectabuzzAttack(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
                    break;
                } else if (pikachu.isPlayable()) {
                    PikachuAttack(leaderSurge, user, voltorb, electabuzz, pikachu, userPokemon);
                    break;
                } else {
                    setBattleOn(false);
                    setCpuTurn(false);
                    setPlayerTurn(false);
                    setPlayerWins(PlayerWins(user));
                    break;
                }
            }
        }
        return isPlayerWins();
    }

    public boolean PlayerWins(Player user){
        System.out.println("You defeated Lt Surge.");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Lt Surge: Now that's a shocker!. You're the real deal kid!\n" +
                "Fine, then, take the THUNDERBADGE!\nPlayer got 2000 for winning.");
        user.setMoney(2000);
        return true;
    }

    public boolean PlayerLoses(Player user){
        System.out.println("You were defeated by Lt Surge!");
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Lt Surge: Just like I thought, you were no match for me! No ones a match for the electric " +
                "type");
        battlemenu.pressAnyKeyToContinue();
        return false; }
}
