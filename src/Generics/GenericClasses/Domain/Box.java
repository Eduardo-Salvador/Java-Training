package Generics.GenericClasses.Domain;

public class Box<T> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Box(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Box{" +
                "content=" + content +
                '}';
    }
}