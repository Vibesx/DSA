package org.example;

public class PrototypeExample {
    public static void testPrototype() {
        Item sword1 = new Weapon(12, 5, 24, "A shiny sword", "sword1.png");
        Item sword2 = sword1.clone();

        sword1.getStats();
        sword2.getStats();
    }
}

interface Item {
    Item clone();
    void getStats();
}

class Weapon implements Item {

    public int damage;
    public int weight;
    public int value;
    public Metadata metadata;

    public Weapon(int damage, int weight, int value, String desc, String iconPath) {
        this.damage = damage;
        this.weight = weight;
        this.value = value;
        this.metadata = new Metadata(desc, iconPath);
    }

    public Weapon clone() {
        return new Weapon(this.damage, this.weight, this.value, this.metadata.description, this.metadata.iconPath);
    }

    public void getStats() {
        System.out.println("Damage: " + this.damage);
        System.out.println("Weight: " + this.weight);
        System.out.println("Value: " + this.value);
        System.out.println(metadata);
        System.out.println("Description " + this.metadata.description);
        System.out.println("Icon Path " + this.metadata.iconPath);
    }
}

class Armor implements Item {

    public int defense;
    public int weight;
    public int value;

    public Armor(int damage, int weight, int value) {
        this.defense = damage;
        this.weight = weight;
        this.value = value;
    }

    public Armor clone() {
        return new Armor(this.defense, this.weight, this.value);
    }

    public void getStats() {
        System.out.println("Defense: " + this.defense);
        System.out.println("Weight: " + this.weight);
        System.out.println("Value: " + this.value);
    }
}

class Metadata {
    public String description;
    public String iconPath;

    public Metadata(String description, String iconPath) {
        this.description = description;
        this.iconPath = iconPath;
    }
}