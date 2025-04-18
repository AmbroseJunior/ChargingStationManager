package dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vao.ChargingStation;
import vao.Provider;

import java.util.List;
import java.util.UUID;

@Stateless
public class ChargingStationDAO {
    @PersistenceContext(unitName = "wildPU")
    private EntityManager em;

    public ChargingStation create(ChargingStation station) {
        em.persist(station);
        return station;
    }

    public ChargingStation findById(UUID id) {
        return em.find(ChargingStation.class, id);
    }

    public List<ChargingStation> findAll() {
        return em.createQuery("SELECT s FROM ChargingStation s", ChargingStation.class).getResultList();
    }

    public List<ChargingStation> findByProvider(Provider provider) {
        return em.createQuery("SELECT s FROM ChargingStation s WHERE s.provider = :provider", ChargingStation.class)
                .setParameter("provider", provider)
                .getResultList();
    }

    public ChargingStation update(ChargingStation station) {
        return em.merge(station);
    }

    public void delete(UUID id) {
        ChargingStation station = em.find(ChargingStation.class, id);
        if (station != null) {
            em.remove(station);
        }
    }
}