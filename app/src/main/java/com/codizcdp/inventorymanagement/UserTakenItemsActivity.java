package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.codizcdp.inventorymanagement.databinding.ActivityUserTakenItemsBinding;

public class UserTakenItemsActivity extends AppCompatActivity {

    private ActivityUserTakenItemsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityUserTakenItemsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    private void setEventLis() {
    }

}