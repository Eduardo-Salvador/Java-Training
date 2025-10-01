package SeminarSystem.Domain;

public class Seminar {
    private String title;
    private Location location;

    public Seminar(String title, Location location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Seminar{" +
                "title='" + title + '\'' +
                ", location=" + location +
                '}';
    }
}
