package ru.elomonosov.service;

import ru.elomonosov.configuration.Configuration;
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
 * Created by n dd on 26.05.2015.
 */
public class Jtm implements JtmService{

    public static final Jtm INCTANCE = new Jtm();

    private Configuration configuration;

    private Jtm() {

    }

    public static Jtm getInstance() {
        return INCTANCE;
    }

    protected void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void createDomain(Domain domain, DomainHandler domainHandler, DomainDataHandler dataHandler) {

    }

    @Override
    public void renameDomain(Domain domain, String newName) {

    }

    @Override
    public void deleteDomain(Domain domain) {

    }

    @Override
    public List<Domain> getAllDomains() {
        return null;
    }

    @Override
    public void createType(Type type, TypeHandler typeHandler, TypeDataHandler dataHandler) {

    }

    @Override
    public void renameType(Type type, String newName) {

    }

    @Override
    public void deleteType(Type type) {

    }

    @Override
    public List<Type> getAllTypes(Domain domain) {
        return null;
    }

    @Override
    public void createVersion(Record record, Version version) {

    }

    @Override
    public void createVersion(Type type, Version version) {

    }

    @Override
    public void editVersion(Version oldVersion, Version newVersion) {

    }

    @Override
    public void deleteVersion(Version version) {

    }

    @Override
    public void deleteRecord(Record record) {

    }
}
