package MethodReference.E5.Domain;

@FunctionalInterface
public interface UserFactory {
    User create(String name, Integer age);
}