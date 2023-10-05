package com.example.retrofit_demo_2;

import com.example.retrofit_demo_2.Models.AddProductData;
import com.example.retrofit_demo_2.Models.LoginData;
import com.example.retrofit_demo_2.Models.RegisterData;
import com.example.retrofit_demo_2.Models.ViewProductData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CallApi_Interface
{
    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterData> registerUser(@Field("name") String name,
                                    @Field("email")String email,
                                    @Field("password")String password);
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> loginUser(@Field("email")String email,
                              @Field("password")String password);

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<AddProductData> addproductUser(@Field("userid")String userid,
                                        @Field("pname")String pname,
                                        @Field("pprize")String pprize,
                                        @Field("pdes")String pdes);

    @FormUrlEncoded
    @POST("viewProduct.php")
    Call<ViewProductData> viewproductUser (@Field("uid")String UID);
}
