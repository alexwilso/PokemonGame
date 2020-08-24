package com.wilson;

public class Squirtle extends Pokemon {
    private HydroPump HydroPump;
    private Tackle tackle;
    private Surf surf;
    private ShellAttack shellAttack;

    public Squirtle(String name, String type, int level, int health, int maxHealth, String status, HydroPump hydroPump, Tackle tackle,
                    Surf surf, ShellAttack shellAttack) {
        super(name, type, level, health, maxHealth, status);
        this.HydroPump = hydroPump;
        this.tackle = tackle;
        this.surf = surf;
        this.shellAttack = shellAttack;
    }

    public HydroPump getHydroPump() {
        return HydroPump;
    }

    public Tackle getTackle() {
        return tackle;
    }

    public Surf getSurf() {
        return surf;
    }

    public ShellAttack getShellAttack() {
        return shellAttack;
    }

    public String[] DisplaySquirtle(Squirtle squirtle) {
        return new String[]{"Type: " + squirtle.getType(), "Growl damage: " + String.valueOf(squirtle.getSurf().getDamage()) + " PP: " + String.valueOf(squirtle.getSurf().getPp()),
                "Quick Attack damage: " + String.valueOf(squirtle.getHydroPump().getDamage()) + " PP: " + String.valueOf(squirtle.getHydroPump().getPp()),
                "Thunder damage: " + String.valueOf(squirtle.getShellAttack().getDamage()) + " PP: " + String.valueOf(squirtle.getShellAttack().getPp()),
                "Thunder Shock Throw: " + String.valueOf(squirtle.getTackle().getDamage()) + " PP: " + String.valueOf(squirtle.getTackle().getPp())};
    }
}

class HydroPump extends Attack{
    // Initializes HydroPump attack
    public HydroPump(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
    }

    public int attack(String type){
        // Carries out attack. If type Fire or Rock, then damage is doubled. If type Water or Grass damage halved.
        // Subtracts 1 from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Fire") || type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if (type.equals("Water") || type.equals("Grass")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            return this.getDamage() / 2;
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class Tackle extends Attack{
    // Initializes Tackle attack
    public Tackle(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
    }

    public int attack(){
        //returns damage
        this.setPp(this.getPp() - 1);
        return this.getDamage();
    }
}

class Surf extends Attack {
    // Initiliazes Surf attack
    public Surf(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
    }

    public int attack(String type){
        // Carries out attack. If type Fire or Rock, then damage is doubled. If type Water or Grass damage halved.
        // Subtracts 1 from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else if (type.equals("Fire") || type.equals("Rock")){
            this.setPp(this.getPp() - 1);
            System.out.println("It's super effective");
            return this.getDamage() * 2;
        } else if (type.equals("Water") || type.equals("Grass")) {
            this.setPp(this.getPp() - 1);
            System.out.println("It's not very effective");
            return this.getDamage() / 2;
        }
        else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}

class ShellAttack extends Attack{
    public ShellAttack(int damage, int remaining, int maxRemaining) {
        super(damage, remaining, maxRemaining);
//        this.setDamage(10);
//        this.setPp(15);
//        this.setMaxRemains(15);
    }

    public int attack(String type){
        // Carries out attack. Subtracts from remaining unless remaining is 0 then returns 0.
        if (this.getPp() == 0) {
            System.out.println("No attack remaining");
            return 0;
        } else {
            this.setPp(this.getPp() - 1);
            return this.getDamage();
        }
    }
}


