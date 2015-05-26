package ru.elomonosov.entity;

import ru.elomonosov.handler.AbstractRecordHandler;

import java.util.Date;
import java.util.List;

/**
 * Created by n dd on 24.05.2015.
 */
public class Record {

    private int id;

    private AbstractRecordHandler recordHandler;

    private Type type;

    public int getId() {
        return id;
    }

    public AbstractRecordHandler getRecordHandler() {

        return recordHandler;
    }


    
    Version getVersion(Date actualDate) {
        return null;
    }

    public List<Version> getAllVersions() {

        return recordHandler.getAllVersions(this);
    }

    public Type getType() {

        return type;
    }
}
