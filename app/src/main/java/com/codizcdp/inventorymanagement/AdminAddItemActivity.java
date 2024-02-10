package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminAddItemBinding;
import com.codizcdp.inventorymanagement.model.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class AdminAddItemActivity extends AppCompatActivity {

    private ActivityAdminAddItemBinding binding;
    private String id, name, total_quantity, available_quantity, description;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminAddItemBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        setEventLis();
    }

    private void init() {
        Log.d("TAG", "initing");

        id = name = total_quantity = available_quantity = description = "-1";

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Log.d("TAG", "inited");
    }

    private void getText() {
        id = UUID.randomUUID().toString();
        name = binding.etName.getText().toString().trim();
        total_quantity = binding.etQuantity.getText().toString().trim();
        available_quantity = total_quantity;
        description = binding.etDescription.getText().toString().trim();
    }

    private void setText() {
        binding.etName.setText("");
        binding.etQuantity.setText("");
        binding.etDescription.setText("");
    }

    private int check() {
        if (name.equals("-1") || total_quantity.equals("-1") || description.equals("-1")) return 1;
        return 0;
    }

    private void addItem() {
        firebaseFirestore
                .collection("Admin")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("Items")
                .add(new Item(id, name, total_quantity, available_quantity, description))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "Admin : Item Add (Success)");
                        Toast.makeText(AdminAddItemActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                        setText();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Admin : Item Add (Failed)");
                        Toast.makeText(AdminAddItemActivity.this, "Failed to Add Item", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setEventLis() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();

                if (check() == 1) {
                    Toast.makeText(AdminAddItemActivity.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    addItem();
                }
            }
        });
    }
}