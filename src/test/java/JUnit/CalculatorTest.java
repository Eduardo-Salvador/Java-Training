package JUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    private CalculatorRepository repository;

    @InjectMocks
    private CalculatorService service;

    @Test
    @DisplayName("Should return correct result on sum")
    void shouldReturnCorrectResultOnSum() {
        assertEquals(5, service.sum(2, 3));
    }

    @Test
    @DisplayName("Should call repository with correct result on sum")
    void shouldCallRepositoryWithCorrectResultOnSum() {
        service.sum(2, 3);
        verify(repository, times(1)).save(5);
    }

    @Test
    @DisplayName("Should throw exception when value is negative on sum")
    void shouldThrowExceptionWhenValueIsNegativeOnSum() {
        assertThrows(IllegalArgumentException.class, () -> service.sum(-1, 3));
    }

    @Test
    @DisplayName("Should return correct result on subtract")
    void shouldReturnCorrectResultOnSubtract() {
        assertEquals(7, service.subtract(10, 3));
    }

    @Test
    @DisplayName("Should call repository with correct result on subtract")
    void shouldCallRepositoryWithCorrectResultOnSubtract() {
        service.subtract(10, 3);
        verify(repository, times(1)).save(7);
    }

    @Test
    @DisplayName("Should return correct result on multiply")
    void shouldReturnCorrectResultOnMultiply() {
        assertEquals(20, service.multiply(4, 5));
    }

    @Test
    @DisplayName("Should call repository with correct result on multiply")
    void shouldCallRepositoryWithCorrectResultOnMultiply() {
        service.multiply(4, 5);
        verify(repository, times(1)).save(20);
    }

    @Test
    @DisplayName("Should return correct result on divide")
    void shouldReturnCorrectResultOnDivide() {
        assertEquals(5, service.divide(10, 2));
    }

    @Test
    @DisplayName("Should call repository with correct result on divide")
    void shouldCallRepositoryWithCorrectResultOnDivide() {
        service.divide(10, 2);
        verify(repository, times(1)).save(5);
    }

    @Test
    @DisplayName("Should throw exception when divisor is zero on divide")
    void shouldThrowExceptionWhenDivisorIsZeroOnDivide() {
        assertThrows(IllegalArgumentException.class, () -> service.divide(1, 0));
    }
}