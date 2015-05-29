package ru.elomonosov.datahandler;

import ru.elomonosov.datatype.Value;
import ru.elomonosov.entity.Attribute;
import ru.elomonosov.entity.Record;
import ru.elomonosov.entity.Type;
import ru.elomonosov.entity.Version;

import java.util.List;

/**
 * Created by n dd on 27.05.2015.
 */
public interface TypeDataHandler {

    default String getName() throws DataHandlerException {
        return getClass().getName();
    }

    List<Attribute> getAttributes() throws DataHandlerException;

    void saveRecord(Record record) throws DataHandlerException;

    List<Attribute> getAttributes(Type type) throws DataHandlerException;

    List<Attribute> getAttributesLinkedTo(Attribute attribute) throws DataHandlerException;

    Attribute getLinkedAttribute(Attribute attribute) throws DataHandlerException;

    Value getValue(Version version, Attribute attribute) throws DataHandlerException;

    void assignAttribute(Attribute attribute, Type type, Attribute linkedTo, boolean isNullable, boolean isUnique) throws DataHandlerException;

    void assignAttribute(Attribute attribute, Attribute linkedTo, boolean isNullable, boolean isUnique) throws DataHandlerException;

    Record getRecord() throws DataHandlerException;

    List<Record> getAllRecords() throws DataHandlerException;

    void deleteRecord(Record record) throws DataHandlerException;

    List<Record> getAllRecords(Type type) throws DataHandlerException;

    Version getVersion(int versionId) throws DataHandlerException;

    List<Version> getAllVersions(Record record) throws DataHandlerException;

    void saveVersion(Version version) throws DataHandlerException;

    void deleteVersion(Version version) throws DataHandlerException;
}
