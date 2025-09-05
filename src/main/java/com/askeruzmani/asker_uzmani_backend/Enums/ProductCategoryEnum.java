package com.askeruzmani.asker_uzmani_backend.Enums;

import com.askeruzmani.asker_uzmani_backend.Utils.IdentifiableEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductCategoryEnum implements IdentifiableEnum {

    NONE(0, "Genel"),
//    CLOSED(1, "Kapalı"),
    CANCELLED(4, "İptal");

    private final int id;
    private final String value;

    ProductCategoryEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    // ID ile enum bulma
    public static ProductCategoryEnum fromId(int id) {
        for (ProductCategoryEnum e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen ID: " + id);
    }

    // Value ile enum bulma
    public static ProductCategoryEnum fromValue(String value) {
        for (ProductCategoryEnum e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Bilinmeyen değer: " + value);
    }

    // JSON serileştirme/deserileştirme
    @JsonCreator
    public static ProductCategoryEnum fromJson(@JsonProperty("id") Integer id, @JsonProperty("value") String value) {
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
    public ProductCategoryEnum.ProductCategoryRepresentation toJson() {
        return new ProductCategoryEnum.ProductCategoryRepresentation(id, value);
    }

    public static class ProductCategoryRepresentation {
        private final int id;
        private final String value;

        public ProductCategoryRepresentation(int id, String value) {
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
