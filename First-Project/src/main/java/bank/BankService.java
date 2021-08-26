package bank;

import repository.ClientRepository;

public class BankService {
    ClientRepository clientRepository;

    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {
        clientRepository.save(client);

    }

    public Client FindByEmail(String email) {
        return clientRepository.FindByEmail(email);
    }

}
