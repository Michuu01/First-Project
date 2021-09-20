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
        Client client = query.uniqueResult();
        session.close();
        if (client == null) {
            System.out.println("invalid email");
        }
            return client;

    }

    @Override
    public void transfer(String fromEmail, String toEmail, BigDecimal amount) {

    }
}
