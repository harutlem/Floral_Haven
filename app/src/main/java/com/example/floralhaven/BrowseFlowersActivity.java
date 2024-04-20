package com.example.floralhaven;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.floralhaven.database.FlowerDAO;
import com.example.floralhaven.database.FlowerRepository;
import com.example.floralhaven.database.entities.Flower;
import com.example.floralhaven.database.entities.User;
import com.example.floralhaven.databinding.ActivityBrowseFlowersBinding;

public class BrowseFlowersActivity extends AppCompatActivity {




    private int rose;
    private int tulip;
    private int lily;
    private int carnation;
    private ActivityBrowseFlowersBinding binding;

    private FlowerRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBrowseFlowersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository =  FlowerRepository.getRepository(getApplication());

        binding.goBackToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(),MainActivity.DEVICE_ID_DEFAULT));
            }

        });

        binding.addCarnationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                insertFlowerRecord();

            }
        });


    }

    private void insertFlowerRecord(){

        Flower flower = new Flower(rose, tulip, lily, carnation);
        repository.insertFlower(flower);
    }



    public void getInformationFromDisplay()
    {
        try {
            rose = Integer.parseInt(binding.roseQuantityEditText.getText().toString());
        }catch (NumberFormatException e)
        {
            Log.d("Flower", "Error reading value from edit text");
        }

        try {
            tulip = Integer.parseInt(binding.tulipQuantityEditText.getText().toString());
        }catch (NumberFormatException e)
        {
            Log.d("Flower", "Error reading value from edit text");
        }

        try {
            lily = Integer.parseInt(binding.lilyQuantityEditText.getText().toString());
        }catch (NumberFormatException e)
        {
            Log.d("Flower", "Error reading value from edit text");
        }

        try {
            carnation = Integer.parseInt(binding.carnationQuantityEditText.getText().toString());
        }catch (NumberFormatException e)
        {
            Log.d("Flower", "Error reading value from edit text");
        }

    }

    static Intent BrowseIntentFactory(Context context){
        return new Intent(context, BrowseFlowersActivity.class);
    }

}