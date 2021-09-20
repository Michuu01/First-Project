package first.project.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = Session();

    private static SessionFactory Session() {
        try {
            File configFile = new File("src\\main\\resources\\hibernate.cfg.xml");
            return new Configuration()
                    .configure(configFile).buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Session create failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getStartSession() {
        return sessionFactory;
    }
    public static void Shutdown(){
        sessionFactory.close();
    }
}
