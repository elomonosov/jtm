package ru.elomonosov.datatype;

/**
 * Created by n dd on 28.05.2015.
 */
public class LinkIntDataType implements DataType {

    public static final LinkIntDataType INSTANCE = new LinkIntDataType();

    private LinkIntDataType() {

    }

    public static LinkIntDataType getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Link int";
    }

    @Override
    public int maxLength() {
        return Integer.bitCount(Integer.MAX_VALUE);
    }

    @Override
    public boolean isLink() {
        return true;
    }

    @Override
    public boolean correctionCheck(Value value) {
        return (value.getLength() <= maxLength());
    }
}
