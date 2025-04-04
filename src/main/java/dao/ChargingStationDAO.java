package dao;

import vao.ChargingStation;
import vao.Provider;

import java.util.*;

public class ChargingStationDAO implements ChargingStationDAOInterface {
    private static volatile ChargingStationDAO instance;
    private final Map<UUID, ChargingStation> stations = new HashMap<>();
    private final List<ChargingStation> allChargingStations = Collections.synchronizedList(new ArrayList<>());

    private ChargingStationDAO() {}

    public static ChargingStationDAO getInstance() {
        if (instance == null) {
            synchronized (ChargingStationDAO.class) {
                if (instance == null) {
                    instance = new ChargingStationDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public ChargingStation addChargingStation(Provider provider, String location, String status, double chargingSpeed, String region) {
        UUID id = UUID.randomUUID();
        ChargingStation station = new ChargingStation(id, provider, location, status, chargingSpeed, region, 0, "AC", true);
        stations.put(id, station);
        allChargingStations.add(station); // Add to the list as well
        return station;
    }

    public void insertChargingStation(ChargingStation station) {
        if (station != null && station.getId() != null) {
            stations.put(station.getId(), station);
            allChargingStations.add(station); // Ensure the station is added to the list
        }
    }

    @Override
    public ChargingStation getChargingStationById(UUID id) {
        return stations.get(id);
    }

    @Override
    public List<ChargingStation> getAllChargingStations() {
        System.out.println("allChargingStations returned: " + allChargingStations);
        return new ArrayList<>(stations.values());
    }

    @Override
    public ChargingStation updateChargingStation(ChargingStation chargingStation) {
        if (stations.containsKey(chargingStation.getId())) {
            stations.put(chargingStation.getId(), chargingStation);
            // Update the list as well
            allChargingStations.removeIf(station -> station.getId().equals(chargingStation.getId()));
            allChargingStations.add(chargingStation);
            return chargingStation;
        }
        return null;
    }

    @Override
    public boolean deleteChargingStation(UUID id) {
        ChargingStation removed = stations.remove(id);
        if (removed != null) {
            allChargingStations.remove(removed);
            return true;
        }
        return false;
    }

    @Override
    public List<ChargingStation> getChargingStationsByProvider(Provider provider) {
        List<ChargingStation> result = new ArrayList<>();
        for (ChargingStation station : stations.values()) {
            if (station.getProvider().equals(provider)) {
                result.add(station);
            }
        }
        return result;
    }
}