package com.wilson;

import java.util.Map;

public class ReturnMove {

    public Integer moveDamage(Map<String, Integer> moveResult, String key){
        return moveResult.get(key);
    }

    public String MoveString(Map<String, Integer> moveResult) {
        // Returns damage of move
        for (String key : moveResult.keySet()) {
            return key;
        }
        return "False";
    }

}
