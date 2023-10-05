package com.example.retrofit_demo_2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit_demo_2.Adapter.ViewAdapter;
import com.example.retrofit_demo_2.InstanceClass;
import com.example.retrofit_demo_2.Models.ViewProductData;
import com.example.retrofit_demo_2.Models.Product_Data;
import com.example.retrofit_demo_2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;


public class ViewProductFragment extends Fragment {


    ViewAdapter adapter;
    RecyclerView recyclerView;
    EditText t1, t2, t3;
    ImageView img;
    Url resultUri;
    Product_Data productData;
    List<Product_Data> productdatalist=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        InstanceClass.API_Calling().viewproductUser("5").enqueue(new Callback<ViewProductData>() {
            @Override
            public void onResponse(Call<ViewProductData> call, Response<ViewProductData> response) {

            }

            @Override
            public void onFailure(Call<ViewProductData> call, Throwable t) {

            }
        });


        return inflater.inflate(R.layout.fragment_view_product,container,false);
    }
}