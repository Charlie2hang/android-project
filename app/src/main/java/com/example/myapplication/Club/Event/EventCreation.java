package com.example.myapplication.Club.Event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Club.RoutePlanning.RoutePlanning;
import com.example.myapplication.R;
import com.example.myapplication.admin.EventType.EventLevels;
import com.example.myapplication.admin.EventType.EventType;
import com.example.myapplication.admin.EventType.EventTypeStatus;
import com.example.myapplication.admin.EventType.EventTypes;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCreation extends AppCompatActivity {
    DatabaseReference EventTypeReference, EventReference;

    Account account;
    ListView listView;
    Map<String, EventType> eventTypeMap = new HashMap<>();
    EditText EventNameEditText, FeesEditText, LimitEditText, DateEditText;
    RadioGroup EventLevelGroup;

    private void connectDB() {
        EventReference = FirebaseDatabase.getInstance().getReference("Events");
        EventTypeReference = FirebaseDatabase.getInstance().getReference("EventTypes");


        EventTypeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventTypeMap.clear();
                List<Map<String, String>> data = new LinkedList<>();

                for (DataSnapshot child : snapshot.getChildren()) {
                    EventType eventType = child.getValue(EventType.class);
                    if (eventType.getEventTypeStatus() == EventTypeStatus.APPROVED) {
                        eventTypeMap.put(eventType.getEventName(), eventType);
                        Map<String, String> map = new HashMap<>();
                        map.put("eventTypeName", eventType.getEventName());
                        data.add(map);
                    }
                }
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.select_type_layout,
                        new String[] {"eventTypeName"}, new int[] {R.id.nameTextView});
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String eventTypeName = ((TextView) view.findViewById(R.id.nameTextView)).getText().toString();
                        String regex = "((18|19|20)[0-9]{2}[\\-.](0[13578]|1[02])[\\-.](0[1-9]|[12][0-9]|3[01]))|(18|19|20)[0-9]{2}[\\-.](0[469]|11)[\\-.](0[1-9]|[12][0-9]|30)|(18|19|20)[0-9]{2}[\\-.](02)[\\-.](0[1-9]|1[0-9]|2[0-8])|(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[\\-.](02)[\\-.]29";
                        String eventName = EventNameEditText.getText().toString();
                        String fees = FeesEditText.getText().toString();
                        String limit = LimitEditText.getText().toString();
                        String date = DateEditText.getText().toString();
                        Pattern pattern = Pattern.compile(regex,Pattern.UNICODE_CASE);
                        Matcher matcher = pattern.matcher(date);

                        EventLevels eventLevels = null;
                        if (EventLevelGroup.getCheckedRadioButtonId() == R.id.beginnerRadioBtn) {
                            eventLevels = EventLevels.Beginner;
                        } else if (EventLevelGroup.getCheckedRadioButtonId() == R.id.juniorRadioBtn) {
                            eventLevels = EventLevels.Junior;
                        } else if (EventLevelGroup.getCheckedRadioButtonId() == R.id.seniorRadioBtn) {
                            eventLevels = EventLevels.Senior;
                        } else {
                            Toast.makeText(getBaseContext(), "Please select a level!", Toast.LENGTH_SHORT).show();
                        }

                        if (fees.equals("")) {
                            Toast toast = Toast.makeText(getBaseContext(), "Please enter a number!", Toast.LENGTH_SHORT);
                            toast.show();
                        } else if (limit.equals("")) {
                            Toast toast = Toast.makeText(getBaseContext(), "Please set a limit!", Toast.LENGTH_SHORT);
                            toast.show();
                        } else if (date.equals("")) {
                            Toast toast = Toast.makeText(getBaseContext(), "Please set a date!", Toast.LENGTH_SHORT);
                            toast.show();
                        } else if (!matcher.matches()) {
                            Toast toast = Toast.makeText(getBaseContext(), "Invalid date", Toast.LENGTH_SHORT);
                            toast.show();
                        } else if (eventName.equals("")) {
                            Toast toast = Toast.makeText(getBaseContext(), "Event name can not be blank!", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            if (eventLevels != null) {
                                Event event = new Event(eventName, account.getName(), fees, limit,
                                                        EventTypes.valueOf(eventTypeName), eventLevels,
                                                        EventStatus.valueOf("PENDING"), date);
                                EventReference.child(eventName).setValue(event);
                                Toast toast = Toast.makeText(getBaseContext(), "Event create/update successfully!", Toast.LENGTH_SHORT);
                                toast.show();
                                finish();
                            }

                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initComponent() {
        EventNameEditText = findViewById(R.id.EventNameEditText);
        FeesEditText = findViewById(R.id.FeesEditText);
        LimitEditText = findViewById(R.id.LimitEditText);
        EventLevelGroup = findViewById(R.id.EventLevelGroup);
        DateEditText = findViewById(R.id.dateEditText);

        listView = findViewById(R.id.selectTypeListView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        initComponent();
        connectDB();

    }

}