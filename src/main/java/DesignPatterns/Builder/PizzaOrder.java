package DesignPatterns.Builder;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class PizzaOrder {
    private final String size;
    private final String crust;
    private final String sauce;
    private final List<String> toppings;
    private final boolean extraCheese;
    private final String notes;

    private PizzaOrder(Builder builder) {
        this.size = builder.size;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = Collections.unmodifiableList(builder.toppings);
        this.extraCheese = builder.extraCheese;
        this.notes = builder.notes;
    }

    @Override
    public String toString() {
        return "PizzaOrder{" +
                "size='" + size + '\'' +
                ", crust='" + crust + '\'' +
                ", sauce='" + sauce + '\'' +
                ", toppings=" + toppings +
                ", extraCheese=" + extraCheese +
                ", notes='" + notes + '\'' +
                '}';
    }

    public static class Builder {
        private String size;
        private String crust;
        private String sauce;
        private final List<String> toppings = new ArrayList<>();
        private boolean extraCheese = false;
        private String notes;

        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder crust(String crust) {
            this.crust = crust;
            return this;
        }

        public Builder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        public Builder extraCheese(boolean extraCheese) {
            this.extraCheese = extraCheese;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public PizzaOrder build() {
            validateRequire();
            return new PizzaOrder(this);
        }

        private void validateRequire() throws IllegalArgumentException {
            if (size == null) throw new IllegalStateException("Size is required");
            if (!size.equals("SMALL") && !size.equals("MEDIUM") && !size.equals("LARGE")) throw new IllegalArgumentException("Invalid size");
            if (crust == null) throw new IllegalStateException("Crust is required");
            if (!crust.equals("THIN") && !crust.equals("THICK")) throw new IllegalArgumentException("Invalid crust type");
            if (sauce == null || sauce.isBlank()) throw new IllegalStateException("Sauce is required");
        }
    }
}