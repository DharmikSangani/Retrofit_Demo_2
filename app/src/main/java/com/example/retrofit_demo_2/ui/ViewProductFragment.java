package com.example.retrofit_demo_2.ui;

import static com.example.retrofit_demo_2.Activity.SplashScreen_Activity.preferences;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit_demo_2.Adapter.ViewAdapter;
import com.example.retrofit_demo_2.InstanceClass;
import com.example.retrofit_demo_2.Models.ViewProductModel.Product_Data;
import com.example.retrofit_demo_2.Models.ViewProductModel.ViewProductData;
import com.example.retrofit_demo_2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewProductFragment extends Fragment {

    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_product, container, false);

        recyclerView= view.findViewById(R.id.f_view_product_recycler);

        Log.d("KKK", "onCreateView: ViewFragment");
        String uid = preferences.getString("id","0");
        Log.d("KKK", "onCreateView: uid ="+uid);

            InstanceClass.API_Calling().viewproductUser(uid).enqueue(new Callback<ViewProductData>() {
                @Override
                public void onResponse(Call<ViewProductData> call, Response<ViewProductData> response) {
                    Log.d("KKK", "onResponse: userid" + uid);
                    Log.d("KKK", "onResponse: productdata=" +response.body().getResult());

                    if (response.body().getConnection() == 1 && response.body().getResult() == 1) {

                        Toast.makeText(getContext(), "Data Available", Toast.LENGTH_LONG).show();


                        ViewAdapter adapter = new ViewAdapter(ViewProductFragment.this,response.body().getProductdata());
                        recyclerView.setAdapter(adapter);

                        LinearLayoutManager manager = new LinearLayoutManager(ViewProductFragment.this.getContext());
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(Call<ViewProductData> call, Throwable t) {
                    Log.e("KKK", "Error="+t.getLocalizedMessage());
                }
            });



        return view;
    }

}