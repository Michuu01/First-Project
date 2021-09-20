package first.project.Service;
import first.project.ClientRepository;
import first.project.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;


public class BankServiceTest {

    private ClientRepository repository;
    private BankService service;

    @BeforeEach
    public void setup() {
        repository = mock(ClientRepository.class);
        service = new BankService(repository);

    }
}