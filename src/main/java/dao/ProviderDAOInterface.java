package dao;

import vao.Provider;

import java.util.List;
import java.util.UUID;

public interface ProviderDAOInterface {
    Provider addProvider(Provider provider);
    Provider getProviderById(UUID id);
    List<Provider> getAllProviders();
    void updateProvider(Provider provider);
    void deleteProvider(UUID id);

}
