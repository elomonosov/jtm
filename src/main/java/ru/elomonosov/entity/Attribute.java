package ru.elomonosov.entity;

import ru.elomonosov.datatype.DataType;

/**
 * Created by n dd on 24.05.2015.
 */
public final class Attribute<E extends DataType> {

    private int id;

    private Type type;

    private E DataType;

    private String name;

    public DataType getDataType() {
        return DataType;
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
