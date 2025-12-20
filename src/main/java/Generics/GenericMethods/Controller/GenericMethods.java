package Generics.GenericMethods.Controller;
import java.util.Arrays;
import java.util.List;

public class GenericMethods {
    public static <T> void printArray(T[] t){
        System.out.println("Array type: " + t.getClass().getComponentType().getSimpleName());
        for (Object o : t){
            System.out.println(o);
        }
    }

    public static <T extends Comparable<T>> T maxValue (T a, T b, T c){
        T max = a;
        if (b.compareTo(max) > 0){
            max = b;
        }
        if (c.compareTo(max) > 0){
            max = c;
        }
        return max;
    }

    public static <T> void swap(T[] array, T e1, T e2){
        int p1 = -1;
        int p2 = -1;
        System.out.println("Original Array: " + Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(e1)){
                p1 = i;
            }
            if (array[i].equals(e2)){
                p2 = i;
            }
        }
        if (p1 == -1 || p2 == -1) {
            System.out.println("One of the elements was not found.");
            return;
        }
        if (!e1.equals(e2)) {
            array[p1] = e2;
            array[p2] = e1;
            System.out.println("Changed Array: " + Arrays.toString(array));
        }
    }

    public static <T> void containsElements(List<T> list, T element){
        if (list.contains(element)){
            System.out.println("Element: " + element + ", is presents on list");
            return;
        }
        System.out.println("Element is not found");
    }

    public static <T> void copyList (List<? extends T> listFrom, List<? super T> listTo){
        listTo.addAll(listFrom);
        listTo.forEach(System.out::println);
    }
}
