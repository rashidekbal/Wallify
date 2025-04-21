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
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class homeFragment extends Fragment {
RecyclerView recyclerView;

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

        String Url="https://pixabay.com/api/?key=47570294-7b13d4203e4690bc9b9af1531&q=nature&image_type=photo&orientation=vertical&per_page=100&page1";
        ArrayList<ImageData> ImageList=new ArrayList<>();

        WallpaperViewAdapter adapter=new WallpaperViewAdapter(ImageList,requireContext());
        recyclerView.setHasFixedSize(true);
        adapter.setHasStableIds(true);
        AndroidNetworking.initialize(view.getContext());
        AndroidNetworking.get(Url).setPriority(Priority.HIGH).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("res",response.toString());
                try {
                    JSONArray linksObjArray=response.getJSONArray("hits");
                    for (int i=0;i<linksObjArray.length();i++){
                        JSONObject imageObject = linksObjArray.getJSONObject(i);
                        int id=imageObject.getInt("id");
                        String previewlink=imageObject.getString("webformatURL");
                        String link=imageObject.getString("largeImageURL");
                        int height=imageObject.getInt("webformatHeight");
                        int width=imageObject.getInt("webformatWidth");
                        ImageList.add(new ImageData(id,previewlink,link,height,width));
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

        for(int i=0;i<ImageList.size();i++){
            Glide.with(this)
                    .load(ImageList.get(i).link)
                    .preload();
        }


        recyclerView.setAdapter(adapter);

        return view;
    }

}