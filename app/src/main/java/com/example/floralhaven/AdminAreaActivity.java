package com.example.floralhaven;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.floralhaven.databinding.ActivityAdminAreaBinding;

public class AdminAreaActivity extends AppCompatActivity {

    private ActivityAdminAreaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAreaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.AddUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignUpActivity.SignUpIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.GoBackAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(),MainActivity.DEVICE_ID_DEFAULT));

            }
        });


    }


    static Intent AdminIntentFactory(Context context){
        return new Intent(context, AdminAreaActivity.class);
    }
}