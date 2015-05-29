package ru.elomonosov.entytyhandler;

import ru.elomonosov.datatype.Value;
import ru.elomonosov.entity.Attribute;
import ru.elomonosov.entity.Record;
import ru.elomonosov.entity.Type;
import ru.elomonosov.entity.Version;
import ru.elomonosov.configuration.ConfigurationException;
import ru.elomonosov.datahandler.DataHandlerException;
import ru.elomonosov.datahandler.TypeDataHandler;
import ru.elomonosov.service.Jtm;

import java.util.*;

/**
 * Created by n dd on 27.05.2015.
 */
public class DefaultTypeHandler implements TypeHandler {

    private final Jtm jtm;

    protected DefaultTypeHandler(Jtm jtm) throws ConfigurationException {
        this.jtm = jtm;
    }

    @Override
    public TypeDataHandler getTypeDataHandler() throws HandlerException {
        try {
            return jtm.getConfiguration().getDataHandler(this);
        } catch (ConfigurationException e) {
            HandlerException exception = new HandlerException("Cannot get data handler.");
            exception.initCause(e);
            throw exception;
        }
    }

    @Override
    public Type getType() throws HandlerException {
        try {
            return jtm.getConfiguration().getType(this);
        } catch (ConfigurationException e) {
            HandlerException exception = new HandlerException("Cannot get type.");
            exception.initCause(e);
            throw exception;
        }
    }

