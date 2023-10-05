package com.example.retrofit_demo_2.ui;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.example.retrofit_demo_2.InstanceClass;

import com.example.retrofit_demo_2.Models.AddProductData;
import com.example.retrofit_demo_2.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductFragment extends Fragment {
    EditText t1, t2, t3;
    ImageView img;
    Button b1;
    TextView t4;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        t1 = container.findViewById(R.id.f_add_Product_name_edittext);
        t2 = container.findViewById(R.id.f_add_Product_Prize_edittext);
        t3 = container.findViewById(R.id.f_add_Product_des_edittext);
        img = container.findViewById(R.id._add_product_img);
        b1 = container.findViewById(R.id.f_add_product_btn);
        t4 = container.findViewById(R.id.f_add_view_product_text);

//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ActivityCompat.checkSelfPermission(getActivity(),
//                        android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
//                {
//                    requestPermissions(
//                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
//                            2000);
//                }
//                else {
//                    Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(cameraIntent,1000);
//                }
//            }
//
//        });

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                InstanceClass.API_Calling().addproductUser(t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),"abc.jpg").enqueue(new Callback<AddProductData>() {
//                    @Override
//                    public void onResponse(Call<AddProductData> call, Response<AddProductData> response) {
//
//
//                        if (response.body().getConnection() == 1)
//                        {
//                            if (response.body().getProductaddd() == 1)
//                            {
//                                Toast.makeText(getContext(), "Your Product Is Successful Add", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(getContext(), "Your Product Is Not Successful Add", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AddProductData> call, Throwable t) {
////                                Toast.makeText(AddProductFragment.this, "Your Product Is Not Successful Add", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK) {
//            if(requestCode == 1000){
//                Uri returnUri = data.getData();
//                Bitmap bitmapImage = null;
//                try {
//                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                img.setImageBitmap(bitmapImage);
//            }
//
//        }
//    }

}