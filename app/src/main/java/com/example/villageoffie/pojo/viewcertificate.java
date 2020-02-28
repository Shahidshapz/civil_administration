package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class viewcertificate {
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
