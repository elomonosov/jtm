package ru.elomonosov.datatype;

/**
 * Created by n dd on 27.05.2015.
 */
public interface DataType {

    String getName();

    int maxLength();

    boolean isLink();

    boolean correctionCheck(Value value);
}
