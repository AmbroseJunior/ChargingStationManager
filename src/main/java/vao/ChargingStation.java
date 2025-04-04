package vao;
import Observers.ChargingStationObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChargingStation {
    private UUID id;
    private Provider providerId;
    private String location;
    private String status;
    private double chargingSpeed;
    private String region;
    private String currentUserEmail;
    private boolean isAvailable;
    private String connectorType;
    private double pricePerKWh;
    private final List<ChargingStationObserver> observers = new ArrayList<>();


    public ChargingStation(UUID id, Provider providerId, String location, String status, double chargingSpeed, String region,
                           double pricePerKWh, String connectorType, boolean isAvailable) {
        this.id = id;
        this.providerId = providerId;
        this.location = location;
        this.status = status;
        this.chargingSpeed = chargingSpeed;
        this.region = region;
        this.pricePerKWh = pricePerKWh;
        this.connectorType = connectorType;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) {
        this.id = id;
    }
    public Provider getProvider() { return providerId; }
    public void setProvider(Provider providerId) {
        this.providerId = providerId;
    }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStatus() { return status; }

    public void setStatus(String newStatus) {
        String previousStatus = this.status;
        this.status = newStatus;
        notifyObservers(previousStatus);
    }
    public double getChargingSpeed() { return chargingSpeed; }
    public void setChargingSpeed(double chargingSpeed) { this.chargingSpeed = chargingSpeed; }
    public String getRegion() { return region;}
    public void setRegion(String region) { this.region = region; }
    public boolean isActive() {
        return status.equalsIgnoreCase("OPERATIONAL");
    }
    public List<ChargingStationObserver> getObservers() {
        return observers;
    }
    public String getConnectorType() {
        return connectorType;
    }
    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPricePerKWh() {
        return pricePerKWh;
    }
    public void setPricePerKWh(double pricePerKWh) {
        this.pricePerKWh = pricePerKWh;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void addObserver(ChargingStationObserver observer) {
        observers.add(observer);
    }
    public String getCurrentUserEmail() {
        return currentUserEmail;
    }
    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
        notifyObservers(this.status);
    }
    public void notifyObservers(String previousStatus) {
        for (ChargingStationObserver observer : observers) {
            observer.update(this, previousStatus);
}
    }
};
