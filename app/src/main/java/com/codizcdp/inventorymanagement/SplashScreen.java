package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivitySplashScreenBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        checkIfAlreadyLogined();
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }


    private void checkIfAlreadyLogined() {
        if (firebaseAuth.getCurrentUser() != null) {
//            firebaseAuth.signOut();
            firebaseFirestore
                    .collection("User")
                    .document(firebaseAuth.getCurrentUser().getEmail())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot doc) {
                            Log.d("TAG", "Found User (Success)");
                            if (doc.exists()) {
                                Log.d("TAG", "User Exists");
                                String type = doc.get("type").toString();

                                if (type.equals("1")) { //Admin
                                    startActivity(new Intent(SplashScreen.this, AdminMainActivity.class));
                                    finish();
                                } else { //Student
                                    startActivity(new Intent(SplashScreen.this, UserMainActivity.class));
                                    finish();
                                }
                            } else {
                                Log.d("TAG", "User Do not Exists");
                                Toast.makeText(SplashScreen.this, "User not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "Found User (Failed)");
                        }
                    });

        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }
            }, 1000);
        }
    }
}