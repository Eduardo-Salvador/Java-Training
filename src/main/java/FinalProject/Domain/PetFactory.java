package FinalProject.Domain;
import FinalProject.Exception.PetValidationException;

public class PetFactory {
    public static Pet create(PetRequestDTO request){
        try {
            return Pet.PetBuilder.aPet()
                    .withName(request.name())
                    .withType(request.type())
                    .withSex(request.sex())
                    .withAge(request.age() != null ? request.age() : 0)
                    .withWeight(request.weight() != null ? request.weight() : 0.0)
                    .withBreed(request.breed())
                    .withAddress(request.address())
                    .withStatus(request.status())
                    .build();
        } catch (PetValidationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}