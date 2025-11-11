package Generics.GenericMethods.Controller;

public class GenericMethods {
    public static <T> void printArray(T[] t){
        System.out.println("Array type: " + t.getClass().getComponentType().getSimpleName());
        for (Object o : t){
            System.out.println(o);
        }
    }
}
