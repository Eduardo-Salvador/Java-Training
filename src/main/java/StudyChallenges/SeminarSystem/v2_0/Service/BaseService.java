package StudyChallenges.SeminarSystem.v2_0.Service;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class BaseService<T> {
    protected ArrayList<T> data;

    public BaseService(ArrayList<T> arrayList) {
        this.data = new ArrayList<>();
    }

    public abstract void register(Scanner input);

    public abstract void change(Scanner newScanner);

    public void list() {
        if (data.isEmpty()){
            System.out.println("No elements available");
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            System.out.println((i + 1) + ". " + data.get(i));
        }
    }
}