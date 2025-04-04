package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import Service.UserService;
import Service.ProviderService;
import Service.ChargingStationService;
import vao.User;
import vao.Provider;
import vao.ChargingStation;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {
    private String name;
    private String email;
    private double accountBalance;
    private String carType;

    private final UserService userService = new UserService();
    private final ProviderService providerService = ProviderService.getInstance();
    private final ChargingStationService chargingStationService = ChargingStationService.getInstance();

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getAccountBalance() { return accountBalance; }
    public void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }
    public String getUserCarType() { return carType; }
    public void setUserCarType(String carType) { this.carType = carType; }

    public void saveUser() {
        User user = new User(UUID.randomUUID(), name, email, accountBalance, carType);
        userService.registerUser(name, email, accountBalance, carType);
        System.out.println("User saved: " + user);
        clearForm();
    }

    public void listProvidersAndStations() {
        List<Provider> providers = providerService.getAllProviders();
        System.out.println("\n=== Providers ===");
        providers.forEach(p -> System.out.println(p.getName() + " | " + p.getContactInfo()));

        List<ChargingStation> stations = chargingStationService.getAllChargingStations();
        System.out.println("\n=== Charging Stations ===");
        stations.forEach(s -> System.out.println(s.getId() + " | " + s.getLocation() + " | " + s.getStatus()));
    }

    private void clearForm() {
        name = "";
        email = "";
        accountBalance = 0.0;
        carType = "";
    }
}