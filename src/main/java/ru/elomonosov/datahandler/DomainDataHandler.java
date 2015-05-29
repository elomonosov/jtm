package ru.elomonosov.datahandler;

import ru.elomonosov.entity.Type;
import ru.elomonosov.entytyhandler.HandlerException;

import java.util.List;

/**
 * Created by n dd on 27.05.2015.
 */
public interface DomainDataHandler {

    default String getName() throws DataHandlerException {
        return getClass().getName();
    }

    List<Type> getAllTypes() throws DataHandlerException;

    void deleteType(Type type) throws DataHandlerException;

    void createType(Type type) throws DataHandlerException;
}
