package Optionals.Services;
import Optionals.Domain.User;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    public UserRepository() {
    }

    public static Optional<User> findByEmail(List<User> userList, String email){
        for (User u : userList){
            if (u.getEmail().equals(email)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}