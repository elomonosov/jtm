package ru.elomonosov.configuration;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import ru.elomonosov.entity.Domain;
import ru.elomonosov.entity.Type;
import ru.elomonosov.datahandler.DomainDataHandler;
import ru.elomonosov.datahandler.TypeDataHandler;
import ru.elomonosov.entytyhandler.DomainHandler;
import ru.elomonosov.entytyhandler.TypeHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by n dd on 26.05.2015.
 */
public class DefaultConfiguration implements Configuration {

    {
        try {
            loadConfigIfNecessary();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static final DefaultConfiguration INSTANCE = new DefaultConfiguration();

    private static BiMap<Domain, DomainHandler> domainHandlerBiMap = null;

    private static BiMap<Type, TypeHandler> typeHandlerBiMap = null;

    private static BiMap<TypeHandler, TypeDataHandler> typeDataHandlerBiMap = null;

    private static BiMap<DomainHandler, DomainDataHandler> domainDataHandlerBiMap = null;

    private DefaultConfiguration(){

    }

    public static DefaultConfiguration getInstance() {

        return INSTANCE;
    }

    private void loadConfigIfNecessary() throws ConfigurationException{
            loadDomainHandlers();
            loadTypeHandlers();
            loadTypeDataHandlers();
            loadDomainDataHandlers();
    }

    @Override
    public void loadDomainHandlers() throws ConfigurationException{
        if (domainHandlerBiMap == null) {
            Map<Domain, DomainHandler> domainHandlerMap = new HashMap<>(); // TODO доделать

            domainHandlerBiMap = HashBiMap.create(domainHandlerMap);
        }
    }

    @Override
    public void loadTypeHandlers() throws ConfigurationException{
        if (typeHandlerBiMap == null) {

            Map<Type, TypeHandler> typeHandlerMap = new HashMap<>(); // TODO доделать

            typeHandlerBiMap = HashBiMap.create(typeHandlerMap);
        }
    }

    @Override
    public void loadTypeDataHandlers() throws ConfigurationException{
        if (typeDataHandlerBiMap == null) {
            Map<TypeHandler, TypeDataHandler> typeDataHandlerMap = new HashMap<>(); // TODO доделать

            typeDataHandlerBiMap = HashBiMap.create(typeDataHandlerMap);
        }
    }

    @Override
    public void loadDomainDataHandlers() throws ConfigurationException{
        if (domainDataHandlerBiMap == null) {
            Map<DomainHandler, DomainDataHandler> domainDataHandlerMap = new HashMap<>(); // TODO доделать

            domainDataHandlerBiMap = HashBiMap.create(domainDataHandlerMap);
        }
    }

    @Override
    public TypeHandler getHandler(Type type) throws ConfigurationException {
        TypeHandler result = typeHandlerBiMap.get(type);

        if (result == null) {
            throw new ConfigurationException("Handler for type '" + type.getName() + "' not found.");
        }

        return result;
    }

    @Override
    public DomainHandler getHandler(Domain domain) throws ConfigurationException {
        DomainHandler result = domainHandlerBiMap.get(domain);

        if (result == null) {
            throw new ConfigurationException("Handler for domain '" + domain.getName() + "' not found.");
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

        TypeDataHandler result = typeDataHandlerBiMap.get(typeHandler);

        if (result == null) {
            throw new ConfigurationException("Data handler for handler '" + typeHandler.toString() + "' not found.");
        }
        return result;
    }

    @Override
    public DomainDataHandler getDataHandler(DomainHandler domainHandler) throws ConfigurationException {

        DomainDataHandler result = domainDataHandlerBiMap.get(domainHandler);

        if (result == null) {
            throw new ConfigurationException("Data handler for handler '" + domainHandler.toString() + "' not found.");
        }
        return result;
    }
}
