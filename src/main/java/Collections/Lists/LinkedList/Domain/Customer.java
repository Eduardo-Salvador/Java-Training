package Collections.Lists.LinkedList.Domain;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o){
        if (o == null || this.getClass() != o.getClass()) return false;
        Customer c = (Customer) o;
        return getName() != null && getName().equals(c.getName());
    }

    @Override
    public int hashCode() {
        return getName() == null ? 0 : getName().hashCode();
    }

    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }
}