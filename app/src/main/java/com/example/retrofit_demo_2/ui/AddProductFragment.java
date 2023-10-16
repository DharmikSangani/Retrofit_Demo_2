package com.example.retrofit_demo_2.ui;

import static android.app.Activity.RESULT_OK;

import static com.example.retrofit_demo_2.Activity.SplashScreen_Activity.editor;
import static com.example.retrofit_demo_2.Activity.SplashScreen_Activity.preferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.retrofit_demo_2.InstanceClass;


import com.example.retrofit_demo_2.Models.AddProductModels.AddProductData;
import com.example.retrofit_demo_2.R;


import java.io.ByteArrayOutputStream;

import okhttp3.internal.cache.DiskLruCache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProductFragment extends Fragment {
    EditText t1, t2, t3;
    ImageView img;
    Button b1;
    TextView t4;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_product, container, false);


        t1 = view.findViewById(R.id.f_add_Product_name_edittext);
        t2 = view.findViewById(R.id.f_add_Product_Prize_edittext);
        t3 = view.findViewById(R.id.f_add_Product_des_edittext);
        img = view.findViewById(R.id._add_product_img);
        b1 = view.findViewById(R.id.f_add_product_btn);
        t4 = view.findViewById(R.id.f_add_view_product_text);

        final CharSequence[] items = {"Camera", "Gallery"};


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                if (ContextCompat.checkSelfPermission(AddProductFragment.this.getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // If not granted, request the CAMERA permission
                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, 100);
                }
            }

        });
        /*if (t1.getText().toString().isEmpty()) {
            t1.setError("Enter Name");
        }
        if (t2.getText().toString().isEmpty()) {
            t2.setError("Enter Price");
        }
        if (t3.getText().toString().isEmpty()) {
            t3.setError("Enter Description");
        } else {*/
            Log.d("LLL", "Add cicke");
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("BBB", "onClick: AddBtn Clicked..");
                    Drawable drawable = img.getDrawable();
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();

                    // Step 2: Encode the byte array as a Base64 string
                    String base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    Log.d("HHH", "onClick: base64=" + base64Image);
                    String uid = preferences.getString("id", "1");
                    editor.commit();
                    InstanceClass.API_Calling().AddproductUser(uid, t1.getText().toString(), t2.getText().toString(), t3.getText().toString(), base64Image).enqueue(new Callback<AddProductData>() {
                        @Override
                        public void onResponse(Call<AddProductData> call, Response<AddProductData> response) {


                            if (response.body().getConnection() == 1) {
                                if (response.body().getProductaddd() == 1) {

                                    editor.putString("userid", uid);
                                    editor.commit();

                                    Toast.makeText(getContext(), "Your Product Is Successful Add", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Your Product Is Not Successful Add", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AddProductData> call, Throwable t) {
                            Toast.makeText(getContext(), "Your Product Is Not Successful Add", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        //}

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //for take image from gallery
        if (resultCode == Activity.RESULT_OK) {

            Log.i("GalleryCode", "" + requestCode);
            Uri ImageURI = data.getData();
            img.setImageURI(ImageURI);

        }

        // for take image from camera
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // Get the captured image from the intent's data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Do something with the captured image
            img.setImageBitmap(imageBitmap);
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