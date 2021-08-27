package first.project.repository;

import first.project.bank.Client;

public interface ClientRepository {
    void save(Client client);

    Client FindByEmail(String email);
}
