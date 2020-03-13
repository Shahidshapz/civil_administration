package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permitpojo {
    @SerializedName("buildingid")
    @Expose
    private String buildingid;
    @SerializedName("oname")
    @Expose
    private String oname;
    @SerializedName("nrooms")
    @Expose
    private String nrooms;
    @SerializedName("sqft")
    @Expose
    private String sqft;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("adate")
    @Expose
    private String adate;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("status")
    @Expose
    private String status;

    public String getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(String buildingid) {
        this.buildingid = buildingid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getNrooms() {
        return nrooms;
    }

    public void setNrooms(String nrooms) {
        this.nrooms = nrooms;
    }

    public String getSqft() {
        return sqft;
    }

    public void setSqft(String sqft) {
        this.sqft = sqft;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
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
