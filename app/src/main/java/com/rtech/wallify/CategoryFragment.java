package com.rtech.wallify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
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
        ArrayList<Category_Data> list=new ArrayList<>();
        list.add(new Category_Data(1," https://pixabay.com/api/?key=47570294-7b13d4203e4690bc9b9af1531&q=Dark+wallpapers&image_type=photo&orientation=vertical&per_page=100&page1","https://pixabay.com/get/gd8eac783c243960e54a7f425544adfd9ca9e16a41c692519cd5e9c1ee0d4bbe9d2ef48e97064ba1e3ec129ac14fa8369_640.jpg","Dark Vibes"));
        list.add(new Category_Data(2," https://pixabay.com/api/?key=47570294-7b13d4203e4690bc9b9af1531&q=space+galaxy&image_type=photo&orientation=vertical&per_page=100&page1","https://pixabay.com/get/gb719e064cba4f9291f296e410f46783df50cbb1293acdc38892d2debf6adeb2e3c88e3296dc22adee4d5d18204cd6ad5798aa3157a31849f6efab861416e2bb3_640.jpg","Space and Galaxy"));
        list.add(new Category_Data(3," https://pixabay.com/api/?key=47570294-7b13d4203e4690bc9b9af1531&q=lion&image_type=photo&orientation=vertical&per_page=100&page1","https://pixabay.com/get/g03f849aeb640ea4d8286cecf62c96c1d6b9ebf0bc71b0201a78c22c1067450cea315fd64a08da85a3cef4a9244c8e2979ea99a93bee2079706e63d8fbfed685a_640.jpg","Lion king"));
        list.add(new Category_Data(4," https://pixabay.com/api/?key=47570294-7b13d4203e4690bc9b9af1531&q=puppies&image_type=photo&orientation=vertical&per_page=100&page1","https://pixabay.com/get/g864ff8cfdcc245aa70cd7bae829ca8e117f7acb0bb72f3025ef1184b4fbcc6576324d08e3c700322cb7e12b765a20c5bad4079b2b8b3320254208a2b3676d076_640.jpg","Dogs"));
        list.add(new Category_Data(5," https://pixabay.com/api/?key=47570294-7b13d4203e4690bc9b9af1531&q=Mountains&image_type=photo&orientation=vertical&per_page=100&page1","https://pixabay.com/get/gafbcd79deb22375743c0f0fcc73a00f509e54d407e29d255b0ddcb6f3dbf81134b3dbdcb6a6189f1a9e37f01908c83c9c1913d84c7c0f7e5a6d9acd46bdbda42_640.jpg","Mountains"));
        list.add(new Category_Data(6," https://pixabay.com/api/?key=47570294-7b13d4203e4690bc9b9af1531&q=roads&image_type=photo&orientation=vertical&per_page=100&page1","https://pixabay.com/get/g34eb09fc0089ceee7ab718f55b7dd4171e994f4e90059c478e37a3eb52c27f298a54e8498532e06d52b2756a4092b76b64a5523d3a02c5940cec0935c6ed4c79_640.jpg","Road to heaven"));


        StaggeredGridLayoutManager layout=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        CategoryViewAdapter adapter=new CategoryViewAdapter(getContext(),list);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);


        return  view;
    }
}