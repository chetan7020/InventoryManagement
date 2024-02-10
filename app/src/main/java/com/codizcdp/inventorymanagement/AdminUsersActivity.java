package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminUsersBinding;

public class AdminUsersActivity extends AppCompatActivity {

    private ActivityAdminUsersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAdminUsersBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {

    }
}