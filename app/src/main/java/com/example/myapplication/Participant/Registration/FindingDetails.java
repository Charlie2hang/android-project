package com.example.myapplication.Participant.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Club.Event.Event;
import com.example.myapplication.R;
import com.example.myapplication.admin.EventType.EventLevels;
import com.example.myapplication.admin.EventType.EventTypes;
import com.example.myapplication.entity.Account;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FindingDetails extends AppCompatActivity {
    Event event;
    Account account;
    DatabaseReference RegistrationReference, AccountReference, EventReference;
    TextView nameTextView, FeesTextView, LimitsTextView, typeTextView, levelTextView, dateTextView, clubNameTextView, user;
    Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finding_details);
        connectDB();
        initComponent();

        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra("Events");
        account = (Account) intent.getSerializableExtra("account");


        nameTextView.setText(event.getID());
        dateTextView.setText(event.getDate());
        FeesTextView.setText(event.getFees());
        LimitsTextView.setText(event.getLimit());
        typeTextView.setText(event.getEventTypes().toString());
        levelTextView.setText(event.getEventLevels().toString());
        clubNameTextView.setText(event.getClubName());
    }


    private void initComponent() {
        nameTextView = findViewById(R.id.nameTextView);
        FeesTextView = findViewById(R.id.FeesTextView);
        LimitsTextView = findViewById(R.id.limitsTextView);
        typeTextView = findViewById(R.id.typeTextView);
        levelTextView = findViewById(R.id.levelTextView);
        dateTextView = findViewById(R.id.dateTextView);
        clubNameTextView = findViewById(R.id.clubNameTextView);
        select = (Button) findViewById(R.id.selectBtn);
    }

    private void connectDB() {
        RegistrationReference = FirebaseDatabase.getInstance().getReference("Registrations");
        AccountReference = FirebaseDatabase.getInstance().getReference("account");
        EventReference = FirebaseDatabase.getInstance().getReference("Events");
    }

    public void RegisterOnClick(View view) {



        String eventName = event.getID();
        String clubName = event.getClubName();
        String fees = event.getFees();
        String limit = event.getLimit();
        String date = event.getDate();
        String type = event.getEventTypes().toString();
        String level = event.getEventLevels().toString();

        Registration registration = new Registration(eventName, clubName, fees, limit,
                date, account.getName(), type, level, RegistrationStatus.PENDING);
        RegistrationReference.child(eventName).setValue(registration);
        finish();
    }
}