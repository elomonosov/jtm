package ru.elomonosov.handler;

import ru.elomonosov.entity.Attribute;
import ru.elomonosov.entity.Type;
import static ru.elomonosov.entity.Attribute.AttributeType;


/**
 * Created by n dd on 25.05.2015.
 */
public abstract class AbstractAttributeHandler {

    public void createAttribute(int id, String name, AttributeType attributeType) {
        //TODO переопределять при наследовании
    }
}
