package dao;

import vao.ChargingStation;
import vao.Provider;

import java.util.List;
import java.util.UUID;

public interface ChargingStationDAOInterface {
    ChargingStation addChargingStation(Provider provider, String location, String status, double ChargingSpeed, String region);
    ChargingStation getChargingStationById(UUID id);
    List<ChargingStation> getAllChargingStations();
    ChargingStation updateChargingStation(ChargingStation chargingStation);
    boolean deleteChargingStation(UUID id);
    List<ChargingStation> getChargingStationsByProvider(Provider provider);
}
