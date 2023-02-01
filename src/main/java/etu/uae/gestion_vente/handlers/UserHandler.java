package etu.uae.gestion_vente.handlers;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import etu.uae.gestion_vente.dtos.UserSignupDTO;
import etu.uae.gestion_vente.entities.User;
import etu.uae.gestion_vente.repositories.UserRepository;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContext;
import java.util.Map;

// Extension de ActionSupport est nécessaire pour faire appele au methodes
// au niveau de Struts
// Implémentation de ModelDriven pour passer les données sous forme de modèle
// presenter en entities pour la vue Struts pour appelle les champs intuitivement
// Implémentation de ServletContextAware pour pouvoir accéder au contexte de
// servlet responsable pour faire appel au Listener de hibernate et obtenir le
// context de la sessionFactory
public class UserHandler  extends ActionSupport implements ModelDriven, ServletContextAware, SessionAware {
    private User user = new User();
    private Map<String, Object> sessionMap;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserSignupDTO getUserSignupDao() {
        return userSignupDao;
    }

    public void setUserSignupDao(UserSignupDTO userSignupDao) {
        this.userSignupDao = userSignupDao;
    }

    private UserSignupDTO userSignupDao = new UserSignupDTO();

    private ServletContext ctx;

    public UserSignupDTO getModel() {
        return userSignupDao;
    }

    public void setServletContext(ServletContext servletContext) {
        this.ctx = servletContext;
    }

    public String execute() throws Exception {
        SessionFactory sessionFactory = (SessionFactory) ctx.getAttribute("SessionFactory");

        UserRepository userRepository = new UserRepository(sessionFactory);

        if (userSignupDao.getLogin() == null || userSignupDao.getPassword() == null)
            return SUCCESS;

        if (userSignupDao.getPassword().equals(userSignupDao.getPasswordConfirm())) {
            user.setLogin(userSignupDao.getLogin());
            user.setPassword(userSignupDao.getPassword());
            if (!userRepository.contains(userSignupDao)) {
                userRepository.save(user);
                return "signedup";
            }
        }
        return SUCCESS;
    }

    public String updatePw() {
        if (sessionMap.get("user") == null) {
            return "unauthorized";
        }

        SessionFactory sessionFactory = (SessionFactory) ctx.getAttribute("SessionFactory");

        UserRepository userRepository = new UserRepository(sessionFactory);

        if (userSignupDao.getPassword() == null)
            return SUCCESS;

        if (userSignupDao.getPassword().equals(userSignupDao.getPasswordConfirm())) {
            User user = (User) sessionMap.get("user");
            user.setPassword(userSignupDao.getPassword());
            if (!userRepository.contains(userSignupDao)) {
                userRepository.update(user);
            }
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
