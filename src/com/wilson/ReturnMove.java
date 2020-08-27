package com.wilson;

import java.util.Map;

public class ReturnMove {

    public String MoveString(Map<Integer, String> moveResult, Integer key){
        return moveResult.get(key);
    }

    public Integer MoveDamage(Map<Integer, String> moveResult) {
        // Returns damage of move
        for (Integer key : moveResult.keySet()) {
            return key;
        }
        return 0;
    }

}
