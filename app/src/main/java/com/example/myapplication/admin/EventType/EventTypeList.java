package com.example.myapplication.admin.EventType;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventTypeList extends AppCompatActivity {
    DatabaseReference EventTypeReference;
    ListView listView;
    Map<String, EventType> eventTypeMap = new HashMap<>();


    private void connectDB() {
        EventTypeReference = FirebaseDatabase.getInstance().getReference("EventTypes");
        EventTypeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventTypeMap.clear();
                List<Map<String, String>> data = new LinkedList<>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    EventType eventType = child.getValue(EventType.class);

                    eventTypeMap.put(eventType.getEventName(), eventType);
                    Map<String, String> map = new HashMap<>();
                    map.put("eventTypeName", eventType.getEventName());
                    map.put("status", eventType.getEventTypeStatus().toString());
                    data.add(map);
                }

                // set list view
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.event_types_layout,
                        new String[] {"eventTypeName","status"}, new int[] {R.id.nameTextView, R.id.statusTextview});
                listView.setAdapter(adapter);

                // set onClick for list view
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String eventTypeName = ((TextView) view.findViewById(R.id.nameTextView)).getText().toString();
                        EventType eventType = eventTypeMap.get(eventTypeName);
                        Intent intent = new Intent(getApplicationContext(), EventTypeModeration.class);
                        intent.putExtra("eventType", eventType);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initComponent() {
        listView = findViewById(R.id.EventTypeListView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_list);
        initComponent();
        connectDB();
    }
}