package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminUpdateProfileBinding;
public class AdminUpdateProfileActivity extends AppCompatActivity {

    private ActivityAdminUpdateProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAdminUpdateProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {

    }
}