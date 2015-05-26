package ru.elomonosov.entity;

import ru.elomonosov.handler.AbstractDomainHandler;

import java.util.List;

/**
 * Created by n dd on 24.05.2015.
 */
public class Domain {

    private String name;

    private List<Type> types;

    private AbstractDomainHandler domainHandler;

    public AbstractDomainHandler getDomainHandler() {
        return domainHandler;
    }

    public List<Type> getTypes() {

        return types;
    }

    public String getName() {

        return name;
    }
}
