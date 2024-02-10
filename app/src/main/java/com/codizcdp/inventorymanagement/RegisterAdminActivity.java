package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.rtt.RangingRequest;
import android.os.Bundle;
import android.view.View;

import com.codizcdp.inventorymanagement.databinding.ActivityRegisterAdminBinding;

public class RegisterAdminActivity extends AppCompatActivity {
    private ActivityRegisterAdminBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityRegisterAdminBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {
        binding.btnRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get all inputs

            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to login screen
                startActivity(new Intent(RegisterAdminActivity.this, LoginActivity.class));
            }
        });

        binding.tvRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to user register screen
                startActivity(new Intent(RegisterAdminActivity.this, RegisterUserActivity.class));
            }
        });
    }
}