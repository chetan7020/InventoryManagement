package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityRegisterUserBinding;
import com.codizcdp.inventorymanagement.model.Student;
import com.codizcdp.inventorymanagement.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterUserActivity extends AppCompatActivity {

    private ActivityRegisterUserBinding binding;

    private String name, class_name, roll_no, collage_code, email, pass;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        setEventLis();
    }

    private void init() {
        Log.d("TAG", "initing");

        name = class_name = roll_no = collage_code = email = pass = "-1";

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Log.d("TAG", "inited");
    }

    private void getText() {
        name = binding.etName.getText().toString().trim();
        class_name = binding.etClass.getText().toString().trim();
        roll_no = binding.etRollNo.getText().toString().trim();
        collage_code = binding.etCollageCode.getText().toString().trim();
        email = binding.etEmail.getText().toString().trim();
        pass = binding.etPassword.getText().toString().trim();
    }

    private int check() {
        if (name.equals("-1") || class_name.equals("-1") || roll_no.equals("-1") || collage_code.equals("-1") || email.equals("-1") || pass.equals("-1"))
            return 1;
        return 0;
    }

    private void register() {
        firebaseAuth
                .createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d("TAG", "User Register : Student (Success)");
                        firebaseFirestore
                                .collection("User")
                                .document(email)
                                .set(new User(email, "2", collage_code))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("TAG", "User Type : Student (Success)");

                                        firebaseFirestore
                                                .collection("Student")
                                                .document(email)
                                                .set(new Student(name, class_name, roll_no, collage_code))
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Log.d("TAG", "User Data : Student (Success)");
                                                        startActivity(new Intent(RegisterUserActivity.this, UserMainActivity.class));
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.d("TAG", "User Data : Student (Failed)");
                                                    }
                                                });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("TAG", "User Type : Student (Failed)");
                                    }
                                });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "User Register : Admin (Failed)");
                        Toast.makeText(RegisterUserActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setEventLis() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getText();

                if (check() == 1) {
                    Toast.makeText(RegisterUserActivity.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    register();
                }

            }
        });

        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterUserActivity.this, LoginActivity.class));
                finish();
            }
        });

        binding.tvRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterUserActivity.this, RegisterAdminActivity.class));
                finish();
            }
        });
    }
}