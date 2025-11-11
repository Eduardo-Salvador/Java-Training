package Generics.Wildcard.Controller;
import Generics.Wildcard.Domain.Animal;
import java.util.List;

public class AnimalManager {
    public static void printAnimalSounds(List<? extends Animal> animalList){
        for (Animal a : animalList){
            System.out.println(a);
            a.makeSound();
        }
    }

    public static void add(List<? super Animal> animalList, List<? extends Animal> a){
        animalList.addAll(a);
        System.out.println("Added: " + a);
    }
}