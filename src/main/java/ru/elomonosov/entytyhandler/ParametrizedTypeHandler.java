package ru.elomonosov.entytyhandler;

import ru.elomonosov.configuration.ConfigurationException;
import ru.elomonosov.service.Jtm;

/**
 * Created by n dd on 28.05.2015.
 */
public class ParametrizedTypeHandler extends DefaultTypeHandler {

    private String parameter;

    public ParametrizedTypeHandler(Jtm jtm, String parameter) throws ConfigurationException {
        super(jtm);
        this.parameter = parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}