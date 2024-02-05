package com.example.myapplication.Club.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.admin.EventType.EventType;
import com.example.myapplication.admin.EventType.EventTypeStatus;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventDetails extends AppCompatActivity {
    Event event;
    DatabaseReference EventReference;
    TextView nameTextView, FeesTextView, LimitsTextView, typeTextView, levelTextView, statusTextView, dateTextView;
    Button approveBtn, rejectBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        initComponent();
        connectDB();
        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra("Events");

        nameTextView.setText(event.getID().toString());
        dateTextView.setText(event.getDate().toString());
        FeesTextView.setText(event.getFees().toString());
        LimitsTextView.setText(event.getLimit().toString());
        typeTextView.setText(event.getEventTypes().toString());
        levelTextView.setText(event.getEventLevels().toString());
        statusTextView.setText(event.getEventStatus().toString());



        if (event.getEventStatus() == EventStatus.APPROVED) {
            approveBtn.setEnabled(false);
            rejectBtn.setEnabled(false);
        } else if (event.getEventStatus() == EventStatus.REJECTED) {
            rejectBtn.setEnabled(false);
        }
    }

    private void initComponent() {
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        FeesTextView = (TextView) findViewById(R.id.FeesTextView);
        LimitsTextView = (TextView) findViewById(R.id.limitsTextView);
        typeTextView = (TextView) findViewById(R.id.typeTextView);
        levelTextView = (TextView) findViewById(R.id.levelTextView);
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        approveBtn = (Button) findViewById(R.id.approveBtn);
        rejectBtn = (Button) findViewById(R.id.rejectBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
    }

    private void connectDB() {
        EventReference = FirebaseDatabase.getInstance().getReference("Events");
    }

    public void approveOnClick(View view) {
        EventReference.child(event.getID()).child("eventStatus").setValue(EventTypeStatus.APPROVED).toString();
        finish();
    }

    public void rejectOnClick(View view) {
        EventReference.child(event.getID()).child("eventStatus").setValue(EventTypeStatus.REJECTED).toString();
        finish();
    }

    public void deleteOnClick(View view) {
        EventReference.child(event.getID()).setValue(null);
        finish();
    }
}