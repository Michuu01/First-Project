package first.project.Service;

import first.project.ClientRepository;
import first.project.bank.Client;
import first.project.service.BankService;
import first.project.service.NoSufficientFoundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


public class BankServiceTest {

    private ClientRepository repository;
    private BankService service;

    @BeforeEach
    public void setup() {
        repository = mock(ClientRepository.class);
        service = new BankService(repository);

    }

    @Test
    public void VerifyIfCorrectTransferMoney() {
        String fromClient = "a@a.";
        String toClient = "b@b.";
        Client clientFrom = new Client("Michal", "a@a.", BigDecimal.valueOf(1000));
        Client clientTo = new Client("Josh", "b@a.", BigDecimal.valueOf(500));
        final BigDecimal amount = BigDecimal.valueOf(500);
        when(repository.FindByEmail(fromClient)).thenReturn(clientFrom);
        when(repository.FindByEmail(toClient)).thenReturn(clientTo);
        service.transfer(fromClient, toClient, amount);
        final Client ExpectedClientFrom = new Client("Michal", "a@a.", BigDecimal.valueOf(500));
        final Client ExpectedClientTo = new Client("Josh", "b@a.", BigDecimal.valueOf(1000));
        verify(repository).save(ExpectedClientFrom);
        verify(repository).save(ExpectedClientTo);
    }

    @Test
    public void VerifyIfYouEnoughFunds() {
        String fromClient = "a@a.";
        String toClient = "b@b.";
        Client clientFrom = new Client("Michal", "a@a.", BigDecimal.valueOf(1000));
        Client clientTo = new Client("Josh", "b@a.", BigDecimal.valueOf(500));
        final BigDecimal amount = BigDecimal.valueOf(1001);
        when(repository.FindByEmail(fromClient)).thenReturn(clientFrom);
        when(repository.FindByEmail(toClient)).thenReturn(clientTo);
        Assertions.assertThrows(NoSufficientFoundsException.class,
                () -> service.transfer(fromClient, toClient, amount));


    }

}