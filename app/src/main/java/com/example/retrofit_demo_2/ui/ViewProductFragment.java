package com.example.retrofit_demo_2.ui;

import static android.app.Activity.RESULT_OK;
import static com.example.retrofit_demo_2.Activity.SplashScreen_Activity.preferences;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_demo_2.Adapter.ViewAdapter;
import com.example.retrofit_demo_2.Dialog_Interface;
import com.example.retrofit_demo_2.InstanceClass;
import com.example.retrofit_demo_2.Models.UpdateData;
import com.example.retrofit_demo_2.Models.ViewProductModel.Product_Data;
import com.example.retrofit_demo_2.Models.ViewProductModel.ViewProductData;
import com.example.retrofit_demo_2.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewProductFragment extends Fragment {

    RecyclerView recyclerView;
    int pos;
    List<Product_Data> list;
    ImageView eimg;


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


                        ViewAdapter adapter = new ViewAdapter(ViewProductFragment.this,response.body().getProductdata(), new Dialog_Interface() {
                            @Override
                            public void showDialog(int position, List<Product_Data> productdatalist) {
                                pos=position;
                                list=productdatalist;
                                createDialog();
                            }
                        });
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

    private void createDialog()
    {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.update_layout);

        EditText ename, eprice, edes;

        Button btn;

        ename = dialog.findViewById(R.id.update_name);
        eprice = dialog.findViewById(R.id.update_price);
        edes = dialog.findViewById(R.id.update_des);
        eimg = dialog.findViewById(R.id.update_img);
        btn = dialog.findViewById(R.id.update_product_btn);

        ename.setText(list.get(pos).getPname());
        eprice.setText(list.get(pos).getPprice());
        edes.setText(list.get(pos).getPdes());

        Glide
                .with(getContext())
                .load("https://dharmikandroid.000webhostapp.com/MyApp/" + list.get(pos).getPimg())
                .centerCrop()
                .placeholder(R.drawable.animation)
                .into(eimg);
        eimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"Camera", "Gallery"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Image");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("Camera")) {
                            launchCamera();

                        } else if (items[which].equals("Gallery")) {

                            Intent GalleryIntent = null;
                            GalleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            GalleryIntent.setType("image/*");
                            GalleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(GalleryIntent, 0);
                        }
                    }
                });
                builder.show();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MMM", "onResponse: btn click"+btn);


                InstanceClass.API_Calling().updateuser(list.get(pos).getId(),
                                list.get(pos).getPname(),
                                list.get(pos).getPprice(),
                                list.get(pos).getPdes(),
                                list.get(pos).getPimg())
                        .enqueue(new Callback<UpdateData>() {


                            @Override
                            public void onResponse(Call<UpdateData> call, Response<UpdateData> response) {

                                Toast.makeText(getContext(), "Data Update", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<UpdateData> call, Throwable t) {

                            }
                        });

            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //for take image from gallery
        if (resultCode == Activity.RESULT_OK) {

            Log.i("GalleryCode", "" + requestCode);
            Uri ImageURI = data.getData();
            eimg.setImageURI(ImageURI);

        }

        // for take image from camera
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // Get the captured image from the intent's data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Do something with the captured image
            eimg.setImageBitmap(imageBitmap);
        }


    }

    private void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 100);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with launching the camera
                launchCamera();
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
            }
        }
    }

}