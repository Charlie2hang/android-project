package com.example.myapplication.entity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Club.ClubActivity;
import com.example.myapplication.Participant.PartivipantActivity;
import com.example.myapplication.R;
import com.example.myapplication.Welcome;
import com.example.myapplication.admin.AdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    DatabaseReference AccountReference;
    EditText accountEditText, passwordEditText;
    String name, password;

    private void connectDB() {
        AccountReference = FirebaseDatabase.getInstance().getReference("account");
    }

    private void initComponent() {
        accountEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.editTextTextPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectDB();
        initComponent();
    }
    public void register(View view) {
        startActivities(new Intent[]{new  Intent(getApplicationContext(), SelectRole.class)});
    }

    public void login(View view) {
        name = accountEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if (name.equals("")) {
            Toast.makeText(getBaseContext(), "Username can not be blank!", Toast.LENGTH_LONG).show();
        } else if (password.equals("")) {
            Toast toast = Toast.makeText(getBaseContext(), "Password can not be blank!", Toast.LENGTH_LONG);
            toast.show();
        } else {
            AccountReference.child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    Account account = task.getResult().getValue(Account.class);
                    if (account == null) {
                        Toast.makeText(getBaseContext(), "Account doesn't exist!", Toast.LENGTH_LONG).show();
                    } else if (!password.equals(account.getPassword())) {
                        Toast.makeText(getBaseContext(), "Password Wrong!", Toast.LENGTH_LONG).show();
                    } else {
                        if (account.getName().equals("Admin") && account.getRole() == AccountRole.ADMIN){
                            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        } else if (account.getRole() == AccountRole.CLUB) {
                            startActivity(new Intent(getApplicationContext(), ClubActivity.class).putExtra("account",account));
                        } else if (account.getRole() == AccountRole.USER) {
                            startActivity(new Intent(getApplicationContext(), PartivipantActivity.class).putExtra("account",account));
                        }
                    }
                }
            });
        }
    }
}