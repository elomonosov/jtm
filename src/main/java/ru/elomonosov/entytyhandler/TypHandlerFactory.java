package ru.elomonosov.entytyhandler;

import ru.elomonosov.configuration.ConfigurationException;
import ru.elomonosov.service.Jtm;

/**
 * Created by n dd on 29.05.2015.
 */
public class TypHandlerFactory {


    public TypHandlerFactory() {
    }

    public TypeHandler getTypeHandler(Jtm jtm) throws ConfigurationException {

        return new DefaultTypeHandler(jtm);
    }

    public ParametrizedTypeHandler getTypeHandler(Jtm jtm, String parameter) throws ConfigurationException {
        return new ParametrizedTypeHandler(jtm, parameter);
    }
}
