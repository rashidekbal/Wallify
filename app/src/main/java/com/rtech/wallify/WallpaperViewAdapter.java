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
public class WallpaperViewAdapter extends RecyclerView.Adapter<WallpaperViewAdapter.viewHolder> {
  Context context;
  ArrayList<ImageData> Datalist;
  public WallpaperViewAdapter(ArrayList<ImageData> list, Context c){
      context=c;
      Datalist=list;
      setHasStableIds(true);

  }

  @Override
  public long getItemId(int position) {
    return Datalist.get(position).id;
  }

  @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view=LayoutInflater.from(context).inflate(R.layout.wallpaper_card,parent,false);
      return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
//int availableWidth=holder.imageview.getWidth();
//int height=Datalist.get(position).height;
//int width=Datalist.get(position).width;
//int finalHeight=Math.round(height*(availableWidth/width));
//ViewGroup.LayoutParams params=holder.imageview.getLayoutParams();
//params.height=finalHeight;
//params.width=availableWidth;
//holder.imageview.setLayoutParams(params);

      Glide.with(context)
              .load(Datalist.get(position).PreviewLink)
              .placeholder(R.drawable.holder) // smooth look
              .into(holder.imageview);

      holder.imageview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent nextpage=new Intent(context,WallpaperAction.class);
        String link =Datalist.get(position).link;
        nextpage.putExtra("Imageurl",link);
        context.startActivity(nextpage);

      }
    });

    }

    @Override
    public int getItemCount() {
      return (Datalist != null) ? Datalist.size() : 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
      ImageView imageview;
      TextView text;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.image_view);

        }
    }
}
