package DesignPatterns.Builder;

public class Main {
    public static void main(String[] args) {
        PizzaOrder completedPizza = new PizzaOrder.Builder()
                .size("LARGE")
                .crust("THIN")
                .sauce("Tomato")
                .addTopping("Pepperoni")
                .addTopping("Eggs")
                .addTopping("Olives")
                .extraCheese(true)
                .notes("Cut into squares")
                .build();

        PizzaOrder minimalPizza = new PizzaOrder.Builder()
                .size("SMALL")
                .crust("THICK")
                .sauce("BBQ")
                .build();

        try {
            PizzaOrder invalidPizza = new PizzaOrder.Builder()
                    .size("LARGE")
                    .build();
            System.out.println(invalidPizza);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(completedPizza);
        System.out.println(minimalPizza);
    }
}