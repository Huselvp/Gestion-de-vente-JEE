package etu.uae.gestion_vente.repositories;

import etu.uae.gestion_vente.entities.Commande;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandeRepository {

    private SessionFactory sessionFactory;

    public CommandeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // findById
    public Optional<Commande> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Commande commande = (Commande) session.get(Commande.class, id);
        if (commande != null) {
            tx.commit();
            session.close();
        }
        return Optional.ofNullable(commande);
    }

    // findAll
    public List<Commande> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Commande> commandes = session.createQuery("from Commande").list();
        tx.commit();
        session.close();
        return commandes;
    }

    // save
    public void save(Commande commande) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(commande);
        tx.commit();
        session.close();
    }

    // update
    public void update(Commande commande) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(commande);
        tx.commit();
        session.close();
    }

    // delete
    public void delete(Commande commande) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(commande);
        tx.commit();
        session.close();
    }

    public List<Commande> findByClient(String client) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Commande> commandes = session.createQuery("from Commande where client = :client")
                .setParameter("client", client)
                .list();
        tx.commit();
        session.close();
        return commandes;
    }
}
