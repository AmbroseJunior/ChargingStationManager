package Service;

import dao.ProviderDAO;
import vao.Provider;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProviderService implements Serializable {
    private static ProviderService instance;
    private final ProviderDAO providerDAO = ProviderDAO.getInstance();

    private ProviderService() {}

    public static synchronized ProviderService getInstance() {
        if (instance == null) {
            instance = new ProviderService();
        }
        return instance;
    }

    public void addProvider(Provider provider) {
        providerDAO.addProvider(provider);
    }

    public void updateProvider(Provider provider) {
        providerDAO.updateProvider(provider);
    }

    public void deleteProvider(UUID id) {
        providerDAO.deleteProvider(id);
    }

    public Optional<Provider> getProviderById(UUID id) {
        return Optional.ofNullable(providerDAO.getProviderById(id));
    }

    public List<Provider> getAllProviders() {
        return providerDAO.getAllProviders();
    }
}