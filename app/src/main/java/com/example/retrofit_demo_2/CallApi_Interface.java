package com.example.retrofit_demo_2;


import com.example.retrofit_demo_2.Models.AddProductModels.AddProductData;
import com.example.retrofit_demo_2.Models.DeleteData;
import com.example.retrofit_demo_2.Models.LoginData;
import com.example.retrofit_demo_2.Models.RegisterData;
import com.example.retrofit_demo_2.Models.UpdateData;
import com.example.retrofit_demo_2.Models.ViewProductModel.ViewProductData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CallApi_Interface {
    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterData> registerUser(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> loginUser(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<AddProductData> AddproductUser(@Field("userid") String userid,
                                        @Field("pname") String pname,
                                        @Field("pprize") String pprize,
                                        @Field("pdes") String pdes,
                                        @Field("productimage") String productimage);

    @FormUrlEncoded
    @POST("viewProduct.php")
    Call<ViewProductData> viewproductUser(@Field("userid") String UID);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<UpdateData> updateuser(@Field("id") String id,
                                @Field("name") String name,
                                @Field("price") String price,
                                @Field("description") String description,
                                @Field("imagedata") String imagedata,
                                @Field("imagename") String imgName);

    @FormUrlEncoded
    @POST("deleteproduct.php")
    Call<DeleteData> deleteuser(@Field("id") String id);
}
