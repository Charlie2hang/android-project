package com.example.myapplication.Participant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Club.Profile.registrationManagement;
import com.example.myapplication.Participant.Feedback.History;
import com.example.myapplication.Participant.Registration.SearchEvents;
import com.example.myapplication.R;
import com.example.myapplication.entity.Account;

public class PartivipantActivity extends AppCompatActivity {
    Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partivipant);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
    }

    public void SearchEventsOnclick(View view){
        startActivity(new Intent(getApplicationContext(), SearchEvents.class).putExtra("account", account));
    }

    public void ViewHistory(View view) {
        startActivity(new Intent(getApplicationContext(), History.class).putExtra("account", account));
    }

}