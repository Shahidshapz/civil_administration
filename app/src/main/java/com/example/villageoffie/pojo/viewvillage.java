package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class viewvillage {
    @SerializedName("v_id")
    @Expose
    private String vId;
    @SerializedName("v_name")
    @Expose
    private String vName;
    @SerializedName("v_taluk")
    @Expose
    private String vTaluk;
    @SerializedName("v_district")
    @Expose
    private String vDistrict;
    @SerializedName("v_state")
    @Expose
    private String vState;
    @SerializedName("v_place")
    @Expose
    private String vPlace;
    @SerializedName("v_pin")
    @Expose
    private String vPin;
    @SerializedName("v_mobile")
    @Expose
    private String vMobile;
    @SerializedName("v_username")
    @Expose
    private String vUsername;
    @SerializedName("v_password")
    @Expose
    private String vPassword;

    public String getVId() {
        return vId;
    }

    public void setVId(String vId) {
        this.vId = vId;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName = vName;
    }

    public String getVTaluk() {
        return vTaluk;
    }

    public void setVTaluk(String vTaluk) {
        this.vTaluk = vTaluk;
    }

    public String getVDistrict() {
        return vDistrict;
    }

    public void setVDistrict(String vDistrict) {
        this.vDistrict = vDistrict;
    }

    public String getVState() {
        return vState;
    }

    public void setVState(String vState) {
        this.vState = vState;
    }

    public String getVPlace() {
        return vPlace;
    }

    public void setVPlace(String vPlace) {
        this.vPlace = vPlace;
    }

    public String getVPin() {
        return vPin;
    }

    public void setVPin(String vPin) {
        this.vPin = vPin;
    }

    public String getVMobile() {
        return vMobile;
    }

    public void setVMobile(String vMobile) {
        this.vMobile = vMobile;
    }

    public String getVUsername() {
        return vUsername;
    }

    public void setVUsername(String vUsername) {
        this.vUsername = vUsername;
    }

    public String getVPassword() {
        return vPassword;
    }

    public void setVPassword(String vPassword) {
        this.vPassword = vPassword;
    }
}
