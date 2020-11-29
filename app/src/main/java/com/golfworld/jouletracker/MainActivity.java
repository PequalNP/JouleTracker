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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Entry> entryArrayList;
    public EntryAdapter adapter;
    public SharedPreferences sharedprefs;
    public RecyclerView.LayoutManager layoutManager;
    public SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabMain);

         sharedprefs = getSharedPreferences("MyPEFERENCES", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedprefs.getString("arraylist", "");
        Type type = new TypeToken<ArrayList<Entry>>() {}.getType();
        entryArrayList = gson.fromJson(json, type);
        if (entryArrayList != null){
         adapter = new EntryAdapter(entryArrayList,this);}
        else{
            adapter = new EntryAdapter(this);
        }

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {

                    @Override
                    public void onRefresh() {
                        Log.i("i", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        updateRecycler();
                    }
                }
        );
    updateAverage();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fabClicked(View view){
        Snackbar.make(view, "New Day!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent calIntent = new Intent(getApplicationContext(),KilojouleCalculater.class);
                startActivity(calIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateRecycler();
        updateAverage();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecycler();
        updateAverage();
    }
    public void updateRecycler(){
        Gson gson = new Gson();
        String json = sharedprefs.getString("arraylist", "");
        Type type = new TypeToken<ArrayList<Entry>>() {}.getType();
        entryArrayList = gson.fromJson(json, type);
        adapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);

    }
    public void updateAverage(){
        int sum = 0;
        TextView aveTextView = findViewById(R.id.averageKJ);
        try {
            for (Entry entry:entryArrayList) sum += entry.TotalEnergy;
            int ave = sum/entryArrayList.size();
            String average = "    Your average is " + ave + " kj. Keep tracking your energy!";

            aveTextView.setText(average);
        }catch (NullPointerException e){
            aveTextView.setText("    Click on the plus to record your first entry!");
        }

    }
}
