package vao;
import Observers.ChargingStationObserver;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "charging_station")
public class ChargingStation  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    private String location;
    private String status;
    private double chargingSpeed;
    private String region;
    private String currentUserEmail;
    private boolean isAvailable;
    private String connectorType;
    private double pricePerKWh;

    public ChargingStation() {}


    public ChargingStation(Provider provider, String location, String status, double chargingSpeed, String region,
                           double pricePerKWh, String connectorType, boolean isAvailable) {
        this.provider = provider;
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
    public Provider getProvider() { return provider; }
    public void setProvider(Provider provider) {
        this.provider = provider;
        if (provider != null && !provider.getChargingStations().contains(this)) {
            provider.addChargingStation(this);
        }
    }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStatus() { return status; }

    public void setStatus(String newStatus) {
        String previousStatus = this.status;
        this.status = newStatus;
    }
    public double getChargingSpeed() { return chargingSpeed; }
    public void setChargingSpeed(double chargingSpeed) { this.chargingSpeed = chargingSpeed; }
    public String getRegion() { return region;}
    public void setRegion(String region) { this.region = region; }
    public boolean isActive() {
        return status.equalsIgnoreCase("OPERATIONAL");
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

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }
    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
    }
}
