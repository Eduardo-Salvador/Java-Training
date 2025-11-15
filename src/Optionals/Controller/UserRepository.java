package Optionals.Controller;
import Optionals.Domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void add(User user){
        if (user != null){
            userList.add(user);
            System.out.println("User add success!");
            return;
        }
        System.out.println("User is null, try again");
    }

    public UserRepository(List<User> userList) {
        this.userList = userList;
    }

    public Optional<User> findByEmail(String email){
        for (User u : userList){
            if (u.getEmail().equals(email)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}