package com.wilson;

import java.util.Hashtable;

public class Leader {
    private final Hashtable<String, Integer> bag;

    public Leader(Hashtable<String, Integer> bag) {
        this.bag = bag;
    }

    public void addItemToBag(String item, int quantity){
        /*
         * Updates quantity of item in bag
         */
        this.bag.merge(item, quantity, Integer::sum);
    }

    public void useItem(String item){
        /*
         * Uses item, quantity is decreased by 1
         */
        this.bag.put(item, this.bag.get(item) - 1);
    }

    public Hashtable<String, Integer> getBag() {
        return bag;
    }
}