    @Override
    public void assignAttribute(Attribute attribute, Attribute linkedTo, boolean isNullable, boolean isUnique)throws HandlerException {
        assignedAttributeCheck(attribute, linkedTo, isNullable, isUnique);
        try {
            getTypeDataHandler().assignAttribute(attribute, linkedTo, isNullable, isUnique);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
    }

    public void assignAttribute(Attribute attribute, boolean isNullable, boolean isUnique) throws HandlerException{
        assignAttribute(attribute, null, isNullable, isUnique);
    }

    public void assignAttribute(Attribute attribute) throws HandlerException{
        assignAttribute(attribute, true, false) ;
    }

    public void assignAttribute(Attribute attribute, Attribute linkedTo) throws HandlerException{
        assignAttribute(attribute, linkedTo, true, false);
    }

    private void assignedAttributeCheck(Attribute attribute, Attribute linkedTo, boolean isNullable, boolean isUnique) throws IllegalArgumentException {
        if ((attribute.getDataType().isLink()) && (linkedTo == null)) {
            throw new IllegalArgumentException("Attribute '" + attribute.getName() + "' type is not LINK, linked attribute must not be set");
        } else {
            if (!(attribute.getDataType().isLink()) && (linkedTo != null)) {
                throw new IllegalArgumentException("Attribute '" + attribute.getName() + "' type is not LINK, linked attribute must not be set");
            }
        }
    }

    @Override
    public List<Attribute> getAttributes() throws HandlerException{
        List<Attribute> result = null;
        try {
            result = getTypeDataHandler().getAttributes();
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
        return result;
    }

    @Override
    public Attribute getLinkedAttribute(Attribute attribute) throws HandlerException {
        Attribute result = null;
        try {
            result = getTypeDataHandler().getLinkedAttribute(attribute);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
        return result;
    }

    @Override
    public List<Attribute> getAttributesLinkedTo(Attribute attribute) throws HandlerException{
        List<Attribute> result = null;
        try {
            result = getTypeDataHandler().getAttributesLinkedTo(attribute);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
        return result;
    }

    @Override
    public Value getValue(Version version, Attribute attribute) throws HandlerException{
        Value result = null;
        try {
            result = getTypeDataHandler().getValue(version, attribute);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
        return result;
    }

    @Override
    public List<Record> getAllRecords() throws HandlerException{
        List<Record> result = null;
        try {
            result = getTypeDataHandler().getAllRecords();
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
        return result;
    }

    @Override
    public void saveRecord(Record record) throws HandlerException{
        try {
            getTypeDataHandler().saveRecord(record);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
    }

    @Override
    public void deleteRecord(Record record) throws HandlerException {
        List<Version> versions = getAllVersions(record);
        for (Version version : versions) {
            Map<Version, Attribute> usedByMap = usageCheck(version);
            if (!usedByMap.isEmpty()) {
                throw new HandlerException("Record with id = " + record.getId() + " cannot be deleted, version " +
                        version.getId() + " referenced by " + usedByMap.entrySet().iterator().next().getKey().getId());
            }
        }
        try {
            getTypeDataHandler().deleteRecord(record);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
    }

    @Override
    public List<Version> getAllVersions(Record record) throws HandlerException{
        List<Version> result = null;
        try {
            result = getTypeDataHandler().getAllVersions(record);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
        return result;
    }

    @Override
    public void saveVersion(Version version) throws HandlerException {
        try {
            correctionCheck(version);
            intersectionCheck(version);
        } catch (IllegalArgumentException e) {
            HandlerException exception = new HandlerException("Version cannot be saved.");
            exception.initCause(e);
            throw exception;
        }
        try {
            getTypeDataHandler().saveVersion(version);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
    }

    @Override
    public void deleteVersion(Version version) throws HandlerException {
        usageCheck(version);

        try {
            getTypeDataHandler().deleteVersion(version);
        } catch (DataHandlerException e) {
            e.printStackTrace(); //TODO add exception
        }
    }

    public void intersectionCheck(Version version) throws HandlerException {
        List<Version> foundVersions = getAllVersions(version.getRecord());

        if (foundVersions.isEmpty()) {
            return;    // it can't be intersection of periods if there are no other records
        }


        if ((foundVersions.size() == 1) && (foundVersions.get(0).equals(version))) { // TODO проверить, достаточно ли в equals проверять только versionId
            return; // it can't be intersection of periods if only 1 period exists
        }

        Date startDate = version.getStartDate();
        Date endDate = version.getEndDate();

        for (Version foundVersion : foundVersions) {
            Date fVerStartDate = foundVersion.getStartDate();
            Date fVerEndDate = foundVersion.getEndDate();

            if (startDate.compareTo(fVerStartDate) == 0) {
                throw new IllegalArgumentException("Intersection of periods, case 11 or 12");
            }

            if ((fVerEndDate != null) && (endDate != null)) {
                if ((fVerStartDate.compareTo(startDate) < 0) && (fVerEndDate.compareTo(startDate) > 0)) // case 2 or 3
                {
                    throw new IllegalArgumentException("Intersection of periods, case 2 or 3");

                } else if ((fVerStartDate.compareTo(startDate) > 0) && (fVerEndDate.compareTo(endDate) < 0)) // case 4
                {
                    throw new IllegalArgumentException("Intersection of periods, case 4");
                } else if ((fVerStartDate.compareTo(startDate) > 0) && (fVerStartDate.compareTo(endDate) < 0)) // case 5
                {
                    throw new IllegalArgumentException("Intersection of periods, case 5");
                }
            }

            if ((fVerEndDate != null) && (endDate == null)) {
                if ((fVerStartDate.compareTo(startDate) < 0) && (fVerEndDate.compareTo(startDate) > 0)) // case 1
                {
                    throw new IllegalArgumentException("Intersection of periods, case 1");
                } else if (startDate.compareTo(fVerStartDate) < 0)                                    // case 6
                {
                    throw new IllegalArgumentException("Intersection of periods, case 6");
                }
            }

            if ((fVerEndDate == null) && (endDate != null)) {
                try {
                    if ((fVerStartDate.compareTo(startDate) < 0) && (!usageCheck(foundVersion).isEmpty()))                            // case 8.
                    {
                        throw new IllegalArgumentException("Intersection of periods, case 8");
                    } else if ((startDate.compareTo(fVerStartDate) < 0) && (endDate.compareTo(fVerStartDate) > 0))        // case 9
                    {
                        throw new IllegalArgumentException("Intersection of periods, case 9");
                    }
                } catch (HandlerException e) {
                    HandlerException exception = new HandlerException("Cannot check intersection for version " + version.getId() + ".");
                    exception.initCause(e);
                    throw exception;
                }
            }

            if ((fVerEndDate == null) && (endDate == null)) {
                try {
                    if ((fVerStartDate.compareTo(startDate) < 0) && (!usageCheck(foundVersion).isEmpty()))                        // case 7.
                    {
                        throw new IllegalArgumentException("Intersection of periods, case 7");
                    } else if (fVerStartDate.compareTo(startDate) > 0)                // case 10
                    {
                        throw new IllegalArgumentException("Intersection of periods, case 10");
                    }
                } catch (HandlerException e) {
                    HandlerException exception = new HandlerException("Cannot check intersection for version " + version.getId() + ".");
                    exception.initCause(e);
                    throw exception;
                }
            }
        }
    }

    public void correctionCheck(Version version) throws IllegalArgumentException, HandlerException {

        if (version.getStartDate() == null) {
            throw new IllegalArgumentException("Start date must be not null");
        }

        if (version.getStartDate().compareTo(version.getEndDate()) > 0) {
            throw new IllegalArgumentException("Start date must be less than or equal to the end date");
        }

        List<Attribute> attributes = getType().getAttributes();
        for (Attribute attribute : attributes) {

            Value value = version.getValue(attribute);
            if ((value.isEmpty()) && (!getType().isAttributeNullable(attribute))) {
                throw new IllegalArgumentException("Attribute '" + attribute.getName() + "' must be not empty.");
            }

            if ((getType().isAttributeUnique(attribute)) && (!uniqueCheck(version.getValue(attribute), attribute))) {
                throw new IllegalArgumentException("Attribute '" + attribute.getName() + "' value '" +
                        value.getValue() + "' is not unique");
            }

            if (value.isEmpty()) { // empty value is ok for any attribute type
                break;
            }

            if (value.getDataType() != attribute.getDataType()) {
                throw new HandlerException("Attribute '" + attribute.getName() + "' data type is '" + attribute.getDataType() + "' , value '"
                        + value.getValue() + "' data type is '" + value.getDataType() + "'.");
            }

            if (value.getDataType().isLink()) {
                try {
                } catch (ClassCastException e) {
                    HandlerException exception = new HandlerException("Attribute '" + attribute.getName() + "' data type is 'Link', but value '" + value.getValue() + "' data type is not 'Link'");
                    exception.initCause(e);
                    throw exception;
                }

                Attribute linkedAttribute = getLinkedAttribute(attribute); // TODO handle if null

                try {
                    boolean versionExist =
                            jtm.getConfiguration().getHandler(linkedAttribute.getType()).isExist(value.getLink()); // check for existance of referenced version
                    if (!versionExist) {
                        throw new IllegalArgumentException("Version with id = " + value.getLink().getId() + "is not exist in " + linkedAttribute.getType().getName());
                    }
                } catch (ConfigurationException e) {
                    HandlerException exception = new HandlerException("Cannot get handler for type '" + linkedAttribute.getType().getName() + "'" );
                    exception.initCause(e);
                    throw exception;
                }
            }
        }
    }

    public boolean uniqueCheck(Value value, Attribute attribute) throws HandlerException{
        List<Record> records = getAllRecords();

        boolean result = true;

        for (Record record : records) {
            List<Version> versions = getAllVersions(record);
            for (Version version : versions) {
                if (version.getValue(attribute).equals(value)) {
                    result = false;
                }
            }
        }
        return result;
    }

    public Map<Version, Attribute> usageCheck(Version version) throws HandlerException {

        Map<Version, Attribute> result = new HashMap<Version, Attribute>();

        List<Attribute> attributes = getType().getAttributes();

        for (Attribute attribute : attributes) {
            List<Attribute> linkedAttributes = getAttributesLinkedTo(attribute);
            for (Attribute linkedAttribute : linkedAttributes) {
                Type linkedType = linkedAttribute.getType();
                List<Version> foundVersions = null;
                try {
                    foundVersions = jtm.getConfiguration().getHandler(linkedType).findVersions(linkedAttribute, version.getValue(attribute));
                } catch (HandlerException | ConfigurationException e) {
                    HandlerException exception = new HandlerException("Cannot check usage in type '" + linkedType.getName() + "'.");
                    exception.initCause(e);
                    throw exception;
                }
                if (!foundVersions.isEmpty()) {
                    for (Version foundVersion : foundVersions) {
                        result.put(foundVersion, linkedAttribute);
                    }
                }
            }
        }
        return result;
    }

    public List<Version> findVersions(Attribute attribute, Value value) throws HandlerException { // TODO переопределять при наследовании
        List<Record> records = getAllRecords();
        List<Version> result = new ArrayList<Version>();

        for (Record record : records) {
            List<Version> recordVersions = getAllVersions(record);
            for (Version version : recordVersions) {
                if (version.getValue(attribute) == value) {
                    result.add(version);
                }
            }
        }
        return result;
    }

    public boolean isExist(Version version) throws HandlerException {
        List<Record> records = getAllRecords();
        for (Record record : records) {
            List<Version> versions = getAllVersions(record);
            for (Version foundedVersion : versions) {
                if (foundedVersion.equals(version)) {
                    return true;
                }
            }
        }
        return false;
    }
}
