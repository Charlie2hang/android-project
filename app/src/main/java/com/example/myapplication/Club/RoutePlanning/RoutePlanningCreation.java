package com.example.myapplication.Club.RoutePlanning;

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

public class RoutePlanningCreation extends AppCompatActivity {


    DatabaseReference routePlanningReference;

    EditText distance, elevation,landmarks,location;
    Button btn_routePlanning;

    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_planning_creation);

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        routePlanningReference = FirebaseDatabase.getInstance().getReference("Route Planning");
        distance = (EditText) findViewById(R.id.RoutePlanning_distance);
        elevation =(EditText) findViewById(R.id.RoutePlanning_elevation);
        landmarks = (EditText) findViewById(R.id.RoutePlanning_landmarks);
        location = (EditText) findViewById(R.id.RoutePlanning_location);
        btn_routePlanning = (Button) findViewById(R.id.Btn_RoutePlanning);

    }

    public void RoutePlanningCreate(View view){

        String dist = distance.getText().toString();
        String elev= elevation.getText().toString();
        String land = landmarks.getText().toString();
        String loc = location.getText().toString();



        if (elev.equals("")){
            Toast.makeText(getBaseContext(),"elevation can not be blank!",Toast.LENGTH_LONG).show();
        }
        else if (dist.equals("")){
            Toast.makeText(getBaseContext(),"elev can not be blank!",Toast.LENGTH_LONG).show();
        }
        else if (land.equals("")){
            Toast.makeText(getBaseContext(),"elev can not be blank!",Toast.LENGTH_LONG).show();
        }
        else if (loc.equals("")){
            Toast.makeText(getBaseContext(),"elev can not be blank!",Toast.LENGTH_LONG).show();
        }
        else{
            RoutePlanning routePlanning = new RoutePlanning(dist,elev,land,account.getName(),loc);
            routePlanningReference.child(account.getName()).setValue(routePlanning);
            Toast.makeText(this, "Create successfully!", Toast.LENGTH_SHORT).show();
            finish();
        }


    }





}