package etu.uae.gestion_vente.repositories;

import etu.uae.gestion_vente.entities.Article;
import etu.uae.gestion_vente.entities.ArticleStock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ArticleStockRepository {

    private SessionFactory sessionFactory;

    public ArticleStockRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // findById
    public Optional<ArticleStock> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        ArticleStock articleStock = (ArticleStock) session.get(ArticleStock.class, id);
        if (articleStock != null) {
            tx.commit();
            session.close();
        }
        return Optional.ofNullable(articleStock);
    }

    // findAll
    public List<ArticleStock> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<ArticleStock> articleStocks = session.createQuery("from ArticleStock").list();
        tx.commit();
        session.close();
        return articleStocks;
    }

    // save
    public void save(ArticleStock articleStock) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(articleStock);
        tx.commit();
        session.close();
    }

    // update
    public void update(ArticleStock articleStock) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(articleStock);
        tx.commit();
        session.close();
    }

    // delete
    public void delete(ArticleStock articleStock) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(articleStock);
        tx.commit();
        session.close();
    }

    public Optional<ArticleStock> findByName(String nom) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<ArticleStock> articleStocks = session.createQuery("from ArticleStock where nom = '" + nom + "'").list();
        Optional<ArticleStock> articleStock = articleStocks.stream().findFirst();
        if (articleStock.isPresent()) {
            tx.commit();
            session.close();
        }
        return articleStock;
    }
}
