package com.example.onthegorocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> Data;

    public productAdapter(Context con, ArrayList<Product> prod){
        this.context = con;
        this.Data = prod;
    }
    @NonNull
    @Override
    public productAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_homepage, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(Data.get(position).getPrImg()).into(holder.imgViewProduct);
        holder.txtViewPrice.setText(Data.get(position).getPrice());
        holder.txtViewName.setText(Data.get(position).getPrName());
        holder.txtViewSize.setText(Data.get(position).getSize());

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewName, txtViewSize, txtViewPrice;
        ImageView imgViewProduct;
        public MyViewHolder(View itemView){
            super(itemView);
            imgViewProduct = (ImageView) itemView.findViewById(R.id.productImage);
            txtViewPrice = (TextView) itemView.findViewById(R.id.txtViewPrice);
            txtViewName = (TextView) itemView.findViewById(R.id.txtViewPrName);
            txtViewSize = (TextView) itemView.findViewById(R.id.txtViewSize);
        }
    }
}
