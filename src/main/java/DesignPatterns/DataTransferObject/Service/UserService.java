package DesignPatterns.DataTransferObject.Service;
import DesignPatterns.DataTransferObject.Domain.User;
import DesignPatterns.DataTransferObject.Domain.UserRequestDTO;
import DesignPatterns.DataTransferObject.Domain.UserResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService {
    private List<User> userList = new ArrayList<>();

    public UserResponseDTO create(UserRequestDTO request){
        Long id = (long) (userList.size() + 1);
        userList.add(new User.Builder()
                .id(id)
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .cpf("000000000000-" + (userList.size() + 1))
                .build()
        );
        return new UserResponseDTO(id, request.name(), request.email());
    }

    public UserResponseDTO findById(Long id) throws NoSuchElementException {
        for (User u : userList){
            if (u.getId().equals(id)){
                return new UserResponseDTO(u.getId(), u.getName(), u.getEmail());
            }
        }
        throw new NoSuchElementException("User not found");
    }

    public List<UserResponseDTO> findAll(){
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User u : userList){
            userResponseDTOList.add(new UserResponseDTO(u.getId(), u.getName(), u.getEmail()));
        }
        return userResponseDTOList;
    }
}