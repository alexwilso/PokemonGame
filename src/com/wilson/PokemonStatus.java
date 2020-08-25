package com.wilson;
import java.util.Random;
public class PokemonStatus {
    Random random = new Random();

    public int BurnChance(){
        // Fire type pokemon have a 20% chance of burning enemy. Returns int to be used by attack to determine if opposing
        // pokemon is burned.
        int min = 1;
        int max = 5;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public int PoisonChance(){
        // Poison type pokemon have a 20% chance of poisoning enemy. Returns int to be used by attack to determine if opposing
        // pokemon is poisoned.
        int min = 1;
        int max = 5;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public int ConfusionChance(){
        // Pokemon have a 20% chance of confusing enemy with certain moves. Returns int to be used by attack to
        // determine if opposing pokemon is confused.
        int min = 1;
        int max = 5;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public int ParalyzeChance() {
        // Electric type pokemon have a 20% chance of paralyzing enemy with certain moves. Returns int to be used by attack to
        // determine if opposing pokemon is paralyzed.
        int min = 1;
        int max = 5;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public int SleepChance(){
        // Pokemon have a 20% chance of putting enemy to sleep with certain moves. Returns int to be used by attack to
        // determine if opposing pokemon is sleep.
        int min = 1;
        int max = 5;
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
