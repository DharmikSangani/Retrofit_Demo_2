package com.example.retrofit_demo_2.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_demo_2.Models.Product_Data;
import com.example.retrofit_demo_2.R;
import com.example.retrofit_demo_2.ui.ViewProductFragment;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.RecycleHolder> {
    private static ViewAdapter adapter;
    ViewProductFragment viewProductFragment;
    List<Product_Data> productdatalist;

    public ViewAdapter(com.example.retrofit_demo_2.ui.ViewProductFragment viewProductFragment, List<Product_Data> productdatalist) {
        this.viewProductFragment = viewProductFragment;
        this.productdatalist = productdatalist;
    }

    public static void setAdapter(ViewAdapter adapter) {
        ViewAdapter.adapter = adapter;
    }

    public static ViewAdapter getAdapter() {
        return adapter;
    }


    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_item,parent,false);
        RecycleHolder holder = new RecycleHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, int position) {
        holder.t1.setText(""+productdatalist.get(position).getPname());
        holder.t2.setText(""+productdatalist.get(position).getPprice());
        holder.t3.setText(""+productdatalist.get(position).getPdes());

        Glide
                .with(viewProductFragment)
                .load(productdatalist.get(position).getPimg())
                .centerCrop()
                .placeholder(R.drawable.animation)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return productdatalist.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        ImageView img;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.item_view_p_name);
            t2 = itemView.findViewById(R.id.item_view_P_Prize);
            t3 = itemView.findViewById(R.id.item_view_P_des);
            img = itemView.findViewById(R.id.item_view_p_img);
        }
    }
}
