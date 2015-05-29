package ru.elomonosov.entytyhandler;

import ru.elomonosov.datatype.Value;
import ru.elomonosov.entity.Attribute;
import ru.elomonosov.entity.Record;
import ru.elomonosov.entity.Type;
import ru.elomonosov.entity.Version;
import ru.elomonosov.datahandler.TypeDataHandler;

import java.util.List;

/**
 * Created by n dd on 25.05.2015.
 */
public interface TypeHandler {

    default String getName() throws HandlerException {
        return getClass().getName();
    }

    TypeDataHandler getTypeDataHandler() throws HandlerException;

    Type getType() throws HandlerException;

    List<Attribute> getAttributes() throws HandlerException;

    Attribute getLinkedAttribute(Attribute attribute) throws HandlerException;

    List<Attribute> getAttributesLinkedTo(Attribute attribute) throws HandlerException;

    Value getValue(Version version, Attribute attribute) throws HandlerException;

    void assignAttribute(Attribute attribute, Attribute linkedTo, boolean isNullable, boolean isUnique) throws HandlerException;

    List<Record> getAllRecords() throws HandlerException;

    void saveRecord(Record record) throws HandlerException;

    void deleteRecord(Record record) throws HandlerException;

    List<Version> getAllVersions(Record record) throws HandlerException;

    void saveVersion(Version version) throws HandlerException;

    void deleteVersion(Version version) throws HandlerException;

    List<Version> findVersions(Attribute attribute, Value value) throws HandlerException;

    boolean isExist(Version version) throws HandlerException;
}
