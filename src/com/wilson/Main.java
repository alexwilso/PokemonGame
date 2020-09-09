package com.wilson;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ChoosePokemon choosePokemon = new ChoosePokemon();
        Battlemenu battlemenu = new Battlemenu();
        PlayerMove playerMove = new PlayerMove();
        ReturnMove returnMove = new ReturnMove();
        BinaryTree binaryTree = new BinaryTree();
        ErikaAI erikaAI = new ErikaAI(binaryTree);
        SurgeAI surgeAI = new SurgeAI(binaryTree);
        BlaineAI blaineAI = new BlaineAI(binaryTree);
        GiovanniAI giovanniAI = new GiovanniAI(binaryTree);
        PokemonStatus pokemonStatus = new PokemonStatus();
        ViridianCityGym viridianCityGym = new ViridianCityGym(returnMove, playerMove, pokemonStatus, battlemenu, giovanniAI);
        CeladonCityGym celadonCityGym = new CeladonCityGym(returnMove, playerMove, pokemonStatus, battlemenu, erikaAI);
        VermilionCityGym vermilionCityGym = new VermilionCityGym(returnMove, playerMove, pokemonStatus, battlemenu, surgeAI);
        CinnabarIsland cinnabarIsland = new CinnabarIsland(returnMove, playerMove, pokemonStatus, battlemenu, blaineAI);
        Bulbasaur bulbasaur = new Bulbasaur("Bulbasuar", "Grass",50, 115,115,
                "Normal", new VineWhip(35, 5, 5), new SludgeBomb(20, 15,15),
                new RazorLeaf(30, 5,5), new LeechSeed(20,20,20,20), battlemenu);

        Charmander charmander = new Charmander("Charmander", "Fire", 50, 120, 120, "Normal",
                new Scratch(15, 25, 25), new Ember(20, 10,10, pokemonStatus),
                new Flamethrower(30, 5, 5, pokemonStatus), new Tailwhip(15,25,25), battlemenu);

        Gengar gengar = new Gengar("Gengar", "Ghost", 50, 120, 120, "Normal",
                new PoisonJab(30, 5,5, pokemonStatus), new ConfusionRay(20,20,20, pokemonStatus),
                new Lick(10,20,20), new ShadowBall(30, 5,5), battlemenu);

        Onix onix = new Onix("Onix", "Rock", 50, 140, 140, "Normal",
                new Rage(15,20,20), new RockThrow(25,10,10),
                new RockSmash(25,5,5), new Bind(15,20,20), battlemenu);

        Pidgey pidgey = new Pidgey("Pidgey", "Flying", 50, 100,100, "Normal",
                new Fly(30, 5, 5), new Gust(20,10,10),
                new WingAttack(15,20,20), new Peck(10,25,25), battlemenu);

        Pikachu pikachu = new Pikachu("Pikacu", "Electric", 50, 120, 120, "Normal",
                new QuickAttack(10,25,25), new ThunderShock(20,15,15, pokemonStatus),
                new Thunder(30,5,5, pokemonStatus), new Growl(15,20,20), battlemenu);

        Squirtle squirtle = new Squirtle("Squirtle", "Water", 50, 120, 120, "Normal",
                new HydroPump(30, 5,5), new Tackle(10,25,25),
                new Surf(25,10,10), new ShellAttack(15,20,20), battlemenu);



        Scanner scanner = new Scanner(System.in);
        String rivalName;
        String name;
        Player player = new Player(2500);
        Store store = new Store(player);
//        Object[] playersPokemon = new Object[3];
//        Object[] rivalPokemon = new Object[3];

        // Introduces player to game
        System.out.println("Professor Oak: Hello there! Welcome to the world of pokémon! " + "\n" +
                "My name is Oak! People call me the pokémon Prof! This world is inhabited by creatures called pokémon! " + "\n" +
                "For some people, pokémon are pets. Others use them for fights. Myself...I study pokémon as a profession."  + "\n" +
                "Lets begin with your name. What do you wish to be called?");
        System.out.println("Your Name?");
        name = scanner.nextLine();
        System.out.println("Professor Oak: Ah yes! Very nice to meet you " + name + "\n" +
                "This is my grandson. He has been your rival since you were babies." + "\n" +
                "... Erm, what was his name now?");
        do {
            System.out.println("Rivals name?");
            rivalName = scanner.nextLine();
            System.out.println("...Er, was it " + rivalName +"?");
            System.out.println("Yes or No?");
        } while (!scanner.nextLine().toLowerCase().equals("yes"));
        Rival rival = new Rival(rivalName);

        System.out.println("Professor Oak: That's right! I remember now! Their name is " + rivalName + "! " + "\n" +
                "Your very own pokémon legend is about to unfold! A world of dreams and adventures awaits!\nThe purpose " +
                " of your journey is to battle your way through 4 gym leaders.\nDefeat their pokémon to move on." +
                " Lose all your pokemon and it's game over. Lets go!");

        Object[] pokemon = choosePokemon.UserSelect(bulbasaur, charmander, gengar, onix, pidgey, pikachu, squirtle, player, rival);
        System.out.println("Professor Oak: So you selected " + pokemon[0] + ", " + pokemon[1] + ", " + pokemon[2] + "! " + "All great pokémon!"); // Fix this, returning objects 
        System.out.println(rivalName + ": I'll take " + pokemon[3] + ", " + pokemon[4] + ", " + pokemon[5] + " then! My pokémon are a lot tougher than yours!");
        System.out.println("Professor Oak: " + rivalName + " also very good pokémon! I'm sure you guys will get to battle soon enough... Now let me explain how this world works.\n" +
                "The goal is to defeat all the gym leaders. Defeating a gym leader will reward you money. Before each match you are able to buy " +
                "items from the store to help your pokemon in battle! \nLets check it out before we send you on your way...\n" );
        Main.visitStore(store, player, scanner);
        System.out.println("Professor Oak: Okay good! You're all set! Remember you're able to visit the store after each battle \n" +
        "First stop is in Celadon City where you'll battle Erika, master of grass type pokémon. Goodluck in there " + name + "!");
        battlemenu.pressAnyKeyToContinue();

