package com.wilson;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Battlemenu {
    Scanner scanner = new Scanner(System.in);

    public int Menu(String pokemon){
        // Displays list of actions user can take. Returns integer of move user wishes to use.
        System.out.println("What will " + pokemon + " do?");
        System.out.println("1. Fight\n2. pokémon\n3. Bag");
        return Integer.parseInt(scanner.nextLine());
    }

    public Map<Integer, String> ChangePokemon(Player user, Object[] userPokemon, int activePokemon){
        // Allows user to change pokemon. Prints out list of playable pokemon. Takes user number and recursively calls
        // usermove function with pokemon user chooses to change to.
        Map<Integer, String> changeMap = new HashMap<>();
        for(int x = 0; x < 3; x++){
            System.out.println(Integer.toString(x+1) + " " + userPokemon[x]);
        }
        boolean playable = false;
        int pokemon;
        do {
            System.out.println("Enter number of pokémon you wish to play");
            pokemon = Integer.parseInt(scanner.nextLine());
            if (pokemon >= 0 && pokemon <= 3){
                if (user.searchFainted(pokemon-1)){
                    System.out.println("That pokemon fainted and is no longer playable. You must revive them to use them");
                    playable = false;} else if ((pokemon - 1) == activePokemon){
                    System.out.println("That Pokemon is already in play");
                    playable = false;
                } else {
                    playable = true;}
            } else {
                System.out.println("Not a valid option");
            }
        } while (! playable);
        System.out.println("Go " + userPokemon[pokemon-1]);
        changeMap.put(pokemon, "changed");
        return changeMap;
    }

    public String UseItem(Player user){
        // Displays users bag to user along. Returns string of item user wishes to use.
        System.out.println("Enter item would you like to use?\n" + user.getBag());
        return scanner.nextLine();
    }

    public void pressAnyKeyToContinue() {
    System.out.println("Press Enter key to continue...");
    try
    {
        System.in.read();
    }
    catch(Exception ignored)
    {}
    }
}

