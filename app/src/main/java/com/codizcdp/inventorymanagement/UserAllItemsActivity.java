package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codizcdp.inventorymanagement.databinding.ActivityAllItemsBinding;


public class UserAllItemsActivity extends AppCompatActivity {

    private ActivityAllItemsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAllItemsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {
    }
}