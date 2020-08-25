package com.wilson;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Hashtable;

public class CeladonCityGym {
    ErikaAI erikaAI = new ErikaAI();

    public void Welcome(Player user, Object[] pokemon){
        Vileplume vileplume = new Vileplume("Vileplume", "Grass", 50, 120,120,"Normal",
                new Absorb(25,  10,10, 25), new StunSpore(15, 20,20),
                new HyperBeam(30,5,5), new Sleep(10, 10,10));

        Victreebel victreebel = new Victreebel("Victreebell", "Grass", 50, 120,120, "Normal",
                new RazorLeaf(25, 10,10), new VineWhip(30,5,5),
                new Sleep(10,10,10), new SpitUp(15,20,20));

        Tangela tangela = new Tangela("Tangela", "Grass", 50,120,120, "Normal",
                new PoisionPowder(15,15,15), new Megadrain(20, 20,20,20),
                new Slam(15,15,15), new Constrict(25,10,10));
        LeaderErika leaderErika = new LeaderErika(victreebel, vileplume, tangela);
        System.out.println("Leader Erica: Hello... Lovely weather, isn't it? It's so pleasant.\n...Oh, dear...\nI must" +
                " have dozed off... Welcome! My name is Erika. I am the leader of Celadon Gym.\nI am a student of the art" +
                "of flower arranging. My pokémon are solely of the grass type.\n... Oh, I'm sorry, I had no idea that you" +
                " wished to challenge me. Very well, but I shall not lose.");
//        setPokemon(user.getPokemon()[0], user.getPokemon()[1], user.getPokemon()[2], pokemon);


        Battle(leaderErika, vileplume, victreebel, tangela);
    }

//    public void setPokemon(Object pokemon1, Object pokemon2, Object pokemon3, Object[] pokemon){
//        for (int x = 0; x< 3; x++){
//           switch (pokemon[x]){
//               case "Bulbasaur":
//
//           }
//        };
//    }



    public void Battle(LeaderErika leaderErika, Vileplume vileplume, Victreebel victreebel, Tangela tangela){
        System.out.println(victreebel.getHealth());
        System.out.println(leaderErika.getBag());
        System.out.println(erikaAI.CreateTreeVictreebel(leaderErika, victreebel, 10, "Ground", "Normal"));
        System.out.println(erikaAI.getOpponentStatus());
        System.out.println(leaderErika.getBag());
        System.out.println(victreebel.getHealth());

    }
}



