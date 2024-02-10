package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminUpdateProfileBinding;
import com.codizcdp.inventorymanagement.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminUpdateProfileActivity extends AppCompatActivity {

    private ActivityAdminUpdateProfileBinding binding;

    String name;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminUpdateProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        setPreviousData();

        setEventLis();
    }

    private void init() {
        name = "-1";

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void setPreviousData() {
        firebaseFirestore
                .collection("Admin")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot doc) {
                        Student admin = doc.toObject(Student.class);

                        binding.etName.setText(admin.getName());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void getText() {
        name = binding.etName.getText().toString().trim();
    }

    private void setText() {
        binding.etName.setText("");
    }

    private void setEventLis() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();

                Map<String, Object> data = new HashMap<>();

//                data.put("class_name", class_name);
                data.put("name", name);
//                data.put("roll_no", roll_no);

                firebaseFirestore
                        .collection("Admin")
                        .document(firebaseAuth.getCurrentUser().getEmail())
                        .update(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "Admin : Update Profile (Success)");
                                Toast.makeText(AdminUpdateProfileActivity.this, "Profiled Updated", Toast.LENGTH_SHORT).show();
                                setText();
                                startActivity(new Intent(AdminUpdateProfileActivity.this, AdminProfileActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "Admin : Update Profile (Failed)");
                                Toast.makeText(AdminUpdateProfileActivity.this, "Failed to Update Profile", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}