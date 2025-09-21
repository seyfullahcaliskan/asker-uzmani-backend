package com.askeruzmani.asker_uzmani_backend.Enums;

import com.askeruzmani.asker_uzmani_backend.Utils.IdentifiableEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CargoStatusEnum implements IdentifiableEnum {
    NONE(0, "NONE"),
    WAITING(1, "Kargo Bekleniyor"),
    IN_CARGO(2, "Kargoya Verildi."),
    DELIVERED(3, "Teslim edildi.");

    private final int id;
    private final String value;

    CargoStatusEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    // ID ile enum bulma
    public static CargoStatusEnum fromId(int id) {
        for (CargoStatusEnum e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen ID: " + id);
    }

    // Value ile enum bulma
    public static CargoStatusEnum fromValue(String value) {
        for (CargoStatusEnum e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen değer: " + value);
    }

    // JSON serileştirme/deserileştirme
    @JsonCreator
    public static CargoStatusEnum fromJson(@JsonProperty("id") Integer id, @JsonProperty("value") String value) {
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
    public CargoStatusEnum.CargoStatusRepresentation toJson() {
        return new CargoStatusEnum.CargoStatusRepresentation(id, value);
    }

    public static class CargoStatusRepresentation {
        private final int id;
        private final String value;

        public CargoStatusRepresentation(int id, String value) {
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