package com.example.appmusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appmusic.Model.User;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.Dataservice;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewDisplayName, textViewEmail, textViewPhone, textViewRole;
    private ImageView imageViewAvatar;
    private static final int REQUEST_EDIT_PROFILE = 1;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        initView();
        getUserInfo(1); // Giả sử id_user là 1
    }

    private void initView() {
        textViewDisplayName = findViewById(R.id.textViewDisplayName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewRole = findViewById(R.id.textViewRole);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        Button buttonEditProfile = findViewById(R.id.buttonEditProfile);
        buttonEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivityForResult(intent, REQUEST_EDIT_PROFILE);
        });
    }

    private void getUserInfo(int userId) {
        Dataservice dataservice = APIService.getService();
        Call<User> call = dataservice.getUserInfo(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    updateUserInfo(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch user info", t);
            }
        });
    }

    private void updateUserInfo(User user) {
        textViewDisplayName.setText(user.getDisplayname());
        textViewEmail.setText(user.getMail());
        textViewPhone.setText(user.getPhone());
        String roleText;
        Integer role = Integer.valueOf(user.getRole()); // Assuming getRole() returns Integer
        if (role != null && role.equals(1)) {
            roleText = "User";
        } else if (role != null && role.equals(0)) {
            roleText = "Admin";
        } else {
            roleText = "Unknown"; // Trường hợp nếu role không phải là 0 hoặc 1 hoặc null
        }
        textViewRole.setText( roleText);
        // Load avatar image
        String avatarUrl = user.getAvatar();
        if (avatarUrl != null && !avatarUrl.isEmpty()) {
            Picasso.with(this).load(avatarUrl).placeholder(R.drawable.profile).into(imageViewAvatar);
        } else {
            imageViewAvatar.setImageResource(R.drawable.profile); // Sử dụng hình ảnh mặc định nếu đường dẫn avatar rỗng
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK) {
            getUserInfo(1);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.userprofile) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
