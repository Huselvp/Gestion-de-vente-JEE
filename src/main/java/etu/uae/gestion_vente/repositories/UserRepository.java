package etu.uae.gestion_vente.repositories;

import etu.uae.gestion_vente.dtos.UserSignupDTO;
import etu.uae.gestion_vente.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserRepository {
    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> getUserByCredentials(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from User where login = :login and password = :password");
        q.setParameter("login", login);
        q.setParameter("password", password);
        User user = (User) q.uniqueResult();
        if (user != null) {
            tx.commit();
            session.close();
        }
        return Optional.ofNullable(user);
    }

    // save User
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    public boolean contains(UserSignupDTO user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from User where login = :login");
        q.setParameter("login", user.getLogin());
        User userDb = (User) q.uniqueResult();
        if (userDb != null) {
            tx.commit();
            session.close();
            return true;
        }
        return false;
    }

    public User getUserByLogin(String username) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from User where login = :login");
        q.setParameter("login", username);
        User user = (User) q.uniqueResult();
        if (user != null) {
            tx.commit();
            session.close();
        }
        return user;
    }

    public void update(User user) {
Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
    }
}
