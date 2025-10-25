package PROGRAMMING_Challenge_RegistrationSystem.Domain;

public class Pet {
    private String name;
    private PetType type;
    private PetSex sex;
    private String addressWasFound;
    private double ageApproximate;
    private double weightApproximate;
    private String race;

    public Pet(String name){
        this.name = name;
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

    public String getAddressWasFound() {
        return addressWasFound;
    }

    public void setAddressWasFound(String addressWasFound) {
        this.addressWasFound = addressWasFound;
    }

    public double getAgeApproximate() {
        return ageApproximate;
    }

    public void setAgeApproximate(double ageApproximate) {
        this.ageApproximate = ageApproximate;
    }

    public double getWeightApproximate() {
        return weightApproximate;
    }

    public void setWeightApproximate(double weightApproximate) {
        this.weightApproximate = weightApproximate;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
