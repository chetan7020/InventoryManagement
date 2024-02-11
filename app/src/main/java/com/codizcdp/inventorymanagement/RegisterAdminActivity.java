package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityRegisterAdminBinding;
import com.codizcdp.inventorymanagement.model.Admin;
import com.codizcdp.inventorymanagement.model.Student;
import com.codizcdp.inventorymanagement.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterAdminActivity extends AppCompatActivity {
    private ActivityRegisterAdminBinding binding;
    private String name, collage_code, email, pass;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterAdminBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        setEventLis();
    }

    private void init() {
        Log.d("TAG", "initing");

        name = collage_code = email = pass = "-1";

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Log.d("TAG", "inited");
    }

    private void getText() {
        name = binding.etName.getText().toString().trim();
        collage_code = binding.etCollageCode.getText().toString().trim();
        email = binding.etEmail.getText().toString().trim();
        pass = binding.etPassword.getText().toString().trim();
    }

    private int check() {
        if (name.equals("-1") || collage_code.equals("-1") || email.equals("-1") || pass.equals("-1"))
            return 1;
        return 0;
    }

    private void register() {
        firebaseAuth
                .createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d("TAG", "User Register : Admin (Success)");
                        firebaseFirestore
                                .collection("User")
                                .document(email)
                                .set(new User(email, "1", collage_code))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("TAG", "User Type : Admin (Success)");

                                        firebaseFirestore
                                                .collection("Admin")
                                                .document(email)
                                                .set(new Admin(name, collage_code))
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Log.d("TAG", "User Data : Admin (Success)");
                                                        startActivity(new Intent(RegisterAdminActivity.this, AdminMainActivity.class));
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.d("TAG", "User Data : Admin (Failed)");
                                                    }
                                                });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "User Type : Admin (Failed) " + e.getMessage());
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "User Register : Admin (Failed)");
                        Toast.makeText(RegisterAdminActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setEventLis() {
        binding.btnRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get all inputs
                getText();

                if (check() == 1) {
                    Toast.makeText(RegisterAdminActivity.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    register();
                }

            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to login screen
                startActivity(new Intent(RegisterAdminActivity.this, LoginActivity.class));
                finish();
            }
        });

        binding.tvRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to user register screen
                startActivity(new Intent(RegisterAdminActivity.this, RegisterUserActivity.class));
                finish();
            }
        });
    }
}