package ru.elomonosov.handler;

import ru.elomonosov.entity.Attribute;
import ru.elomonosov.entity.Record;
import ru.elomonosov.entity.Type;

import java.util.List;

/**
 * Created by n dd on 25.05.2015.
 */
public abstract class AbstractTypeHandler {

    private AbstractRecordHandler recordHandler;

    private Type type;

    public AbstractRecordHandler getRecordHandler() {
        return recordHandler;
    }

    public List<Attribute> getAttributes(Type type) {
        return null;
    }

    public List<Attribute> getAttributesLinkedTo(Attribute attribute) {

        return null;
    }

    public void assignAttribute(Attribute attribute, Type type, boolean isNullable, boolean isUnique) {

    }

    public void assignAttribute(Attribute attribute, Type type, Attribute linkedTo, boolean isNullable, boolean isUnique) {

    }

    public void assignAttribute(Attribute attribute, Type type, Attribute linkedTo) {
        this.assignAttribute(attribute, type, linkedTo, true, true);
    }

    public void assignAttribute(Attribute attribute, Type type) {
        this.assignAttribute(attribute, type, true, true);
    }

}
