package com.example.ast.model;

public class Sparrow extends Bird {
    public Sparrow(String species, double wingspan, double weight) {
        super(species, wingspan, weight);
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp chirp");
    }
}
