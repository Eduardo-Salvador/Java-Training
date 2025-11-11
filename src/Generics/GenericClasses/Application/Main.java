package Generics.GenericClasses.Application;

import Generics.GenericClasses.Domain.Box;
import Generics.GenericClasses.Domain.Pair;
import Generics.Wildcard.Domain.Animal;

public class Main {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("String content");
        Box<Integer> integerBox = new Box<>(123);
        Box<Animal> animalBox = new Box<>(new Animal("Bolt"));

        System.out.println(stringBox);
        System.out.println(integerBox);
        System.out.println(animalBox);
        System.out.println("---------------");

        Pair<String, Integer> age = new Pair<>("Age", 25);
        Pair<String, String> name = new Pair<>("Name", "John");
        System.out.println(age);
        System.out.println(name);
        System.out.println("--------------");
    }
}