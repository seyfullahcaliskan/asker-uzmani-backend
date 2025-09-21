package com.askeruzmani.asker_uzmani_backend.Enums;

import com.askeruzmani.asker_uzmani_backend.Utils.IdentifiableEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PayStatusEnum implements IdentifiableEnum {
    NONE(0, "NONE"),
    PENDING(1, "Bekleniyor"),
    SUCCESS(2, "Başarılı"),
    FAILED(3, "Başarısız"),
    RETURNED(4, "İade Edildi");

    private final int id;
    private final String value;

    PayStatusEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    // ID ile enum bulma
    public static PayStatusEnum fromId(int id) {
        for (PayStatusEnum e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen ID: " + id);
    }

    // Value ile enum bulma
    public static PayStatusEnum fromValue(String value) {
        for (PayStatusEnum e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen değer: " + value);
    }

    // JSON serileştirme/deserileştirme
    @JsonCreator
    public static PayStatusEnum fromJson(@JsonProperty("id") Integer id, @JsonProperty("value") String value) {
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
    public PayStatusRepresentation toJson() {
        return new PayStatusRepresentation(id, value);
    }

    public static class PayStatusRepresentation {
        private final int id;
        private final String value;

        public PayStatusRepresentation(int id, String value) {
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
