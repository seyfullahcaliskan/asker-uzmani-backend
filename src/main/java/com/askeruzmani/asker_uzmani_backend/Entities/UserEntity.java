package com.askeruzmani.asker_uzmani_backend.Entities;

import com.askeruzmani.asker_uzmani_backend.Enums.YesNoEnum;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "phone_number_1", nullable = false, unique = true)
    private String phoneNumber1;

    @Column(name = "phone_number_2", nullable = true, unique = true)
    private String phoneNumber2;

    @Column(name = "membership_start_date", nullable = false)
    private Timestamp membershipStartDate;

    @Column(name = "membership_end_date", nullable = false)
    private Timestamp membershipEndDate;

    @Column(name = "birtday", nullable = false)
    private Timestamp birthday;

    @Column(name = "country_of_birth", nullable = false)
    private String countryOfBirth;

    @Column(name = "district_of_birth", nullable = false)
    private String districtOfBirth;

    @Column(name = "country_of_residential_address", nullable = false)
    private String countryOfResidentialAddress;

    @Column(name = "district_of_residential_address", nullable = false)
    private String districtOfResidentialAddress;

    @Column(name = "detail_of_residential_address", nullable = false)
    private String detailOfResidentialAddress;

    @Column(name = "country_of_work_address", nullable = true)
    private String countryOfWorkAddress;

    @Column(name = "district_of_work_address", nullable = true)
    private String districtOfWorkAddress;

    @Column(name = "detail_of_work_address", nullable = true)
    private String detailOfWorkAddress;

    @Column(name = "identity_number", nullable = false, unique = true)
    private String identityNumber;

    @Column(name = "vkn", nullable = false, unique = true)
    private String vkn;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "kvkk", nullable = false)
    private YesNoEnum kvkk;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "privacy_agreement", nullable = false)
    private YesNoEnum privacyAgreement;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "information_agreement", nullable = false)
    private YesNoEnum informationAgreement;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public Timestamp getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(Timestamp membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public Timestamp getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Timestamp membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getDistrictOfBirth() {
        return districtOfBirth;
    }

    public void setDistrictOfBirth(String districtOfBirth) {
        this.districtOfBirth = districtOfBirth;
    }

    public String getCountryOfResidentialAddress() {
        return countryOfResidentialAddress;
    }

    public void setCountryOfResidentialAddress(String countryOfResidentialAddress) {
        this.countryOfResidentialAddress = countryOfResidentialAddress;
    }

    public String getDistrictOfResidentialAddress() {
        return districtOfResidentialAddress;
    }

    public void setDistrictOfResidentialAddress(String districtOfResidentialAddress) {
        this.districtOfResidentialAddress = districtOfResidentialAddress;
    }

    public String getDetailOfResidentialAddress() {
        return detailOfResidentialAddress;
    }

    public void setDetailOfResidentialAddress(String detailOfResidentialAddress) {
        this.detailOfResidentialAddress = detailOfResidentialAddress;
    }

    public String getCountryOfWorkAddress() {
        return countryOfWorkAddress;
    }

    public void setCountryOfWorkAddress(String countryOfWorkAddress) {
        this.countryOfWorkAddress = countryOfWorkAddress;
    }

    public String getDistrictOfWorkAddress() {
        return districtOfWorkAddress;
    }

    public void setDistrictOfWorkAddress(String districtOfWorkAddress) {
        this.districtOfWorkAddress = districtOfWorkAddress;
    }

    public String getDetailOfWorkAddress() {
        return detailOfWorkAddress;
    }

    public void setDetailOfWorkAddress(String detailOfWorkAddress) {
        this.detailOfWorkAddress = detailOfWorkAddress;
    }

    public YesNoEnum getKvkk() {
        return kvkk;
    }

    public void setKvkk(YesNoEnum kvkk) {
        this.kvkk = kvkk;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getVkn() {
        return vkn;
    }

    public void setVkn(String vkn) {
        this.vkn = vkn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public YesNoEnum getPrivacyAgreement() {
        return privacyAgreement;
    }

    public void setPrivacyAgreement(YesNoEnum privacyAgreement) {
        this.privacyAgreement = privacyAgreement;
    }

    public YesNoEnum getInformationAgreement() {
        return informationAgreement;
    }

    public void setInformationAgreement(YesNoEnum informationAgreement) {
        this.informationAgreement = informationAgreement;
    }
}
