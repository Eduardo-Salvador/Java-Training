package Generics.Wildcard.Domain;

public class Dog extends Animal{
    private String name;

    public String getName() {
        return name;
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound(){
        System.out.println("Au Au Au");
    }
}