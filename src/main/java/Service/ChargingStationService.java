package Service;

import dao.ChargingStationDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vao.ChargingStation;
import vao.Provider;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Stateless
public class ChargingStationService implements Serializable {
    @Inject
    private ChargingStationDAO stationDAO;

    public ChargingStation createStation(ChargingStation station) {
        station.getProvider().addChargingStation(station);
        return stationDAO.create(station);
    }

    public ChargingStation updateStation(ChargingStation station) {
        return stationDAO.update(station);
    }

    public void deleteStation(UUID id) {
        stationDAO.delete(id);
    }

    public List<ChargingStation> getAllChargingStations() {
        return stationDAO.findAll();
    }

    public List<ChargingStation> getStationsByProvider(Provider provider) {
        return stationDAO.findByProvider(provider);
    }

    public ChargingStation getStationById(UUID id) {
        return stationDAO.findById(id);
    }
}