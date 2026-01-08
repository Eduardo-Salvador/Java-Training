package Collections.Sets.Set_HashSet_LinkedHashSet.Domain;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email != null && email.equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return email == null ? 0 : this.email.hashCode();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getName() + " - " + getEmail() + " - " + hashCode();
    }
}