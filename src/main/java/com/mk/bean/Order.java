package com.mk.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;

    private Long orderNo;

    private Integer userId;

    private Integer shippingId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private Date updateTime;

    public Order(Integer id, Long orderNo, Integer userId, Integer shippingId, BigDecimal payment, Integer paymentType, Integer postage, Integer status, Date paymentTime, Date sendTime, Date endTime, Date closeTime, Date createTime, Date updateTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.shippingId = shippingId;
        this.payment = payment;
        this.paymentType = paymentType;
        this.postage = postage;
        this.status = status;
        this.paymentTime = paymentTime;
        this.sendTime = sendTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public Integer getPostage() {
        return postage;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}