package com.example.myapplication.Club.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.Club.Profile.Profile;
import com.example.myapplication.R;

public class ProfileInfo extends AppCompatActivity {

    Profile profile;

    TextView profileName, profileLink, profilePhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        profileName = (TextView) findViewById(R.id.Profile_name);
        profileLink = (TextView) findViewById(R.id.Profile_link);
        profilePhoneNumber = (TextView) findViewById(R.id.Profile_phoneNumber);

        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("profile");
        profileName.setText("Name: " + profile.getName());
        profileLink.setText("Social Media: " + profile.getLink());
        profilePhoneNumber.setText("Phone Number: " + profile.getPhoneNumber());


    }



}