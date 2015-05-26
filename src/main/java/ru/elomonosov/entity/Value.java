package ru.elomonosov.entity;

import java.util.Date;

/**
 * Created by n dd on 24.05.2015.
 */
public class Value<T> {

    private Version version;

    private Attribute attribute;

    Object value;

    public Integer getAsInteger() throws ClassCastException{

        return (Integer) value;
    }

    public String getAsString() throws ClassCastException{

        return (String) value;
    }

    public Double getAsDouble() throws ClassCastException{

        return (Double) value;
    }

    public Version getAsVersion() throws ClassCastException{

        return (Version) value;
    }

    public Date getAsDate() throws ClassCastException{

        return (Date) value;
    }

    public boolean isEmpty() {

        return (value.equals(null)||value.equals(""));
    }

    public Version getVersion() {
        return version;
    }
}
