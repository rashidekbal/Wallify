package com.rtech.wallify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class homeFragment extends Fragment{
RecyclerView recyclerView;
int page=1;
String Url;
    ArrayList<ImageData> ImageList;
    public homeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);

        Url="https://wallify-ebon.vercel.app/api/link?q=nature";
       ImageList=new ArrayList<>();

        WallpaperViewAdapter adapter=new WallpaperViewAdapter(ImageList,requireContext());
        recyclerView.setHasFixedSize(true);
        adapter.setHasStableIds(true);
        AndroidNetworking.initialize(view.getContext());
        AndroidNetworking.get(Url).addQueryParameter("page","1").setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0; i<response.length();i++){
                            try {
                                JSONObject obj=response.getJSONObject(i);
                                ImageList.add(new ImageData(obj.getInt("id"),obj.getString("previewLink"), obj.getString("largeImage"), obj.getInt("largeHeight"), obj.getInt("largeWidth") ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
// ImageList.add(new ImageData(id,previewlink,link,height,width));
        for(int i=0;i<ImageList.size();i++){
            Glide.with(this)
                    .load(ImageList.get(i).link)
                    .preload();
        }


        recyclerView.setAdapter(adapter);

        return view;
    }



}