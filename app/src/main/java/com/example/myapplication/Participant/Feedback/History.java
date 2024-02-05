package com.example.myapplication.Participant.Feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myapplication.Club.RegistrationManagement.RegistrationDetails;
import com.example.myapplication.Participant.Registration.Registration;
import com.example.myapplication.Participant.Registration.RegistrationStatus;
import com.example.myapplication.R;
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

public class History extends AppCompatActivity {
    Account account;

    DatabaseReference RegistrationReference, AccountReference;
    ListView listView;

    Map<String, Registration> HistoryMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        initComponent();
        connectDB();
    }

    private void initComponent(){
        listView = findViewById(R.id.HistoryListView);
    }

    private void connectDB(){
        RegistrationReference = FirebaseDatabase.getInstance().getReference("Registrations");
        AccountReference = FirebaseDatabase.getInstance().getReference("account");

        RegistrationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HistoryMap.clear();
                List<Map<String, String>> data = new LinkedList<>();
                for (DataSnapshot child : snapshot.getChildren()){
                    Registration registration = child.getValue(Registration.class);
                    if (registration.getRegistrationStatus() == RegistrationStatus.APPROVED &&
                    registration.getParticipantName().equals(account.getName())) {

                        HistoryMap.put(registration.getEventName(), registration);
                        Map<String, String> map = new HashMap<>();
                        map.put("EventID", registration.getEventName());
                        map.put("Type", registration.getEventTypes());
                        map.put("Provider", registration.getClubName());
                        data.add(map);
                    }
                }

                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.history_layout,
                        new String[] {"EventID", "Type", "Provider"},
                        new int[] {R.id.nameTextView, R.id.eventtypeTextview, R.id.clubNameTextView});
                listView.setAdapter(adapter);



                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String eventID = ((TextView) view.findViewById(R.id.nameTextView)).getText().toString();
                        Registration registration = HistoryMap.get(eventID);
                        Intent intent = new Intent(getApplicationContext(), Feedback.class);
                        intent.putExtra("account", account);
                        intent.putExtra("Registrations", registration);
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