package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class spinnerresponse {
    @SerializedName("v_name")
    @Expose
    private String v_name;
    @SerializedName("v_taluk")
    @Expose
    private String v_taluk;
    @SerializedName("v_district")
    @Expose
    private String v_district;

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getV_taluk() {
        return v_taluk;
    }

    public void setV_taluk(String v_taluk) {
        this.v_taluk = v_taluk;
    }

    public String getV_district() {
        return v_district;
    }

    public void setV_district(String v_district) {
        this.v_district = v_district;
    }
}
