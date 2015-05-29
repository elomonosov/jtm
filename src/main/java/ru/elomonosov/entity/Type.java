package ru.elomonosov.entity;

import ru.elomonosov.configuration.Configuration;
import ru.elomonosov.configuration.ConfigurationException;
import ru.elomonosov.configuration.DefaultConfiguration;
import ru.elomonosov.entytyhandler.TypeHandler;
import ru.elomonosov.service.Jtm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by n dd on 24.05.2015.
 */
public final class Type {

    private final Jtm jtm;

    private final int id;

    private final String name;

    private final Domain domain;

    private final List<Attribute> invariableAttributes;

    private final List<Attribute> variableAttributes;

    private final Map<Attribute, Boolean> nullableAttributes;

    private final Map<Attribute, Boolean> uniqueAttributes;

    private final Map<Attribute, Attribute> linksToAttribute;

    public Type(Jtm jtm, int id, String name, Domain domain, List<Attribute> invariableAttributes, List<Attribute> variableAttributes,
                Map<Attribute, Boolean> nullableAttributes, Map<Attribute, Boolean> uniqueAttributes, Map<Attribute, Attribute> linksToAttribute) {
        this.jtm = jtm;
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.invariableAttributes = invariableAttributes;
        this.variableAttributes = variableAttributes;
        this.nullableAttributes = nullableAttributes;
        this.uniqueAttributes = uniqueAttributes;
        this.linksToAttribute = linksToAttribute;
    }

    public Jtm getJtm() {return jtm; }

    public int getId() {return id; }

    public String getName() {
        return name;
    }

    public Domain getDomain() { return domain; }

    List<Attribute> getInvariableAttributes() {
        return invariableAttributes;
    }

    List<Attribute> getVariableAttributes() {
        return variableAttributes;
    }

    public List<Attribute> getAttributes() {
        List<Attribute> result = new ArrayList<Attribute>();
        result.addAll(invariableAttributes);
        result.addAll(variableAttributes);
        return result;
    }

    public Attribute getLinkedAttribute(Attribute attribute) {
        return linksToAttribute.get(attribute);
    }

    public boolean isAttributeNullable(Attribute attribute) {
        return nullableAttributes.containsKey(attribute);
    }

    public boolean isAttributeUnique(Attribute attribute) {
        return uniqueAttributes.containsKey(attribute);
    }

    public static class Builder {

        private Jtm jtm;

        private int id;

        private String name;

        private Domain domain;

        private List<Attribute> invariableAttributes;

        private List<Attribute> variableAttributes;

        private Map<Attribute, Boolean> nullableAttributes;

        private Map<Attribute, Boolean> uniqueAttributes;

        private Map<Attribute, Attribute> linksToAttribute;

        public Builder setJtm(Jtm jtm) {
            this.jtm = jtm;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDomain(Domain domain) {
            this.domain = domain;
            return this;
        }

        public Builder setInvariableAttributes(List<Attribute> invariableAttributes) {
            this.invariableAttributes = invariableAttributes;
            return this;
        }

        public Builder setVariableAttributes(List<Attribute> variableAttributes) {
            this.variableAttributes = variableAttributes;
            return this;
        }

        public Builder setNullableAttributes(Map<Attribute, Boolean> nullableAttributes) {
            this.nullableAttributes = nullableAttributes;
            return this;
        }

        public Builder setUniqueAttributes(Map<Attribute, Boolean> uniqueAttributes) {
            this.uniqueAttributes = uniqueAttributes;
            return this;
        }

        public Builder setLinksToAttribute(Map<Attribute, Attribute> linksToAttribute) {
            this.linksToAttribute = linksToAttribute;
            return this;
        }

        public Type build() {
            return new Type(jtm, id, name, domain, invariableAttributes, variableAttributes, nullableAttributes, uniqueAttributes, linksToAttribute);
        }

    }
}
