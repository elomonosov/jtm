package ru.elomonosov.entytyhandler;

import ru.elomonosov.entity.Domain;
import ru.elomonosov.entity.Type;
import ru.elomonosov.configuration.Configuration;
import ru.elomonosov.configuration.ConfigurationException;
import ru.elomonosov.configuration.DefaultConfiguration;
import ru.elomonosov.datahandler.DataHandlerException;
import ru.elomonosov.datahandler.DomainDataHandler;
import ru.elomonosov.service.Jtm;

import java.util.List;

/**
 * Created by n dd on 27.05.2015.
 */
public class DefaultDomainHandler implements DomainHandler {

    private final Jtm jtm;

    protected DefaultDomainHandler(Jtm jtm) throws ConfigurationException {

        this.jtm = jtm;
    }

    @Override
    public Domain getDomain() throws HandlerException {
        try {
            return jtm.getConfiguration().getDomain(this);
        } catch (ConfigurationException e) {
            HandlerException exception = new HandlerException("Cannot get domain.");
            exception.initCause(e);
            throw exception;
        }
    }

    @Override
    public DomainDataHandler getDataHandler() throws HandlerException {
        try {
            return jtm.getConfiguration().getDataHandler(this);
        } catch (ConfigurationException e) {
            HandlerException exception = new HandlerException("Cannot get data handler.");
            exception.initCause(e);
            throw exception;
        }
    }

    @Override
    public void createType(Type type) throws HandlerException {
        uniqueCheck(type);
        try {
            getDataHandler().createType(type);
        } catch (DataHandlerException e) {
            HandlerException exception = new HandlerException("Cannot create type '" + type.getName() + "' in domain '" + getDomain().getName() + "'");
            exception.initCause(e);
            throw exception;
        }
    }

    @Override
    public void deleteType(Type type) throws HandlerException {
        try {
            getDataHandler().deleteType(type);
        } catch (DataHandlerException e) {
            HandlerException exception = new HandlerException("Cannot delete type '" + type.getName() + "' in domain '" + getDomain().getName() + "'");
            exception.initCause(e);
            throw exception;
        }
    }

    @Override
    public List<Type> getAllTypes() throws HandlerException {
        List<Type> result;
        try {
            result = getDataHandler().getAllTypes();
        } catch (DataHandlerException e) {
            HandlerException exception = new HandlerException("Cannot get all types in domain '" + getDomain().getName() + "'");
            exception.initCause(e);
            throw exception;
        }
        return result;
    }

    private void uniqueCheck(Type type) throws HandlerException {
        List<Type> types = getAllTypes();

        for (Type foundedType : types) {
            if (foundedType.equals(type)) {
                throw new HandlerException("Type '" + type.getName() + "' already exists in domain '" + foundedType.getDomain().getName() + "'");
            }
        }
    }
}
