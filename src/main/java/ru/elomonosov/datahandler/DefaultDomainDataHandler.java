package ru.elomonosov.datahandler;

import ru.elomonosov.entity.Type;

import java.util.List;

/**
 * Created by n dd on 27.05.2015.
 */
public class DefaultDomainDataHandler implements DomainDataHandler {
    @Override
    public List<Type> getAllTypes() throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteType(Type type) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createType(Type type) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }
}
