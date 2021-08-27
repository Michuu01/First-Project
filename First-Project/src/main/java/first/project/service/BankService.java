package first.project.service;

import first.project.bank.Client;
import first.project.repository.ClientRepository;

public class BankService {
    ClientRepository clientRepository;

    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {


        if (!client.getEmail().contains("@")) {
            throw new IllegalArgumentException("xz@");
        }
        clientRepository.save(client);

    }

    public Client FindByEmail(String email) {
        return clientRepository.FindByEmail(email);
    }
}

