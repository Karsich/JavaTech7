package com.web.files.Service;

import com.web.files.Model.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBService {
    private static final SessionFactory sessionFactory = initSessionFactory();

    public static boolean isUserExist(String login, String password) {
        assert sessionFactory != null;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        UserProfile userProfile = session.createQuery("FROM UserProfile WHERE login = :login AND password = :password", UserProfile.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResult();

        session.getTransaction().commit();

        return userProfile != null;
    }

    public static void createUser(UserProfile profile) {
        assert sessionFactory != null;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(profile);

        session.getTransaction().commit();
    }
    private static SessionFactory initSessionFactory(){
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(UserProfile.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}