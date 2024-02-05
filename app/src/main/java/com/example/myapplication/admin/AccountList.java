package com.example.myapplication.admin;


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
import com.example.myapplication.entity.Account;
import com.example.myapplication.entity.AccountRole;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AccountList extends AppCompatActivity {
    DatabaseReference accountReference;
    ListView listView;
    Map<String, Account> accountMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        initComponent();
        connectDB();
    }

    private void initComponent() {
        listView = findViewById(R.id.listView);
    }

    private void connectDB() {
        accountReference = FirebaseDatabase.getInstance().getReference("account");
        accountReference.addValueEventListener(new ValueEventListener() {
            // add accounts to list view
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                accountMap.clear();

                List<Map<String, String>> data = new LinkedList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Account account = child.getValue(Account.class);
                    if (account.getRole() != AccountRole.ADMIN) {
                        accountMap.put(account.getName(), account);
                        Map<String, String> map = new HashMap<>();
                        map.put("name", account.getName());
                        map.put("role", account.getRole().toString());
                        data.add(map);
                    }
                }

                // set list view
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.registration_layout,
                        new String[] {"name","role"}, new int[] {R.id.nameTextView, R.id.roleTextview});
                listView.setAdapter(adapter);

                // set onClick for list view
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String accountName = ((TextView) view.findViewById(R.id.nameTextView)).getText().toString();
                        Account account = accountMap.get(accountName);
                        Intent intent = new Intent(getApplicationContext(), deleteAccount.class);
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