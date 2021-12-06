package hibernate;

import domain.Pojo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    // private static final bir değişken tanımlayıp bunu da bir metod ile
    // bunun instance'ını döndürmek gerekir.
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // sessionFactory oluşturup bize dönecek
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Pojo.class);
            SessionFactory sessionFactory = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
            return sessionFactory;

        } catch (Exception e) {
            System.out.println("Session factory oluşturulurken bir hata oluştu " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
