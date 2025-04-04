package vao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Provider {
    private UUID id;
    private String name;
    private String contactInfo;
    private final List<ChargingStation> chargingStations;

    public Provider(UUID id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.chargingStations = new ArrayList<>();
    }

    public void addChargingStation(ChargingStation chargingStation) {
        chargingStations.add(chargingStation);
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<ChargingStation> getChargingStations() {
        return chargingStations;
    }

    public void ifPresentOrElse(ChargingStation chargingStation) {
        if (chargingStation != null) {
            System.out.println("\n=== Charging Station Details ===");
            System.out.println("ID: " + chargingStation.getId());
            System.out.println("Name: " + chargingStation.getProvider().getName());
            System.out.println("Location: " + chargingStation.getLocation());
            System.out.println("Status: " + chargingStation.getStatus());
            System.out.println("Speed: " + chargingStation.getChargingSpeed());
            System.out.println("Region: " + chargingStation.getRegion());
        } else {
            System.out.println("Charging station not found!");
        }
    }
    public Provider orElse(Object o) {
        return null;
    }
}
