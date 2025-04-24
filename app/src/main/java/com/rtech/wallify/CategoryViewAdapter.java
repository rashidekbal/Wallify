package com.rtech.wallify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.viewHolder> {

    ArrayList<Category_Data> data;
    Context context;
    public CategoryViewAdapter(Context context,ArrayList<Category_Data> list){
        this.context=context;
        this.data=list;
setHasStableIds(true);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.category_card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(data.get(position).image_link).placeholder(R.drawable.holder).into(holder.imageView);
         holder.desc.setText(data.get(position).desc);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page=new Intent(context,CategoryActivity.class);
                page.putExtra("api",data.get(position).api);
                context.startActivity(page);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView desc;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
           this.imageView=itemView.findViewById(R.id.Category_imageView);
           this.desc=itemView.findViewById(R.id.desc);
        }
    }
}
