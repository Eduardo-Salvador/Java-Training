package StudyChallenges.SeminarSystem.v2_0.Domain;

public class Seminar {
    private static long NEXT_ID = 1L;
    private long id;
    private String title;
    private Location location;

    public Seminar(String title, Location location) {
        this.id = NEXT_ID++;
        this.title = title;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                " | Location: " + location.address();
    }
}