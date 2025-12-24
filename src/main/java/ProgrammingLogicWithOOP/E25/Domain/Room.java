package ProgrammingLogicWithOOP.E25.Domain;

public class Room {
    private String name;
    private double minTemp;
    private double maxTemp;
    private double actualTemp;

    public Room() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getActualTemp() {
        return actualTemp;
    }

    public void setActualTemp(double actualTemp) {
        this.actualTemp = actualTemp;
    }
}
