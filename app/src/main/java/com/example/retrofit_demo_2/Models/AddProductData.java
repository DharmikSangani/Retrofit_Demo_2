
package com.example.retrofit_demo_2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddProductData {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("productaddd")
    @Expose
    private Integer productaddd;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getProductaddd() {
        return productaddd;
    }

    public void setProductaddd(Integer productaddd) {
        this.productaddd = productaddd;
    }

    @Override
    public String toString() {
        return "AddProductData{" +
                "connection=" + connection +
                ", productaddd=" + productaddd +
                '}';
    }
}
