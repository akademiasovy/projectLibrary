package com.miscik;

import com.miscik.entity.Book;
import com.miscik.entity.Writer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try{
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Writer.class);
            configuration.addAnnotatedClass(Book.class);

            return  configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
        }catch(Exception e){
            e.printStackTrace();
            throw  new RuntimeException("An error occured while building the session factory!");
        }
    }

}
