package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManagerFactory emf;

    public UserDaoImp(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> listUsers() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT user FROM User user");
        List<User> users = (List<User>) query.getResultList();
        em.close();
        return users;
    }

    @Override
    public User getUser(long id) {
        EntityManager em = emf.createEntityManager();
        User user = (User) em.find(User.class, id);
        em.close();
        return user;
    }

    @Override
    public void deleteUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        User u = em.find(User.class, user.getId());
        em.getTransaction().begin();
        em.detach(u);
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());

        em.merge(u);
        em.getTransaction().commit();
        em.close();
     }
}

