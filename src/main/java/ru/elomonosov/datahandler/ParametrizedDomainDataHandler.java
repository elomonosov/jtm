package ru.elomonosov.datahandler;

/**
 * Created by n dd on 29.05.2015.
 */
public class ParametrizedDomainDataHandler extends DefaultDomainDataHandler {

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    private String parameter;

    public ParametrizedDomainDataHandler(String parameter) {
        this.parameter = parameter;
    }
}
