package FinalProject;
import FinalProject.Domain.*;
import FinalProject.Exception.*;
import FinalProject.Repository.*;
import FinalProject.Server.*;
import FinalProject.Service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.quality.Strictness;
import java.util.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PetServiceTest {
    @Mock
    private PetRepository<Pet, Long> repository;

    @Mock
    private NotificationServer notificationServer;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setup() throws DatabaseConnectionException {
        when(repository.findAll()).thenReturn(List.of());
    }

    @Test
    @DisplayName("The DTO should return a response that matches the request, indicating that the database has the correct data when registering the pet.")
    void shouldReturnDTO_WhenRegisteringPet() throws PetValidationException {
        //Arrange
        PetRequestDTO request = new PetRequestDTO(
                "Spyke", PetType.DOG, PetSex.MALE, 12.0, 20.2,
                "Dobermann", "Street 2, 123, Center", PetStatus.AVAILABLE
        );

        //Act
        PetResponseDTO result = petService.register(request);

        //Assert
        assertEquals(request.name(), result.name());
        assertEquals(request.type(), result.type());
        assertEquals(request.sex(), result.sex());
        assertEquals(request.age(), result.age());
        assertEquals(request.weight(), result.weight());
        assertEquals(request.breed(), result.breed());
        assertEquals(request.address(), result.address());
        assertEquals(request.status(), result.status());
        verify(repository, times(1)).save(any(Pet.class));
    }

    @Test
    @DisplayName("Throws PetValidationException when the name is empty.")
    void shouldReturnPetValidationException_WhenRegisterNameIsEmpty() {
        //Arrange
        PetRequestDTO request = new PetRequestDTO(
                "", PetType.DOG, PetSex.MALE, 12.0, 20.2,
                "Dobermann", "Street 2, 123, Center", PetStatus.AVAILABLE
        );

        //Act and Assert
        assertThrows(PetValidationException.class, () -> petService.register(request));
    }

    @Test
    @DisplayName("Should return correct DTO when pet exists in cache")
    void shouldReturnCorrectDTO_WhenPetExists() throws PetValidationException {
        // Arrange
        PetRequestDTO request = new PetRequestDTO(
                "Spyke",
                PetType.DOG,
                PetSex.MALE,
                12.0,
                20.2,
                "Dobermann",
                "Street 2, 123, Center",
                PetStatus.AVAILABLE
        );

        Pet savedPet = PetFactory.create(request);
        savedPet.setId(1L);

        doAnswer(invocation -> {
            Pet pet = invocation.getArgument(0);
            pet.setId(1L);
            return null;
        }).when(repository).save(any(Pet.class));

        // Act
        PetResponseDTO registered = petService.register(request);
        Optional<PetResponseDTO> found = petService.findById(1L);

        // Assert
        assertTrue(found.isPresent());
        assertEquals(registered.id(), found.get().id());
        assertEquals(registered.name(), found.get().name());
    }

    @Test
    @DisplayName("Throws PetNotFoundException when the id does not exist.")
    void shouldReturnPetNotFoundException_WhenPetNoExists() {
        // Act, Assert
        assertThrows(PetNotFoundException.class, () -> petService.findById(2L));
    }

    @Test
    @DisplayName("Change the status and call repository.update exactly once.")
    void shouldReturnNewStatus_WhenUpdatePet() throws PetValidationException {
        //Arrange
        PetRequestDTO request = new PetRequestDTO(
                "Spyke", PetType.DOG, PetSex.MALE, 12.0, 20.2,
                "Dobermann", "Street 2, 123, Center", PetStatus.AVAILABLE
        );

        //Act
        Pet savedPet = PetFactory.create(request);
        savedPet.setId(1L);

        doAnswer(invocation -> {
            Pet pet = invocation.getArgument(0);
            pet.setId(1L);
            return null;
        }).when(repository).save(any(Pet.class));

        petService.register(request);

        savedPet.setStatus(PetStatus.ADOPTED);

        petService.update(savedPet);
        Optional<PetResponseDTO> found = petService.findById(1L);

        //Assert
        assertEquals(PetStatus.ADOPTED, found.get().status());
        verify(repository, times(1)).update(any(Pet.class));
    }

    @Test
    @DisplayName("Throws PetNotFoundException when the id does not exist.")
    void shouldReturnPetNotFoundException_WhenUpdatePet() {
        // Arrange
        PetRequestDTO request = new PetRequestDTO(
                "Spyke", PetType.DOG, PetSex.MALE, 12.0, 20.2,
                "Dobermann", "Street 2, 123, Center", PetStatus.AVAILABLE
        );

        // Act & Assert
        Pet savedPet = PetFactory.create(request);
        savedPet.setId(999L);
        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> petService.update(savedPet));
    }

    @Test
    @DisplayName("Returns the filtered results correctly.")
    void shouldCorrectResults_WhenFilterByNameAndBreed() throws Exception {
        //Arrange
        Pet pet1 = PetFactory.create(new PetRequestDTO(
                "Spyke", PetType.DOG, PetSex.MALE, 12.0, 20.2,
                "Dobermann", "Street 2", PetStatus.AVAILABLE
        ));
        pet1.setId(1L);

        Pet pet2 = PetFactory.create(new PetRequestDTO(
                "Lola", PetType.DOG, PetSex.FEMALE, 12.0, 20.2,
                "Labrador", "Street 2", PetStatus.AVAILABLE
        ));
        pet2.setId(2L);

        when(repository.findAll()).thenReturn(List.of(pet1, pet2));
        PetService service = new PetService(repository, notificationServer);

        //Act
        List<PetResponseDTO> result = service.findByNameAndBreed("Spyke", "Dobermann");
        List<PetResponseDTO> result2 = service.findByNameAndBreed("Lola", "Labrador");

        //Assert
        assertEquals(1, result.size());
        assertEquals("Spyke", result.get(0).name());
        assertEquals(1, result2.size());
        assertEquals("Lola", result2.get(0).name());
    }

    @Test
    @DisplayName("Returns the correct counts by type.")
    void shouldCorrectResults_WhenCallCountsByType() {
        //Arrange
        Pet pet1 = PetFactory.create(new PetRequestDTO(
                "Spyke", PetType.DOG, PetSex.MALE, 12.0, 20.2,
                "Dobermann", "Street 2", PetStatus.AVAILABLE
        ));
        pet1.setId(1L);

        Pet pet2 = PetFactory.create(new PetRequestDTO(
                "Lola", PetType.CAT, PetSex.FEMALE, 12.0, 10.2,
                "Cinza", "Street 2", PetStatus.AVAILABLE
        ));
        pet2.setId(2L);

        when(repository.findAll()).thenReturn(List.of(pet1, pet2));
        PetService service = new PetService(repository, notificationServer);

        //Act
        Map<PetType, Long> result = service.countByType();

        //Assert
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals(Map.of(PetType.DOG, 1L, PetType.CAT, 1L), result);
    }
}