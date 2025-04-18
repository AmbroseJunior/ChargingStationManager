package dao;

import jakarta.ejb.Local;
import vao.Provider;

import java.util.List;

@Local
public interface ProviderDAOLocal {
    void saveProvider(Provider provider);
    Provider findByName(String name);
    List<Provider> findAllProviders();
    void updateProvider(Provider provider);
    void deleteProvider(String name);
}
