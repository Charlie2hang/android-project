package com.example.myapplication.Participant.Feedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Participant.Registration.Registration;
import com.example.myapplication.R;
import com.example.myapplication.entity.Account;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {
    DatabaseReference RegistrationReference, AccountReference, FeedbackReference;
    Registration registration;
    Account account;
    TextView nameTextView, FeesTextView, clubNameTextView, typeTextView, levelTextView, dateTextView;
    EditText feedback;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initComponent();
        connectDB();
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        registration = (Registration) intent.getSerializableExtra("Registrations");


        nameTextView.setText(registration.getEventName());
        FeesTextView.setText(registration.getFees());
        clubNameTextView.setText(registration.getClubName());
        typeTextView.setText(registration.getEventTypes());
        levelTextView.setText(registration.getEventLevels());
        dateTextView.setText(registration.getDate());
    }

    private void initComponent() {
        nameTextView = findViewById(R.id.nameTextView);
        FeesTextView = findViewById(R.id.FeesTextView);
        clubNameTextView = findViewById(R.id.clubNameTextView);
        typeTextView = findViewById(R.id.typeTextView);
        levelTextView = findViewById(R.id.levelTextView);
        dateTextView = findViewById(R.id.dateTextView);
        feedback = findViewById(R.id.feedbackEditText);

        spinner = (Spinner) findViewById(R.id.rateSpinner);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                new String[] {"5", "4", "3", "2", "1"});
        spinner.setAdapter(adapter);
    }

    private void connectDB() {
        RegistrationReference = FirebaseDatabase.getInstance().getReference("Registrations");
        AccountReference = FirebaseDatabase.getInstance().getReference("account");
        FeedbackReference = FirebaseDatabase.getInstance().getReference("Feedback & Rate");
    }

    public void SubmitFeedbackOnClick(View view) {
        String eventName = registration.getEventName();
        String providerName = registration.getClubName();
        String participantName = account.getName();
        String eventLevel = registration.getEventLevels();
        String eventType = registration.getEventTypes();
        String feedbacks = feedback.getText().toString();
        String eventDate = registration.getDate();
        String myRate = spinner.getSelectedItem().toString();
        String fees = registration.getFees();

        RateAndFeedback rateAndFeedback = new RateAndFeedback(providerName, participantName, eventName, eventDate,
                fees, eventType, eventLevel, feedbacks, myRate);
        FeedbackReference.child(eventName).setValue(rateAndFeedback);
        Toast.makeText(getBaseContext(), "Feedback submit!", Toast.LENGTH_SHORT).show();
        finish();
    }
}