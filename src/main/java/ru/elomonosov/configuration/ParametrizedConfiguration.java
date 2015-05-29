package ru.elomonosov.configuration;

import com.google.common.collect.BiMap;
import ru.elomonosov.datahandler.DomainDataHandler;
import ru.elomonosov.datahandler.ParametrizedDomainDataHandler;
import ru.elomonosov.datahandler.ParametrizedTypeDataHandler;
import ru.elomonosov.datahandler.TypeDataHandler;
import ru.elomonosov.entity.Domain;
import ru.elomonosov.entity.Type;
import ru.elomonosov.entytyhandler.DomainHandler;
import ru.elomonosov.entytyhandler.ParametrizedDomainHandler;
import ru.elomonosov.entytyhandler.ParametrizedTypeHandler;
import ru.elomonosov.entytyhandler.TypeHandler;

/**
 * Created by n dd on 29.05.2015.
 */
public class ParametrizedConfiguration implements Configuration {

    {
        try {
            loadConfigIfNecessary();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static final ParametrizedConfiguration INSTANCE = new ParametrizedConfiguration();

    private ParametrizedConfiguration() {

    }

    public static ParametrizedConfiguration getInstance() {
        return INSTANCE;
    }

    private static BiMap<Type, ParametrizedTypeDataHandler> typeDataHandlerBiMap;

    private static BiMap<Type, ParametrizedTypeHandler> typeHandlerBiMap;

    private static BiMap<Type, String> typeParameterBiMap;

    private static BiMap<Domain, ParametrizedDomainDataHandler> domainDataHandlerBiMap;

    private static BiMap<Domain, ParametrizedDomainHandler> domainHandlerBiMap;

    private static BiMap<Domain, String> domainParameterBiMap;

    private void loadConfigIfNecessary() throws ConfigurationException{
            loadDomainHandlers();
            loadTypeHandlers();
            loadTypeDataHandlers();
            loadDomainDataHandlers();
            loadTypeParameters();
            loadDomainParameters();
    }

    @Override
    public void loadDomainHandlers() throws ConfigurationException {

    }

    @Override
    public void loadTypeHandlers() throws ConfigurationException {

    }

    @Override
    public void loadTypeDataHandlers() throws ConfigurationException {

    }

    @Override
    public void loadDomainDataHandlers() throws ConfigurationException {

    }

    public void loadTypeParameters() throws ConfigurationException {

    }

    public void loadDomainParameters() throws ConfigurationException {

    }

    @Override
    public TypeHandler getHandler(Type type) throws ConfigurationException {

        ParametrizedTypeHandler result;
        try {
            result = typeHandlerBiMap.get(type);
            result.setParameter(typeParameterBiMap.get(type));
        } catch (NullPointerException e) {
            ConfigurationException exception = new ConfigurationException("Handler for type '" + type.getName() + "' not found.");
            exception.initCause(e);
            throw exception;
        }
        return result;
    }

    @Override
    public DomainHandler getHandler(Domain domain) throws ConfigurationException {
        ParametrizedDomainHandler result;
        try {
            result = domainHandlerBiMap.get(domain);
            result.setParameter(domainParameterBiMap.get(domain));
        } catch (NullPointerException e) {
            ConfigurationException exception = new ConfigurationException("Handler for domain '" + domain.getName() + "' not found.");
            exception.initCause(e);
            throw exception;
        }
        return result;
    }

    @Override
    public Type getType(TypeHandler typeHandler) throws ConfigurationException {
        Type result = typeHandlerBiMap.inverse().get(typeHandler);

        if (result == null) {
            throw new ConfigurationException("Type for handler '" + typeHandler.toString() + "' not found.");
        }
        return result;
    }

    @Override
    public Domain getDomain(DomainHandler domainHandler) throws ConfigurationException {
        Domain result = domainHandlerBiMap.inverse().get(domainHandler);

        if (result == null) {
            throw new ConfigurationException("Domain for handler '" + domainHandler.toString() + "' not found.");
        }
        return result;
    }

    @Override
    public TypeDataHandler getDataHandler(TypeHandler typeHandler) throws ConfigurationException {

        ParametrizedTypeDataHandler result;
        try {
            result = typeDataHandlerBiMap.get(typeHandler);
            result.setParameter(typeParameterBiMap.get(typeHandlerBiMap.inverse().get(typeHandler)));
        } catch (NullPointerException e) {
            ConfigurationException exception = new ConfigurationException("Data handler for handler '" + typeHandler.toString() + "' not found.");
            exception.initCause(e);
            throw exception;
        }
        return result;
    }

    @Override
    public DomainDataHandler getDataHandler(DomainHandler domainHandler) throws ConfigurationException {
        ParametrizedDomainDataHandler result;
        try {
            result = domainDataHandlerBiMap.get(domainHandler);
            result.setParameter(domainParameterBiMap.get(domainHandlerBiMap.inverse().get(domainHandler)));
        } catch (NullPointerException e) {
            ConfigurationException exception = new ConfigurationException("Data handler for handler '" + domainHandler.toString() + "' not found.");
            exception.initCause(e);
            throw exception;
        }
        return result;
    }
}
