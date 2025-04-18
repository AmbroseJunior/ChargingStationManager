package Service;

import dao.ProviderDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import vao.Provider;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Stateless
public class ProviderService implements Serializable {
    @Inject
    private ProviderDAO providerDAO;

    public Provider addProvider(Provider provider) {
        return providerDAO.create(provider);
    }

    public Provider updateProvider(Provider provider) {
        return providerDAO.update(provider);
    }

    public void deleteProvider(UUID id) {
        providerDAO.delete(id);
    }

    public Provider getProviderById(UUID id) {
        return providerDAO.findById(id);
    }

    public Provider getProviderByName(String name) {
        return providerDAO.findByName(name);
    }

    public List<Provider> getAllProviders() {
        return providerDAO.findAll();
    }
}