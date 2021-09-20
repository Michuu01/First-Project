package first.project.Service;
import first.project.ClientRepository;
import first.project.bank.Client;
import first.project.service.BankService;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;

//
//public class BankServiceTest {
//
//    private ClientRepository repository;
//    private BankService service;
//
//
//    @BeforeEach
//    public void setup() {
//        repository = mock(ClientRepository.class);
//        service = new BankService(repository);
//    }
//    @Test
//    public void VerifyIfDataIsGood() {
//        Client client = new Client("Michal", "m@m.pl", BigDecimal.valueOf(10));
//        Client ClientToEquals = new Client("Michal", "m@m.pl", BigDecimal.valueOf(10));
//
//when(repository.FindByEmail(client.getEmail())).thenReturn(ClientToEquals);
//        repository.save(client);
//        Assertions.assertEquals(client, ClientToEquals);
//    }
//
//
//
//}
