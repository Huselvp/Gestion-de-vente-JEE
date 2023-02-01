package etu.uae.gestion_vente.handlers;

import javax.servlet.ServletContext;

import etu.uae.gestion_vente.repositories.UserRepository;
import etu.uae.gestion_vente.entities.User;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import java.util.Map;
import java.util.Optional;

public class LoginHandler implements SessionAware, Action, ModelDriven<User>, ServletContextAware {

    private User user = new User();

    private ServletContext ctx;
    private Map<String, Object> session;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() {

        SessionFactory sessionFactory = (SessionFactory) ctx.getAttribute("SessionFactory");
        UserRepository userRep = new UserRepository(sessionFactory);
        Optional<User> userOpt = userRep.getUserByCredentials(user.getLogin(), user.getPassword());
        if(!userOpt.isPresent()) return SUCCESS;
        else {
            User userDb = userOpt.get();
            session.put("user", userOpt.get());
            user.setId(userDb.getId());
            user.setLogin(userDb.getLogin());
            return LOGIN;
        }
    }

    public String logout() {

        session.remove("userId");

        return "logout";
    }

    @Override
    public User getModel() {
        return user;
    }

    @Override
    public void setServletContext(ServletContext sc) {
        this.ctx = sc;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }
}
