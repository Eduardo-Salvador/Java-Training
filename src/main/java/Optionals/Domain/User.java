package Optionals.Domain;

public class User {
    private final Integer ID;
    private final String NAME;
    private final String EMAIL;

    public User(Integer ID, String NAME, String EMAIL) {
        this.ID = ID;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
    }

    public Integer getId() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public String getEmail() {
        return EMAIL;
    }

    @Override
    public String toString() {
        return ID + " : " + NAME + " - " + EMAIL;
    }
}