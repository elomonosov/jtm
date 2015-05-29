package ru.elomonosov.datatype;

import ru.elomonosov.entity.Version;

/**
 * Created by n dd on 28.05.2015.
 */
public class StringValue implements Value {

    private String value;

    @Override
    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public DataType getDataType() {
        return StringDataType.getInstance();
    }

    @Override
    public int getLength() {
        return value.length();
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (String) value;
    }

    @Override
    public Version getLink() {
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
