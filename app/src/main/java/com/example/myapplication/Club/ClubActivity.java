package com.example.myapplication.Club;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Club.Event.EventCreation;
import com.example.myapplication.Club.Profile.Profile;
import com.example.myapplication.Club.Profile.registrationManagement;
import com.example.myapplication.Club.RegistrationManagement.RegistrationManagement;
import com.example.myapplication.Club.RoutePlanning.RoutePlanningCreation;
import com.example.myapplication.R;
import com.example.myapplication.entity.Account;

public class ClubActivity extends AppCompatActivity {
    Account account;

    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        profile = (Profile) intent.getSerializableExtra("profile");

    }


    public void ProfileManagement(View view){
        startActivity(new Intent(getApplicationContext(), registrationManagement.class).putExtra("account", account));
    }

    public void Event(View view){
        startActivity(new Intent(getApplicationContext(), EventCreation.class).putExtra("account", account));
    }
    public void RoutePlanningManagement(View view){
        startActivity(new Intent(getApplicationContext(), RoutePlanningCreation.class).putExtra("account",account));
    }

    public void RegistrationManagement(View view) {
        startActivity(new Intent(getApplicationContext(), RegistrationManagement.class).putExtra("account",account));
    }
}