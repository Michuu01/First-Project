package first.project.repository;

import first.project.bank.Client;
import first.project.service.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;

public class InMemoryRepositoryTest {
    private BankService repository;
    private HashSet<Client> clients;

    @BeforeEach
    public void setup() {
        clients = new HashSet<>();
        repository = new BankService(new InMemoryRepostiory(clients));

    }

    @Test
    public void VerifyIfDataIsGood() {
        Client client = new Client("Michal", "m@m.pl", BigDecimal.valueOf(10));
        repository.save(client);
        Client ActualClient = clients.stream().findFirst().get();
        final Client ClientToEquals = new Client("Michal", "m@m.pl", BigDecimal.valueOf(10));
        Assertions.assertEquals(ActualClient, ClientToEquals);
    }
//    @Test
//    public void VerifyIfEmailIsUnique(){
//        final Client NewClient = new Client("Michal", "m@m.pl", BigDecimal.valueOf(10));
//        repository.save(NewClient);
//        Client ActualClient = clients.stream().findFirst().get();
//        Client ExpectedClient = new Client ("Josh", "m@m.pl", BigDecimal.valueOf(130.5));
//       if (ActualClient.getEmail().equals(ExpectedClient.getEmail())){
//           Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(ExpectedClient));
//       }


    @Test
    public void VerifyIfEmailHasAt() {
        final Client client = new Client("Michal", "mm.pl", BigDecimal.valueOf(10));
        clients.add(client);
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));

    }

    @Test
    public void VerifyIfEmailHasDot() {
        final Client client = new Client("Michal", "mm@pl", BigDecimal.valueOf(10));
        clients.add(client);
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));

    }
    @Test
    public void VerifyIfEmailIsNull(){
        final Client client = new Client("Michal", null, BigDecimal.valueOf(10));
        clients.add(client);
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));
    }
    @Test
    public void VerifyIfBalanceIsMinus(){
        final Client client = new Client("Michal","m@m.pl",BigDecimal.valueOf(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));
    }
    @Test
    public void VerifyIfBalanceIsZero(){
        final Client client = new Client("Michal","m@m.pl",BigDecimal.valueOf(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.save(client));
    }


}
