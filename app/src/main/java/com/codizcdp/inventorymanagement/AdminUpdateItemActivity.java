package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminUpdateItemBinding;
import com.codizcdp.inventorymanagement.model.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdminUpdateItemActivity extends AppCompatActivity {

    private ActivityAdminUpdateItemBinding binding;

    private String ID, name, total_quantity, available_quantity, desc;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private Item existing_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminUpdateItemBinding.inflate(getLayoutInflater());

        init();

        setEventLis();

        set();

        setContentView(binding.getRoot());
    }

    private void init() {
        existing_item = (Item) getIntent().getSerializableExtra("item");

        ID = existing_item.getID();
        name = existing_item.getName();
        total_quantity = existing_item.getTotal_quantity();
        available_quantity = existing_item.getAvailable_quantity();
        desc = existing_item.getDesc();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void set() {
        binding.etName.setText(name);
        binding.etQuantity.setText(total_quantity);
        binding.etDesc.setText(desc);
    }

    private int get() {
        int curr_total_quantity = Integer.parseInt(binding.etQuantity.getText().toString().trim());
        int in_total_quantity= Integer.parseInt(total_quantity);
        if ( in_total_quantity <= curr_total_quantity) {
            total_quantity = binding.etQuantity.getText().toString().trim();
            available_quantity = Integer.toString(Integer.parseInt(available_quantity)+(curr_total_quantity-in_total_quantity));
        } else {
            return 0;
        }

        name = binding.etName.getText().toString().trim();
        desc = binding.etDesc.getText().toString().trim();

        return 1;
    }

    private void update() {
        Map<String, Object> data = new HashMap<>();

        data.put("name", name);
        data.put("total_quantity", total_quantity);
        data.put("available_quantity", available_quantity);
        data.put("desc", desc);

        firebaseFirestore
                .collection("Admin")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("Items")
                .document(ID)
                .update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "Admin : Update Item (Success)");
                        Toast.makeText(AdminUpdateItemActivity.this, "Item Updated", Toast.LENGTH_SHORT).show();
                        binding.etName.setText("");
                        binding.etQuantity.setText("");
                        binding.etDesc.setText("");
                        startActivity(new Intent(AdminUpdateItemActivity.this, AdminItemsActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "MSG : " + e.getMessage());
                        Toast.makeText(AdminUpdateItemActivity.this, "Failed to Update Item", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setEventLis() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (get() == 1) {
                    Log.d("TAG", "Get Success");
                    update();
                } else {
                    Log.d("TAG", "Get Failed");
                    Toast.makeText(AdminUpdateItemActivity.this, "New Total Quantity is less than Previous Quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}