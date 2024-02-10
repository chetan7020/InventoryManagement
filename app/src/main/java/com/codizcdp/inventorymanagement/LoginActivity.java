package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAllItemsBinding;
import com.codizcdp.inventorymanagement.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                //If user is not admin
//                startActivity(new Intent(LoginActivity.this, UserMainActivity.class));
//                finish();

                //If user is admin
                startActivity(new Intent(LoginActivity.this, AdminMainActivity.class));
                finish();
            }
        });

        binding.tvRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //register user screen
                startActivity(new Intent(LoginActivity.this, RegisterUserActivity.class));
            }
        });

        binding.tvRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //register admin screen
                startActivity(new Intent(LoginActivity.this, RegisterAdminActivity.class));
            }
        });
    }
}