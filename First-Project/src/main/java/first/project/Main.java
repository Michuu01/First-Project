package first.project;

import first.project.Repository.HibernateRepository;
import first.project.bank.Client;
import first.project.service.BankService;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private BankService bankservice;


    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        HibernateRepository repository = new HibernateRepository();
        bankservice = new BankService(repository);


        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("'1' = add user");
                System.out.println("'2' = find user");
                System.out.println("'3' = transfer Money");
                System.out.println("'4' = delete usr");
                System.out.println("'5' = Exit");
                String next = scanner.next();

                switch (next) {
                    case "1":
                        addUser(scanner);
                        continue;
                    case "2":
                        findUser(scanner);
                        continue;
                    case "3":
                        transferMoney(scanner);
                        continue;
                    case "4":
                        delete(scanner);
                        continue;
                    case "5":
                        return;
                }
            }
        }
    }

    private void delete(Scanner scanner) {
        System.out.println("enter email");
        String email = scanner.next();
        bankservice.delete(email);
    }


    private void addUser(Scanner scanner) {
        System.out.println("Enter name");
        String name = scanner.next();
        System.out.println("Enter email");
        String email = scanner.next();
        System.out.println("Enter balance");
        String balance = scanner.next();
        bankservice.save(new Client(name, email.toLowerCase(Locale.ROOT), new BigDecimal(balance)));
    }

    private void findUser(Scanner scanner) {
        System.out.println("Enter email");
        String email = scanner.next();
        System.out.println(bankservice.FindByEmail(email));
    }

    private void transferMoney(Scanner scanner) {
        System.out.println("Enter email from which you will get the money");
        String emailFrom = scanner.next();
        System.out.println("Enter email to which you will give the money");
        String emailTo = scanner.next();
        System.out.println("Enter amount (make sure the account has enough money)");
        String money = scanner.next();
        bankservice.transfer(emailFrom, emailTo, new BigDecimal(money));
    }
}
