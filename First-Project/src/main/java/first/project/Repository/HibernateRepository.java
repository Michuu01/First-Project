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
        final org.hibernate.query.Query query = session.createQuery("from Client where email=:email", Client.class);
        query.setParameter("email", email);
        Client client = (Client) query.uniqueResult();
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
        Client clientFrom = FindByEmail(fromEmail);
        Client clientTo = FindByEmail(toEmail);
        BigDecimal balanceFrom = clientFrom.getBalance();
        BigDecimal balanceTo = clientTo.getBalance();
        BigDecimal subtract = balanceFrom.subtract(amount);
        BigDecimal add = balanceTo.add(amount);
        System.out.println("account balance = "+subtract);

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

}
