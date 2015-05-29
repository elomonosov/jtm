package ru.elomonosov.datahandler;

import ru.elomonosov.datatype.Value;
import ru.elomonosov.entity.Attribute;
import ru.elomonosov.entity.Record;
import ru.elomonosov.entity.Type;
import ru.elomonosov.entity.Version;

import java.util.List;

/**
 * Created by n dd on 28.05.2015.
 */
public class DefaulTypeDataHandler implements TypeDataHandler {

    @Override
    public List<Attribute> getAttributes() throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveRecord(Record record) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Attribute> getAttributes(Type type) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Attribute> getAttributesLinkedTo(Attribute attribute) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Attribute getLinkedAttribute(Attribute attribute) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value getValue(Version version, Attribute attribute) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void assignAttribute(Attribute attribute, Type type, Attribute linkedTo, boolean isNullable, boolean isUnique) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void assignAttribute(Attribute attribute, Attribute linkedTo, boolean isNullable, boolean isUnique) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Record getRecord() throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Record> getAllRecords() throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteRecord(Record record) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Record> getAllRecords(Type type) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Version getVersion(int versionId) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Version> getAllVersions(Record record) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveVersion(Version version) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteVersion(Version version) throws DataHandlerException {
        throw new UnsupportedOperationException();
    }
}
