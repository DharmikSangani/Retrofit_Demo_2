package com.example.retrofit_demo_2.Models;

public class Product_Data {
    Integer id;
    String uid;
    String pname;
    String pdes;
    String pprice;
    String pimg;

    public Product_Data(Integer id, String uid, String pname, String pdes, String pprice, String pimg) {
        this.id = id;
        this.uid = uid;
        this.pname = pname;
        this.pdes = pdes;
        this.pprice = pprice;
        this.pimg = pimg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdes() {
        return pdes;
    }

    public void setPdes(String pdes) {
        this.pdes = pdes;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    @Override
    public String toString() {
        return "Product_Data{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", pname='" + pname + '\'' +
                ", pdes='" + pdes + '\'' +
                ", pprice='" + pprice + '\'' +
                ", pimg='" + pimg + '\'' +
                '}';
    }
}
