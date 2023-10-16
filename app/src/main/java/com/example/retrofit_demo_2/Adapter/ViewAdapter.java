package com.example.retrofit_demo_2.Adapter;



import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_demo_2.Models.ViewProductModel.Product_Data;
import com.example.retrofit_demo_2.R;
import com.example.retrofit_demo_2.ui.ViewProductFragment;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.RecycleHolder> {

    ViewProductFragment viewProductFragment;
    List<Product_Data> productdatalist;

    public ViewAdapter(ViewProductFragment viewProductFragment, List<Product_Data> productdatalist) {
        this.viewProductFragment = viewProductFragment;
        this.productdatalist = productdatalist;
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
                .load("https://dharmikandroid.000webhostapp.com/MyApp/"+productdatalist.get(position).getPimg())
                .centerCrop()
                .placeholder(R.drawable.animation)
                .into(holder.img);

        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(viewProductFragment.getContext(),holder.img2);
                viewProductFragment.getContext().getMenuInflater().inflate(R.menu.view_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId()==R.id.menu_update){

                        }
                        if (menuItem.getItemId()==R.id.menu_dalete){

                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productdatalist.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        ImageView img,img2;
        public RecycleHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.item_view_p_name);
            t2 = itemView.findViewById(R.id.item_view_P_Prize);
            t3 = itemView.findViewById(R.id.item_view_P_des);
            img = itemView.findViewById(R.id.item_view_p_img);
            img2 = itemView.findViewById(R.id.item_v_p_menu);
        }
    }
}
