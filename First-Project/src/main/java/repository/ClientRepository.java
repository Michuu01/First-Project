package repository;

import bank.Client;

public interface ClientRepository {
    void save (Client client);
    Client FindByEmail(String email);
}
