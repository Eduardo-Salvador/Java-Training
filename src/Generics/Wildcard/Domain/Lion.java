package Generics.Wildcard.Domain;

public class Lion extends Animal{
    private String name;

    public String getName() {
        return name;
    }

    public Lion(String name) {
        super(name);
    }

    @Override
    public void makeSound(){
        System.out.println("Roar");
    }
}