package com.example.myapplication.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.admin.EventType.EventTypeCreate;
import com.example.myapplication.admin.EventType.EventTypeList;
import com.example.myapplication.entity.Account;


public class AdminActivity extends AppCompatActivity {
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
    }
    public void AccountManagement(View view) {
        startActivity(new Intent(getApplicationContext(), AccountList.class));
    }
    public void EventTypeManagement(View view) {
        startActivity(new Intent(getApplicationContext(), EventTypeCreate.class));
    }

    public void ModerationOnClick(View view) {
        startActivity(new Intent(getApplicationContext(), EventTypeList.class));
    }
}