package dao;

import vao.Provider;

import java.util.*;

public class ProviderDAO implements ProviderDAOInterface {
    private static ProviderDAO instance;
    private final Map<UUID, Provider> providers = new HashMap<>();

    private ProviderDAO() {}

    public static ProviderDAO getInstance() {
        if (instance == null) {
            synchronized (ProviderDAO.class) {
                if (instance == null) {
                    instance = new ProviderDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public Provider addProvider(Provider provider) {
        if (provider != null && provider.getId() != null) {
        providers.put(provider.getId(), provider);
        }
        return provider;
    }

    @Override
    public Provider getProviderById(UUID id) {
        return providers.get(id);
    }

    @Override
    public List<Provider> getAllProviders() {
        return new ArrayList<>(providers.values());
    }

    @Override
    public void updateProvider(Provider provider) {
        if (provider != null && provider.getId() != null) {
            providers.put(provider.getId(), provider);
        }
    }

    @Override
    public void deleteProvider(UUID id) {
        providers.remove(id);
    }

}