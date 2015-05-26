package ru.elomonosov.handler;

import ru.elomonosov.entity.Attribute;
import ru.elomonosov.entity.Domain;
import ru.elomonosov.entity.Type;
import static ru.elomonosov.entity.Attribute.AttributeType;

import java.util.List;

/**
 * Created by n dd on 24.05.2015.
 */
public abstract class AbstractDomainHandler {

    /*public List<Domain> getAllDomains() {
        return null;
    }*/

    public void createAttribute(int id, String name, AttributeType attributeType, AbstractAttributeHandler attributeHandler) {
        attributeHandler.createAttribute(id, name, attributeType);
    }

    public void assignAttribute(Attribute attribute, Type type, Attribute linkedTo, boolean isNullable,
                                boolean isUnique, AbstractTypeHandler typeHandler) {
        if (attribute.getAttributeType() == AttributeType.LINK) {
            typeHandler.assignAttribute(attribute, type, linkedTo, isNullable, isUnique);
        } else {
            typeHandler.assignAttribute(attribute, type, isNullable, isUnique);
        }
    }

    public void assignAttribute(Attribute attribute, Type type, Attribute linkedTo, AbstractTypeHandler typeHandler) {
        if (attribute.getAttributeType() == AttributeType.LINK) {
            typeHandler.assignAttribute(attribute, type, linkedTo);
        } else {
            typeHandler.assignAttribute(attribute, type);
        }
    }


    public List<Type> getTypes(Domain domain) {
        return null;
    }

    public List<Type> getAllTypes() {
        return null;
    }

}
