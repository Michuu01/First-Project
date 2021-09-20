package first.project.Repository;

import first.project.ClientRepository;
import first.project.bank.Client;
import first.project.service.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class RepositoryTest {
    private ClientRepository repository;
    private BankService service;


    @BeforeEach
    public void setup() {
        repository = mock(ClientRepository.class);
        service = new BankService(repository);
    }

    @Test
    public void VerifyIfDataIsGood() {
        String email = "m@m.pl";
        Client client = new Client("Michal", email, BigDecimal.valueOf(10));
        repository.save(client);
        Client ClientToEquals = new Client("Michal", "m@m.pl", BigDecimal.valueOf(10));
        when(repository.FindByEmail(email)).thenReturn(ClientToEquals);
        verify(repository).save(ClientToEquals);
    }

    @Test
    public void VerifyIfEmailHasAt() {
        String email = "m.m";
        Client client = new Client("Michal", email, BigDecimal.valueOf(10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client));
    }

    @Test
    public void VerifyIfEmailHasDot() {
        String email = "m@m";
        final Client client = new Client("Michal", email, BigDecimal.valueOf(10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client));
    }

    @Test
    public void VerifyIfEmailIsNull() {
        String email = null;
        final Client client = new Client("Michal", email, BigDecimal.valueOf(10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client));
    }

    @Test
    public void VerifyIfNameIsNull() {
        String name = null;
        final Client client = new Client(name, "m@m.pl", BigDecimal.valueOf(10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client));
    }

    @Test
    public void VerifyIfBalanceIsNull() {
        final Client client = new Client("Michal", "m@m.pl", null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client));
    }

    @Test
    public void VerifyIfBalanceIsMinus() {
        final Client client = new Client("Michal", "m@m.pl", BigDecimal.valueOf(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client));
    }

    @Test
    public void VerifyIfBalanceIsZero() {
        final Client client = new Client("Michal", "m@m.pl", BigDecimal.valueOf(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client));
    }

    @Test
    public void VerifyIfEmailIsSaveToLowercase() {
        String email = "M@m.Pl";
        Client client = new Client("Michal", email, BigDecimal.valueOf(10));
        when(repository.FindByEmail(email)).thenReturn(client);
        service.save(client);
        Client EClient = new Client("Michal", "m@m.pl", BigDecimal.valueOf(10));
        Assertions.assertEquals(client, EClient);


    }


}
