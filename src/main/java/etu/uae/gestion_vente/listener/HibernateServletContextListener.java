package etu.uae.gestion_vente.listener;

import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateServletContextListener implements ServletContextListener {

    // private static final String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SessionFactory sf = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
        sf.close();
    }

    // Fonction responsable de la configuration de la sessionFactory
    // Elle est appelée lorsque le contexte est initialisé
    // au niveau des servlets
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        URL url = HibernateServletContextListener.class.getResource("/hibernate_g_vente.cfg.xml");
        Configuration config = new Configuration();
        config.configure(url);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
        SessionFactory sf = config.buildSessionFactory(serviceRegistry);
        sce.getServletContext().setAttribute("SessionFactory", sf);
    }
}
