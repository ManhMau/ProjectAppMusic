package com.example.appmusic.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmusic.Fragment.Fragment_Banner;
import com.example.appmusic.Fragment.Fragment_Playlist;
import com.example.appmusic.Fragment.Fragment_Trang_Chu;
import com.example.appmusic.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Load the default fragment (Trang Chủ) when the app starts
        if (savedInstanceState == null) {
            loadFragment(new Fragment_Trang_Chu());
            navigationView.setCheckedItem(R.id.home);
        }

        // Handle the back press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        int id = item.getItemId();

        if (id == R.id.home) {

            selectedFragment = new Fragment_Trang_Chu();

        } else if (id == R.id.category) {

            selectedFragment = new Fragment_Banner();

        } else if (id == R.id.all_songs) {

            selectedFragment = new Fragment_Playlist();

        } else if (id == R.id.popular_songs) {

        } else if (id == R.id.favorite_songs) {

        } else if (id == R.id.userprofile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.change_password) {

        } else if (id == R.id.sign_out) {
        }
        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}