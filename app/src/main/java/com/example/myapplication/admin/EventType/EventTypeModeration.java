package com.example.myapplication.admin.EventType;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventTypeModeration extends AppCompatActivity {
    EventType eventType;
    DatabaseReference EventTypeReference;
    TextView nameTextView, statusTextView;
    Button approveBtn, rejectBtn, deleteBtn;

    private void initComponent() {
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        approveBtn = (Button) findViewById(R.id.approveBtn);
        rejectBtn = (Button) findViewById(R.id.rejectBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);

    }

    private void connectDB() {
        EventTypeReference = FirebaseDatabase.getInstance().getReference("EventTypes");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_moderation);

        initComponent();
        connectDB();

        Intent intent = getIntent();
        eventType = (EventType) intent.getSerializableExtra("eventType");
        nameTextView.setText(eventType.getEventTypes().toString());
        statusTextView.setText(eventType.getEventTypeStatus().toString());

        if (eventType.getEventTypeStatus() == EventTypeStatus.APPROVED) {
            approveBtn.setEnabled(false);
            rejectBtn.setEnabled(false);
        } else if (eventType.getEventTypeStatus() == EventTypeStatus.REJECTED) {
            rejectBtn.setEnabled(false);
        }
    }

    public void approveOnClick(View view) {
        EventTypeReference.child(eventType.getEventName()).child("eventTypeStatus").setValue(EventTypeStatus.APPROVED).toString();
        finish();
    }

    public void rejectOnClick(View view) {
        EventTypeReference.child(eventType.getEventName()).child("eventTypeStatus").setValue(EventTypeStatus.REJECTED).toString();
        finish();
    }

    public void deleteOnClick(View view) {
        EventTypeReference.child(eventType.getEventName()).setValue(null);
        finish();
    }

}