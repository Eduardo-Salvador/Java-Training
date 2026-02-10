package Streams.ParallelStreams.Domain;

public class Task {
    private Integer id;
    private Integer processingTime;

    public Task(Integer id, Integer processingTime) {
        this.id = id;
        this.processingTime = processingTime;
    }

    public void execute() {
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getProcessingTime() {
        return processingTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProcessingTime(Integer processingTime) {
        this.processingTime = processingTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", processingTime=" + processingTime +
                '}';
    }
}