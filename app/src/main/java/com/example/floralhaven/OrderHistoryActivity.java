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
import com.example.floralhaven.database.entities.OrderHistory;
import com.example.floralhaven.databinding.ActivityOrderHistoryBinding;

public class OrderHistoryActivity extends AppCompatActivity {

    private ActivityOrderHistoryBinding binding;

    private FlowerRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository =  FlowerRepository.getRepository(getApplication());

        binding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(),MainActivity.DEVICE_ID_DEFAULT));

            }
        });


        binding.orderHistoryView.setText(repository.toString());

    }



    static Intent OrderIntentFactory(Context context){
        return new Intent(context, OrderHistoryActivity.class);
    }
}