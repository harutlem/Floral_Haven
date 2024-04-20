package com.example.floralhaven;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.floralhaven.database.FlowerRepository;
import com.example.floralhaven.database.entities.User;
import com.example.floralhaven.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private FlowerRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = FlowerRepository.getRepository(getApplication());

        binding.SignUpButtonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });



    }

    private void verifyUser()
    {
        String username = binding.userNameSignUpEditText.getText().toString();

        if(username.isEmpty())
        {
            Toast.makeText(this, "(Username should not be blank!", Toast.LENGTH_SHORT).show();
            return;
        }

        LiveData<User> userObserver = repository.getUserByUsername(username);
        userObserver.observe(this, user -> {
            if(user != null){
                if(!username.equals(user.getUserName())){
                    repository.insertUser(user);
                    startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
                }

            }

        });


    }

    static Intent SignUpIntentFactory(Context context){
        return new Intent(context, SignUpActivity.class);
    }

}