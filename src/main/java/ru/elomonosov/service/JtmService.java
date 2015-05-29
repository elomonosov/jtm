package ru.elomonosov.service;

import ru.elomonosov.datahandler.DomainDataHandler;
import ru.elomonosov.datahandler.TypeDataHandler;
import ru.elomonosov.entity.Domain;
import ru.elomonosov.entity.Record;
import ru.elomonosov.entity.Type;
import ru.elomonosov.entity.Version;
import ru.elomonosov.entytyhandler.DomainHandler;
import ru.elomonosov.entytyhandler.TypeHandler;

import java.util.List;

/**
 * Created by n dd on 28.05.2015.
 */
public interface JtmService {

    void createDomain(Domain domain, DomainHandler domainHandler, DomainDataHandler dataHandler);

    void renameDomain(Domain domain, String newName);

    void deleteDomain(Domain domain);

    List<Domain> getAllDomains();

    void createType(Type type, TypeHandler typeHandler, TypeDataHandler dataHandler);

    void renameType(Type type, String newName);

    void deleteType(Type type);

    List<Type> getAllTypes(Domain domain);

    void createVersion(Record record, Version version);

    void createVersion(Type type, Version version);

    void editVersion(Version oldVersion, Version newVersion);

    void deleteVersion(Version version);

    void deleteRecord(Record record);
}
