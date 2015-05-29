package ru.elomonosov.datatype;

/**
 * Created by n dd on 28.05.2015.
 */
public class StringDataType implements DataType {

    public static final StringDataType INSTANCE = new StringDataType();

    private StringDataType() {

    }

    public static StringDataType getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "String";
    }

    @Override
    public int maxLength() {
        return 2000;
    }

    @Override
    public boolean isLink() {
        return false;
    }

    @Override
    public boolean correctionCheck(Value value) {
        return (value.getLength() <= maxLength());
    }
}
