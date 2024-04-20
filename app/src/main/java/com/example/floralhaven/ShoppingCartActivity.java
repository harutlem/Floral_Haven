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

import com.example.floralhaven.database.FlowerRepository;
import com.example.floralhaven.databinding.ActivityShoppingCartBinding;

public class ShoppingCartActivity extends AppCompatActivity {

    private ActivityShoppingCartBinding binding;

    private FlowerRepository repository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShoppingCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository =  FlowerRepository.getRepository(getApplication());

        binding.goBackToMainActivitySC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(),MainActivity.DEVICE_ID_DEFAULT));

            }
        });


    }

    static Intent ShoppingIntentFactory(Context context){
        return new Intent(context, ShoppingCartActivity.class);
    }
}