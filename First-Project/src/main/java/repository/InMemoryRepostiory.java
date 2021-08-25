package repository;

import bank.Client;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class InMemoryRepostiory implements ClientRepository{

    private Set<Client> clients;

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
