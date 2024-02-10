package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminAddItemBinding;

public class AdminAddItemActivity extends AppCompatActivity {

    private ActivityAdminAddItemBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding= ActivityAdminAddItemBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {

    }
}