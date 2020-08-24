package com.wilson;

import java.util.Hashtable;


public class Store {
    Hashtable<String, Integer> store = new Hashtable<String, Integer>();
    private Player player;

    public Store(Player player) {
        this.store.put("Burn Heal", 250);
        this.store.put("Antidote", 100);
        this.store.put("Awakening", 250);
        this.store.put("Revive", 1500);
        this.store.put("Paralyze Heal", 200);
        this.store.put("Potion", 100);
        this.store.put("Hyper Potion", 600);
        this.store.put("Max Potion", 1000);
        this.store.put("Elixir", 300);
        this.player = player;
    }

    public Hashtable<String, Integer> getStore() {
        return store;
    }

    public boolean buyItems(String item, int quantity){
        /*
         * Allows user to buy items from store. Calculates total case if user has enough money for purchase subtracts
         * cost from users money and adds quantity to bag
         */

        if (!store.containsKey(item)){
            System.out.println("Store Owner: Looks like we don't carry that item");
            return false;
        }
        int cost = quantity * store.get(item);
        if (cost > this.player.getMoney()){
            System.out.println("Store Owner: It looks like you don't have enough money to buy that item");
            return false;
        } else {
            this.player.buyItem(cost);
            this.player.addItemToBag(item, quantity);
        }
        System.out.println("Store Owner: Purchase Successful");
        return true;
    }

    public boolean sellItems(String item, int quantity){
        /*
         * Allows user to sell items in bag to store. If user selling more than they have in bag, false is returned.
         * If not, quantity is removed from users bag and total is added to their money.
         */
        if (!this.player.getBag().containsKey(item)){
            System.out.println("Store Owner: Looks like we don't accept that item");
            return false;
        }
        if (this.player.getBag().get(item) - quantity < 0){
            System.out.println("Store Owner: Looks like you don't have enough " + item + " to sell");
            return false;
        } else {
            int total = quantity * store.get(item);
            this.player.sellItem(item, quantity, total);
            System.out.println("Store Owner: " + item + " successfully sold");
            if (this.player.getBag().get(item) == 0){
                this.player.getBag().remove(item);
            }
            return true;
        }
    }

    public String learnMore(String item){
        // Tells user more about item in store
        switch (item){
            case "Revive":
                return "Revives a fainted pokémon. Restores health to max health.";
            case "Potion":
                return "Restores health by 20 points. Not effective on fainted pokémon.";
            case "Elixir":
                return "Restores pp of single attack by 10. If max pp less then 10 restores pp to max.";
            case "Paralyze heal":
                return "Heals pokémon from paralyzed state.";
            case "Max Potion":
                return  "Restores health to max health on a single pokémon. Not effective on fainted pokémon.";
            case "Hyper Potion":
                return "Restores health by 100 points on a single pokemon. Not effective on fainted pokémon.";
            case "Antidote":
                return "Heals pokémon from poisoned state.";
            case "Burn Heal":
                return "Heals pokémon from burned state.";

        }
        return "Store Owner: I'm not familiar with that item";
    }
}



