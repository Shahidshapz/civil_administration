package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewdeathpojo {
    @SerializedName("did")
    @Expose
    private String did;
    @SerializedName("dname")
    @Expose
    private String dname;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("dod")
    @Expose
    private String dod;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("dor")
    @Expose
    private String dor;
    @SerializedName("mname")
    @Expose
    private String mname;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("issue_date")
    @Expose
    private String issueDate;
    @SerializedName("dtime")
    @Expose
    private String dtime;
    @SerializedName("dreason")
    @Expose
    private String dreason;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getDreason() {
        return dreason;
    }

    public void setDreason(String dreason) {
        this.dreason = dreason;
    }

}
