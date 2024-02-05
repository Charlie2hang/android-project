package com.example.myapplication.Club.RegistrationManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Club.Event.Event;
import com.example.myapplication.Participant.Registration.Registration;
import com.example.myapplication.Participant.Registration.RegistrationStatus;
import com.example.myapplication.R;
import com.example.myapplication.admin.EventType.EventTypeStatus;
import com.example.myapplication.entity.Account;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationDetails extends AppCompatActivity {
    DatabaseReference RegistrationReference, AccountReference;
    Registration registration;
    Account account;
    TextView nameTextView, FeesTextView, userNameTextView, typeTextView, levelTextView, statusTextView, dateTextView;
    Button approveBtn, rejectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_details);
        initComponent();
        connectDB();
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        registration = (Registration) intent.getSerializableExtra("Registrations");

        nameTextView.setText(registration.getEventName());
        FeesTextView.setText(registration.getFees());
        userNameTextView.setText(account.getName());
        typeTextView.setText(registration.getEventTypes());
        levelTextView.setText(registration.getEventTypes());
        statusTextView.setText(registration.getRegistrationStatus().toString());
        dateTextView.setText(registration.getDate());


        if (registration.getRegistrationStatus() == RegistrationStatus.APPROVED) {
            approveBtn.setEnabled(false);
            rejectBtn.setEnabled(false);
        } else if (registration.getRegistrationStatus() == RegistrationStatus.REJECTED) {
            rejectBtn.setEnabled(false);
        }


    }

    private void initComponent() {
        nameTextView = findViewById(R.id.nameTextView);
        FeesTextView = findViewById(R.id.FeesTextView);
        userNameTextView = findViewById(R.id.userNameTextView);
        typeTextView = findViewById(R.id.typeTextView);
        levelTextView = findViewById(R.id.levelTextView);
        statusTextView = findViewById(R.id.statusTextView);
        dateTextView = findViewById(R.id.dateTextView);
        approveBtn = findViewById(R.id.approveBtn);
        rejectBtn = findViewById(R.id.rejectBtn);
    }

    private void connectDB() {
        RegistrationReference = FirebaseDatabase.getInstance().getReference("Registrations");
        AccountReference = FirebaseDatabase.getInstance().getReference("account");
    }

    public void approveOnClick(View view) {
        RegistrationReference.child(registration.getEventName()).child("registrationStatus").setValue(RegistrationStatus.APPROVED).toString();
        finish();
    }

    public void rejectOnClick(View view) {
        RegistrationReference.child(registration.getEventName()).child("registrationStatus").setValue(RegistrationStatus.REJECTED).toString();
        finish();
    }
}