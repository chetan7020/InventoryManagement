package com.codizcdp.inventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codizcdp.inventorymanagement.databinding.ActivityAdminItemsBinding;
import com.codizcdp.inventorymanagement.model.Item;
import com.codizcdp.inventorymanagement.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminItemsActivity extends AppCompatActivity {

    private ActivityAdminItemsBinding binding;
    private String ID, name, total_quantity, available_quantity, desc;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private TextView tv_id, tv_name, tv_total_quantity, tv_available_quantity, tv_desc;
    private LinearLayout ll_item_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityAdminItemsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        get();

        setEventLis();

    }

    private void init(){
        ID = name = total_quantity = available_quantity = desc = "-1";

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void get(){
        firebaseFirestore
                .collection("Admin")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("Items")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d("TAG", "Admin Items retrieve : Success");

                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            Item item= doc.toObject(Item.class);

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
                        Log.d("TAG", "Admin Items retrieve : Failed");
                    }
                });
    }

    private void add(){
        View view = getLayoutInflater().inflate(R.layout.item_plain_layout, null, false);

        tv_id= view.findViewById(R.id.tv_id);
        tv_name= view.findViewById(R.id.tv_name);
        tv_total_quantity= view.findViewById(R.id.tv_total_quantity);
        tv_available_quantity= view.findViewById(R.id.tv_available_quantity);
        tv_desc= view.findViewById(R.id.tv_desc);

        ll_item_card= view.findViewById(R.id.ll_item_card);

        set();

        ll_item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminItemsActivity.this, AdminItemInfoActivity.class);

                Item item= new Item(ID, name, total_quantity, available_quantity, desc);

                intent.putExtra("item", item);

                startActivity(intent);
            }
        });

        binding.llData.addView(view);
    }

    private void set(){
        tv_id.setText(ID);
        tv_name.setText(name);
        tv_total_quantity.setText(total_quantity);
        tv_available_quantity.setText(available_quantity);
        tv_desc.setText(desc);
    }

    private void setEventLis() {
        binding.btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminItemsActivity.this, AdminAddItemActivity.class));
            }
        });
    }


}