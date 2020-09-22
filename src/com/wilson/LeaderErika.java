package com.wilson;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class LeaderErika extends Leader {
    private static final Hashtable<String, Integer> bag = new Hashtable<>();
    private final Victreebel victreebel;
    private final Vileplume vileplume;
    private final Tangela tangela;
    private String strength;

    public LeaderErika(Victreebel victreebel, Vileplume vileplume, Tangela tangela) {
        super(bag);
        this.victreebel = victreebel;
        this.vileplume = vileplume;
        this.tangela = tangela;
        this.addItemToBag("Potion", 2);
        this.addItemToBag("Max Potion", 1); }

    public String getStrength() {
        return strength; }

    public void setStrength(String strength) {
        this.strength = strength; }

    public Victreebel getVictreebel() {
        return victreebel;
    }

    public Vileplume getVileplume() {
        return vileplume;
    }

    public Tangela getTangela() {
        return tangela;
    }
}

class ErikaAI{
    /* Responsible for determining move to be used by Leader Erika. Creates attack tree for Victrebell, Tangela, and
      Vileplume to determine the attack to be used by each. */

    BinaryTree binaryTree;
    private String opponentStatus;

    public ErikaAI(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    public String getOpponentStatus() {
        return opponentStatus;
    }

    public void setOpponentStatus(String opponentStatus) {
        this.opponentStatus = opponentStatus;
    }

    public String moveStatus(Map<Integer, String> moveResult, int key){
         return moveResult.get(key);
    }

    public int ReturnKeys(Map<Integer, String> moveResult){
        // Returns damage of move
        for ( Integer key : moveResult.keySet() ) {
            return key; }
        return 0; }

    public int ErikaAttackVictreebel(LeaderErika leaderErika, Victreebel victreebel, String move, String enemyType){
        /* Takes attack generated by search Trees largest value and returns damage done by attack. If move is heal,
        0 is returned. Sets opponent status based on result of move.*/

        String randomMove = "Random";
        Map<Integer, String>  erikaAttack;
        switch (move) {
            case "Max Potion":
                victreebel.use_item("Max Potion");
                leaderErika.useItem("Max Potion");
                setOpponentStatus("Normal");
                victreebel.setAttackName("Max Potion");
                leaderErika.setStrength("Normal");
                return 0;
            case "Vine Whip":
                erikaAttack = victreebel.getVineWhip().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                victreebel.setAttackName("Vine Whip");
                leaderErika.setStrength(victreebel.getVineWhip().getStrength());
                return ReturnKeys(erikaAttack);
            case "Razor Leaf":
                erikaAttack = victreebel.getRazorLeaf().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                victreebel.setAttackName("Razor Leaf");
                leaderErika.setStrength(victreebel.getRazorLeaf().getStrength());
                return ReturnKeys(erikaAttack);
            case "Sleep":
                erikaAttack = victreebel.getSleep().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                victreebel.setAttackName("Sleep");
                leaderErika.setStrength(victreebel.getSleep().getStrength());
                return ReturnKeys(erikaAttack);
            case "Spit up":
                erikaAttack = victreebel.getSpitUp().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                victreebel.setAttackName("Spit up");
                leaderErika.setStrength(victreebel.getSpitUp().getStrength());
                return ReturnKeys(erikaAttack);
            case "Random":
                int min = 1;
                int max = 4;
                int random = (int) (Math.random() * (max - min + 1)) + min;
                switch (random) {
                    case 1:
                        if (victreebel.getVineWhip().getPp() > 0) {
                            victreebel.setAttackName("Vine Whip");
                            randomMove = "Vine Whip";
                        break;} break;
                    case 2:
                        if (victreebel.getRazorLeaf().getPp() > 0) {
                            victreebel.setAttackName("Razor Leaf");
                            randomMove = "Razor Leaf";
                        break;} break;
                    case 3:
                        if (victreebel.getSpitUp().getPp() > 0) {
                            victreebel.setAttackName("Spit up");
                            randomMove = "Spit up";
                        break;} break;
                    case 4:
                        if (victreebel.getSleep().getPp() > 0) {
                            victreebel.setAttackName("Sleep");
                            randomMove = "Sleep";
                            break;} break; }
                return ErikaAttackVictreebel(leaderErika, victreebel, randomMove, enemyType);
        }
        return 0; }


    public Map<Integer, String> CreateTreeVictreebel(LeaderErika leaderErika, Victreebel victreebel, int enemyHealth,
                                                     String enemyType, String enemyStatus) {
        /* Creates search tree based on input of victreebels health and status of enemy. Calls VictrebellAttack to
        determine, damage done with attack. Returns hashmap with string and damage as values. */

        binaryTree.deleteTree();
        Map<Integer, String> move = new HashMap<>();
        if (victreebel.getHealth() < 20) {
            if (leaderErika.getBag().get("Max Potion") > 0) {
                binaryTree.addNode(90, "Max Potion");}}

        if (enemyType.equals("Rock") || enemyType.equals("Water")) {
            if(victreebel.getVineWhip().getPp() > 0) {
                binaryTree.addNode(70, "Vine Whip");}
            if (victreebel.getRazorLeaf().getPp() > 0) {
                binaryTree.addNode(60, "Razor Leaf"); }}

        if (enemyType.equals("Flying") || enemyType.equals("Fire")) {
            if(victreebel.getVineWhip().getPp() > 0) {
                binaryTree.addNode(10, "Vine Whip");}
            if (victreebel.getRazorLeaf().getPp() > 0) {
                binaryTree.addNode(20, "Razor Leaf");}}

        if (enemyHealth <= 10 && victreebel.getSleep().getPp() > 0) {
            binaryTree.addNode(100, "Sleep");}

        if (enemyHealth <= 15 && victreebel.getSpitUp().getPp() > 0) {
            binaryTree.addNode(95, "Spit up");}

        if (enemyStatus.equals("Normal") && victreebel.getSleep().getPp() > 0) {
            binaryTree.addNode(50, "Sleep");}

        binaryTree.addNode(40, "Random");
        move.put(ErikaAttackVictreebel(leaderErika, victreebel, binaryTree.maxValue(binaryTree.root), enemyType),
                binaryTree.maxValue(binaryTree.root));
        return move; }

    public int ErikaAttackVileplume(LeaderErika leaderErika, Vileplume vileplume, String move, String enemyType){
         /* Takes attack generated by search Trees largest value and returns damage done by attack. If move is heal,
        0 is returned. Sets opponent status based on result of move.*/

        String randomMove = "Random";
        Map<Integer, String>  erikaAttack;
        switch (move) {
            case "Potion":
                vileplume.use_item("Potion");
                leaderErika.useItem("Potion");
                setOpponentStatus("Normal");
                vileplume.setAttackName("Potion");
                leaderErika.setStrength("Normal");
                return 0;
            case "Absorb":
                erikaAttack = vileplume.getAbsorb(true).attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                vileplume.setAttackName("Absorb");
                leaderErika.setStrength(vileplume.getAbsorb(false).getStrength());
                return ReturnKeys(erikaAttack);
            case "HyperBeam":
                erikaAttack = vileplume.getHyperBeam().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                vileplume.setAttackName("Hyper Beam");
                leaderErika.setStrength(vileplume.getHyperBeam().getStrength());
                return ReturnKeys(erikaAttack);
            case "StunSpore":
                erikaAttack = vileplume.getStunSpore().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                vileplume.setAttackName("Stun spore");
                leaderErika.setStrength(vileplume.getStunSpore().getStrength());
                return ReturnKeys(erikaAttack);
            case "Sleep":
                erikaAttack = vileplume.getSleep().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                vileplume.setAttackName("Sleep");
                leaderErika.setStrength(vileplume.getSleep().getStrength());
                return ReturnKeys(erikaAttack);
            case "Random":
                int min = 1;
                int max = 4;
                int random = (int) (Math.random() * (max - min + 1)) + min;
                switch (random) {
                    case 1:
                        if (vileplume.getAbsorb(false).getPp() > 0) {
                            vileplume.setAttackName("Absorb");
                            randomMove = "Absorb";
                            break;} break;
                    case 2:
                        if (vileplume.getHyperBeam().getPp() > 0) {
                            vileplume.setAttackName("Hyper Beam");
                            randomMove = "HyperBeam";
                            break;} break;
                    case 3:
                        if (vileplume.getStunSpore().getPp() > 0){
                        vileplume.setAttackName("Stun spore");
                        randomMove = "StunSpore";
                        break;} break;
                    case 4:
                        if(vileplume.getSleep().getPp() > 0){
                        vileplume.setAttackName("Sleep");
                        randomMove = "Sleep";
                        break;} break; }
                return ErikaAttackVileplume(leaderErika, vileplume, randomMove, enemyType);
        }
            return 0;
    }

    public Map<Integer, String> CreateTreeVileplume(LeaderErika leaderErika, Vileplume vileplume, int enemyHealth, String enemyType, String enemyStatus) {
        /* Creates search tree based on input of Vileplume's health and status of enemy. Calls VileplumeAttack to determine,
        damage done with attack. Returns hashmap with string and damage as values. */

        Map<Integer, String> move = new HashMap<>();
        binaryTree.deleteTree();
        if (vileplume.getHealth() < 20) {
            if (leaderErika.getBag().get("Potion") > 0) {
                binaryTree.addNode(70, "Potion"); }
            else if(vileplume.getAbsorb(false).getPp() > 0) {
                    binaryTree.addNode(70, "Absorb"); }}

        if (enemyType.equals("Rock") || enemyType.equals("Water")) {
            if (vileplume.getHyperBeam().getPp() > 0) {
                binaryTree.addNode(80, "HyperBeam"); }}

        if (enemyType.equals("Flying") || enemyType.equals("Fire")) {
                if (vileplume.getHyperBeam().getPp() > 0) {
                    binaryTree.addNode(10, "HyperBeam");}}

        if (enemyHealth <= 10 && vileplume.getSleep().getPp() > 0) {
            binaryTree.addNode(100, "Sleep");}

        if (enemyHealth <= 15 && vileplume.getStunSpore().getPp() > 0) {
            binaryTree.addNode(90, "StunSpore"); }

        if (enemyHealth <= 25 && vileplume.getAbsorb(false).getPp() > 0){
            binaryTree.addNode(85, "Absorb"); }

        if (enemyStatus.equals("Normal")){
            if (vileplume.getSleep().getPp() > 0) {
                binaryTree.addNode(60, "sleep");}
            if (vileplume.getStunSpore().getPp() > 0) {
                binaryTree.addNode(50, "stunSpore");}}
        binaryTree.addNode(40, "Random");
        move.put(ErikaAttackVileplume(leaderErika, vileplume, binaryTree.maxValue(binaryTree.root), enemyType),
                binaryTree.maxValue(binaryTree.root));
        return move; }

    public int ErikaAttackTangela(LeaderErika leaderErika, Tangela tangela, String move, String enemyType){
        /* Takes attack generated by search Trees largest value and returns damage done by attack. If move is heal,
        0 is returned. Sets opponent status based on result of move.*/

        String randomMove = "Random";
        Map<Integer, String>  erikaAttack;
        switch (move) {
            case "Potion":
                tangela.use_item("Potion");
                leaderErika.useItem("Potion");
                setOpponentStatus("Normal");
                tangela.setAttackName("Potion");
                leaderErika.setStrength("Normal");
                return 0;
            case "Mega Drain":
                erikaAttack = tangela.getMegadrain(true).attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                tangela.setAttackName("Mega Drain");
                leaderErika.setStrength(tangela.getMegadrain(false).getStrength());
                return ReturnKeys(erikaAttack);
            case "Constrict":
                erikaAttack = tangela.getConstrict().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                tangela.setAttackName("Constrict");
                leaderErika.setStrength((tangela.getConstrict().getStrength()));
                return ReturnKeys(erikaAttack);
            case "Poison Powder":
                erikaAttack = tangela.getPoisionPowder().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                tangela.setAttackName("Poison powder");
                leaderErika.setStrength(tangela.getPoisionPowder().getStrength());
                return ReturnKeys(erikaAttack);
            case "Slam":
                erikaAttack = tangela.getSlam().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                tangela.setAttackName("Slam");
                leaderErika.setStrength(tangela.getSlam().getStrength());
                return ReturnKeys(erikaAttack);
            case "Random":
                int min = 1;
                int max = 4;
                int random = (int) (Math.random() * (max - min + 1)) + min;
                switch (random) {
                    case 1:
                        if (tangela.getMegadrain(false).getPp() > 0){
                        tangela.setAttackName("Mega Drain");
                        randomMove = "Mega Drain";
                        break;} break;
                    case 2:
                        if (tangela.getConstrict().getPp() > 0){
                        tangela.setAttackName("Constrict");
                        randomMove = "Constrict";
                        break;} break;
                    case 3:
                        if (tangela.getPoisionPowder().getPp() > 0){
                        tangela.setAttackName("Poison powder");
                        randomMove = "Poison Powder";
                        break;} break;
                    case 4:
                        if(tangela.getSlam().getPp() > 0){
                        tangela.setAttackName("Slam");
                        randomMove = "Slam";
                        break;} break;
                }

                return ErikaAttackTangela(leaderErika, tangela, randomMove, enemyType);
        }
        return 0;
    }


    public Map<Integer, String> CreateTreeTangela(LeaderErika leaderErika, Tangela tangela, int enemyHealth, String enemyType, String enemyStatus) {
        /* Creates search tree based on input of Tangela's health and status of enemy. Calls TangelasAttack to determine,
        damage done with attack. Returns hashmap with string and damage as values. */

        Map<Integer, String> move = new HashMap<>();
        binaryTree.deleteTree();
        if (tangela.getHealth() < 20) {
            if (leaderErika.getBag().get("Potion") > 1) {
                binaryTree.addNode(70, "Potion"); }
            else if (tangela.getMegadrain(false).getPp() > 0) {
                binaryTree.addNode(60, "Mega Drain"); }}

        if (enemyHealth <= 25 && tangela.getConstrict().getPp() > 0) {
            binaryTree.addNode(80, "Constrict"); }

        if (enemyHealth <= 15 && tangela.getSlam().getPp() > 0) {
            binaryTree.addNode(100, "Slam"); }

        if (enemyHealth <= 20 && tangela.getMegadrain(false).getPp() > 0){
            binaryTree.addNode(90, "Mega Drain"); }

        if (enemyStatus.equals("Normal") && tangela.getPoisionPowder().getPp() > 0){
            binaryTree.addNode(50, "Poison Powder"); }
        binaryTree.addNode(40, "Random");
        move.put(ErikaAttackTangela(leaderErika, tangela, binaryTree.maxValue(binaryTree.root), enemyType), binaryTree.maxValue(binaryTree.root));
        return move; }
}

