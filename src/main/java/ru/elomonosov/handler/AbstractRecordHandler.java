package ru.elomonosov.handler;

import ru.elomonosov.entity.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by n dd on 24.05.2015.
 */
public abstract class AbstractRecordHandler {

    private AbstractTypeHandler typeHandler;

    public List<Record> getRecords() {
        return null;
    }

    public Record getRecord() {

        return null;
    }

    public void deleteRecord(Record record) throws HandlerException {
        List<Version> versions = getAllVersions(record);
        for (Version version : versions) {
            Map<Version, Attribute> usedByMap = usageCheck(version);
            if (!usedByMap.isEmpty()) {
                throw new HandlerException("Record with id = " + record.getId() + " cannot be deleted, version " +
                        version.getId() + " referenced by " + usedByMap.entrySet().iterator().next().getKey().getId());
            }
        }
    }

    public List<Record> getAllRecords(Type type) {
        return null;
    }

    public Version getVersion(int versionId) {
        return null;
    }

    public List<Version> getAllVersions(Record record) {
        return null;
    }

    void saveVersion(Version version) throws HandlerException{
        try {
            correctionCheck(version);
            intersectionCheck(version);
        } catch (IllegalArgumentException e) {
            HandlerException exception = new HandlerException("Version cannot be saved");
            exception.initCause(e);
            throw exception;
        }
    }

    void deleteVersion(Version version) {
        usageCheck(version); //TODO delete
    }

