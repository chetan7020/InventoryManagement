package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminMainBinding;


public class AdminMainActivity extends AppCompatActivity {

    private ActivityAdminMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAdminMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {

        binding.adminCardItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, AdminItemsActivity.class));
//                startActivity(new Intent(AdminMainActivity.this, AdminItemInfoActivity.class));
            }
        });

        binding.adminCardUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, AdminUsersActivity.class));
            }
        });

        binding.adminCardRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, AdminItemRequestActivity.class));
            }
        });

        binding.adminCardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, AdminProfileActivity.class));
            }
        });

    }
}