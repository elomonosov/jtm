package ru.elomonosov.entity;

import ru.elomonosov.handler.AbstractAttributeHandler;
import ru.elomonosov.handler.AbstractRecordHandler;
import ru.elomonosov.handler.AbstractTypeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by n dd on 24.05.2015.
 */
public class Type {

    private int id;

    private String name;

    private Domain domain;

    private List<Attribute> invariableAttributes;

    private List<Attribute> variableAttributes;

    private Map<Attribute, Boolean> nullableAttributes;

    private Map<Attribute, Boolean> uniqueAttributes;

    private Map<Attribute, Attribute> linksToAttribute;

    private AbstractTypeHandler typeHandler;

    private AbstractRecordHandler recordHandler;

    private Map<Attribute, AbstractAttributeHandler> attributeHandlers;

    public AbstractTypeHandler getTypeHandler() {
        return typeHandler;
    }

    public Domain getDomain() {
        return domain;
    }


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

    public Map<Attribute, AbstractAttributeHandler> getAttributeHandlers(Type type) {
        return null;
    }

    public Attribute getLinkedAttribute(Attribute attribute) {
        return linksToAttribute.get(attribute);
    }

    public boolean isAttributeNullable(Attribute attribute) {
       return  nullableAttributes.containsKey(attribute);
    }

    public boolean isAttributeUnique(Attribute attribute) {
        return  uniqueAttributes.containsKey(attribute);
    }

    public List<Record> getRecords() {
        return recordHandler.getRecords(); // TODO сделать вызов хэндлера
    }

    public List<Version> getAllVersions() {
        List<Record> records = getRecords();
        List<Version> result = new ArrayList<Version>();

        for (Record record : records) {
            result.addAll(record.getAllVersions());
        }
        return result;
    }

    public List<Version> findVersions(Attribute attribute, Value value) { // TODO переопределять при наследовании
        List<Record> records = getRecords();
        List<Version> result = new ArrayList<Version>();

        for (Record record : records) {
            List<Version> recordVersions = record.getAllVersions();
            for (Version version : recordVersions) {
                if (version.getValue(attribute) == value) {
                    result.add(version);
                }
            }
        }
        return result;
    }

    public Version findVersion(int versionId) {
        List<Record> records = getRecords();
        for (Record record : records) {
            List<Version> versions = record.getAllVersions();
            for (Version version : versions) {
                if (version.getId() == versionId) {
                    return version;
                }
            }
        }
        return null;
    }
}
