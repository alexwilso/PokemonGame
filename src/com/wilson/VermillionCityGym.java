package com.wilson;

import java.util.Scanner;

public class VermillionCityGym extends Gym{
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

    public VermillionCityGym(ReturnMove returnMove, PlayerMove playerMove, PokemonStatus pokemonStatus, Battlemenu battlemenu) {
        super(returnMove, playerMove, pokemonStatus, battlemenu);
    }

    public void Welcome(Player user, Object[] pokemon){
        System.out.println("Lt Surge: Hey kid, what do you think you're doing here? You won't live long in combat!\nNot" +
                " with your puny power! I tell you, kid, electric pokémon saved me during the war! They zapped my enemies" +
                " into paralysis! The same as I'll do yo you!");
        battlemenu.pressAnyKeyToContinue();

    }

}
