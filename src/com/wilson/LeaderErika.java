package com.wilson;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class LeaderErika extends Leader {
    private static final Hashtable<String, Integer> bag = new Hashtable<String, Integer>();
    private final Victreebel victreebel;
    private final Vileplume vileplume;
    private final Tangela tangela;

    public LeaderErika(Victreebel victreebel, Vileplume vileplume, Tangela tangela) {
        super(bag);
        this.victreebel = victreebel;
        this.vileplume = vileplume;
        this.tangela = tangela;
        this.addItemToBag("Potion", 2);
        this.addItemToBag("Max Potion", 1);
    }

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
    /*
    / Responsible for determining move to be used by Leader Erika
     */
    BinaryTree binaryTree = new BinaryTree();
    private String opponentStatus;

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
            return key;
        }
        return 0;
    }

    public int ErikaAttackVictreebel(LeaderErika leaderErika, Victreebel victreebel, String move, String enemyType){
        /*
        / Takes attack generated by search Tree result and returns damage done by attack. If move is heal, 0 is returned.
        / Sets pokemon status based on result of move.
         */
        String randomMove = "Random";
        Map<Integer, String>  erikaAttack;
        switch (move) {
            case "Max Potion":
                victreebel.use_item("Max Potion");
                leaderErika.useItem("Max Potion");
                break;
            case "Vine Whip":
                // You are calling this 3 times, you only want to call once
                erikaAttack = victreebel.getVineWhip().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                return ReturnKeys(erikaAttack);
            case "Razor Leaf":
                erikaAttack = victreebel.getRazorLeaf().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                return ReturnKeys(erikaAttack);
            case "Sleep":
                erikaAttack = victreebel.getSleep().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                return ReturnKeys(erikaAttack);
            case "Spit up":
                erikaAttack = victreebel.getSpitUp().attack(enemyType);
                setOpponentStatus(moveStatus(erikaAttack,
                        ReturnKeys(erikaAttack)));
                return ReturnKeys(erikaAttack);
            case "Random":
                int min = 1;
                int max = 4;
                int random = (int) (Math.random() * (max - min + 1)) + min;
                switch (random) {
                    case 1:
                        if (victreebel.getVineWhip().getPp() > 0) {
                            randomMove = "Vine Whip";
                            break; } break;
                    case 2:
                        if (victreebel.getRazorLeaf().getPp() > 0) {
                            randomMove = "Razor Leaf";
                            break; } break;
                    case 3:
                        if (victreebel.getSpitUp().getPp() > 0) {
                            randomMove = "Spit up";
                            break; } break;
                    case 4:
                        if (victreebel.getSleep().getPp() > 0) {
                            randomMove = "Sleep";
                            break; } break;
                }

                return ErikaAttackVictreebel(leaderErika, victreebel, randomMove, enemyType);
        }
        return 0;
    }


    public Map<String, Integer> CreateTreeVictreebel(LeaderErika leaderErika, Victreebel victreebel, int enemyHealth, String enemyType, String enemyStatus) {
        /*
        / Creates search tree based on input of victreebels status and status of enemy. Calls ErikaAttack to determine,
        / damage done with attack. Returns hashmap with string and damage as values.
         */
        Map<String, Integer> move = new HashMap<>();
        if (victreebel.getHealth() < 20) {
            if (leaderErika.getBag().get("Max Potion") > 0) {
                binaryTree.addNode(80, "Max Potion");
            } else {
                binaryTree.addNode(0, "Max Potion");
            }
        }
        if (enemyType.equals("Rock") || enemyType.equals("Water")) {
            if(victreebel.getVineWhip().getPp() > 0) {
                binaryTree.addNode(70, "Vine Whip");
            } if (victreebel.getRazorLeaf().getPp() > 0) {
                binaryTree.addNode(60, "Razor Leaf");
            }
        } else if (enemyType.equals("Flying") || enemyType.equals("Fire")) {
            if(victreebel.getVineWhip().getPp() > 0) {
                binaryTree.addNode(10, "Vine Whip");
            } if (victreebel.getRazorLeaf().getPp() > 0) {
                binaryTree.addNode(20, "Razor Leaf");
            }
        }
        if (enemyHealth <= 10 && victreebel.getSleep().getPp() > 0) {
            binaryTree.addNode(100, "Sleep");
        }
        if (enemyHealth <= 15 && victreebel.getSpitUp().getPp() > 0) {
            binaryTree.addNode(90, "Spit up");
        }
        if (enemyStatus.equals("Normal") && victreebel.getSleep().getPp() > 0) {
            binaryTree.addNode(50, "Sleep");
        }
        binaryTree.addNode(40, "Random");
        move.put(binaryTree.maxValue(binaryTree.root), ErikaAttackVictreebel(leaderErika, victreebel, binaryTree.maxValue(binaryTree.root), enemyType));
        return move;
    }

    public int ErikaAttackVileplume(LeaderErika leaderErika, Vileplume vileplume, String move, String enemyType){
        /*
        / Takes attack generated by search Tree result and returns damage done by attack. If move is heal, 0 is returned.
         */
        String randomMove = "Random";
        switch (move) {
            case "Potion":
                vileplume.use_item("Potion");
                leaderErika.useItem("Potion");
                break;
            case "Absorb":
                setOpponentStatus(moveStatus(vileplume.getAbsorb().attack(enemyType),
                        ReturnKeys(vileplume.getAbsorb().attack(enemyType))));
                return ReturnKeys(vileplume.getAbsorb().attack(enemyType));
            case "HyperBeam":
                setOpponentStatus(moveStatus(vileplume.getHyperBeam().attack(enemyType),
                        ReturnKeys(vileplume.getHyperBeam().attack(enemyType))));
                return ReturnKeys(vileplume.getHyperBeam().attack(enemyType));
            case "StunSpore":
                setOpponentStatus(moveStatus(vileplume.getStunSpore().attack(enemyType),
                        ReturnKeys(vileplume.getStunSpore().attack(enemyType))));
                return ReturnKeys(vileplume.getStunSpore().attack(enemyType));
            case "Sleep":
                return ReturnKeys(vileplume.getSleep().attack(enemyType));
            case "Random":
                int min = 1;
                int max = 4;
                int random = (int) (Math.random() * (max - min + 1)) + min;
                switch (random) {
                    case 1:
                        if (vileplume.getAbsorb().getPp() > 0) {
                            randomMove = "Absorb";
                            break;} break;
                    case 2:
                        if (vileplume.getHyperBeam().getPp() > 0) {
                            randomMove = "HyperBeam";
                            break;} break;
                    case 3:
                        if (vileplume.getStunSpore().getPp() > 0){
                        randomMove = "StunSpore";
                        break;} break;
                    case 4:
                        if(vileplume.getSleep().getPp() > 0){
                        randomMove = "Sleep";
                        break;} break;
                }

                return ErikaAttackVileplume(leaderErika, vileplume, randomMove, enemyType);
        }
        return 0;
    }

    public Map<String, Integer> CreateTreeVileplume(LeaderErika leaderErika, Vileplume vileplume, int enemyHealth, String enemyType, String enemyStatus) {
         /*
        / Creates search tree based on input of vileplume status and status of enemy. Calls ErikaAttack to determine,
        / damage done with attack. Returns hashmap with string and damage as values.
         */
        Map<String, Integer> move = new HashMap<>();
        if (vileplume.getHealth() < 20) {
            if (leaderErika.getBag().get("Potion") > 1) {
                binaryTree.addNode(70, "Potion");
            } else {
                binaryTree.addNode(0, "Potion");
                if(vileplume.getAbsorb().getPp() > 0) {
                    binaryTree.addNode(70, "Absorb");
                }
            }
            if (enemyType.equals("Rock") || enemyType.equals("Water")) {
                if (vileplume.getHyperBeam().getPp() > 0) {
                    binaryTree.addNode(70, "HyperBeam");
                }
            } else if (enemyType.equals("Flying") || enemyType.equals("Fire")) {
                if (vileplume.getHyperBeam().getPp() > 0) {
                    binaryTree.addNode(10, "HyperBeam");
                }
            }
            if (enemyHealth <= 10 && vileplume.getSleep().getPp() > 0) {
                binaryTree.addNode(100, "Sleep");
            }
            if (enemyHealth <= 15 && vileplume.getStunSpore().getPp() > 0) {
                binaryTree.addNode(90, "StunSpore");
            }
            if (enemyHealth <= 25 && vileplume.getAbsorb().getPp() > 0){
                binaryTree.addNode(80, "Absorb");
            }
            if (!enemyStatus.equals("Normal")){
                if (vileplume.getSleep().getPp() > 0) {
                    binaryTree.addNode(60, "sleep");
                } if (vileplume.getStunSpore().getPp() > 0) {
                    binaryTree.addNode(50, "stunSpore");
                }
            }

            binaryTree.addNode(40, "Random");
        }
        move.put(binaryTree.maxValue(binaryTree.root), ErikaAttackVileplume(leaderErika, vileplume, binaryTree.maxValue(binaryTree.root), enemyType));
        return move;
    }

    public int ErikaAttackTangela(LeaderErika leaderErika, Tangela tangela, String move, String enemyType){
        /*
        / Takes attack generated by search Tree result and returns damage done by attack. If move is heal, 0 is returned.
         */
        String randomMove = "Random";
        switch (move) {
            case "Potion":
                tangela.use_item("Potion");
                leaderErika.useItem("Potion");
                break;
            case "Mega Drain":
                setOpponentStatus(moveStatus(tangela.getMegadrain().attack(enemyType),
                        ReturnKeys(tangela.getMegadrain().attack(enemyType))));
                return ReturnKeys(tangela.getMegadrain().attack(enemyType));
            case "Constrict":
                setOpponentStatus(moveStatus(tangela.getConstrict().attack(enemyType),
                        ReturnKeys(tangela.getConstrict().attack(enemyType))));
                return ReturnKeys(tangela.getConstrict().attack(enemyType));
            case "Poison Powder":
                setOpponentStatus(moveStatus(tangela.getPoisionPowder().attack(enemyType),
                        ReturnKeys(tangela.getPoisionPowder().attack(enemyType))));
                return ReturnKeys(tangela.getPoisionPowder().attack(enemyType));
            case "Slam":
                setOpponentStatus(moveStatus(tangela.getSlam().attack(enemyType),
                        ReturnKeys(tangela.getSlam().attack(enemyType))));
                return ReturnKeys(tangela.getSlam().attack(enemyType));
            case "Random":
                int min = 1;
                int max = 4;
                int random = (int) (Math.random() * (max - min + 1)) + min;
                switch (random) {
                    case 1:
                        if (tangela.getMegadrain().getPp() > 0){
                        randomMove = "Mega Drain";
                        break;} break;
                    case 2:
                        if (tangela.getConstrict().getPp() > 0){
                        randomMove = "Constrict";
                        break;} break;
                    case 3:
                        if (tangela.getPoisionPowder().getPp() > 0){
                        randomMove = "Poison Powder";
                        break;} break;
                    case 4:
                        if(tangela.getSlam().getPp() > 0){
                        randomMove = "Slam";
                        break;} break;
                }

                return ErikaAttackTangela(leaderErika, tangela, randomMove, enemyType);
        }
        return 0;
    }


    public Map<String, Integer> CreateTreeTangela(LeaderErika leaderErika, Tangela tangela, int enemyHealth, String enemyType, String enemyStatus) {
         /*
        / Creates search tree based on input of Tangela status and status of enemy. Calls ErikaAttack to determine,
        / damage done with attack. Returns hashmap with string and damage as values.
         */
        Map<String, Integer> move = new HashMap<>();
        if (tangela.getHealth() < 20) {
            if (leaderErika.getBag().get("Potion") > 0) {
                binaryTree.addNode(70, "Potion");
            } else if (tangela.getMegadrain().getPp() > 0) {
                binaryTree.addNode(0, "Potion");
                if (tangela.getMegadrain().getPp() > 0) {
                    binaryTree.addNode(60, "Mega Drain");
                }
            }
            if (enemyHealth <= 25 && tangela.getConstrict().getPp() > 0) {
                binaryTree.addNode(70, "Constrict");
            }
            if (enemyHealth <= 15 && tangela.getSlam().getPp() > 0) {
                binaryTree.addNode(90, "Slam");
            }
            if (enemyHealth <= 20 && tangela.getMegadrain().getPp() > 0){
                binaryTree.addNode(80, "Mega Drain");
            }
            if (!enemyStatus.equals("Normal") && tangela.getPoisionPowder().getPp() > 0){
                binaryTree.addNode(50, "Poison Powder");
            }

            binaryTree.addNode(40, "Random");
        }
        move.put(binaryTree.maxValue(binaryTree.root), ErikaAttackTangela(leaderErika, tangela, binaryTree.maxValue(binaryTree.root), enemyType));
        return move;
    }

}

// ORDER FOR BATTLE... VILEPLUME, TANGELA, VICTREBELL