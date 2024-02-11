package com.codizcdp.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminItemInfoBinding;
import com.codizcdp.inventorymanagement.model.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class AdminItemInfoActivity extends AppCompatActivity {

    private ActivityAdminItemInfoBinding binding;
    private String ID, name, total_quantity, available_quantity, desc;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminItemInfoBinding.inflate(getLayoutInflater());

        init();

        set();

        setEventLis();

        setContentView(binding.getRoot());
    }

    private void init() {
        item = (Item) getIntent().getSerializableExtra("item");

        ID = item.getID();
        name = item.getName();
        total_quantity = item.getTotal_quantity();
        available_quantity = item.getAvailable_quantity();
        desc = item.getDesc();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void set(){
        binding.tvId.setText(ID);
        binding.tvName.setText(name);
        binding.tvTotalQuantity.setText(total_quantity);
        binding.tvAvailableQuantity.setText(available_quantity);
        binding.tvDesc.setText(desc);
    }

    private void setEventLis() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminItemInfoActivity.this, AdminUpdateItemActivity.class);

                Item item= new Item(ID, name, total_quantity, available_quantity, desc);

                intent.putExtra("item", item);

                startActivity(intent);

            }
        });
    }
}