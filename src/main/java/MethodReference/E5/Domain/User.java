package MethodReference.E5.Domain;

public class User {
    private final String NAME;
    private final Integer AGE;

    public User(String NAME, Integer AGE) {
        this.NAME = NAME;
        this.AGE = AGE;
    }

    public String getNAME() {
        return NAME;
    }

    public Integer getAGE() {
        return AGE;
    }

    @Override
    public String toString() {
        return "User{" +
                "NAME='" + NAME + '\'' +
                ", AGE=" + AGE +
                '}';
    }
}