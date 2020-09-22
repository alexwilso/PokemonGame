package com.wilson;

import java.util.Map;

public class RivalBattle extends Gym{
    RivalAI rivalAI;
    private Integer num;

    public RivalBattle(ReturnMove returnMove, PlayerMove playerMove, PokemonStatus pokemonStatus, Battlemenu battlemenu, RivalAI rivalAI) {
        super(returnMove, playerMove, pokemonStatus, battlemenu);
        this.rivalAI = rivalAI;
        this.num = 3;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String SetRivalPokemon(Object[] userpokemon, int pokemonNum){
        return userpokemon[pokemonNum].toString();}

    public void FaintedPokemonAttack(){
    setCpuAttackStatus("Normal");
    setCpuAttackDamage(0);
    setCpuDamage(true);
    setCpuTurn(false);
    setPlayerTurn(true);}

    public void PokemonNotPlayable(Rival rival, Player user, Object[] userPokemon){
        // Calls battle function when Pokemon is not playable(Health=0). Sets next pokemon to cpus pokemon.
        setCpuDamage(false);
        setCpuAttackStatus("Normal");
        setCpuAttackDamage(0);
        setNum(getNum() + 1);
        Battle(rival, user, userPokemon); }

    public void BulbasuarAttack(Rival rival, Player user, Bulbasaur bulbasaur,
                                Object[] userPokemon){
        /* Updates Bulbasuar's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in rival's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            bulbasaur.loseHealth(getPlayerAttackDamage());
            if (bulbasaur.getStatus().equals("Normal")) {
                bulbasaur.setStatus(getPlayerAttackStatus()); }}

        if (!bulbasaur.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        System.out.println(rival.getName() + " pokemon: Bulbasaur. Health is " + bulbasaur.getHealth() + ". Status is " +
                bulbasaur.getStatus() + ".");

        // Updates move and health based on current status
        if (!bulbasaur.getStatus().equals("Normal")){
            if (bulbasaur.BulbasaurStatus(bulbasaur)) {
                FaintedPokemonAttack();
                return;} }

        if (!bulbasaur.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = rivalAI.CreateTreeBulbasaur(rival, bulbasaur, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(rivalAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println(rival.getName() + "'s " + bulbasaur.getName() + " used " + bulbasaur.getAttackName() + ".");
        AttackStrength(rival.getStrength());
        if (bulbasaur.getAttackName().equals("Max Potion")){
            System.out.println("Bulbasuar health: " + bulbasaur.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }


    public void SquirtleAttack(Rival rival, Player user, Squirtle squirtle,
                                Object[] userPokemon){
        /* Updates Squirtle's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in rival's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            squirtle.loseHealth(getPlayerAttackDamage());
            if (squirtle.getStatus().equals("Normal")) {
                squirtle.setStatus(getPlayerAttackStatus()); }}

        if (!squirtle.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        System.out.println(rival.getName() + " pokemon: Squirtle. Health is " + squirtle.getHealth() + ". Status is " +
                squirtle.getStatus() + ".");

        // Updates move and health based on current status
        if (!squirtle.getStatus().equals("Normal")){
            if (squirtle.SquirtleStatus(squirtle)) {
                FaintedPokemonAttack();
                return;} }

        if (!squirtle.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = rivalAI.CreateTreeSquirtle(rival, squirtle, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(rivalAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println(rival.getName() + "'s " + squirtle.getName() + " used " + squirtle.getAttackName() + ".");
        AttackStrength(rival.getStrength());
        if (squirtle.getAttackName().equals("Max Potion")){
            System.out.println("Squirtle's health: " + squirtle.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void CharmanderAttack(Rival rival, Player user, Charmander charmander,
                               Object[] userPokemon){
        /* Updates Charmander's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in rival's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            charmander.loseHealth(getPlayerAttackDamage());
            if (charmander.getStatus().equals("Normal")) {
                charmander.setStatus(getPlayerAttackStatus()); }}

        if (!charmander.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        System.out.println(rival.getName() + " pokemon: Charmander. Health is " + charmander.getHealth() + ". Status is " +
                charmander.getStatus() + ".");

        // Updates move and health based on current status
        if (!charmander.getStatus().equals("Normal")){
            if (charmander.CharmanderStatus(charmander)) {
                FaintedPokemonAttack();
                return;} }

        if (!charmander.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = rivalAI.CreateTreeCharmander(rival, charmander, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(rivalAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));

        System.out.println(rival.getName() + "'s " + charmander.getName() + " used " + charmander.getAttackName() + ".");
        AttackStrength(rival.getStrength());
        if (charmander.getAttackName().equals("Max Potion")){
            System.out.println("Charmander's health: " + charmander.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void GengarAttack(Rival rival, Player user, Gengar gengar,
                                 Object[] userPokemon){
        /* Updates Gengar's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in rival's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            gengar.loseHealth(getPlayerAttackDamage());
            if (gengar.getStatus().equals("Normal")) {
                gengar.setStatus(getPlayerAttackStatus()); }}

        if (!gengar.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        System.out.println(rival.getName() + " pokemon: Gengar. Health is " + gengar.getHealth() + ". Status is " +
                gengar.getStatus() + ".");

        // Updates move and health based on current status
        if (!gengar.getStatus().equals("Normal")){
            if (gengar.GengarStatus(gengar)) {
                FaintedPokemonAttack();
                return;} }

        if (!gengar.isPlayable()){
            FaintedPokemonAttack();
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = rivalAI.CreateTreeGengar(rival, gengar, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(rivalAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println(rival.getName() + "'s " + gengar.getName() + " used " + gengar.getAttackName() + ".");
        AttackStrength(rival.getStrength());
        if (gengar.getAttackName().equals("Max Potion")){
            System.out.println("Gengar's health: " + gengar.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void PikachuAttack(Rival rival, Player user, Pikachu pikachu,
                             Object[] userPokemon){
        /* Updates Pikachu's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in rival's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            pikachu.loseHealth(getPlayerAttackDamage());
            if (pikachu.getStatus().equals("Normal")) {
                pikachu.setStatus(getPlayerAttackStatus()); }}

        if (!pikachu.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        System.out.println(rival.getName() + " pokemon: Pikachu. Health is " + pikachu.getHealth() + ". Status is " +
                pikachu.getStatus() + ".");

        // Updates move and health based on current status
        if (!pikachu.getStatus().equals("Normal")){
            if (pikachu.PikachuStatus(pikachu)) {
                FaintedPokemonAttack();
                return;} }

        if (!pikachu.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = rivalAI.CreateTreePikachu(rival, pikachu, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(rivalAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println(rival.getName() + "'s " + pikachu.getName() + " used " + pikachu.getAttackName() + ".");
        AttackStrength(rival.getStrength());
        if (pikachu.getAttackName().equals("Max Potion")){
            System.out.println("Pikachu's health: " + pikachu.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void OnixAttack(Rival rival, Player user, Onix onix,
                             Object[] userPokemon){
        /* Updates Onix's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in rival's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            onix.loseHealth(getPlayerAttackDamage());
            if (onix.getStatus().equals("Normal")) {
                onix.setStatus(getPlayerAttackStatus()); }}

        if (!onix.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        System.out.println(rival.getName() + " pokemon: Onix. Health is " + onix.getHealth() + ". Status is " +
                onix.getStatus() + ".");

        // Updates move and health based on current status
        if (!onix.getStatus().equals("Normal")){
            if (onix.OnixStatus(onix)) {
                FaintedPokemonAttack();
                return;} }

        if (!onix.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = rivalAI.CreateTreeOnix(rival, onix, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(rivalAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println(rival.getName() + "'s " + onix.getName() + " used " + onix.getAttackName() + ".");
        AttackStrength(rival.getStrength());
        if (onix.getAttackName().equals("Max Potion")){
            System.out.println("Onix's health: " + onix.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }

    public void PidgeyAttack(Rival rival, Player user, Pidgey pidgey,
                           Object[] userPokemon){
        /* Updates Pidgey's health and status based on player move.
         Sets damage and status of opposing pokemon based on create tree function in rival's ai class. */

        // Updates status and damage done based on user move
        if (isCpuDamage()) {
            pidgey.loseHealth(getPlayerAttackDamage());
            if (pidgey.getStatus().equals("Normal")) {
                pidgey.setStatus(getPlayerAttackStatus()); }}

        if (!pidgey.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        System.out.println(rival.getName() + " pokemon: Pidgey. Health is " + pidgey.getHealth() + ". Status is " +
                pidgey.getStatus() + ".");

        // Updates move and health based on current status
        if (!pidgey.getStatus().equals("Normal")){
            if (pidgey.PidgeyStatus(pidgey)) {
                FaintedPokemonAttack();
                return;} }

        if (!pidgey.isPlayable()){
            PokemonNotPlayable(rival, user, userPokemon);
            return; }

        // Sets attack based on return of AI class. Updates players status and health based on result.
        Map<Integer, String> cpuMove = rivalAI.CreateTreePidgey(rival, pidgey, getPokemonHealthPlayer(),
                getPokemonTypePLayer(), getPokemonStatusPlayer());
        if (getPokemonStatusPlayer().equals("Normal")) {
            setCpuAttackStatus(rivalAI.getOpponentStatus()); }
        setCpuAttackDamage(returnMove.MoveDamage(cpuMove));
//               returnMove.MoveString(cpuMove, returnMove.MoveDamage(cpuMove))
        System.out.println(rival.getName() + "'s " + pidgey.getName() + " used " + pidgey.getAttackName() + ".");
        AttackStrength(rival.getStrength());
        if (pidgey.getAttackName().equals("Max Potion")){
            System.out.println("Pidgey's health: " + pidgey.getHealth()); }
        else {
            System.out.println("Dealt " +  getCpuAttackDamage() + " damage to opponent."); }
        setCpuDamage(true);
        setCpuTurn(false);
        setPlayerTurn(true); }


    public void RivalAttack(String pokemon, Rival rival, Player user, Object[] userPokemon){
        switch (pokemon) {
            case("Charmander"):
                Charmander rivalCharmander = (Charmander) rival.getPokemon()[getNum()-3];
                setPokemonTypeCpu(rivalCharmander.getType());
                CharmanderAttack(rival, user, rivalCharmander, userPokemon);
                break;
            case("Bulbasuar"):
                Bulbasaur rivalBulbasur = (Bulbasaur) rival.getPokemon()[getNum() -3];
                setPokemonTypeCpu(rivalBulbasur.getType());
                BulbasuarAttack(rival, user, rivalBulbasur, userPokemon );
                break;
            case("Gengar"):
                Gengar rivalGengar = (Gengar) rival.getPokemon()[getNum()-3];
                setPokemonTypeCpu(rivalGengar.getType());
                GengarAttack(rival, user, rivalGengar, userPokemon);
                break;
            case("Onix"):
                Onix rivalOnix = (Onix) rival.getPokemon()[getNum()-3];
                setPokemonTypeCpu(rivalOnix.getType());
                OnixAttack(rival, user, rivalOnix, userPokemon);
                break;
            case("Pidgey"):
                Pidgey rivalPidgey = (Pidgey) rival.getPokemon()[getNum()-3];
                setPokemonTypeCpu(rivalPidgey.getType());
                PidgeyAttack(rival, user, rivalPidgey, userPokemon);
                break;
            case("Pikachu"):
                Pikachu rivalPikachu = (Pikachu) rival.getPokemon()[getNum()-3];
                setPokemonTypeCpu(rivalPikachu.getType());
                PikachuAttack(rival, user, rivalPikachu, userPokemon);
                break;
            case("Squirtle"):
                Squirtle rivalSquirtle = (Squirtle) rival.getPokemon()[getNum()-3];
                setPokemonTypeCpu(rivalSquirtle.getType());
                SquirtleAttack(rival, user, rivalSquirtle, userPokemon);
                break;
        }
    }

    public boolean Battle(Rival rival, Player user, Object[] userPokemon){
        while (isBattleOn()) {
            // Sets player attack and health and status of user pokemon based on cpu move. Checks if player loses.
            while (isPlayerTurn()) {
                if (isFirstTurn()) {
                    System.out.println("Player sent out " + userPokemon[0]);
                    setFirstTurn(false);
                    setPokemonTypeCpu("Normal");
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
                        setPlayerWins(PlayerLoses(rival)); } } }
            // Sets Cpu attack and health and status of cpu pokemon based on player move. Checks if cpu  loses.
            while (isCpuTurn()) {
                if (getNum() == 3) {
                    RivalAttack(SetRivalPokemon(userPokemon, getNum()), rival, user, userPokemon);
                    break; }
                else if (getNum() == 4) {
                    RivalAttack(SetRivalPokemon(userPokemon, getNum()), rival, user, userPokemon);
                    break; }
                else if (getNum() == 5) {
                    RivalAttack(SetRivalPokemon(userPokemon, getNum()), rival, user, userPokemon);
                    break; }
                else {
                    setBattleOn(false);
                    setCpuTurn(false);
                    setPlayerTurn(false);
                    setPlayerWins(PlayerWins(user, rival));
                    break; } } }
        return isPlayerWins(); }

    public boolean PlayerWins(Player user, Rival rival){
        // Called if player wins. Adds 2000 to player money.
        System.out.println("You defeated " + rival.getName());
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Rival: What?! How could this be? You beat me?? I don't believe it, I am the best trainer\n" +
                "there is! I'm going to go train my pokemon to be the strongest, just you wait!");
        System.out.println("Professor Oak: Congratulations! You are truly a pokemon master! You Win!");
        user.setMoney(2000);
        return true; }

    public boolean PlayerLoses(Rival rival){
        // Called when player loses.
        System.out.println("You were defeated by " + rival.getName());
        battlemenu.pressAnyKeyToContinue();
        System.out.println("Rival: I knew there was no way you could beat me! I am so much better then you'll ever be! You lose!");
        battlemenu.pressAnyKeyToContinue();
        return false;
    }
}

