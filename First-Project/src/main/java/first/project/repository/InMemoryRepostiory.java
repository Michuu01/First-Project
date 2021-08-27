package first.project.repository;

import first.project.bank.Client;

import java.util.Objects;
import java.util.Set;

public class InMemoryRepostiory implements ClientRepository {

    private final Set<Client> clients;

    public InMemoryRepostiory(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void save(Client client) {

        clients.add(client);
    }

    @Override
    public Client FindByEmail(String email) {
        return clients.stream().filter(client -> Objects.equals(client.getEmail(), email)).findFirst().get();

    }
}
