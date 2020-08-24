package com.wilson;

import java.util.Hashtable;

public class Rival {
    private Hashtable<String, Integer> bag;
    private final Object[] pokeballs = new Object[3];
    private String name;

    public Rival(String name) {
        this.bag = new Hashtable<String, Integer>();
        this.name = name;
    }


    public Hashtable<String, Integer> getBag() {
        return bag;
    }



    public void setPokemon(Object pokemon, int number) {
        this.pokeballs[number] = pokemon;
    }

    public Object[] getPokemon() {
        return pokeballs;
    }

    public void checkBag() {
        /*
         * Prints out items in bag with quantities
         */
        for (String key : this.bag.keySet()) {
            System.out.println((key + " : " + this.bag.get(key)));
        }
    }

    public String addItemToBag(String item, int quantity) {
        /*
         * Updates quantity of item in bag
         */
        this.bag.merge(item, quantity, Integer::sum);
        return (item + " was successfully added to your bag");
    }

    public void useItem(String item) {
        /*
         * If user uses item, quantity is decreased by 1
         */
        this.bag.put(item, this.bag.get(item) - 1);
    }
}