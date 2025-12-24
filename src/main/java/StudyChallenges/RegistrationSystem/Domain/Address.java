package StudyChallenges.RegistrationSystem.Domain;

public class Address {
    private String houseNumber;
    private String city;
    private String street;
    private static final String NO_INFORMED = "No informed";

    public Address() {
        houseNumber = NO_INFORMED;
        city = NO_INFORMED;
        street = NO_INFORMED;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}