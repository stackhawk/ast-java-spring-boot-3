package com.example.ast.model;

public class Penguin extends Bird {
    public Penguin(String species, double wingspan, double weight) {
        super(species, wingspan, weight);
    }

    @Override
    public void makeSound() {
        System.out.println("Honk honk");
    }
}
