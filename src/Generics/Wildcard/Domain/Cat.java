package Generics.Wildcard.Domain;

public class Cat extends Animal{
    private String name;

    public String getName() {
        return name;
    }

    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound(){
        System.out.println("Miau Miau");
    }
}