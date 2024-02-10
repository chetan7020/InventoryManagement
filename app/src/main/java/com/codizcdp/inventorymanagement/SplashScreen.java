package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.codizcdp.inventorymanagement.databinding.ActivityLoginBinding;
import com.codizcdp.inventorymanagement.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivitySplashScreenBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        }, 1000);
    }

    private void setEventLis() {
    }
}