package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class viewvillage {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("taluk")
    @Expose
    private String taluk;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("cno")
    @Expose
    private String cno;
    @SerializedName("cvv")
    @Expose
    private String cvv;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("mname")
    @Expose
    private String mname;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTaluk() {
        return taluk;
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @SerializedName("off_id")
    @Expose
    private String vId;
    @SerializedName("off_name")
    @Expose
    private String vName;
    @SerializedName("off_designation")
    @Expose
    private String vTaluk;
    @SerializedName("off_district")
    @Expose
    private String vDistrict;
    @SerializedName("off_state")
    @Expose
    private String vState;
    @SerializedName("off_place")
    @Expose
    private String vPlace;
    @SerializedName("off_pin")
    @Expose
    private String vPin;
    @SerializedName("off_mobile")
    @Expose
    private String vMobile;
    @SerializedName("off_username")
    @Expose
    private String vUsername;
    @SerializedName("off_password")
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
