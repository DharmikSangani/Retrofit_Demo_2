
package com.example.retrofit_demo_2.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginData {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("userdata")
    @Expose
    private UserData userdata;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public UserData getUserdata() {
        return userdata;
    }

    public void setUserdata(UserData userdata) {
        this.userdata = userdata;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "connection=" + connection +
                ", result=" + result +
                ", userdata=" + userdata +
                '}';
    }
}
