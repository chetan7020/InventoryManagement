package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminUserInfoBinding;

public class AdminUserInfoActivity extends AppCompatActivity {

    private ActivityAdminUserInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAdminUserInfoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {

    }
}