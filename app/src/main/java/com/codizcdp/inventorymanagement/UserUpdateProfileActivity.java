package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityUpdateUserProfileBinding;
import com.codizcdp.inventorymanagement.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class UserUpdateProfileActivity extends AppCompatActivity {

    private ActivityUpdateUserProfileBinding binding;
    String name, class_name, roll_no;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateUserProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        setPreviousData();

        setEventLis();
    }

    private void init() {
        name = class_name = roll_no = "-1";

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void setPreviousData() {
        firebaseFirestore
                .collection("Student")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot doc) {
                        Student student= doc.toObject(Student.class);

                        binding.etName.setText(student.getName());
                        binding.etClass.setText(student.getClass_name());
                        binding.etRollNo.setText(student.getRoll_no());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void setEventLis() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();

                Map<String, Object> data = new HashMap<>();

                data.put("class_name", class_name);
                data.put("name", name);
                data.put("roll_no", roll_no);

                firebaseFirestore
                        .collection("Student")
                        .document(firebaseAuth.getCurrentUser().getEmail())
                        .update(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "Student : Update Profile (Success)");
                                Toast.makeText(UserUpdateProfileActivity.this, "Profiled Updated", Toast.LENGTH_SHORT).show();
                                setText();
                                startActivity(new Intent(UserUpdateProfileActivity.this, UserProfileActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "Student : Update Profile (Failed)");
                                Toast.makeText(UserUpdateProfileActivity.this, "Failed to Update Profile", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void getText() {
        name = binding.etName.getText().toString().trim();
        class_name = binding.etClass.getText().toString().trim();
        roll_no = binding.etRollNo.getText().toString().trim();
    }

    private void setText() {
        binding.etName.setText("");
        binding.etClass.setText("");
        binding.etRollNo.setText("");
    }

    private int check() {
        if(name.equals("-1") || class_name.equals("-1") || roll_no.equals("-1"))return 1;
        return 0;
    }

}