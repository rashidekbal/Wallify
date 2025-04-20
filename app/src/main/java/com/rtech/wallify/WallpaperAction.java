package com.rtech.wallify;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;

import java.io.IOException;

public class WallpaperAction extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wallpaper_action);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView=findViewById(R.id.PreView_img);
        Intent intent=getIntent();
        String link=intent.getStringExtra("Imageurl");
        Button btn=findViewById(R.id.setAction);
        Glide.with(this)
                .load(link)
                .placeholder(R.drawable.holder) // smooth look
                .into(imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            Context context=getApplicationContext();
            String imageUrl=link;

            @Override
            public void onClick(View v) {
                Glide.with(context)
                        .asBitmap()
                        .load(imageUrl)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                                // Resize image to fit screen size
                                Glide.with(context)
                                        .asBitmap()
                                        .load(imageUrl) // Your image URL
                                        .into(new CustomTarget<Bitmap>() {


                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                                                // Get screen width and height
                                                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                                                int screenWidth = displayMetrics.widthPixels;
                                                int screenHeight = displayMetrics.heightPixels;

                                                // Adding padding to prevent cropping (adjust padding ratio as needed)
                                                Bitmap resizedBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
                                                Canvas canvas = new Canvas(resizedBitmap);
                                                float scaleX = (float) screenWidth / resource.getWidth();
                                                float scaleY = (float) screenHeight / resource.getHeight();
                                                float scale = Math.max(scaleX, scaleY); // To make sure it's scaled without losing ratio

                                                float dx = (screenWidth - resource.getWidth() * scale) / 2;
                                                float dy = (screenHeight - resource.getHeight() * scale) / 2;

                                                canvas.translate(dx, dy);
                                                canvas.scale(scale, scale);
                                                canvas.drawBitmap(resource, 0, 0, null);

                                                // Alert Dialog for choose option
                                                new AlertDialog.Builder(WallpaperAction.this)
                                                        .setTitle("Set Wallpaper 💖")
                                                        .setMessage("Where do you wanna set this sexy wallpaper?")
                                                        .setPositiveButton("Home Screen", (dialog, which) -> {
                                                            setWallpaper(resizedBitmap, WallpaperManager.FLAG_SYSTEM);
                                                        })
                                                        .setNegativeButton("Lock Screen", (dialog, which) -> {
                                                            setWallpaper(resizedBitmap, WallpaperManager.FLAG_LOCK);
                                                        })
                                                        .setNeutralButton("Both", (dialog, which) -> {
                                                            setWallpaper(resizedBitmap, WallpaperManager.FLAG_SYSTEM | WallpaperManager.FLAG_LOCK);
                                                        })
                                                        .show();
                                            }

                                            @Override
                                            public void onLoadCleared(@Nullable Drawable placeholder) {}
                                        });

                            }



                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {}
                        });




            }
        });
    }
    private void setWallpaper(Bitmap bitmap, int flag) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setBitmap(bitmap, null, true, flag);
            Toast.makeText(this, "Wallpaper set successfully 💥", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error setting wallpaper 😩", Toast.LENGTH_SHORT).show();
        }
    }

}