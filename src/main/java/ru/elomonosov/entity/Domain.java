package ru.elomonosov.entity;

import ru.elomonosov.entytyhandler.DomainHandler;
import ru.elomonosov.service.Jtm;

import java.util.List;

/**
 * Created by n dd on 24.05.2015.
 */
public final class Domain {

    private final Jtm jtm;

    private final String name;

    private final List<Type> types;

    public Jtm getJtm() {
        return jtm;
    }

    public Domain(Jtm jtm, String name, List<Type> types) {
        this.jtm = jtm;
        this.name = name;
        this.types = types;
    }

    public List<Type> getTypes() {

        return types;
    }

    public String getName() {

        return name;
    }

    public static class Builder {
        private String name;

        private List<Type> types;

        private Jtm jtm;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTypes(List<Type> types) {
            this.types = types;
            return this;
        }

        public Builder setJtm(Jtm jtm) {
            this.jtm = jtm;
            return this;
        }

        public Domain build() {
            return new Domain(jtm, name, types);
        }

    }
}
