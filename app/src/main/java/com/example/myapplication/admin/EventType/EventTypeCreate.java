package com.example.myapplication.admin.EventType;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.myapplication.R;

public class EventTypeCreate extends AppCompatActivity {
    DatabaseReference EventTypeReference;
    EditText EventTypeNameEditText;


    private void connectDB() {
        EventTypeReference = FirebaseDatabase.getInstance().getReference("EventTypes");
    }

    private void initComponent() {
        EventTypeNameEditText = findViewById(R.id.EventTypeNameEditText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_create);
        connectDB();
        initComponent();
    }

    public void EventTypeCreateOnclick(View view) {

        String eventTypeName = EventTypeNameEditText.getText().toString();
        //
        EventTypes eventTypes = null;
        if (eventTypeName.equals("TimeTrial")) {
            eventTypes = EventTypes.TimeTrial;
        } else if (eventTypeName.equals("HillClimb")) {
            eventTypes = EventTypes.HillClimb;
        } else if (eventTypeName.equals("RoadStageRace")) {
            eventTypes = EventTypes.RoadStageRace;
        } else if (eventTypeName.equals("RoadRace")) {
            eventTypes = EventTypes.RoadRace;
        } else if (eventTypeName.equals("GroupRides")) {
            eventTypes = EventTypes.GroupRides;
        } else {
            Toast.makeText(getBaseContext(), "Invalid Type Name!", Toast.LENGTH_LONG).show();
        }

        if (eventTypes != null) {
            EventType eventType = new EventType(eventTypeName, eventTypes, EventTypeStatus.valueOf("PENDING"));
            EventTypeReference.child(eventTypeName).setValue(eventType);
            Toast.makeText(getBaseContext(), "Event Type Create.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}