package com.example.villageoffie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class login
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("uname")
    @Expose
    private String uname;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("utype")
    @Expose
    private String utype;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

}
