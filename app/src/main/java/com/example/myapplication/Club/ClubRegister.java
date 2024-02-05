package com.example.myapplication.Club;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.entity.Account;
import com.example.myapplication.entity.AccountRole;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClubRegister extends AppCompatActivity {
    DatabaseReference AccountReference;
    EditText accountEditText, passwordEditText,emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_register);
        connectDB();
        initComponent();
    }

    private void connectDB() {
        AccountReference = FirebaseDatabase.getInstance().getReference("account");
    }

    private void initComponent() {
        accountEditText = findViewById(R.id.editTextText2);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        emailEditText = findViewById(R.id.editTextTextEmail);
    }

    public void register(View view) {
        String regex = "^[a-zA-Z0-9_][a-zA-Z0-9_]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)";

        String name = accountEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();

        Pattern pattern = Pattern.compile(regex,Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(email);
        if (name.equals("")) {
            Toast.makeText(getBaseContext(), "Username can not be blank!", Toast.LENGTH_LONG).show();
        } else if (password.equals("")) {
            Toast toast = Toast.makeText(getBaseContext(), "Password can not be blank!", Toast.LENGTH_LONG);
            toast.show();
        } else if (email.equals("")) {
            Toast.makeText(getBaseContext(), "Email can not be blank!", Toast.LENGTH_LONG).show();
        } else if (!matcher.matches()) {
            Toast.makeText(getBaseContext(), "Email Invalid!", Toast.LENGTH_LONG).show();
        } else {
            Account account = new Account(name, password, email, AccountRole.CLUB);
            AccountReference.child(name).setValue(account);
            finish();
        }

    }

}