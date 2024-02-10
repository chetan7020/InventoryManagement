package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminProfileBinding;
import com.codizcdp.inventorymanagement.model.Admin;
import com.codizcdp.inventorymanagement.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminProfileActivity extends AppCompatActivity {

    private ActivityAdminProfileBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAdminProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        setData();

        setEventLis();
    }

    private void init(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void setData() {
        firebaseFirestore
                .collection("Admin")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot doc) {
                        Log.d("TAG", "Admin Profile : Data Get (Success)");

                        Admin admin = doc.toObject(Admin.class);

                        binding.tvName.setText(admin.getName());
                        binding.tvCollageCode.setText(admin.getCollage_code());
                        binding.tvEmail.setText(firebaseAuth.getCurrentUser().getEmail());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Admin Profile : Data Get (Failed)");
                    }
                });
    }


    private void setEventLis() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProfileActivity.this, AdminUpdateProfileActivity.class));
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(AdminProfileActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminProfileActivity.this, LoginActivity.class));
            }
        });

    }
}