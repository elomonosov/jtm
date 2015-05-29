package ru.elomonosov.entytyhandler;

import ru.elomonosov.entity.Domain;
import ru.elomonosov.entity.Type;
import ru.elomonosov.datahandler.DataHandlerException;
import ru.elomonosov.datahandler.DomainDataHandler;

import java.util.List;

/**
 * Created by n dd on 24.05.2015.
 */
public interface DomainHandler {

    default String getName() throws HandlerException {
        return getClass().getName();
    }

    Domain getDomain() throws HandlerException;

    DomainDataHandler getDataHandler() throws HandlerException;

    void createType(Type type) throws HandlerException;

    void deleteType(Type type) throws HandlerException;

    List<Type> getAllTypes() throws HandlerException, DataHandlerException;

}
