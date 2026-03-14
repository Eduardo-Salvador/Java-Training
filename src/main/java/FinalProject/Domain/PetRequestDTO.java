package FinalProject.Domain;

public record PetRequestDTO(
        String name,
        PetType type,
        PetSex sex,
        Double age,
        Double weight,
        String breed,
        String address,
        PetStatus status
) {}