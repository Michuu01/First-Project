package first.project.service;

import first.project.bank.Client;
import first.project.repository.ClientRepository;
import first.project.repository.InMemoryRepository;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class BankService {
    ClientRepository clientRepository;

    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {

        if (client.getEmail() == null) {
            throw new IllegalArgumentException("null problem");
        }
        if (!client.getEmail().contains("@")) {
            throw new IllegalArgumentException("you don't have a '@' ");
        }
        if (!client.getEmail().contains(".")) {
            throw new IllegalArgumentException("your email is invalid ");
        }
        if (client.getBalance().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException("faulty account balance");
        }



        }



    public Client FindByEmail(String email) {
        return clientRepository.FindByEmail(email);
    }

    public void transfer(String fromEmail, String toEmail, BigDecimal amount) {
        Client fromClient = FindByEmail(fromEmail);
        Client toClient = FindByEmail(toEmail);
        {

        }
    }


}

