package first.project.Repository;

import first.project.ClientRepository;
import first.project.bank.Client;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class InMemoryRepository implements ClientRepository {

    private final Set<Client> clients;

    public InMemoryRepository(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void save(Client client) {


        clients.add(client);
    }

    @Override
    public Client FindByEmail(String email) {
        return clients.stream().filter(client -> Objects.equals(client.getEmail(), email.toLowerCase())).findFirst().get();

    }

    @Override
    public void transfer(String fromEmail, String toEmail, BigDecimal amount) {

    }
}
