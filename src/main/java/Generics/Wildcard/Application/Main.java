package Generics.Wildcard.Application;
import Generics.Wildcard.Services.AnimalManager;
import Generics.Wildcard.Domain.Animal;
import Generics.Wildcard.Domain.Cat;
import Generics.Wildcard.Domain.Dog;
import Generics.Wildcard.Domain.Lion;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>(List.of(new Dog("Rex"), new Dog("Bolt")));
        List<Cat> cats = new ArrayList<>(List.of(new Cat("Mimi"), new Cat("Garfield")));
        List<Lion> lions = new ArrayList<>(List.of(new Lion("Simba")));

        AnimalManager.printAnimalSounds(dogs);
        System.out.println("----------------");
        AnimalManager.printAnimalSounds(cats);
        System.out.println("----------------");
        AnimalManager.printAnimalSounds(lions);
        System.out.println("----------------");

        List<Animal> animals = new ArrayList<>();
        AnimalManager.add(animals, dogs);
        AnimalManager.printAnimalSounds(animals);
        System.out.println("----------------");

        AnimalManager.add(animals, cats);
        AnimalManager.printAnimalSounds(animals);
        System.out.println("----------------");

        AnimalManager.add(animals, lions);
        AnimalManager.printAnimalSounds(animals);
        System.out.println("----------------");
    }
}