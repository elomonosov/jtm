package ru.elomonosov.entytyhandler;

import ru.elomonosov.configuration.ConfigurationException;
import ru.elomonosov.service.Jtm;

/**
 * Created by n dd on 29.05.2015.
 */
public class ParametrizedDomainHandler extends DefaultDomainHandler {

    private String parameter;

    public ParametrizedDomainHandler(Jtm jtm, String parameter) throws ConfigurationException {
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
