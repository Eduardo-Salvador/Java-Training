package Generics.GenericClasses.Application;

import Generics.GenericClasses.Domain.Box;
import Generics.GenericClasses.Domain.Pair;
import Generics.Wildcard.Domain.Animal;
import Generics.Wildcard.Domain.Cat;
import Generics.Wildcard.Domain.Dog;

public class Main {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("String content");
        Box<Integer> integerBox = new Box<>(123);
        Box<Animal> dogBox = new Box(new Dog("Spyke"));
        Box<Animal> catBox = new Box(new Cat("Luff"));
        System.out.println(stringBox);
        System.out.println(integerBox);
        System.out.println(dogBox);
        System.out.println(catBox);
        System.out.println("---------------");

        Pair<String, Integer> age = new Pair<>("Age", 25);
        Pair<String, String> name = new Pair<>("Name", "John");
        Pair<Integer, Animal> animal = new Pair<>(1, catBox.getContent());
        System.out.println(age);
        System.out.println(name);
        System.out.println(animal);
        System.out.println("--------------");
    }
}