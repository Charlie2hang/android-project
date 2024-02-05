package com.example.myapplication.Club.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.entity.Account;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileCreation extends AppCompatActivity {

    DatabaseReference profileReference;

    Account account;


    private EditText mLink,mName,mPhoneNumber;
    private Button mbtnCreate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

        profileReference = FirebaseDatabase.getInstance().getReference("Profile");
        mLink = (EditText) findViewById(R.id.link);
        mName = (EditText) findViewById(R.id.Contact_name);
        mPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        mbtnCreate = (Button) findViewById(R.id.btn_profile);

    }



    public void ProfileCreation1(View view){


        String regex = "(?:instagram\\.com|instagr\\.am|twitter\\.com)\\/([A-Za-z0-9-_\\.]+)";
        String link = mLink.getText().toString();
        String name = mName.getText().toString();
        String phoneNumber = mPhoneNumber.getText().toString();
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(link);


        if (link.equals("")){
            Toast.makeText(getBaseContext(),"Social media link can not be blank!",Toast.LENGTH_SHORT).show();
        } else if (!matcher.matches()) {
            Toast.makeText(getBaseContext(), "Invalid link!", Toast.LENGTH_SHORT).show();

        } else if (phoneNumber.equals("")){
            Toast.makeText(getBaseContext(),"Phone number can not be blank!",Toast.LENGTH_SHORT).show();
        } else if (phoneNumber.length() != 10){
            Toast.makeText(getBaseContext(),"invalid phone number",Toast.LENGTH_SHORT).show();
        }
        else{
            if (name.equals("")){
                name = account.getName();
            }
            Profile profile = new Profile(link, name, account.getName(), phoneNumber);
            profileReference.child(account.getName()).setValue(profile);
            Toast.makeText(getBaseContext(), "Profile create!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), ProfileInfo.class).putExtra("profile", profile));
        }
    }
}