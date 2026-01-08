package Collections.Queues.Queue_PriorityQueue.Domain;

import java.util.Objects;

public class Patient implements Comparable<Patient>{
    private String name;
    private String description;
    private Integer priority;

    public Patient(String name, Integer priority, String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(priority, patient.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(priority);
    }

    @Override
    public String toString() {
        return name + " | " + description + " | priority: " + priority;
    }

    @Override
    public int compareTo(Patient o) {
        return this.priority.compareTo(o.getPriority());
    }
}
