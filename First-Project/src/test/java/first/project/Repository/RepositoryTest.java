package first.project.Repository;
import first.project.ClientRepository;
import first.project.bank.Client;

import first.project.service.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;

//public class RepositoryTest {
//    private ClientRepository repository;
//    private BankService service;
//

//    @BeforeEach
//    public void setup() {
//        repository = mock(ClientRepository.class);
//        service = new BankService(repository);
//    }
//
//
//    @Test
//    public void VerifyIfEmailHasAt() {
//    final Client client = new Client("Michal", "m.m", BigDecimal.valueOf(10));
//
//    }
//
//    @Test
//    public void VerifyIfEmailHasDot() {
//        final Client client = new Client("Michal", "mm@pl", BigDecimal.valueOf(10));
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));
//    }
//
//    @Test
//    public void VerifyIfEmailIsNull() {
//        final Client client = new Client("Michal", null, BigDecimal.valueOf(10));
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));
//    }
//
//    @Test
//    public void VerifyIfBalanceIsMinus() {
//        final Client client = new Client("Michal", "m@m.pl", BigDecimal.valueOf(-1));
//        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));
//    }
//
//    @Test
//    public void VerifyIfBalanceIsZero() {
//        final Client client = new Client("Michal", "m@m.pl", BigDecimal.valueOf(0));
//        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));
//    }
//
//}
