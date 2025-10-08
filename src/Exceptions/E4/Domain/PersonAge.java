package Exceptions.E4.Domain;

public class PersonAge {
    private int age;

    public PersonAge(int age) throws InvalidAgeException {
        if (age > 120 || age < 0) {
            throw new InvalidAgeException("Age cannot be greater than 120 or less than 0");
        } else {
            this.age = age;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}