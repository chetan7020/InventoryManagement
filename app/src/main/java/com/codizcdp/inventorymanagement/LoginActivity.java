package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAllItemsBinding;
import com.codizcdp.inventorymanagement.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String email = "-1", pass = "-1";

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setEventLis();
    }

    void getText() {

        Log.d("TAG", "initing");

        email = binding.etEmail.getText().toString();
        pass = binding.etPass.getText().toString();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Log.d("TAG", "inited");
    }

    private int check() {
        if (email.equals("-1") || pass.equals("-1")) return 1;
        return 0;
    }

    private void loginAdmin() {
        firebaseAuth
                .signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d("TAG", "Admin Sign In (Success)");
                        startActivity(new Intent(LoginActivity.this, AdminMainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Admin Sign In (Failed)");
                    }
                });

    }

    private void loginStudent() {
        firebaseAuth
                .signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d("TAG", "Student Sign In (Success)");
                        startActivity(new Intent(LoginActivity.this, UserMainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Student Sign In (Failed)");
                    }
                });
    }

    private void login() {
        firebaseFirestore
                .collection("User")
                .document(email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot doc) {
                        Log.d("TAG", "Found User (Success)");
                        if (doc.exists()) {
                            Log.d("TAG", "User Exists");
                            String type = doc.get("type").toString();

                            if (type.equals("1")) { //Admin
                                loginAdmin();
                            } else { //Student
                                loginStudent();
                            }
                        } else {
                            Log.d("TAG", "User Do not Exists");
                            Toast.makeText(LoginActivity.this, "User not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", e.getMessage());
                    }
                });
    }

    private void setEventLis() {

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();

                if (check() == 1) {
                    Toast.makeText(LoginActivity.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }

//                Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                //If user is not admin
//                startActivity(new Intent(LoginActivity.this, UserMainActivity.class));
//                finish();

                //If user is admin
//                startActivity(new Intent(LoginActivity.this, AdminMainActivity.class));
//                finish();
            }
        });

        binding.tvRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //register user screen
                startActivity(new Intent(LoginActivity.this, RegisterUserActivity.class));
                finish();
            }
        });

        binding.tvRegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //register admin screen
                startActivity(new Intent(LoginActivity.this, RegisterAdminActivity.class));
                finish();
            }
        });
    }
}