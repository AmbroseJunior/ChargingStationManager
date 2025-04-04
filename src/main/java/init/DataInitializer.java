package init;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import Service.ChargingStationService;
import Service.ProviderService;
import Service.UserService;
import vao.Provider;

import java.util.UUID;

@Named
@ApplicationScoped
public class DataInitializer {
    @Inject
    private ProviderService providerService;
    @Inject
    private ChargingStationService chargingStationService;
    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        System.out.println("DataInitializer: Starting initialization at " + new java.util.Date());
        try {
            if (providerService == null || chargingStationService == null || userService == null) {
                System.err.println("DataInitializer: One or more services are null!");
                return;
            }

            // Initialize Providers
            Provider provider = new Provider(UUID.randomUUID(), "Initial Provider", "initial@provider.com");
            providerService.addProvider(provider);
            System.out.println("DataInitializer: Added provider - " + provider.getName());

            // Initialize Charging Stations
            chargingStationService.createStation(
                    provider,
                    "Initial Station",
                    "OPERATIONAL",
                    50.0,
                    "South",
                    0.7,
                    "Type3",
                    true
            );
            System.out.println("DataInitializer: Added charging station - Initial Station");

            // Initialize Users
            userService.registerUser("Initial User", "initial@user.com", 200.0, "Type4");
            System.out.println("DataInitializer: Added user - Initial User");
        } catch (Exception e) {
            System.err.println("DataInitializer: Error during initialization - " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("DataInitializer: Initialization complete at " + new java.util.Date());
    }
}