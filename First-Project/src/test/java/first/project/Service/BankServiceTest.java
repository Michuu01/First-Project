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
        String fromEmail = "a@a.";
        String toEmail = "b@b.";
        Client clientFrom = new Client("Michal", "a@a.", BigDecimal.valueOf(1000));
        Client clientTo = new Client("Josh", "b@a.", BigDecimal.valueOf(500));
        when(repository.FindByEmail(fromEmail)).thenReturn(clientFrom);
        when(repository.FindByEmail(toEmail)).thenReturn(clientTo);
        final BigDecimal amount = BigDecimal.valueOf(500);
        service.transfer(fromEmail, toEmail, amount);
        final Client ExpectedClientFrom = new Client("Michal", "a@a.", BigDecimal.valueOf(500));
        final Client ExpectedClientTo = new Client("Josh", "b@a.", BigDecimal.valueOf(1000));
        final Client In = repository.FindByEmail(fromEmail);
        final Client In2 = repository.FindByEmail(toEmail);
        Assertions.assertEquals(ExpectedClientFrom, In);
        Assertions.assertEquals(ExpectedClientTo, In2);

    }

    @Test
    public void VerifyIfYouEnoughFunds() {
        String fromEmail = "a@a.";
        String toEmail = "b@b.";
        Client clientFrom = new Client("Michal", "a@a.", BigDecimal.valueOf(1000));
        Client clientTo = new Client("Josh", "b@a.", BigDecimal.valueOf(500));
        final BigDecimal amount = BigDecimal.valueOf(1001);
        when(repository.FindByEmail(fromEmail)).thenReturn(clientFrom);
        when(repository.FindByEmail(toEmail)).thenReturn(clientTo);
        Assertions.assertThrows(NoSufficientFoundsException.class,
                () -> service.transfer(fromEmail, toEmail, amount));
    }

    @Test
    public void VerifyIfYouSendToYourself() {
        String fromEmail = "a@a.";
        Assertions.assertThrows
                (IllegalArgumentException.class,
                        () -> service.transfer(fromEmail, fromEmail, BigDecimal.valueOf(10)));
    }

    @Test
    public void VerifyIfAmountIsMinus() {
        String fromEmail = "a@a.";
        String toEmail = "b@b.";
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.transfer
                (fromEmail, toEmail, BigDecimal.valueOf(-1)));
    }


}