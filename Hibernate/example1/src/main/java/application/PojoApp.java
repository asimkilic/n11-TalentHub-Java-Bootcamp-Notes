package application;

import domain.Pojo;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PojoApp {
    public static void main(String[] args) {
        Pojo pojo= new Pojo();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(pojo);
        transaction.commit();
    }
}
