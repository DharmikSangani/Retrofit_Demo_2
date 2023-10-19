package com.example.retrofit_demo_2;

import com.example.retrofit_demo_2.Models.ViewProductModel.Product_Data;

import java.util.List;

public interface Dialog_Interface
{
    void showDialog(int position, List<Product_Data> productdatalist);
}
