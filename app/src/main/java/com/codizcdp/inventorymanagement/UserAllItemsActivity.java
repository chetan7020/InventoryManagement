package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codizcdp.inventorymanagement.databinding.ActivityAllItemsBinding;
import com.codizcdp.inventorymanagement.model.Admin;
import com.codizcdp.inventorymanagement.model.Item;
import com.codizcdp.inventorymanagement.model.Student;
import com.codizcdp.inventorymanagement.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class UserAllItemsActivity extends AppCompatActivity {

    private ActivityAllItemsBinding binding;
    private String ID, name, total_quantity, available_quantity, desc;
    private TextView tv_id, tv_name, tv_total_quantity, tv_available_quantity, tv_desc;
    private Button btn_request;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAllItemsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        getStudentCollageCode();

//        Log.d("TAG", admin_email);

//        get();

//        setEventLis();
    }

    private void init() {
        ID = name = total_quantity = available_quantity = desc = "-1";

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void getAdminEmail(String student_collage_code) {
        firebaseFirestore
                .collection("User")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            User user = doc.toObject(User.class);

                            String collage_code = user.getCollage_code();
                            String type = user.getType();

                            if (collage_code.equals(student_collage_code) && type.equals("1")) {
                                get(user.getEmail());
                            }
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserAllItemsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getStudentCollageCode() {
        firebaseFirestore
                .collection("Student")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot doc) {
                        Log.d("TAG", "User Items : Data Get (Success)");

                        Student student = doc.toObject(Student.class);

//                        Log.d("TAG", student.getCollage_code());
                        String student_collage_code = student.getCollage_code();
                        getAdminEmail(student_collage_code);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "User Items : Data Get (Failed)");
                    }
                });

    }

    private void get(String admin_email){
        firebaseFirestore
                .collection("Admin")
                .document(admin_email)
                .collection("Items")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            Item item = doc.toObject(Item.class);

                            ID= item.getID();
                            name= item.getName();
                            total_quantity= item.getTotal_quantity();
                            available_quantity= item.getAvailable_quantity();
                            desc= item.getDesc();

                            add();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserAllItemsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void add() {
        View view = getLayoutInflater().inflate(R.layout.item_card_layout, null, false);

        tv_id = view.findViewById(R.id.tv_id);
        tv_name = view.findViewById(R.id.tv_name);
        tv_total_quantity = view.findViewById(R.id.tv_total_quantity);
        tv_available_quantity = view.findViewById(R.id.tv_available_quantity);
        tv_desc = view.findViewById(R.id.tv_desc);

        btn_request = view.findViewById(R.id.btn_request);

        set();

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserAllItemsActivity.this, "Request Send", Toast.LENGTH_SHORT).show();
            }
        });

        binding.llData.addView(view);
    }

    private void set() {
        tv_id.setText(ID);
        tv_name.setText(name);
        tv_total_quantity.setText(total_quantity);
        tv_available_quantity.setText(available_quantity);
        tv_desc.setText(desc);
    }

    private void setEventLis() {

    }

}