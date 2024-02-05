package com.example.myapplication.Participant.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.Club.Event.Event;
import com.example.myapplication.Club.Event.EventDetails;
import com.example.myapplication.Club.Event.EventStatus;
import com.example.myapplication.R;
import com.example.myapplication.admin.EventType.EventType;
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

public class SearchEvents extends AppCompatActivity {
    DatabaseReference EventReference, AccountReference;
    ListView listView;
    Spinner spinner;
    SearchView searchView;
    List<Event> eventList = new LinkedList<>();
    Map<String, Event> eventMap = new HashMap<>();

    Account account;

    String filter ="EventType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_events);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        initComponent();
        connectDB();
    }

    private void initComponent(){
        listView = findViewById(R.id.EventsListView);
        spinner = findViewById(R.id.filterSpinner);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                                                android.R.layout.simple_spinner_item,
                                                new String[] {"EventType", "EventName", "ClubName"});
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    filter = "EventType";
                } else if (position == 1) {
                    filter = "EventName";
                } else {
                    filter = "ClubName";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase();
                List<Map<String, String>> data = new LinkedList<>();
                for (Event event : eventList) {
                    if (filter.equals("EventType")) {
                        if (event.getEventTypes().toString().toLowerCase().contains(newText)){
                            if (event.getEventStatus() == EventStatus.APPROVED) {
                                Map<String, String> map = new HashMap<>();
                                map.put("EventID", event.getID());
                                map.put("Type", event.getEventTypes().toString());
                                map.put("Provider", event.getClubName().toString());
                                data.add(map);
                            }
                        }
                    } else if (filter.equals("EventName")) {
                        if (event.getID().toString().toLowerCase().contains(newText)) {
                            if (event.getEventStatus() == EventStatus.APPROVED) {
                                Map<String, String> map = new HashMap<>();
                                map.put("EventID", event.getID());
                                map.put("Type", event.getEventTypes().toString());
                                map.put("Provider", event.getClubName().toString());
                                data.add(map);
                            }
                        }
                    } else if (filter.equals("ClubName")) {
                        if (event.getClubName().toString().toLowerCase().contains(newText)) {
                            if (event.getEventStatus() == EventStatus.APPROVED) {
                                Map<String, String> map = new HashMap<>();
                                map.put("EventID", event.getID());
                                map.put("Type", event.getEventTypes().toString());
                                map.put("Provider", event.getClubName().toString());
                                data.add(map);
                            }
                        }
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.event_layout,
                        new String[] {"EventID","Type", "Provider"}, new int[] {R.id.nameTextView, R.id.eventtypeTextview, R.id.providerTextview});
                listView.setAdapter(adapter);
                return true;
            }
        });

    }

    private void connectDB(){
        EventReference = FirebaseDatabase.getInstance().getReference("Events");
        AccountReference = FirebaseDatabase.getInstance().getReference("account");

        EventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                eventMap.clear();
                eventList.clear();
                List<Map<String, String>> data = new LinkedList<>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    Event event = child.getValue(Event.class);
                    if (event.getEventStatus() == EventStatus.APPROVED) {
                        eventMap.put(event.getID(),event);
                        eventList.add(event);
                        Map<String, String> map = new HashMap<>();
                        map.put("EventID", event.getID().toString());
                        map.put("Type", event.getEventTypes().toString());
                        map.put("Provider", event.getClubName().toString());
                        data.add(map);
                    }
                }
                // set list view
                SimpleAdapter adapter1 = new SimpleAdapter(getApplicationContext(), data, R.layout.event_layout,
                        new String[] {"EventID","Type", "Provider"},
                        new int[] {R.id.nameTextView, R.id.eventtypeTextview, R.id.providerTextview});
                listView.setAdapter(adapter1);


                // set onClick for list view
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String eventName = ((TextView) view.findViewById(R.id.nameTextView)).getText().toString();
                        Event event = eventMap.get(eventName);
                        Intent intent = new Intent(getApplicationContext(), FindingDetails.class);

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