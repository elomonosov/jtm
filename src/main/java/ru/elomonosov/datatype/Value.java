package ru.elomonosov.datatype;

import ru.elomonosov.entity.Version;

/**
 * Created by n dd on 24.05.2015.
 */
public interface Value<E extends Object> {

    boolean isEmpty();

    DataType getDataType();

    int getLength();

    E getValue();

    void setValue(E value);

    Version getLink();
}
