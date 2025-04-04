package beans;

import dao.ChargingStationDAO;
import dao.ProviderDAO;
import dao.UserDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import Service.ChargingStationService;
import Service.ProviderService;
import Service.UserService;
import vao.ChargingStation;
import vao.Provider;
import vao.User;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class AppBean implements Serializable {
    @Inject
    private ProviderService providerService;
    @Inject
    private ChargingStationService chargingStationService;
    @Inject
    private UserService userService;

    // Provider fields
    private String providerName;
    private String providerContactInfo;
    private Provider selectedProvider;

    // Charging Station fields
    private Provider stationProvider;
    private String stationLocation;
    private String stationStatus;
    private double stationChargingSpeed;
    private String stationRegion;
    private double stationPricePerKWh;
    private String stationConnectorType;
    private boolean stationIsAvailable;
    private ChargingStation selectedStation;

    // User fields
    private String userName;
    private String userEmail;
    private double userAccountBalance;
    private String userCarType;
    private User selectedUser;

    @PostConstruct
    public void init() {
        System.out.println("AppBean initialized for session");
        // Force initial data refresh if needed
        getAllProviders();
        getAllChargingStations();
        getAllUsers();
    }

    // Provider methods
    public String saveProvider() {
        try {
            if (selectedProvider == null) {
                Provider provider = new Provider(UUID.randomUUID(), providerName, providerContactInfo);
                providerService.addProvider(provider);
                System.out.println("Added new provider: " + provider.getName());
            } else {
                String oldName = selectedProvider.getName();
                selectedProvider.setName(providerName);
                selectedProvider.setContactInfo(providerContactInfo);
                providerService.updateProvider(selectedProvider);
                System.out.println("Updated provider: " + oldName + " to " + providerName);
                selectedProvider = null;
            }
            clearProviderFields();
            return "/providers.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public String prepareEditProvider(Provider provider) {
        this.selectedProvider = provider;
        this.providerName = provider.getName();
        this.providerContactInfo = provider.getContactInfo();
        System.out.println("Prepared to edit provider: " + provider.getName());
        return null;
    }

    public String deleteProvider(Provider provider) {
        providerService.deleteProvider(provider.getId());
        return "/providers.xhtml?faces-redirect=true";
    }

    public List<Provider> getAllProviders() {
        List<Provider> providers = providerService.getAllProviders();
        if (providers.isEmpty()) {
            System.out.println("Warning: No providers available in getAllProviders()");
        }
        return providers;
    }
    private void clearProviderFields() {
        providerName = null;
        providerContactInfo = null;
        selectedProvider = null;
        System.out.println("Provider fields cleared");
    }

    // Charging Station methods
    public String saveChargingStation() {
        try {
            if (stationProvider == null) {
                throw new IllegalArgumentException("Provider is required");
            }
            if (stationStatus == null) {
                throw new IllegalArgumentException("Status is required");
            }
            if (selectedStation == null) {
                // Adding a new station
                chargingStationService.createStation(
                        stationProvider, stationLocation, stationStatus, stationChargingSpeed,
                        stationRegion, stationPricePerKWh, stationConnectorType, stationIsAvailable
                );
                System.out.println("Added new station at: " + stationLocation);
            } else {

                selectedStation.setProvider(stationProvider);
                selectedStation.setLocation(stationLocation);
                selectedStation.setStatus(stationStatus);
                selectedStation.setChargingSpeed(stationChargingSpeed);
                selectedStation.setRegion(stationRegion);
                selectedStation.setPricePerKWh(stationPricePerKWh);
                selectedStation.setConnectorType(stationConnectorType);
                selectedStation.setAvailable(stationIsAvailable);
                chargingStationService.updateStation(selectedStation);
                System.out.println("Updated station at: " + stationLocation);
                selectedStation = null;
            }
            clearStationFields();
            return "/chargingStations.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }
    public String prepareEditChargingStation(ChargingStation station) {
        this.selectedStation = station;
        this.stationProvider = station.getProvider();
        this.stationLocation = station.getLocation();
        this.stationStatus = station.getStatus();
        this.stationChargingSpeed = station.getChargingSpeed();
        this.stationRegion = station.getRegion();
        this.stationPricePerKWh = station.getPricePerKWh();
        this.stationConnectorType = station.getConnectorType();
        this.stationIsAvailable = station.isAvailable();
        System.out.println("Prepared to edit charging station: " + station.getLocation());
        return null;
    }

    public String deleteChargingStation(ChargingStation station) {
        chargingStationService.deleteStation(station.getId());
        return "/chargingStations.xhtml?faces-redirect=true";
    }

    public List<ChargingStation> getAllChargingStations() {
        List<ChargingStation> stations = chargingStationService.getAllChargingStations();
        System.out.println("AppBean.getAllChargingStations() returned " + (stations != null ? stations.size() : "null") + " stations: " + stations);
        if (stations == null || stations.isEmpty()) {
            System.out.println("Warning: No charging stations available in getAllChargingStations()");
        }
        return stations;
    }

    private void clearStationFields() {
        stationProvider = null;
        stationLocation = null;
        stationStatus = null;
        stationChargingSpeed = 0.0;
        stationRegion = null;
        stationPricePerKWh = 0.0;
        stationConnectorType = null;
        stationIsAvailable = false;
        selectedUser = null;
        System.out.println("Charging station fields cleared");
    }
    // User methods
    public String saveUser() {
        try {
            if (selectedUser == null) {
                User user = new User(UUID.randomUUID(), userName, userEmail, userAccountBalance, userCarType);
                userService.addUser(user);
                System.out.println("Added new user: " + userName);
            } else {
                String oldName = selectedUser.getName();
                selectedUser.setName(userName);
                selectedUser.setEmail(userEmail);
                selectedUser.setAccountBalance(userAccountBalance);
                selectedUser.setCarType(userCarType);
                userService.updateUser(selectedUser);
                System.out.println("Updated user: " + oldName + " to " + userName);
                selectedUser = null;
            }
            clearUserFields();
            return "/users.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public String prepareEditUser(User user) {
        this.selectedUser = user;
        this.userName = user.getName();
        this.userEmail = user.getEmail();
        this.userAccountBalance = user.getAccountBalance();
        this.userCarType = user.getCarType();
        System.out.println("Prepared to edit user: " + user.getName());
        return null;
    }

    public String deleteUser(User user) {
        userService.deleteUser(user.getName());
        return "/users.xhtml?faces-redirect=true";
    }
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("getAllUsers() returned " + users.size() + " users: " + users);
        if (users.isEmpty()) {
            System.out.println("Warning: No users available in getAllUsers()");
        }
        return users;
    }

    private void clearUserFields() {
        userName = null;
        userEmail = null;
        userAccountBalance = 0.0;
        userCarType = null;
        selectedUser = null;
        System.out.println("User fields cleared");
    }

    // Updated listProvidersAndStations method
    public void listProvidersAndStations() {
        List<Provider> providers = getAllProviders();
        List<ChargingStation> stations = getAllChargingStations();
        List<User> users = getAllUsers();

        System.out.println("listProvidersAndStations: Providers - " + (providers != null ? providers.size() : "null"));
        System.out.println("listProvidersAndStations: Charging Stations - " + (stations != null ? stations.size() : "null"));
        System.out.println("listProvidersAndStations: Users - " + (users != null ? users.size() : "null"));

        // Print to console for debugging
        System.out.println("=== Providers ===");
        if (providers != null) {
            for (Provider provider : providers) {
                System.out.println(provider.getName() + " | " + provider.getContactInfo());
            }
        }
        System.out.println("\n=== Charging Stations ===");
        if (stations != null) {
            for (ChargingStation station : stations) {
                System.out.println(station.getId() + " | " + station.getLocation() + " | " + station.getStatus());
            }
        }
        System.out.println("\n=== Users ===");
        if (users != null) {
            for (User user : users) {
                System.out.println(user.getName() + " | " + user.getEmail());
            }
        }
    }

    // Getters and Setters
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderContactInfo() {
        return providerContactInfo;
    }

    public void setProviderContactInfo(String providerContactInfo) {
        this.providerContactInfo = providerContactInfo;
    }

    public Provider getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(Provider selectedProvider) {
        this.selectedProvider = selectedProvider;
    }

    public Provider getStationProvider() {
        return stationProvider;
    }

    public void setStationProvider(Provider stationProvider) {
        this.stationProvider = stationProvider;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public String getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(String stationStatus) {
        this.stationStatus = stationStatus;
    }

    public double getStationChargingSpeed() {
        return stationChargingSpeed;
    }

    public void setStationChargingSpeed(double stationChargingSpeed) {
        this.stationChargingSpeed = stationChargingSpeed;
    }

    public String getStationRegion() {
        return stationRegion;
    }

    public void setStationRegion(String stationRegion) {
        this.stationRegion = stationRegion;
    }

    public double getStationPricePerKWh() {
        return stationPricePerKWh;
    }

    public void setStationPricePerKWh(double stationPricePerKWh) {
        this.stationPricePerKWh = stationPricePerKWh;
    }

    public String getStationConnectorType() {
        return stationConnectorType;
    }

    public void setStationConnectorType(String stationConnectorType) {
        this.stationConnectorType = stationConnectorType;
    }

    public boolean isStationIsAvailable() {
        return stationIsAvailable;
    }

    public void setStationIsAvailable(boolean stationIsAvailable) {
        this.stationIsAvailable = stationIsAvailable;
    }

    public ChargingStation getSelectedStation() {
        return selectedStation;
    }

    public void setSelectedStation(ChargingStation selectedStation) {
        this.selectedStation = selectedStation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getUserAccountBalance() {
        return userAccountBalance;
    }

    public void setUserAccountBalance(double userAccountBalance) {
        this.userAccountBalance = userAccountBalance;
    }

    public String getUserCarType() {
        return userCarType;
    }

    public void setUserCarType(String userCarType) {
        this.userCarType = userCarType;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}