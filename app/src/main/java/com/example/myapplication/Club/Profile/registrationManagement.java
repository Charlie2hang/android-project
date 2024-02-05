package com.example.myapplication.Club.Profile;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Club.Profile.ProfileCreation;
import com.example.myapplication.Club.Profile.ProfileInfo;
import com.example.myapplication.R;
import com.example.myapplication.entity.Account;

public class registrationManagement extends AppCompatActivity {

    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_management);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

    }

    public void ProfileCreate(View view){
        startActivity(new Intent(getApplicationContext(), ProfileCreation.class).putExtra("account", account));
    }

    public void EventListView(View view){
        startActivity(new Intent(getApplicationContext(), EventList.class).putExtra("account", account));
    }











}