package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ItemDAO itemDAO;
    ItemAdapter itemAdapter;
    ArrayList<Item> list;
    RecyclerView rcv;
    FloatingActionButton btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd= findViewById(R.id.btnAdd);
        itemDAO = new ItemDAO(getApplicationContext());
        list = itemDAO.getAll();
        itemAdapter = new ItemAdapter(this,list);
        rcv = findViewById(R.id.rcv);
       LinearLayoutManager layoutManager = new LinearLayoutManager(this);
       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       rcv.setLayoutManager(layoutManager);
       rcv.setAdapter(itemAdapter);

       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Dialog dialog = new Dialog(MainActivity.this);
               dialog.setContentView(R.layout.dialog);
               EditText edtTen, edtSdt, edtEmail;
               edtTen = dialog.findViewById(R.id.edtTen);
               edtSdt = dialog.findViewById(R.id.edtSdt);
               edtEmail = dialog.findViewById(R.id.edtEmail);
               Button btnSubmit, btnCancel;
               btnSubmit = dialog.findViewById(R.id.btnSubmit);
               btnCancel = dialog.findViewById(R.id.btnCancel);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtTen.getText().toString();
                        String email =edtEmail.getText().toString();
                        String sdt = edtSdt.getText().toString();
                        Item item = new Item(ten,email, sdt);
                        if (itemDAO.insert(item)){
                            Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(itemDAO.getAll());
                            itemAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
              btnCancel.setOnClickListener( view -> dialog.dismiss());
               dialog.show();
           }
       });
    }
}