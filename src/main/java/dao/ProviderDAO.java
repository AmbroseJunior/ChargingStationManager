package dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vao.Provider;

import java.util.List;
import java.util.UUID;

@Stateless
public class ProviderDAO {
    @PersistenceContext(unitName = "wildPU")
    private EntityManager em;

    public Provider create(Provider provider) {
        em.persist(provider);
        return provider;
    }

    public Provider findById(UUID id) {
        return em.createQuery(
                        "SELECT p FROM Provider p LEFT JOIN FETCH p.chargingStations WHERE p.id = :id", Provider.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public Provider findByName(String name) {
        return em.createQuery("SELECT p FROM Provider p WHERE p.name = :name", Provider.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<Provider> findAll() {
        return em.createQuery("SELECT p FROM Provider p", Provider.class).getResultList();
    }

    public Provider update(Provider provider) {
        return em.merge(provider);
    }

    public void delete(UUID id) {
        Provider provider = em.find(Provider.class, id);
        if (provider != null) {
            em.remove(provider);
        }
    }
}
