package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codizcdp.inventorymanagement.databinding.ActivityRegisterUserBinding;

public class RegisterUserActivity extends AppCompatActivity {

    private ActivityRegisterUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityRegisterUserBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {
       binding.btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(RegisterUserActivity.this, UserMainActivity.class));
               finish();
           }
       });

       binding.tvLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(RegisterUserActivity.this, LoginActivity.class));
           }
       });

       binding.tvRegisterAdmin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(RegisterUserActivity.this, RegisterAdminActivity.class));
           }
       });
    }
}