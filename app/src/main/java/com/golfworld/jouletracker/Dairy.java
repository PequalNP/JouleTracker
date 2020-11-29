package com.golfworld.jouletracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Dairy extends AppCompatActivity {

    public int position;
    private ArrayList<Entry> entryArrayList;
    public TextView date, intake, out, net;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        position = intent.getIntExtra("index",-1);

        SharedPreferences sharedprefs = getSharedPreferences("MyPEFERENCES", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedprefs.getString("arraylist", "");
        Type type = new TypeToken<ArrayList<Entry>>() {}.getType();

        entryArrayList = gson.fromJson(json, type);
        date = findViewById(R.id.dairyDateTextView);
        intake = findViewById(R.id.dairyIntakeTextView);
        out = findViewById(R.id.dairyOutTextView);
        net = findViewById(R.id.dairyNetTextView);
        FloatingActionButton fab = findViewById(R.id.fabDairy);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateUI();
    }
    public void fabClicked(View view){
        Snackbar.make(view, "New Day!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        Intent calIntent = new Intent(getApplicationContext(),KilojouleCalculater.class);
        startActivity(calIntent);
    }
    public void right(View v){
        if (position<entryArrayList.size()-1){
            position++;
            updateUI();
        }
    }
    public void left(View v){
        if(position>0){
            position--;
            updateUI();
        }

    }
    public void updateUI(){
        Entry entry = entryArrayList.get(position);
        String str = entry.date+" kj";
        date.setText(str);

        str = entry.EnergyIntake+" kj";
        intake.setText(str);

        str = entry.EnergyOut+" kj";
        out.setText(str);

        str = entry.TotalEnergy+" kj";
        net.setText(str);
    }
}
