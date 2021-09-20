package first.project.Repository;

import first.project.ClientRepository;
import first.project.Hibernate.HibernateUtil;
import first.project.bank.Client;
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
        HibernateUtil.getStartSession().close();
        return FindByEmail(email);

    }

    @Override
    public void transfer(String fromEmail, String toEmail, BigDecimal amount) {

    }
}
