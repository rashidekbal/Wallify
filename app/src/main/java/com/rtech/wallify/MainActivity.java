package com.rtech.wallify;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;


import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
DrawerLayout drawerlayout;
BottomNavigationView bottomNavigationView;
NavigationView barView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            toolbar.setPadding(0, systemBars.top, 0, 0);
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(MainActivity.this,drawerlayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        barView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.feedback){
                    changeFragment(new feedbackFragment());

                }else{
                    changeFragment(new privacyFragment());

                }
                drawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home){
                   changeFragment(new homeFragment());
                } else if (item.getItemId()==R.id.Favourite) {
                    changeFragment(new favouriteFragment());

                }else{
                    changeFragment(new CategoryFragment());

                }

                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

    }



    private void init(){
        toolbar=findViewById(R.id.toolbar);
        drawerlayout=findViewById(R.id.main);
        bottomNavigationView=findViewById(R.id.Bottom_navigation);
        barView=findViewById(R.id.sideBar);

    }
    private void changeFragment(Fragment fragment){
        FragmentManager manager= getSupportFragmentManager();
        @SuppressLint("CommitTransaction") FragmentTransaction fragmentTransaction= manager.beginTransaction();

            fragmentTransaction.replace(R.id.container,fragment);

        fragmentTransaction.commit();
    }

}