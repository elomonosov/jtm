package ru.elomonosov.service;

import ru.elomonosov.configuration.Configuration;
import ru.elomonosov.configuration.DefaultConfiguration;

/**
 * Created by n dd on 28.05.2015.
 */
public class JtmFactory {

    public static final JtmFactory INSTANCE = new JtmFactory();

    public Jtm getJtm(Configuration configuration) {
        Jtm result = Jtm.getInstance();
        result.setConfiguration(configuration);
        return result;
    }

    public Jtm getJtm() {
        Jtm result = Jtm.getInstance();
        result.setConfiguration(DefaultConfiguration.getInstance());
        return result;
    }

    private JtmFactory() {

    }

    public static JtmFactory getInstance() {
        return INSTANCE;
    }
}
