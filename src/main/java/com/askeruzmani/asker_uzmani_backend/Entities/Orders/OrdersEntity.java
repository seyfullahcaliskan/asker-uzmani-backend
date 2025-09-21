package com.askeruzmani.asker_uzmani_backend.Entities.Orders;

import com.askeruzmani.asker_uzmani_backend.Entities.BaseEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.PayStatusEnum;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class OrdersEntity extends BaseEntity {
    @Column(name="merchant_oid",unique = true, length = 64)
    private String merchantOid;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="customer_email")
    private String customerEmail;

    @Column(name="customer_phone")
    private String customerPhone;

    @Column(name="customer_address")
    private String customerAddress;

    @Type(JsonBinaryType.class)
    @Column(name = "items", columnDefinition = "jsonb")
    private List<Map<String, Object>> items;

    @Column(name="amount")
    private BigDecimal amount;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "pay_status", nullable = false)
    private PayStatusEnum payStatus = PayStatusEnum.PENDING;

    @Column(name="order_no",unique = true, length = 10)
    private String orderNo;

    public String getMerchantOid() {
        return merchantOid;
    }

    public void setMerchantOid(String merchantOid) {
        this.merchantOid = merchantOid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
