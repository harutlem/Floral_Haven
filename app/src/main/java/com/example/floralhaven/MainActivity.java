package com.example.floralhaven;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.floralhaven.database.FlowerRepository;
import com.example.floralhaven.databinding.ActivityMainBinding;

import kotlinx.coroutines.flow.Flow;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.floralhaven.MAIN_ACTIVITY_USER_ID";
    ActivityMainBinding binding;

    private FlowerRepository repository;

    public static final String TAG = "HL_FLOWER";

    int loggedInUserId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginUser();

        if(loggedInUserId == -1)
        {
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }

        repository = FlowerRepository.getRepository(getApplication());



    }

    private void loginUser() {
        //Todo: create login method
        loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);
    }

    static Intent mainActivityIntentFactory(Context context, int userId)
    {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }



}