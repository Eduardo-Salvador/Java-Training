package StudyChallenges.SeminarSystem.v2_0.Domain;

public class Seminar {
    private long id = 1L;
    private int counter;
    private String title;
    private Location location;

    public Seminar(String title, Location location) {
        this.id = counter;
        counter++;
        this.title = title;
        this.location = location;
    }

    public long getId() {
        return id;
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