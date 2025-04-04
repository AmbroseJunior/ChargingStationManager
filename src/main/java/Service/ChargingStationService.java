package Service;

import Observers.ChargingStationDisplay;
import Observers.ProviderNotifier;
import Observers.UserNotifier;
import dao.ChargingStationDAO;
import vao.ChargingStation;
import vao.Provider;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Iterator;

public class ChargingStationService implements Serializable {
    private static ChargingStationService instance;
    private final ChargingStationDAO stationDAO = ChargingStationDAO.getInstance();

    private ChargingStationService() {}

    public static synchronized ChargingStationService getInstance() {
        if (instance == null) {
            instance = new ChargingStationService();
        }
        return instance;
    }

    public void createStation(Provider provider, String location, String status, double chargingSpeed, String region, double pricePerKWh, String connectorType, boolean isAvailable) {
        if (provider == null) throw new IllegalArgumentException("Provider cannot be null");
        if (location == null || location.trim().isEmpty()) throw new IllegalArgumentException("Location cannot be empty");
        if (status == null || (!status.equalsIgnoreCase("OPERATIONAL") && !status.equalsIgnoreCase("MAINTENANCE") && !status.equalsIgnoreCase("OCCUPIED")))
            throw new IllegalArgumentException("Invalid status value: " + status);

        ChargingStation station = new ChargingStation(UUID.randomUUID(), provider, location, status, chargingSpeed, region, pricePerKWh, connectorType, isAvailable);
        stationDAO.insertChargingStation(station);
    }

    // Overloaded method for backward compatibility
    public void createStation(Provider provider, String location, String status, double chargingSpeed, String region) {
        createStation(provider, location, status, chargingSpeed, region, 0.0, "AC", true);
    }

    public void updateStation(ChargingStation station) {
        stationDAO.updateChargingStation(station);
    }

    public void deleteStation(UUID id) {
        stationDAO.deleteChargingStation(id);
    }

    public List<ChargingStation> getAllChargingStations() {
        List<ChargingStation> stations = stationDAO.getAllChargingStations();
        System.out.println("ChargingStationService.getAllChargingStations() returned " + (stations != null ? stations.size() : "null") + " stations: " + stations);
        return stations;
    }
    public Optional<ChargingStation> getStation(UUID id) {
        return Optional.ofNullable(stationDAO.getChargingStationById(id));
    }

    public Iterator<ChargingStation> getActiveStationsIterator(Provider provider) {
        List<ChargingStation> activeStations = stationDAO.getAllChargingStations().stream()
                .filter(station -> station.getProvider().equals(provider) && "OPERATIONAL".equalsIgnoreCase(station.getStatus()))
                .collect(Collectors.toList());
        return activeStations.iterator();
    }

    public Iterator<ChargingStation> getSpeedFilteredIterator(double chargingSpeed) {
        List<ChargingStation> speedFilteredStations = stationDAO.getAllChargingStations().stream()
                .filter(station -> station.getChargingSpeed() > chargingSpeed)
                .collect(Collectors.toList());
        return speedFilteredStations.iterator();
    }

    public Iterator<ChargingStation> getRegionFilteredIterator(String region) {
        List<ChargingStation> regionFilteredStations = stationDAO.getAllChargingStations().stream()
                .filter(station -> region.equalsIgnoreCase(station.getRegion()))
                .collect(Collectors.toList());
        return regionFilteredStations.iterator();
    }

    public Iterator<ChargingStation> getAlphabeticalIterator() {
        List<ChargingStation> allStations = stationDAO.getAllChargingStations().stream()
                .sorted((s1, s2) -> s1.getLocation().compareToIgnoreCase(s2.getLocation()))
                .collect(Collectors.toList());
        return allStations.iterator();
    }

    public Iterator<ChargingStation> getAllStationsByProvider(Provider provider) {
        List<ChargingStation> providerStations = stationDAO.getAllChargingStations().stream()
                .filter(station -> station.getProvider().equals(provider))
                .toList();
        return providerStations.iterator();
    }

    public Iterator<ChargingStation> getActiveStationsIteratorForAllProviders() {
        List<ChargingStation> allStations = stationDAO.getAllChargingStations().stream()
                .filter(station -> "OPERATIONAL".equalsIgnoreCase(station.getStatus()))
                .collect(Collectors.toList());
        return allStations.iterator();
    }

    // Observers
    public void initializeObservers(ChargingStation station) {
        station.addObserver(new UserNotifier());
        station.addObserver(new ProviderNotifier());
        station.addObserver(new ChargingStationDisplay());
    }

    public ChargingStation startCharging(UUID stationId, String userEmail) {
        Optional<ChargingStation> station = getStation(stationId);
        if (station.isPresent() && station.get().getStatus().equals("OPERATIONAL")) {
            station.get().setCurrentUserEmail(userEmail);
            station.get().setStatus("OCCUPIED");
            return station.get();
        }
        return null;
    }

    public ChargingStation stopCharging(UUID stationId) {
        Optional<ChargingStation> station = getStation(stationId);
        if (station.isPresent() && station.get().getStatus().equals("OCCUPIED")) {
            station.get().setStatus("OPERATIONAL");
            station.get().setCurrentUserEmail(null);
            return station.get();
        }
        return null;
    }

}