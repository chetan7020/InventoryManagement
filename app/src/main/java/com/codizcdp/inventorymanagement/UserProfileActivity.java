package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityUserMainBinding;
import com.codizcdp.inventorymanagement.databinding.ActivityUserProfileBinding;
import com.codizcdp.inventorymanagement.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AppCompatActivity {
    private ActivityUserProfileBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityUserProfileBinding.inflate(getLayoutInflater());

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
                .collection("Student")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot doc) {
                        Log.d("TAG", "Student Profile : Data Get (Success)");

                        Student student= doc.toObject(Student.class);

                        binding.tvName.setText(student.getName());
                        binding.tvClass.setText(student.getClass_name());
                        binding.tvRollNo.setText(student.getRoll_no());
                        binding.tvCollageCode.setText(student.getCollage_code());
                        binding.tvEmail.setText(firebaseAuth.getCurrentUser().getEmail());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Student Profile : Data Get (Failed)");
                    }
                });
    }

    private void setEventLis() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfileActivity.this, UserUpdateProfileActivity.class));
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(UserProfileActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}