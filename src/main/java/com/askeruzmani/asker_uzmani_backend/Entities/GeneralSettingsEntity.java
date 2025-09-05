package com.askeruzmani.asker_uzmani_backend.Entities;

import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "general_settings")
public class GeneralSettingsEntity extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "free_cargo", nullable = false)
    private YesNoEnum freeCargo = YesNoEnum.NO;

    @Column(name = "free_cargo_price")
    private Integer freeCargoPrice;

    @Column(name = "cargo_price")
    private Integer cargoPrice;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "online_payment", nullable = false)
    private YesNoEnum onlinePayment = YesNoEnum.NO;

    public YesNoEnum getFreeCargo() {
        return freeCargo;
    }

    public void setFreeCargo(YesNoEnum freeCargo) {
        this.freeCargo = freeCargo;
    }

    public Integer getFreeCargoPrice() {
        return freeCargoPrice;
    }

    public void setFreeCargoPrice(Integer freeCargoPrice) {
        this.freeCargoPrice = freeCargoPrice;
    }

    public Integer getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(Integer cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public YesNoEnum getOnlinePayment() {
        return onlinePayment;
    }

    public void setOnlinePayment(YesNoEnum onlinePayment) {
        this.onlinePayment = onlinePayment;
    }
}
