package com.rtech.wallify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
    public CategoryFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView recyclerView =view.findViewById(R.id.Category_recycler_view);
        ProgressBar progressbar=view.findViewById(R.id.CategoryLoader);
        ArrayList<Category_Data> list=new ArrayList<>();
        StaggeredGridLayoutManager layout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        CategoryViewAdapter adapter=new CategoryViewAdapter(getContext(),list);
        progressbar.setVisibility(View.VISIBLE);
        AndroidNetworking.initialize(view.getContext());
        AndroidNetworking.get("https://wallify-ebon.vercel.app/api/category").setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                progressbar.setVisibility(View.GONE);
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject obj=response.getJSONObject(i);
                        list.add(new Category_Data(obj.getInt("id"),"https://wallify-ebon.vercel.app/api/links?q="+obj.getString("name"),obj.getString("image"),obj.getString("name")));
                    } catch (JSONException e) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(view.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onError(ANError anError) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "failed loading data", Toast.LENGTH_SHORT).show();

            }
        });


        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);


        return  view;
    }
}