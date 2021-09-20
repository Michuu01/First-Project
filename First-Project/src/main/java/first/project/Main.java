package first.project;

import first.project.Repository.HibernateRepository;
import first.project.bank.Client;
import first.project.service.BankService;
import java.math.BigDecimal;
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
                System.out.println("'3' = exit app");
                String next = scanner.next();

                switch (next) {
                    case "1":
                        addUser(scanner);
                        continue;
                    case "2":
                        findUser(scanner);
                        continue;
                    case "3":
                        return;
                }
            }
        }
    }

    private void addUser(Scanner scanner) {
        System.out.println("Enter name");
        String name = scanner.next();
        System.out.println("Enter email");
        String email = scanner.next();
        System.out.println("Enter balance");
        String balance = scanner.next();
        bankservice.save(new Client(name, email, new BigDecimal(balance)));
    }
    private void findUser(Scanner scanner) {
        System.out.println("Enter email");
        String email = scanner.next();
        System.out.println(bankservice.FindByEmail(email));
    }
}
