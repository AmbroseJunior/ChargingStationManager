package beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import Service.ChargingStationService;
import Service.ProviderService;
import Service.UserService;
import jakarta.validation.ConstraintViolationException;
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
    private String stationProviderId;
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
        refreshAllData();
    }

    private void refreshAllData() {
        getAllProviders();
        getAllChargingStations();
        getAllUsers();
    }

    // Provider methods
    public String saveProvider() {
        try {
            if (selectedProvider == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Select a provider"));
                return null;
            } else {
                selectedProvider.setName(providerName);
                selectedProvider.setContactInfo(providerContactInfo);
                providerService.updateProvider(selectedProvider);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Provider updated successfully!"));
                selectedProvider = null;
            }
            clearProviderFields();
            refreshAllData();
            return "/providers.xhtml?faces-redirect=true";
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public String prepareEditProvider(Provider provider) {
        this.selectedProvider = provider;
        this.providerName = provider.getName();
        this.providerContactInfo = provider.getContactInfo();
        return null;
    }

    public String deleteProvider(Provider provider) {
        providerService.deleteProvider(provider.getId());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Provider deleted successfully!"));
        refreshAllData();
        return "/providers.xhtml?faces-redirect=true";
    }

    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }

    // Charging Station methods
    public String saveChargingStation() {
        try {
            if (stationProviderId == null || stationProviderId.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Select a provider"));
                return null;
            }

            Provider provider = providerService.getProviderById(UUID.fromString(stationProviderId));
            if (provider == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Provider not found"));
                return null;
            }

            if (selectedStation == null) {
                ChargingStation station = new ChargingStation();
                station.setProvider(provider);
                station.setLocation(stationLocation);
                station.setStatus(stationStatus);
                station.setChargingSpeed(stationChargingSpeed);
                station.setRegion(stationRegion);
                station.setPricePerKWh(stationPricePerKWh);
                station.setConnectorType(stationConnectorType);
                station.setAvailable(stationIsAvailable);

                chargingStationService.createStation(station);
            } else {
                selectedStation.setProvider(provider);
                selectedStation.setLocation(stationLocation);
                selectedStation.setStatus(stationStatus);
                selectedStation.setChargingSpeed(stationChargingSpeed);
                selectedStation.setRegion(stationRegion);
                selectedStation.setPricePerKWh(stationPricePerKWh);
                selectedStation.setConnectorType(stationConnectorType);
                selectedStation.setAvailable(stationIsAvailable);

                chargingStationService.updateStation(selectedStation);
            }

            clearStationFields();
            refreshAllData();
            return "/chargingStations.xhtml?faces-redirect=true";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }
    public String prepareEditChargingStation(ChargingStation station) {
        this.selectedStation = station;
        this.stationProviderId = String.valueOf(station.getProvider().getId());
        this.stationLocation = station.getLocation();
        this.stationStatus = station.getStatus();
        this.stationChargingSpeed = station.getChargingSpeed();
        this.stationRegion = station.getRegion();
        this.stationPricePerKWh = station.getPricePerKWh();
        this.stationConnectorType = station.getConnectorType();
        this.stationIsAvailable = station.isAvailable();
        return null;
    }

    public String deleteChargingStation(ChargingStation station) {
        try {
            chargingStationService.deleteStation(station.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Charging station deleted successfully!"));
            refreshAllData();
            return "/chargingStations.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public List<ChargingStation> getAllChargingStations() {
        return chargingStationService.getAllChargingStations();
    }
    // User methods
    public String saveUser() {
        try {
            if (selectedUser == null) {
                User user = new User(userName, userEmail, userAccountBalance, userCarType);
                userService.registerUser(user);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("User added successfully!"));
            } else {
                selectedUser.setName(userName);
                selectedUser.setEmail(userEmail);
                selectedUser.setAccountBalance(userAccountBalance);
                selectedUser.setCarType(userCarType);
                userService.updateUser(selectedUser);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("User updated successfully!"));
                selectedUser = null;
            }
            clearUserFields();
            refreshAllData();
            return "/users.xhtml?faces-redirect=true";
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    public String prepareEditUser(User user) {
        this.selectedUser = user;
        this.userName = user.getName();
        this.userEmail = user.getEmail();
        this.userAccountBalance = user.getAccountBalance();
        this.userCarType = user.getCarType();
        return null;
    }

    public String deleteUser(User user) {
        userService.deleteUser(user.getName());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("User deleted successfully!"));
        refreshAllData();
        return "/users.xhtml?faces-redirect=true";
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Utility methods
    private void handleException(Exception e) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        e.printStackTrace();
    }

    private void clearProviderFields() {
        providerName = null;
        providerContactInfo = null;
        selectedProvider = null;
    }

    private void clearStationFields() {
        stationProviderId = null;
        stationLocation = null;
        stationStatus = null;
        stationChargingSpeed = 0.0;
        stationRegion = null;
        stationPricePerKWh = 0.0;
        stationConnectorType = null;
        stationIsAvailable = false;
    }

    private void clearUserFields() {
        userName = null;
        userEmail = null;
        userAccountBalance = 0.0;
        userCarType = null;
        selectedUser = null;
    }

    // Getters and setters
    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }
    public String getProviderContactInfo() { return providerContactInfo; }
    public void setProviderContactInfo(String providerContactInfo) { this.providerContactInfo = providerContactInfo; }
    public Provider getSelectedProvider() { return selectedProvider; }
    public void setSelectedProvider(Provider selectedProvider) { this.selectedProvider = selectedProvider; }
    public String getStationProviderId() { return stationProviderId; }
    public void setStationProviderId(String stationProviderId) { this.stationProviderId = stationProviderId; }
    public String getStationLocation() { return stationLocation; }
    public void setStationLocation(String stationLocation) { this.stationLocation = stationLocation; }
    public String getStationStatus() { return stationStatus; }
    public void setStationStatus(String stationStatus) { this.stationStatus = stationStatus; }
    public double getStationChargingSpeed() { return stationChargingSpeed; }
    public void setStationChargingSpeed(double stationChargingSpeed) { this.stationChargingSpeed = stationChargingSpeed; }
    public String getStationRegion() { return stationRegion; }
    public void setStationRegion(String stationRegion) { this.stationRegion = stationRegion; }
    public double getStationPricePerKWh() { return stationPricePerKWh; }
    public void setStationPricePerKWh(double stationPricePerKWh) { this.stationPricePerKWh = stationPricePerKWh; }
    public String getStationConnectorType() { return stationConnectorType; }
    public void setStationConnectorType(String stationConnectorType) { this.stationConnectorType = stationConnectorType; }
    public boolean isStationIsAvailable() { return stationIsAvailable; }
    public void setStationIsAvailable(boolean stationIsAvailable) { this.stationIsAvailable = stationIsAvailable; }
    public ChargingStation getSelectedStation() { return selectedStation; }
    public void setSelectedStation(ChargingStation selectedStation) { this.selectedStation = selectedStation; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public double getUserAccountBalance() { return userAccountBalance; }
    public void setUserAccountBalance(double userAccountBalance) { this.userAccountBalance = userAccountBalance; }
    public String getUserCarType() { return userCarType; }
    public void setUserCarType(String userCarType) { this.userCarType = userCarType; }
    public User getSelectedUser() { return selectedUser; }
    public void setSelectedUser(User selectedUser) { this.selectedUser = selectedUser; }
}