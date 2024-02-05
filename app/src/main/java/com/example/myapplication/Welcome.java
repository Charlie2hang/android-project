package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.entity.Account;


public class Welcome extends AppCompatActivity {
    Account account;
    TextView welcomeTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeTextView =(TextView) findViewById(R.id.welcomeTextView);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        welcomeTextView.setText("Welcome " + account.getName() + " as a " + account.getRole());
    }
}