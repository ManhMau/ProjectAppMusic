package com.example.appmusic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appmusic.Model.User;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIService;
import com.example.appmusic.Service.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editTextDisplayName, editTextEmail, editTextPhone, editTextAvatar;
    private Button buttonSave;
    private User currentUser; // To store current user information

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_custom);
            getSupportActionBar().setTitle("Edit Profile");
        }

        // Initialize views
        initView();

        int userId = 1; // Replace with actual user ID
        getUserInfo(userId); // Fetch user info from server

        buttonSave.setOnClickListener(v -> {
            if (currentUser != null) {
                // Get updated values from EditText fields
                String displayName = editTextDisplayName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String avatar = editTextAvatar.getText().toString().trim();

                // Update user profile with current user ID and updated fields
                updateUserProfile(userId, displayName, email, phone, avatar);
            }
        });
    }

    private void initView() {
        editTextDisplayName = findViewById(R.id.editTextDisplayName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAvatar = findViewById(R.id.editTextAvatar);
        buttonSave = findViewById(R.id.buttonSave);
    }

    private void getUserInfo(int userId) {
        Dataservice dataservice = APIService.getService();
        Call<User> call = dataservice.getUserInfo(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentUser = response.body();
                    // Update EditText fields with current user information
                    updateUIWithUserData(currentUser);
                } else {
                    Toast.makeText(EditProfileActivity.this, "Failed to fetch user info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch user info", t);
                Toast.makeText(EditProfileActivity.this, "Failed to fetch user info", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUIWithUserData(User user) {
        editTextDisplayName.setText(user.getDisplayname());
        editTextEmail.setText(user.getMail());
        editTextPhone.setText(user.getPhone());
        editTextAvatar.setText(user.getAvatar()); // Update with avatar URL or other relevant field
    }

    private void updateUserProfile(int userId, String displayName, String email, String phone, String avatar) {
        Dataservice dataservice = APIService.getService();
        Call<User> call = dataservice.updateUserInfo(userId, displayName, email, phone, avatar);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User updatedUser = response.body();
                    Intent intent = new Intent();
                    intent.putExtra("UPDATED_USER", updatedUser);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // Handle error
                    String errorMessage = "Failed to update profile";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (Exception e) {
                            Log.e("API_ERROR", "Failed to parse error response", e);
                        }
                    }
                    Toast.makeText(EditProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API_ERROR", "Failed to update profile", t);
                Toast.makeText(EditProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
