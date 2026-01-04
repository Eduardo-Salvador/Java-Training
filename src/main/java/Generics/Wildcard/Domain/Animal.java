package Generics.Wildcard.Domain;

public abstract class Animal {
    private String name;

    public String getName() {
        return name;
    }

    public Animal(String name) {
        this.name = name;
    }

    public void makeSound() {
        System.out.println("Animal sound");
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}