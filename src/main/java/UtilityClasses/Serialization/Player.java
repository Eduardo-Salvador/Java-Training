package UtilityClasses.Serialization;
import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private Integer level;
    private Double points;
    @Serial
    private static final long serialVersionUID = 1L;
    private transient String password;

    public Player(String name, Integer level, Double points, String password) {
        this.name = name;
        this.level = level;
        this.points = points;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", points=" + points +
                ", password='" + password + '\'' +
                '}';
    }
}