package Collections.LinkedList.Domain;

public class Customer {
    private String name;
    private String description;

    public Customer(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        Customer c = (Customer) o;
        return getName() != null && getName().equals(c.getName());
    }

    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }
}