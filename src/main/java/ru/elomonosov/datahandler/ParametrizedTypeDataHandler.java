package ru.elomonosov.datahandler;

/**
 * Created by n dd on 28.05.2015.
 */
public class ParametrizedTypeDataHandler extends DefaulTypeDataHandler {

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    private String parameter;

   public ParametrizedTypeDataHandler(String parameter) {
       this.parameter = parameter;
   }
}
