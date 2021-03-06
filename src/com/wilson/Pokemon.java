package com.wilson;

public class Pokemon {
    private String name;
    private String type;
    private int level;
    private int health;
    private int maxHealth;
    private String status;
    private boolean playable;
    private String attackName;
    private String attackStrength;


    public Pokemon(String name, String type, int level, int health, int maxHealth, String status) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.health = health;
        this.maxHealth = maxHealth;
        this.status = status;
        this.playable = true;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public String getStatus() {
        return status;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int RandomNum(int min, int max){
        // generates random number
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public boolean WakeUp(){
        // Chance of a pokemon waking up
        if (RandomNum(1, 3) == 2){
            return true;
        }
        return false;
    }

    public void Burn(){
        // Pokemon loses 5 health per turn while burned
        this.loseHealth(10);
    }

    public boolean Confusion(){
        // Pokemon has 50% chance of hitting self, instead of opponent if confused. Has 50% chance to snap out of confusion

        if (RandomNum(1, 2) == 2){
            loseHealth(10);
            return true;
        }
        return false;
    }

    public boolean Paralyzed(){
        // Chance of a pokemon waking up
        if (RandomNum(1, 3) == 2){
            return true;
        }
        return false;
    }

    public void Poisioned(){
        this.loseHealth(10);
    }


    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public String getAttackStrength() {
        return attackStrength;
    }

    public void setAttackStrength(String attackStrength) {
        this.attackStrength = attackStrength;
    }

    public void loseHealth(int damage) {
        // Takes health away from pokemon. If pokemon loses all its health, user presented with fainted message,
        setHealth(getHealth() - damage);
        if (getHealth() <= 0){
            System.out.println(this.name +" Fainted");
            setPlayable(false);
        }
    }

    public void gainHealth(int heal) {
        // Adds health back to pokemon. User cannot give pokemon more health than maxHealth amount.
        this.health = this.health + heal;
        if (this.health > this.maxHealth){
         this.health = this.maxHealth;
        }
    }

    public void levelUp() {
        // Increase pokemon level by 1 and adds 10 to max health.
        // Look back into adding an experience aspect to this.
        this.level += 1;
        this.maxHealth += 10;
    }

    public void revive(){
        this.health = this.maxHealth;
        this.playable = true;
        System.out.println(this.name + " was revived. May now be used again to battle.");
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable() {
        if (getHealth() == 0){
            this.playable = false;
        }
    }

    public String use_item(String item){
        /*
         * Uses item on pokemon. Different items effect pokemon status and health. Inventory is decreased if item
         * is successfully used. MAKE SURE YOU DECREASE INVENTORY HERE ONCE YOU HAVE CLASS WORKED OUT!
         */
        switch (item){
            // Player uses burn heal, if burned inventory decreases and pokemon status reset to Normal
            case "Burn Heal":
                if (this.getStatus().equals("Burned")){
                    this.setStatus("Normal");
                    return(this.getName() + " is no longer burned");
                    // Need to decrease item status by one
                } else {
                    return("This item has no affect");
                }

            // Player uses paralyse heal, if paralyzed inventory decreases and pokemon status reset to Normal
            case "Paralyze Heal":
                if(this.getStatus().equals("Paralyzed")){
                    this.setStatus("Normal");
                    return(this.getName() + " is no longer paralyzed");
                } else {
                    return("This ite" +
                            "m has no affect");
                }

            // Player uses Awakening, if asleep inventory decreases and pokemon status reset to Normal
            case "Awakening":
                if (this.getStatus().equals("Asleep")){
                    this.setStatus("Normal");
                    return(this.getName() + " is no longer asleep");
                } else {
                    return("This item seems to have no affect");
                }

            // Player uses Antidote, if Poisoned inventory decreases and pokemon status reset to Normal
            case "Antidote":
                if (this.getStatus().equals("Poison")){
                    this.setStatus("Normal");
                    return(this.getName() + " is no longer poisoned");
                } else {
                    return("This item seems to have no affect");
                }

            // Player uses Hyper Potion, Health is restored by 100 points
            case "Hyper Potion":
                if (this.getHealth() != this.getMaxHealth()){
                    this.gainHealth(100);
                    return(this.getName() + " was healed. Health is now " + this.getHealth());
                } else {
                    return (this.getName() + " is already at max health");
                }

            // Player uses Max Potion, Health is restored to max health
            case "Max Potion":
                if (this.getHealth() != this.getMaxHealth()){
                    this.setHealth(this.getMaxHealth());
                    return(this.getName() + " was healed. Health is now " + this.getHealth());
                } else {
                    return (this.getName() + " is already at max health");
                }

            // Player uses potion, Health is restored by 20 points
            case "Potion":
                if (this.getHealth() != this.getMaxHealth()){
                    this.gainHealth(20);
                    return(this.getName() + " was healed. Health is now " + this.getHealth());
                } else {
                    return (this.getName() + " is already at max health");
                }
            }
        return(item + " is not valid");
    }}



