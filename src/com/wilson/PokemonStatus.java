package com.wilson;
import java.util.Random;
public class PokemonStatus {

    public int RandomNum(int min, int max){
        // generates random number
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public int BurnChance(){
        // Fire type pokemon have a 20% chance of burning enemy. Returns int to be used by attack to determine if opposing
        // pokemon is burned.
        return RandomNum(1, 4);
    }

    public int PoisonChance(){
        // Poison type pokemon have a 20% chance of poisoning enemy. Returns int to be used by attack to determine if opposing
        // pokemon is poisoned.
        return RandomNum(1, 4);
    }

    public int ConfusionChance(){
        // Pokemon have a 20% chance of confusing enemy with certain moves. Returns int to be used by attack to
        // determine if opposing pokemon is confused.
        return RandomNum(1, 4);
    }

    public int ParalyzeChance() {
        // Electric type pokemon have a 20% chance of paralyzing enemy with certain moves. Returns int to be used by attack to
        // determine if opposing pokemon is paralyzed.
        return RandomNum(1, 4);
    }

    public int SleepChance() {
        // Pokemon have a 25% chance of putting enemy to sleep with certain moves. Returns int to be used by attack to
        // determine if opposing pokemon is sleep.
        return RandomNum(1, 4);
    }

}

