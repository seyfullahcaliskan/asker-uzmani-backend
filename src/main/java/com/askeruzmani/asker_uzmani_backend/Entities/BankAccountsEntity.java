package com.askeruzmani.asker_uzmani_backend.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccountsEntity extends BaseEntity {

    @Column(name = "iban")
    private String iban;

    @Column(name = "display_iban")
    private String displayIban;

    @Column(name = "holder")
    private String holder;

    @Column(name = "bank")
    private String bankId;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDisplayIban() {
        return displayIban;
    }

    public void setDisplayIban(String displayIban) {
        this.displayIban = displayIban;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
