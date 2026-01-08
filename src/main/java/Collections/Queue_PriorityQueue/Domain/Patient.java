package Collections.Queue_PriorityQueue.Domain;

public class Patient implements Comparable<Patient> {
    private final String name;
    private final int priority;

    public Patient(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Patient o){
        if (this.getPriority() < o.getPriority()) return -1;
        if (this.getPriority() == o.getPriority()) return 0;
        return 1;
    }

    @Override
    public String toString() {
        return getName() + " - Priority: " + getPriority();
    }
}