package DesignPatterns.DataTransferObject;
import DesignPatterns.DataTransferObject.Domain.UserRequestDTO;
import DesignPatterns.DataTransferObject.Domain.UserResponseDTO;
import DesignPatterns.DataTransferObject.Service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();
        service.create(new UserRequestDTO("Eduardo", "edu@email.com", "123"));
        service.create(new UserRequestDTO("Ana", "ana@email.com", "456"));
        service.create(new UserRequestDTO("Carlos", "carlos@email.com", "789"));

        System.out.println("All users:");
        for(UserResponseDTO u : service.findAll()){
            System.out.println(u);
        }

        System.out.println("Find user by id 2:");
        System.out.println(service.findById(2L));
    }
}