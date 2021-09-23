package first.project;

import first.project.bank.Client;
import java.math.BigDecimal;

public interface ClientRepository {
    void save(Client client);
    Client FindByEmail(String email);
    void transfer(String fromEmail, String toEmail, BigDecimal amount);
    void delete(String email);
}
