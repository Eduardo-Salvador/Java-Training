package StudyChallenges.SeminarSystem.v2_0.Service;
import java.util.ArrayList;
import java.util.Scanner;

public class BaseService<T> {
    protected ArrayList<T> data;

    public BaseService(ArrayList<T> arrayList) {
        this.data = new ArrayList<>();
    }

    public abstract void register(Scanner input);

    public abstract void change(int index, T newObj) {
        if (index >= 0 && index < data.size()) {
            data.set(index, newObj);
        }
    }

    public void list() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println((i + 1) + ". " + data.get(i));
        }
    }
}