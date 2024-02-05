package com.example.myapplication.entity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Club.ClubRegister;
import com.example.myapplication.Participant.UserRegister;
import com.example.myapplication.R;

public class SelectRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
    }

    public void clubRegister(View view) {
        startActivities(new Intent[]{new  Intent(getApplicationContext(), ClubRegister.class)});
        finish();
    }

    public void participantRegister(View view) {
        startActivities(new Intent[]{new  Intent(getApplicationContext(), UserRegister.class)});
        finish();
    }
}