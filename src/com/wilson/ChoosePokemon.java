package com.wilson;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ChoosePokemon {

    public void DisplayPokemon (Bulbasaur bulbasaur, Charmander charmander, Gengar gengar, Onix onix, Pidgey pidgey, Pikachu pikachu, Squirtle squirtle){
        // Displays pokemon for user. Displays pokemon name, type, and attacks with damae and PP
        Object[] pokemon = {bulbasaur.getName(), charmander.getName(), gengar.getName(), onix.getName(), pidgey.getName(),
                pikachu.getName(), squirtle.getName()};

        String[][] pokemonInfo = {bulbasaur.DisplayBulbasaur(bulbasaur), charmander.DisplayCharmander(charmander),
                gengar.DisplayGengar(gengar), onix.DisplayOnix(onix), pidgey.DisplayPidgey(pidgey), pikachu.DisplayPikachu(pikachu),
                squirtle.DisplaySquirtle(squirtle)};

        System.out.println("Professor Oak: First things first we need to get you some pokémon. Lets see what we've got" +
                " here. Ah yes, the pokémon I have for you to choose are... \n");

        for (int i = 0; i < 7; i++){
            System.out.println((i + 1) +". " + pokemon[i] + Arrays.toString(pokemonInfo[i]) + "\n");
        }
    }

    public void PickPokemon (Bulbasaur bulbasaur, Charmander charmander, Gengar gengar, Onix onix, Pidgey pidgey,
                             Pikachu pikachu, Squirtle squirtle, int select, int x, Object[] choice, int[] chosen, Player player, Rival rival) {
        if (select == 1) {
            choice[x - 1] = bulbasaur.getName();
            chosen[x - 1] = select;
            if(x <= 3){
                player.setPokemon(bulbasaur, (x - 1));
            } else {
                rival.setPokemon(bulbasaur, (-4 + x));
            }

        } else if (select == 2) {
            choice[x - 1] = charmander.getName();
            chosen[x - 1] = select;
            if(x <= 3){
                player.setPokemon(charmander, (x-1));
            } else {
                rival.setPokemon(charmander, (-4 + x));
            }

        } else if (select == 3) {
            choice[x - 1] = gengar.getName();
            chosen[x - 1] = select;
            if(x <= 3){
                player.setPokemon(gengar, (x - 1));
            } else {
                rival.setPokemon(gengar, (-4 + x));
            }

        } else if (select == 4) {
            choice[x - 1] = onix.getName();
            chosen[x - 1] = select;
            if(x <= 3){
                player.setPokemon(onix, (x - 1));
            } else {
                rival.setPokemon(onix, (-4 + x));
            }

        } else if (select == 5) {
            choice[x - 1] = pidgey.getName();
            chosen[x - 1] = select;
            if(x <= 3){
                player.setPokemon(pidgey, (x - 1));
            } else {
                rival.setPokemon(pidgey, (-4 + x));
            }

        } else if (select == 6) {
            choice[x - 1] = pikachu.getName();
            chosen[x - 1] = select;
            if(x <= 3){
                player.setPokemon(pikachu, (x - 1));
            } else {
                rival.setPokemon(pikachu, (-4 + x));
            }

        } else if (select == 7) {
            choice[x - 1] = squirtle.getName();
            chosen[x - 1] = select;
            if(x <= 3){
                player.setPokemon(squirtle, (x - 1));
            } else {
                rival.setPokemon(squirtle, (-4 + x));
            }

        } else {
            System.out.println("Professor Oak: I didn't quite get that, what is your selection going to be?");
        }

    }

    public Object[] UserSelect (Bulbasaur bulbasaur, Charmander charmander, Gengar gengar, Onix onix, Pidgey pidgey,
                                Pikachu pikachu, Squirtle squirtle, Player player, Rival rival) {
        // Allows user to select 3 pokemon. Returns a list containing names of 3 pokemon user selected.

        Scanner scanner = new Scanner(System.in);
        Object[] playerChoice = new Object[6];
        DisplayPokemon(bulbasaur, charmander, gengar, onix, pidgey, pikachu, squirtle);

        System.out.println("Enter the number of the pokemon you would like to choose. You may choose 3, enter one at a time...");
        int x = 3;
        int[] chosen = new int[6];
        while (x > 0){
            System.out.println("Professor Oak: You can choose " + x + " more pokémon. Which will it be?");
            int pokemonChoice = Integer.parseInt(scanner.nextLine());
            boolean toChoose = true;
            // If user has already selected pokemon, toChoose is set to false to prevent user from selecting same pokemon twice
            for (int element : chosen){
                if (element == pokemonChoice) {
                    toChoose = false;
                    break;
                }
            }
            if (toChoose) {
                if (1 <= pokemonChoice && pokemonChoice < 8){
                    switch (pokemonChoice){
                        case 1:
                            System.out.println("Professor Oak: I see! Bulbasaur is your Choice!");
                            break;
                        case 2:
                            System.out.println("Professor Oak: I see! Charmander is your Choice!");
                            break;
                        case 3:
                            System.out.println("Professor Oak: I see! Gengar is your Choice!");
                            break;
                        case 4:
                            System.out.println("Professor Oak: I see! Onix is your Choice!");
                            break;
                        case 5:
                            System.out.println("Professor Oak: I see! Pidgey is your Choice!");
                            break;
                        case 6:
                            System.out.println("Professor Oak: I see! Pikachu is your Choice!");
                            break;
                        case 7:
                            System.out.println("Professor Oak: I see! Squirtle is your Choice!");
                            break;

                    }
                    PickPokemon(bulbasaur, charmander, gengar, onix, pidgey, pikachu, squirtle, pokemonChoice, x, playerChoice, chosen, player, rival);
                    x--;
                } else {
                    System.out.println("Professor Oak: I didn't quite get that, what is your selection going to be?");
                }
            } else {
                System.out.println("Professor Oak: It looks like you've already chosen that pokemon! Chose another...");
            }
        }
        RivalSelect(bulbasaur, charmander, gengar, onix, pidgey, pikachu, squirtle, playerChoice, chosen, player, rival);
        return playerChoice;
    }

    public void RivalSelect(Bulbasaur bulbasaur, Charmander charmander, Gengar gengar, Onix onix, Pidgey pidgey,
                            Pikachu pikachu, Squirtle squirtle, Object[] choice, int[] chosen, Player player, Rival rival){

        Random random = new Random();
        int rand;
        int y = 6;
        while (y > 3){
            boolean toChoose = true;
            rand = random.nextInt(8);
            for (int element : chosen) {
                if (element == rand) {
                    toChoose = false;
                    break;
                }
            }
            if (toChoose){
                PickPokemon(bulbasaur, charmander, gengar, onix, pidgey, pikachu, squirtle, rand, y, choice, chosen, player, rival);
                y--;
            }
        }
    }
}
