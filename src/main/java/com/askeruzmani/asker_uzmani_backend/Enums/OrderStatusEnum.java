package com.askeruzmani.asker_uzmani_backend.Enums;

import com.askeruzmani.asker_uzmani_backend.Utils.IdentifiableEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatusEnum implements IdentifiableEnum {
    NONE(0, "NONE"),
    PENDING(1, "Bekleniyor"),
    SUCCESS(2, "Başarılı"),
    FAILED(3, "Başarısız");

    private final int id;
    private final String value;

    OrderStatusEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    // ID ile enum bulma
    public static OrderStatusEnum fromId(int id) {
        for (OrderStatusEnum e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen ID: " + id);
    }

    // Value ile enum bulma
    public static OrderStatusEnum fromValue(String value) {
        for (OrderStatusEnum e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen değer: " + value);
    }

    // JSON serileştirme/deserileştirme
    @JsonCreator
    public static OrderStatusEnum fromJson(@JsonProperty("id") Integer id, @JsonProperty("value") String value) {
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
    public OrderStatusRepresentation toJson() {
        return new OrderStatusRepresentation(id, value);
    }

    public static class OrderStatusRepresentation {
        private final int id;
        private final String value;

        public OrderStatusRepresentation(int id, String value) {
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
