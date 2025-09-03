package com.askeruzmani.asker_uzmani_backend.Enums;

import com.askeruzmani.asker_uzmani_backend.Utils.IdentifiableEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum YesNoEnum implements IdentifiableEnum {
    NO(0, "Hayır"),
    YES(1, "Evet");

    private final int id;
    private final String value;

    YesNoEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    // ID ile enum bulma
    public static YesNoEnum fromId(int id) {
        for (YesNoEnum e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen ID: " + id);
    }

    // Value ile enum bulma
    public static YesNoEnum fromValue(String value) {
        for (YesNoEnum e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen değer: " + value);
    }

    // JSON serileştirme/deserileştirme
    @JsonCreator
    public static YesNoEnum fromObject(Object value) {
        if (value instanceof Integer || value instanceof Number) {
            return fromId(((Number) value).intValue());
        } else if (value instanceof String) {
            return fromValue((String) value);
        }
        throw new IllegalArgumentException("YesNoEnum için geçersiz değer: " + value);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getValue() {
        return value;
    }

    @JsonValue
    public StatusRepresentation toJson() {
        return new StatusRepresentation(id, value);
    }

    public static class StatusRepresentation {
        private final int id;
        private final String value;

        public StatusRepresentation(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }
}
