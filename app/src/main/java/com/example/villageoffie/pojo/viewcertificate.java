package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class viewcertificate {
    @SerializedName("from1")
    @Expose
    private String from;
    @SerializedName("feedback")
    @Expose
    private String feedback;
    @SerializedName("date")
    @Expose
    private String date;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcReq() {
        return cReq;
    }

    public void setcReq(String cReq) {
        this.cReq = cReq;
    }

    public String getcFee() {
        return cFee;
    }

    public void setcFee(String cFee) {
        this.cFee = cFee;
    }

    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("c_name")
    @Expose
    private String cName;
    @SerializedName("c_req")
    @Expose
    private String cReq;
    @SerializedName("c_fee")
    @Expose
    private String cFee;

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCReq() {
        return cReq;
    }

    public void setCReq(String cReq) {
        this.cReq = cReq;
    }

    public String getCFee() {
        return cFee;
    }

    public void setCFee(String cFee) {
        this.cFee = cFee;
    }

}
