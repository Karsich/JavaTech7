package com.web.files.Service;

import com.web.files.Model.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBService {
    private static final SessionFactory sessionFactory = new Configuration()
                                                            .configure("hibernate.cfg.xml")
                                                            .addAnnotatedClass(UserDataSet.class)
                                                            .buildSessionFactory();

    public static UserDataSet read(String login,String password) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        UserDataSet userDataSet = session.createQuery("FROM UserDataSet WHERE login = :login AND password = :password", UserDataSet.class)
                .setParameter("login", login)
                .setParameter("password",password)
                .uniqueResult();
        session.getTransaction().commit();
        return userDataSet;
    }

    public static void save(UserDataSet profile) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(profile);
        session.getTransaction().commit();
    }
}