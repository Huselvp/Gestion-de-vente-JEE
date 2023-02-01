package etu.uae.gestion_vente.repositories;

import etu.uae.gestion_vente.entities.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ArticleRepository {

    private SessionFactory sessionFactory;

    public ArticleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // findById: retourne l'article dont l'id est passé en paramètre
    public Optional<Article> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Article article = (Article) session.get(Article.class, id);
        if (article != null) {
            tx.commit();
            session.close();
        }
        return Optional.ofNullable(article);
    }

    // findAll: Retourne la liste des articles enregistrés dans la base de données
    public List<Article> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Article> articles = session.createQuery("from Article").list();
        tx.commit();
        session.close();
        return articles;
    }

    // save: Enregistre l'article passé en paramètre dans la base de données
    public void save(Article article) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(article);
        tx.commit();
        session.close();
    }

    // update: Met à jour l'article passé en paramètre dans la base de données
    public void update(Article article) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(article);
        tx.commit();
        session.close();
    }

    // delete: Supprime l'article passé en paramètre dans la base de données
    public void delete(Article article) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(article);
        tx.commit();
        session.close();
    }

    public boolean containsNom(String nom) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Article> articles = session.createQuery("from Article where nom = '" + nom + "'").list();
        tx.commit();
        session.close();
        return !articles.isEmpty();
    }

    public Optional<Article> findByName(String nom) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Article> articles = session.createQuery("from Article where nom = '" + nom + "'").list();
        tx.commit();
        session.close();
        return Optional.ofNullable(articles.get(0));
    }
}
