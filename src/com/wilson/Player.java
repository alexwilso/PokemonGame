package com.wilson;

import java.util.Hashtable;

public class Player {
    private Hashtable<String, Integer> bag;
    private int money;
    private Object[] pokeballs = new Object[3];

    public Player(int money) {
        this.bag = new Hashtable<String, Integer>();
        this.money = money;
    }


    public Hashtable<String, Integer> getBag() {
        return bag;
    }

    public int getMoney() {
        return this.money;
    }



    public void setPokemon(Object pokemon, int number) {
        this.pokeballs[number] = pokemon;
    }

    public Object getPokemon(int x) {
        return pokeballs[x];
    }


    public void checkBag(){
        /*
         * Prints out items in bag with quantities
         */
        for (String key : this.bag.keySet()) {
            System.out.println((key + " : " + this.bag.get(key)));
        }
    }

    public String addItemToBag(String item, int quantity){
        /*
         * Updates quantity of item in bag
         */
        this.bag.merge(item, quantity, Integer::sum);
        return(item + " was successfully added to your bag");
    }

    public void useItem(String item){
        /*
         * If user uses item, quantity is decreased by 1
         */
        this.bag.put(item, this.bag.get(item) - 1);
    }

    public void buyItem(int amount){
        /*
         * If user sells item(s) quantity is decreased by number user sold by and total is added to money
         */
        this.money = getMoney() - amount;
    }

    public void sellItem(String item, int quantity, int total){
        /*
         * If user sells item(s) quantity is decreased by number user sold by and total is added to money
         */
        this.bag.put(item, this.bag.get(item) - quantity);
        this.money = getMoney() + total;
    }
}


