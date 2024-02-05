package com.example.myapplication.Club.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myapplication.Club.Event.Event;
import com.example.myapplication.Club.Event.EventDetails;
import com.example.myapplication.R;
import com.example.myapplication.admin.EventType.EventType;
import com.example.myapplication.admin.deleteAccount;
import com.example.myapplication.entity.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventList extends AppCompatActivity {

    DatabaseReference EventReference;
    Account account;
    ListView listView;
    Map<String, Event> eventMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        initComponent();
        connectDB();
    }

    private void initComponent(){
        listView = findViewById(R.id.MyEventsListView);
    }

    private void connectDB(){
        EventReference = FirebaseDatabase.getInstance().getReference("Events");

        EventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventMap.clear();
                List<Map<String, String>> data = new LinkedList<>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    Event event = child.getValue(Event.class);
                    if (account.getName().equals(event.getClubName())) {
                        eventMap.put(event.getID(), event);
                        Map<String, String> map = new HashMap<>();
                        map.put("EventID", event.getID());
                        map.put("status", event.getEventStatus().toString());
                        data.add(map);
                    }
                }
                // set list view
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.my_event_layout,
                        new String[] {"EventID","status"}, new int[] {R.id.nameTextView, R.id.statusTextview});
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String eventID = ((TextView) view.findViewById(R.id.nameTextView)).getText().toString();
                        Event event = eventMap.get(eventID);
                        Intent intent = new Intent(getApplicationContext(), EventDetails.class);
                        intent.putExtra("Events", event);
                        intent.putExtra("account", account);
                        startActivity(intent);
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}