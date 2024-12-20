package com.example.ast.model;

public class Bird {
    private String species;
    private double wingspan;
    private double weight;

    public Bird(String species, double wingspan, double weight) {
        this.species = species;
        this.wingspan = wingspan;
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getWingspan() {
        return wingspan;
    }

    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void makeSound() {
        System.out.println("Kaakaww!");
    };
}