    public void intersectionCheck(Version version) {
        List<Version> foundVersions = version.getRecord().getAllVersions();

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

            if ( (fVerEndDate != null) && (endDate != null) )
            {
                if ( (fVerStartDate.compareTo(startDate) < 0) && (fVerEndDate.compareTo(startDate) > 0) ) // case 2 or 3
                {
                    throw new IllegalArgumentException("Intersection of periods, case 2 or 3");

                } else if ( (fVerStartDate.compareTo(startDate) > 0) && (fVerEndDate.compareTo(endDate) < 0) ) // case 4
                {
                    throw new IllegalArgumentException("Intersection of periods, case 4");
                } else if ( (fVerStartDate.compareTo(startDate) > 0) && (fVerStartDate.compareTo(endDate) < 0) ) // case 5
                {
                    throw new IllegalArgumentException("Intersection of periods, case 5");
                }
            }

            if ( (fVerEndDate != null) && (endDate == null) )
            {
                if ( (fVerStartDate.compareTo(startDate) < 0) && (fVerEndDate.compareTo(startDate) > 0) ) // case 1
                {
                    throw new IllegalArgumentException("Intersection of periods, case 1");
                } else if (startDate.compareTo(fVerStartDate) < 0) 									// case 6
                {
                    throw new IllegalArgumentException("Intersection of periods, case 6");
                }
            }

            if ( (fVerEndDate == null) && (endDate != null) )
            {
                if ((fVerStartDate.compareTo(startDate) < 0)&&(!usageCheck(foundVersion).isEmpty())) 							// case 8.
                {
                    throw new IllegalArgumentException("Intersection of periods, case 8");
                } else if ( (startDate.compareTo(fVerStartDate)<0) && (endDate.compareTo(fVerStartDate) > 0) ) 		// case 9
                {
                    throw new IllegalArgumentException("Intersection of periods, case 9");
                }
            }

            if ( (fVerEndDate == null) && (endDate == null) )
            {
                if ((fVerStartDate.compareTo(startDate) < 0)&&(!usageCheck(foundVersion).isEmpty())) 						// case 7.
                {
                    throw new IllegalArgumentException("Intersection of periods, case 7");
                } else if (fVerStartDate.compareTo(startDate) > 0)				// case 10
                {
                    throw new IllegalArgumentException("Intersection of periods, case 10");
                }
            }
        }
    }

    public void correctionCheck(Version version) throws IllegalArgumentException{

        if (version.getStartDate() == null) {
            throw new IllegalArgumentException("Start date must be not null");
        }

        if (version.getStartDate().compareTo(version.getEndDate()) > 0) {
            throw new IllegalArgumentException("Start date must be less than or equal to the end date");
        }

        Type type = version.getRecord().getType();
        List<Attribute> attributes = version.getRecord().getType().getAttributes();
        for (Attribute attribute : attributes) {

            Value value = version.getValue(attribute);
            if ((value.isEmpty())&&(!type.isAttributeNullable(attribute))) {
                throw new IllegalArgumentException("Attribute '" + attribute.getName() + "' must be not empty.");
            }

            if ((type.isAttributeUnique(attribute))&&(!isUnique(version.getValue(attribute), attribute))) {
                throw new IllegalArgumentException("Attribute '" + attribute.getName() + "' value '"+
                        value.getAsString() + "' is not unique");
            }

            if(value.isEmpty()) { // empty value is ok for any attribute type
                break;
            }

            switch (attribute.getAttributeType()) { //  value matching type check
                case DATE: {
                    try {
                        value.getAsDate();
                    } catch (ClassCastException e) {
                        IllegalArgumentException exception =
                                new IllegalArgumentException("Attribute '"+attribute.getName()+ "' must contain date. Value '"+
                                        value.getAsString()+ " is not date.");
                        exception.initCause(e);
                        throw exception;
                    }
                    break;
                }
                case INTEGER: {
                    try {
                        value.getAsInteger();
                    } catch (ClassCastException e) {
                        IllegalArgumentException exception =
                                new IllegalArgumentException("Attribute '"+attribute.getName()+ "' must contain integer. Value '"+
                                        value.getAsString()+ " is not integer.");
                        exception.initCause(e);
                        throw exception;
                    }
                    break;
                }
                case LINK: {
                    try {
                        int versionId = value.getAsInteger();

                        List<Attribute> linkedAttributes = type.getTypeHandler().getAttributesLinkedTo(attribute); // TODO handle if null
                        Version foundVersion = null;
                        for (Attribute linkedAttribute : linkedAttributes) {
                            foundVersion = linkedAttribute.getType().findVersion(versionId);
                            if (foundVersion != null) {
                                break;
                            }
                        }

                        if (foundVersion == null) {
                            throw new IllegalArgumentException("Version with id = " + versionId + "is not exist in " + type.getDomain().getName());
                        }
                    } catch (ClassCastException e) {
                        IllegalArgumentException exception = new IllegalArgumentException("Value '" +value.getAsString()+"' is not link");
                        exception.initCause(e);
                        throw exception;
                    }
                }
            }
        }
    }

    public boolean isUnique(Value value, Attribute attribute) {
        List<Record> records = value.getVersion().getRecord().getType().getRecords();

        boolean result = true;

        for (Record record : records) {
            List<Version> versions = record.getAllVersions();
            for (Version version : versions) {
                if (version.getValue(attribute).equals(value)) {
                    result = false;
                }
            }
        }
        return result;
    }

    public Map<Version, Attribute> usageCheck(Version version) {
        Map<Version, Attribute> result = new HashMap<Version, Attribute>();

        Type type = version.getRecord().getType();
        List<Attribute> attributes = type.getAttributes();
        AbstractTypeHandler typeHandler = type.getTypeHandler();

        for (Attribute attribute : attributes) {
            List<Attribute> linkedAttributes = typeHandler.getAttributesLinkedTo(attribute);
            for (Attribute linkedAttribute : linkedAttributes) {
                Type linkedType = linkedAttribute.getType();
                List<Version> foundVersions = linkedType.findVersions(linkedAttribute, version.getValue(attribute));
                if (!foundVersions.isEmpty()) {
                    for (Version foundVersion : foundVersions) {
                        result.put(foundVersion, linkedAttribute);
                    }
                }
            }
        }
        return result;
    }
}
