package com.example.ast.model;

public class Eagle extends Bird {
    public Eagle(String species, double wingspan, double weight) {
        super(species, wingspan, weight);
    }

    @Override
    public void makeSound() {
        System.out.println("Screech");
    }
}
