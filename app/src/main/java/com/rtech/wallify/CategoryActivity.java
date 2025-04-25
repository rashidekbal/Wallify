package com.rtech.wallify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setActionBar(toolbar);
        recyclerView=findViewById(R.id.recyclerview_Catagoryactivity);
        progressbar=findViewById(R.id.categoryActivityLoader);
        Intent recieved=getIntent();
        String Url=recieved.getStringExtra("api");
        ArrayList<ImageData> list=new ArrayList<>();
        StaggeredGridLayoutManager layout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        WallpaperViewAdapter adapter =new WallpaperViewAdapter(list,this);
        progressbar.setVisibility(View.VISIBLE);
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(Url).setPriority(Priority.HIGH).build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressbar.setVisibility(View.GONE);
                        for(int i=0; i<response.length();i++){
                            try {
                                JSONObject obj=response.getJSONObject(i);
                                list.add(new ImageData(obj.getInt("id"),obj.getString("previewLink"), obj.getString("largeImage"), obj.getInt("largeHeight"), obj.getInt("largeWidth") ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "failed loading data", Toast.LENGTH_SHORT).show();

                    }
                });

        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);




    }
}