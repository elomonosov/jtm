package ru.elomonosov.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by n dd on 24.05.2015.
 */
public interface Version {

    public int getId();

    public Date getStartDate();

    public Date getEndDate();

    public Value getValue(Attribute attribute);

    public Record getRecord();
}
