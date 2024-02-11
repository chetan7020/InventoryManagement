package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminUsersBinding;
import com.codizcdp.inventorymanagement.model.Admin;
import com.codizcdp.inventorymanagement.model.Item;
import com.codizcdp.inventorymanagement.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminUsersActivity extends AppCompatActivity {

    private ActivityAdminUsersBinding binding;
    private String name, class_name, roll_no, collage_code, admin_collage_code;
    private TextView tv_name, tv_class, tv_roll_no;
    private LinearLayout ll_user_card;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAdminUsersBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        getAdminCollageCode();

        get();

        setEventLis();
    }

    private void init(){
        name = class_name = roll_no = collage_code = "-1";

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void getAdminCollageCode(){
        firebaseFirestore
                .collection("Admin")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot doc) {
                        Log.d("TAG", "Admin Profile : Data Get (Success)");

                        Admin admin = doc.toObject(Admin.class);

                        admin_collage_code= admin.getCollage_code();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Admin Profile : Data Get (Failed)");
                    }
                });

    }

    private void set(){
        tv_name.setText(name);
        tv_class.setText(class_name);
        tv_roll_no.setText(roll_no);
    }

    private void add(){
        View view = getLayoutInflater().inflate(R.layout.user_card_layout, null, false);

        tv_name= view.findViewById(R.id.tv_name);
        tv_class= view.findViewById(R.id.tv_class);
        tv_roll_no= view.findViewById(R.id.tv_roll_no);

        ll_user_card= view.findViewById(R.id.ll_user_card);

        set();
        ll_user_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent= new Intent(AdminItemsActivity.this, AdminItemInfoActivity.class);
//
//                Item item= new Item(ID, name, total_quantity, available_quantity, desc);
//
//                intent.putExtra("item", item);
//
//                startActivity(intent);
            }
        });

        binding.llData.addView(view);
    }

    private void get(){
        firebaseFirestore
                .collection("Student")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d("TAG", "Admin Users retrieve : Success");

                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            Student student= doc.toObject(Student.class);

                            name = student.getName();
                            class_name = student.getClass_name();
                            roll_no = student.getRoll_no();
                            collage_code = student.getCollage_code();

                            if(!collage_code.equals(admin_collage_code))continue;

                            add();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Admin Items retrieve : Failed");
                    }
                });

    }

    private void setEventLis() {

    }
}