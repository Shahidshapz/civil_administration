package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradePojo {
    @SerializedName("payid")
    @Expose
    private String payid;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("pdate")
    @Expose
    private String pdate;
    @SerializedName("tid")
    @Expose
    private String tid;
    @SerializedName("shopname")
    @Expose
    private String shopname;
    @SerializedName("shoppurpose")
    @Expose
    private String shoppurpose;
    @SerializedName("shopowner")
    @Expose
    private String shopowner;
    @SerializedName("ward")
    @Expose
    private String ward;
    @SerializedName("bno")
    @Expose
    private String bno;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("dor")
    @Expose
    private String dor;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("status")
    @Expose
    private String status;

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShoppurpose() {
        return shoppurpose;
    }

    public void setShoppurpose(String shoppurpose) {
        this.shoppurpose = shoppurpose;
    }

    public String getShopowner() {
        return shopowner;
    }

    public void setShopowner(String shopowner) {
        this.shopowner = shopowner;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
