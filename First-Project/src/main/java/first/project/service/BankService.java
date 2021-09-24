package first.project.service;

import first.project.bank.Client;
import first.project.ClientRepository;

import java.math.BigDecimal;


public class BankService {
    ClientRepository clientRepository;

    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {


        if (client.getEmail() == null) {
            throw new IllegalArgumentException("Null problem");
        }
        if (client.getName() == null) {
            throw new IllegalArgumentException("Null problem");
        }
        if (client.getBalance() == null) {
            throw new IllegalArgumentException("Null problem");
        }

        for (int i = 0; i < client.getEmail().length(); i++)
            if (Character.isUpperCase(client.getEmail().charAt(i))) {
                throw new IllegalArgumentException("Email must be in lowercase");
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

        clientRepository.save(client);
    }

    public Client FindByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Invalid email!");
        }

        return clientRepository.FindByEmail(email.toLowerCase());
    }

    public void transfer(String fromEmail, String toEmail, BigDecimal amount) {
        if (fromEmail.equals(toEmail)) {
            throw new IllegalArgumentException("email from and email to is equals!");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Wrong value");
        }
        Client fromClient = FindByEmail(fromEmail);
        Client toClient = FindByEmail(toEmail);

        BigDecimal amounts = fromClient.getBalance().subtract(amount);
        if (amounts.compareTo(BigDecimal.valueOf(0)) >= 0) {
            fromClient.setBalance(amounts);
            toClient.setBalance(toClient.getBalance().add(amount));
        } else {
            throw new NoSufficientFoundsException();
        }
        clientRepository.transfer(fromEmail, toEmail, amount);
    }

    public void delete(String email) {

        if (FindByEmail(email) == null) {
            throw new IllegalArgumentException("invalid email");

        }

        clientRepository.delete(email);


    }


}

