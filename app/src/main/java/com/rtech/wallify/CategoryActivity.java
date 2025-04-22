package com.rtech.wallify;

import android.content.Intent;
import android.os.Bundle;
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
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;

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
        Intent recieved=getIntent();
        String Url=recieved.getStringExtra("api");
        ArrayList<ImageData> list=new ArrayList<>();
        StaggeredGridLayoutManager layout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        WallpaperViewAdapter adapter =new WallpaperViewAdapter(list,this);
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(Url).setPriority(Priority.HIGH).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array=response.getJSONArray("hits");
                    for (int i=0;i<array.length();i++){
                        JSONObject obj=array.getJSONObject(i);
                        list.add(new ImageData(obj.getInt("id"),
                                obj.getString("webformatURL"),
                                obj.getString("largeImageURL"),
                                obj.getInt("webformatHeight"),
                                obj.getInt("webformatWidth") ));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });

        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);




    }
}