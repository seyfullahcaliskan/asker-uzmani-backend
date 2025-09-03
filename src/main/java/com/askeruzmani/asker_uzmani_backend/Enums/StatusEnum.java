package com.askeruzmani.asker_uzmani_backend.Enums;


import com.askeruzmani.asker_uzmani_backend.Utils.IdentifiableEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum implements IdentifiableEnum {
    NONE(0, "NONE"),
    CLOSED(1, "Kapalı"),
    PASSIVE(2, "Pasif"),
    ACTIVE(3, "Aktif"),
    CANCELLED(4, "İptal");

    private final int id;
    private final String value;

    StatusEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    // ID ile enum bulma
    public static StatusEnum fromId(int id) {
        for (StatusEnum e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen ID: " + id);
    }

    // Value ile enum bulma
    public static StatusEnum fromValue(String value) {
        for (StatusEnum e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen değer: " + value);
    }

    // JSON serileştirme/deserileştirme
    @JsonCreator
    public static StatusEnum fromJson(@JsonProperty("id") Integer id, @JsonProperty("value") String value) {
        if (id != null) {
            return fromId(id);
        } else if (value != null) {
            return fromValue(value);
        }
        throw new IllegalArgumentException("ID veya value belirtilmeli");
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