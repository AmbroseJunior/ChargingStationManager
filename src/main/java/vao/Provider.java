package vao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "provider")
public class Provider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;


    private String contactInfo;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChargingStation> chargingStations = new ArrayList<>();


    public Provider() {}

    public Provider(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void addChargingStation(ChargingStation chargingStation) {
        chargingStations.add(chargingStation);
        chargingStation.setProvider(this);
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
