package com.example.retrofit_demo_2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegisterData {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("result")
    @Expose
    private Integer result;

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

    @Override
    public String toString() {
        return "RegisterData{" +
                "connection=" + connection +
                ", result=" + result +
                '}';
    }
}