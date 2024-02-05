package com.example.myapplication.admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.entity.Account;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deleteAccount extends AppCompatActivity {
    DatabaseReference AccountReference;
    TextView nameTextView, emailTextView, roleTextView;
    Button deleteAccountBtn;
    Account account;

    private void connectDB() {
        AccountReference = FirebaseDatabase.getInstance().getReference("account");
    }

    private void initComponent() {
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        roleTextView = (TextView) findViewById(R.id.roleTextview);
        deleteAccountBtn = (Button) findViewById(R.id.deleteAccountBtn);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        initComponent();
        connectDB();

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        nameTextView.setText(account.getName());
        emailTextView.setText(account.getEmail());
        roleTextView.setText(account.getRole().toString());
    }

    public void deleteAccount(View view){
        AccountReference.child(account.getName()).setValue(null);
        finish();
    }

}