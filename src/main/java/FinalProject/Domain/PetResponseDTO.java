package FinalProject.Domain;

public record PetResponseDTO(
        Long id,
        String name,
        PetType type,
        PetSex sex,
        Double age,
        Double weight,
        String breed,
        String address,
        PetStatus status
) {
    public static PetResponseDTO fromPet(Pet pet) {
        return new PetResponseDTO(
                pet.getId(),
                pet.getName(),
                pet.getType(),
                pet.getSex(),
                pet.getAge(),
                pet.getWeight(),
                pet.getBreed(),
                pet.getAddress(),
                pet.getStatus()
        );
    }
}