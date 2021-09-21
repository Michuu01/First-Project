package first.project.Repository;

import first.project.ClientRepository;
import first.project.Hibernate.HibernateUtil;
import first.project.bank.Client;
import org.hibernate.Query;
import org.hibernate.Session;

import java.math.BigDecimal;

public class HibernateRepository implements ClientRepository {
    @Override
    public void save(Client client) {
        final Session session = HibernateUtil.getStartSession().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Client FindByEmail(String email) {
        final Session session = HibernateUtil.getStartSession().openSession();
        session.beginTransaction();
        final Query<Client> query = session.createQuery("from Client where email=:email", Client.class);
        query.setParameter("email", email);
        Client client = query.setMaxResults(1).uniqueResult();
        session.close();
        if (client == null) {
            System.out.println("invalid email");
        }
        return client;

    }

    @Override
    public void transfer(String fromEmail, String toEmail, BigDecimal amount) {
        final Session session = HibernateUtil.getStartSession().openSession();
        session.beginTransaction();
        Client client = FindByEmail(fromEmail);
        Client client2 = FindByEmail(toEmail);
        BigDecimal balance1 = client2.getBalance();
        BigDecimal balance = client.getBalance();

        BigDecimal subtract = balance.subtract(amount);
        BigDecimal add = balance1.add(amount);
        System.out.println(subtract);
        System.out.println(add);
        final org.hibernate.query.Query query = session.createQuery("update Client set balance=:amount "+ "where email=:fromEmail");
        final org.hibernate.query.Query query2 = session.createQuery("update Client set balance=:amount"+" where email =:toEmail");
        query.setParameter("amount", subtract);
        query.setParameter("fromEmail", fromEmail);
        query2.setParameter("toEmail", toEmail);

        query2.setParameter("amount", add);
        query.executeUpdate();
        query2.executeUpdate();
        session.getTransaction().commit();
        session.close();


    }
//       final Session session = HibernateUtil.getStartSession().openSession();
//        session.beginTransaction();
//        final Query<Client> query = session.createQuery("delete Client where email =:fromEmail", Client.class);
//        final Query<Client> query2 = session.createQuery("update from Client where email =:toEmail", Client.class);
//        query.setParameter("fromEmail", fromEmail);
//        query2.setParameter("toEmail", toEmail);
//        query.uniqueResult();
//        session.getTransaction().commit();
//        session.close();
//
//    }

}
