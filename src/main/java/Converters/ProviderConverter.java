package Converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import Service.ProviderService;
import vao.Provider;

import java.util.UUID;

@FacesConverter(value = "providerConverter", forClass = Provider.class)
public class ProviderConverter implements Converter {
    private final ProviderService providerService = ProviderService.getInstance();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            UUID id = UUID.fromString(value);
            return providerService.getProviderById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Provider) {
            return ((Provider) value).getId().toString();
        }
        return "";
    }
}