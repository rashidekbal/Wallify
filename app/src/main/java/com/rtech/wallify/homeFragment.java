package com.rtech.wallify;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

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
        ArrayList<ImageData> ImageList=new ArrayList<>();
        ImageList.add(new ImageData("https://pixabay.com/get/g8feb5add22a2e99fd8101d785f5f84e9ade40d10898d1cee0fded62e08df4b97b69cf314137668a7bb0569872b12b5c3dc6e041c250456f19c9b8b88ceb174a6_640.jpg"));
        ImageList.add(new ImageData("https://pixabay.com/get/gdcd66ec41ae4e4506398117be5c09ece4321be67f71432c7d6c7d6fdc4a2298543de51bb442cea0a5961b61c31fd9e9b5a18765e4fd32c870caec0bfc7d23fc1_640.jpg"));
        ImageList.add(new ImageData("https://pixabay.com/get/ga57017cd54e15faa27e2865b40d5675757d555a23c9ac5e761aa780ec347800fece110f2e315040b3d546095177faf10fb9e83f69c794e997a19525745af9238_640.jpg"));
        ImageList.add(new ImageData("https://pixabay.com/get/ge12ee9dbfd70d1e16988db99eaeffde8443a52228e7dada02d53d90b8b11dd2c062f532add0bc1aba5def188399dce0016a8238f6ac746d12c28e1e6c7d61d32_640.jpg"));
        ImageList.add(new ImageData("https://pixabay.com/get/g0c682a3e2d926f41a24ced8dae774bbfa501260a1daf7bb39b744f1a12f6769aab7ba63fc1ed11d6158cab2a65da518710baa09cb7391b06ddf2c8848fd1ddcb_640.jpg"));
        WallpaperViewAdapter adapter=new WallpaperViewAdapter(ImageList,requireContext());
        recyclerView.setHasFixedSize(true);
        adapter.setHasStableIds(true);
        for(int i=0;i<ImageList.size();i++){
            Glide.with(this)
                    .load(ImageList.get(i).link)
                    .preload();
        }


        recyclerView.setAdapter(adapter);

        return view;
    }
}