//        if (!celadonCityGym.Welcome(player, pokemon)){
//            System.out.println("Professor Oak: Looks like you were defeated. Maybe next time you should try different pokemon. Thanks " +
//                    "for stopping by " + name + ", be sure to come by when you're ready to try again!");
//            System.exit(0);}
//
//        System.out.println("Professor Oak: Wonderful job " + name + "One down, three to go. Lets get you to the " +
//                "PokéStore before we send you on your way...");
//        Main.visitStore(store, player, scanner);
//        System.out.println("Professor Oak: Next stop is Vermilion City where you'll take on Lt. Surge, trainer of all " +
//                "things electric. I will say he packs quite the power! Good luck in there " + name +"!");
//        battlemenu.pressAnyKeyToContinue();

//        if (!vermilionCityGym.Welcome(player, pokemon)){
//                System.out.println("Professor Oak: Looks like you were defeated. Maybe next time you should try different pokemon. Thanks " +
//                        "for stopping by " + name + ", be sure to come by when you're ready to try again!");
//                System.exit(0);}
//
//        System.out.println("Professor Oak: Wonderful job " + name + " Two down, Two to go! Lets get you to the " +
//                "PokéStore before we send you on your way...");
//        Main.visitStore(store, player, scanner);
//        System.out.println("Professor Oak: Next stop is Cinnabar Island where you'll take on Leader Blain!\nI have a " +
//                "feeling things are really going to start to heat up now! Goodluck " + name);
//        battlemenu.pressAnyKeyToContinue();

//         if (!cinnabarIsland.Welcome(player, pokemon)){
//                System.out.println("Professor Oak: Looks like you were defeated. Maybe next time you should try different pokemon. Thanks " +
//                        "for stopping by " + name + ", be sure to come by when you're ready to try again!");
//                System.exit(0);}
//        System.out.println("Professor Oak: Wonderful job " + name + " Three down, One to go! Lets get you to the " +
//                "PokéStore before we send you on your way...");
//        Main.visitStore(store, player, scanner);
        System.out.println("Professor Oak: Next stop is Viridian City to take on the all mighty Giovanni.\nRumor has it he's " +
                "never lost! This is it " + name + ", defeat Giovanni and you'll be the best of the best! Goodluck!");
        battlemenu.pressAnyKeyToContinue();
        if (!viridianCityGym.Welcome(player, pokemon)){
            System.out.println("Professor Oak: Looks like you were defeated. Maybe next time you should try different pokemon. Thanks " +
                    "for stopping by " + name + ", be sure to come by when you're ready to try again!");
            System.exit(0);}
        System.out.println("Professor Oak: Wow very impressive! " + name + " Looks like you're the best of the best...");
        battlemenu.pressAnyKeyToContinue();
        System.out.println(rivalName + ": Hey, I heard that! Gramps, whats with favoring " + name + " over me all the time?\n" +
                "My pokémon are faster, stronger, and way better than him and I'll show you!");
    }


    public static void visitStore(Store store, Player player, Scanner scanner) {
        /*
        / Displays store for user. User selects option based on store menu.
         */

        System.out.println("\nWelcome to the PokéStore!\nItems in stock today...");
        String display = ("Store Menu:\n" + "1. Buy Items\n" + "2. Sell Items\n" + "3. Check your items\n" + "4. Check your money\n"
                + "5. Learn more about an item\n6. Leave Store");
        boolean quit = false;
        do {
            System.out.println(store.getStore() + "\n" + display);
            System.out.println("Enter item of option you wish to do");
            int option = Integer.parseInt(scanner.nextLine());
           switch (option){
               // Buy item from the store
               case 1:
                    System.out.println("Enter the name of the item you wish to purchase...");
                    String toBuy = scanner.nextLine();
                    System.out.println("How many " + toBuy + " would you like to purchase?");
                    int buyQuantity = Integer.parseInt(scanner.nextLine());
                    store.buyItems(toBuy, buyQuantity);
                    break;
                // Sell item to the store
               case 2:
                   System.out.println("Enter the name of the item you wish to sell...");
                   String toSell = scanner.nextLine();
                   System.out.println("How many " + toSell + " would you like to sell?");
                   int sellQuantity = Integer.parseInt(scanner.nextLine());
                   store.sellItems(toSell, sellQuantity);
                   break;
               // Displays items owned by user
               case 3:
                   System.out.println(player.getBag());
                   break;
               // Displays amount of money user has
               case 4:
                   System.out.println("Money: " + player.getMoney());
                   break;
               // Gives item descriptions to user
               case 5:
                   System.out.println("Enter the name of the item you wish to hear more about...");
                   String toLearn = scanner.nextLine();
                   System.out.println(store.learnMore(toLearn));
                   break;
               // Quits store
               case 6:
                   quit = true;
                   break;
            }
        } while (!quit);
    }
}
