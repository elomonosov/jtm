package ru.elomonosov.entity;

import ru.elomonosov.datatype.Value;

import java.util.Date;
import java.util.Map;

/**
 * Created by n dd on 24.05.2015.
 */
public final class Version {

    private int id;

    private Date startDate;

    private Date endDate;

    private Map<Attribute, Value> values;

    private Record record;

    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return getEndDate();
    }

    public Value getValue(Attribute attribute) {
        return values.get(attribute);
    }

    public Record getRecord() {
        return record;
    }
}
