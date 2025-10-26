package PROGRAMMING_Challenge_RegistrationSystem.Domain;

public class Pet {
    private String name;
    private PetType type;
    private PetSex sex;
    private Address addressWasFound;
    private Double ageApproximate;
    private Double weightApproximate;
    private String race;
    private static final String NO_INFORMED = "No informed";

    public String getNO_INFORMED() {
        return NO_INFORMED;
    }

    public Pet(String name){
        this.name = name;
        type = PetType.NO_INFORMED;
        sex = PetSex.NO_INFORMED;
        race = NO_INFORMED;
        ageApproximate = null;
        weightApproximate = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public PetSex getSex() {
        return sex;
    }

    public void setSex(PetSex sex) {
        this.sex = sex;
    }

    public Address getAddressWasFound() {
        return addressWasFound;
    }

    public void setAddressWasFound(Address addressWasFound) {
        this.addressWasFound = addressWasFound;
    }

    public Double getAgeApproximate() {
        return ageApproximate;
    }

    public void setAgeApproximate(Double ageApproximate) {
        this.ageApproximate = ageApproximate;
    }

    public Double getWeightApproximate() {
        return weightApproximate;
    }

    public void setWeightApproximate(Double weightApproximate) {
        this.weightApproximate = weightApproximate;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", sex=" + sex +
                ", addressWasFound=" + addressWasFound +
                ", ageApproximate=" + (ageApproximate == null ? NO_INFORMED : ageApproximate + " years") +
                ", weightApproximate=" + (weightApproximate == null ? NO_INFORMED : weightApproximate + " kg") +
                ", race='" + race + '\'' +
                '}';
    }

}
