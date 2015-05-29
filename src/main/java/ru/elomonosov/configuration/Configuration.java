package ru.elomonosov.configuration;

import ru.elomonosov.entity.Domain;
import ru.elomonosov.entity.Type;
import ru.elomonosov.datahandler.DomainDataHandler;
import ru.elomonosov.datahandler.TypeDataHandler;
import ru.elomonosov.entytyhandler.DomainHandler;
import ru.elomonosov.entytyhandler.TypeHandler;

/**
 * Created by n dd on 27.05.2015.
 */
public interface Configuration {

    void loadDomainHandlers() throws ConfigurationException;

    void loadTypeHandlers() throws ConfigurationException;

    void loadTypeDataHandlers() throws ConfigurationException;

    void loadDomainDataHandlers() throws ConfigurationException;

    TypeHandler getHandler(Type type) throws ConfigurationException;

    DomainHandler getHandler(Domain domain) throws ConfigurationException;

    Type getType(TypeHandler typeHandler) throws ConfigurationException;

    Domain getDomain(DomainHandler domainHandler) throws ConfigurationException;

    TypeDataHandler getDataHandler(TypeHandler typeHandler) throws ConfigurationException;

    DomainDataHandler getDataHandler(DomainHandler domainHandler) throws ConfigurationException;
}
