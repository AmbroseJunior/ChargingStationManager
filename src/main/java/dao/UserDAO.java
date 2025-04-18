package dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vao.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "wildPU")
    private EntityManager em;

    public User create(User user) {
        em.persist(user);
        return user;
    }

    public User findById(UUID id) {
        return em.find(User.class, id);
    }

    public Optional<User> findByName(String name) {
        return em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User update(User user) {
        return em.merge(user);
    }

    public void delete(String name) {
        findByName(name).ifPresent(user -> em.remove(user));
    }
}