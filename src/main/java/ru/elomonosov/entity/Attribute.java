package ru.elomonosov.entity;

/**
 * Created by n dd on 24.05.2015.
 */
public class Attribute {

    private int id;

    private Type type;

    public enum AttributeType {STRING, INTEGER, DATE, LINK}

    private AttributeType AttributeType;

    private String name;

    public AttributeType getAttributeType() {
        return AttributeType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
