package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codizcdp.inventorymanagement.databinding.ActivityUpdateUserProfileBinding;


public class UserUpdateProfileActivity extends AppCompatActivity {

    private ActivityUpdateUserProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_user_profile);
        binding= ActivityUpdateUserProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

}