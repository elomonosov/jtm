package ru.elomonosov.datatype;

import ru.elomonosov.entity.Version;

/**
 * Created by n dd on 28.05.2015.
 */
public class LinkIntValue implements Value {

    private int value;

    @Override
    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public DataType getDataType() {
        return LinkIntDataType.getInstance();
    }

    @Override
    public int getLength() {
        return String.valueOf(value).length();
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = ((Version) value).getId();
    }

    @Override
    public Version getLink() {
        return null;
    }

    public void setValue(Version value) {
        this.value = value.getId();
    }
}
