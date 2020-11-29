package com.golfworld.jouletracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import static com.golfworld.jouletracker.R.*;

public class KilojouleCalculater extends AppCompatActivity {


    public  ArrayList<Entry> entryArrayList;


    public static final String breakfastTag = (String) "breakfastButton";
    public static final String lunchTag = (String) "lunchButton";
    public static final String dinnerTag = (String) "dinnerButton";
    public static final String gymTag = (String) "gymButton";
    public static final String sportTag = (String) "sportButton";
    public static final String joggingTag = (String) "joggingButton";
    public static final String snackTag = (String) "snackButton";

    public  int Breakfast= 0;
    public  int Lunch= 0;
    public  int Dinner= 0;
    public  int Snack= 0;
    public  int Gym= 0;
    public  int Sport= 0;
    public  int Jogging= 0;

    public  SeekBar seekBarBreakfast;
    public  SeekBar seekBarLunch;
    public  SeekBar seekBarDinner;
    public  SeekBar seekBarSnack;
    public  SeekBar seekBarGym;
    public  SeekBar seekBarSport;
    public  SeekBar seekBarJogging;

    public int EnergyIntake;
    public int EnergyOut;
    public int TotalEnergy;

    public String date;
    private Calendar myCalendar;
    private SharedPreferences sharedprefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_kilojoule_calculater);
        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        sharedprefs = getSharedPreferences("MyPEFERENCES", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedprefs.getString("arraylist", "");
        Type type = new TypeToken<ArrayList<Entry>>() {}.getType();
        entryArrayList = gson.fromJson(json, type);

        seekBarBreakfast = findViewById(id.seekBarBreakfast);
        seekBarBreakfast.setMax(9500);
        seekBarLunch = findViewById(id.seekBarLunch);
        seekBarLunch.setMax(9500);
        seekBarDinner = findViewById(id.seekBarDinner);
        seekBarDinner.setMax(9500);
        seekBarSnack = findViewById(id.seekBarSnack);
        seekBarSnack.setMax(9500);
        seekBarGym = findViewById(id.seekBarGym);
        seekBarGym.setMax(9500);
        seekBarSport = findViewById(id.seekBarSport);
        seekBarSport.setMax(9500);
        seekBarJogging = findViewById(id.seekBarJogging);
        seekBarJogging.setMax(9500);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        final SimpleDateFormat df = new SimpleDateFormat("EEE MMM d ''yy");
        date = df.format(c);

        final TextView dateTextView = findViewById(id.dairyDateTextView);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            myCalendar = Calendar.getInstance();

            final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    date = df.format(myCalendar.getTime());
                    dateTextView.setText(date);


                }

            };

            dateTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(KilojouleCalculater.this, date1, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
        }
        dateTextView.setText(date);

    }

    public void optionSelected(View button){
        TextView title = findViewById(id.calculaterInstructTextView);
        hideSeekBars();
        switch((String)button.getTag()) {
            case breakfastTag :
                title.setText("Record your breakfast");



                seekBarBreakfast.bringToFront();
                seekBarBreakfast.setVisibility(View.VISIBLE);

                seekBarBreakfast.setProgress(Breakfast);
                Button breakfastbutton = findViewById(id.breakfastButton);
                breakfastbutton.setBackgroundColor( getResources().getColor(color.button_material_dark));
                seekBarBreakfast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    int kj;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Breakfast = i;
                        TextView breakfastTextView = findViewById(id.breakfastJouleTextView);
                        String str = i + "kj";
                        breakfastTextView.setText(str);
                        kj =i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(KilojouleCalculater.this, "Breakfast Recorded!"+String.valueOf(kj), Toast.LENGTH_SHORT).show();
                        updateTotals();

                    }
                });
                break; // optional

            case lunchTag :

                seekBarLunch.setVisibility(View.VISIBLE);
                title.setText("Record your lunch");
                seekBarLunch.setProgress(Lunch);
                seekBarLunch.bringToFront();

                Button lunchbutton = findViewById(id.lunchButton);
                lunchbutton.setBackgroundColor( getResources().getColor(color.button_material_dark));
                seekBarLunch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        TextView lunchTextView = findViewById(id.lunchJouleTextView);
                        String str = i + "kj";
                        lunchTextView.setText(str);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        Toast.makeText(KilojouleCalculater.this, "Lunch Recorded!", Toast.LENGTH_SHORT).show();
                        updateTotals();
                    }
                });
                updateTotals();
                break; // optional
            case dinnerTag :
                title.setText("Record your dinner");

                seekBarDinner.setVisibility(View.VISIBLE);
                seekBarDinner.bringToFront();
                seekBarDinner.setProgress(Dinner);
                Button dinnerButton = findViewById(id.dinnerButton);
                dinnerButton.setBackgroundColor( getResources().getColor(color.button_material_dark));
                seekBarDinner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    int kj;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Dinner = i;
                        TextView dinnerTextView = findViewById(id.dinnerJouleTextView);
                        String str = i + "kj";
                        dinnerTextView.setText(str);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(KilojouleCalculater.this, "Dinner Recorded!", Toast.LENGTH_SHORT).show();
                        updateTotals();
                    }
                });
                break; // optional
            case snackTag :
                title.setText("Record your snack");

                seekBarSnack.setVisibility(View.VISIBLE);
                seekBarSnack.bringToFront();
                seekBarSnack.setProgress(Snack);
                Button snackButton = findViewById(id.snackButton);
                snackButton.setBackgroundColor( getResources().getColor(color.button_material_dark));
                seekBarSnack.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Snack = i;
                        TextView TextView = findViewById(id.snackJouleTextView);
                        String str = i + "kj";
                        TextView.setText(str);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        Toast.makeText(KilojouleCalculater.this, "Snack Recorded!", Toast.LENGTH_SHORT).show();
                        updateTotals();
                    }
                });
                break; // optional
            case gymTag :


                seekBarGym.setVisibility(View.VISIBLE);
                seekBarGym.bringToFront();
                title.setText("Record your gym");
                seekBarGym.setProgress(Gym);
                Button gymButton = findViewById(id.gymButton);
                gymButton.setBackgroundColor( getResources().getColor(color.button_material_dark));
                seekBarGym.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Gym = i;
                        TextView lunchTextView = findViewById(id.gymJouleTextView);
                        String str = i + "kj";
                        lunchTextView.setText(str);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(KilojouleCalculater.this, "Gym Recorded!", Toast.LENGTH_SHORT).show();
                        updateTotals();
                    }
                });
                break; // optional
            case sportTag :
                title.setText("Record your sport");


                seekBarSport.bringToFront();
                seekBarSport.setProgress(Sport);
                seekBarSport.setVisibility(View.VISIBLE);
                Button sportButton = findViewById(id.sportButton);
                sportButton.setBackgroundColor( getResources().getColor(color.button_material_dark));
                seekBarSport.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    int progressKj;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Sport = i;
                        TextView sportTextView = findViewById(id.sportJoule);
                        String str = i + "kj";
                        sportTextView.setText(str);
                        progressKj =i;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(KilojouleCalculater.this, "Sport Recorded!", Toast.LENGTH_SHORT).show();
                        setSport(progressKj);
                        updateTotals();
                    }
                });
                break; // optional
            case joggingTag :
                title.setText("Record your jogging");

                seekBarJogging.setVisibility(View.VISIBLE);
                seekBarJogging.bringToFront();
                seekBarJogging.setProgress(Jogging);
                Button joggingButton = findViewById(id.joggingButton);
                joggingButton.setBackgroundColor( getResources().getColor(color.button_material_dark));
                seekBarJogging.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Jogging = i;
                        TextView joggingTextView = findViewById(id.joggingJouleTextView);
                        String str = i + "kj";
                        joggingTextView.setText(str);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(KilojouleCalculater.this, "Jogging Recorded!", Toast.LENGTH_SHORT).show();
                        updateTotals();
                    }
                });
                break; // optional
            // You can have any number of case statements.
            default : // Optional
                // Statements
        }
    }

    private void updateTotals() {
//        TextView breakfastTextView = findViewById(id.breakfastJouleTextView);
//        breakfastTextView.setText(String.valueOf(Breakfast));
        EnergyIntake = Breakfast + Lunch + Dinner + Snack;
        EnergyOut = Sport + Jogging + Gym;
        TotalEnergy = EnergyIntake - EnergyOut;

        TextView energyInTextView = findViewById(id.energyIntakeTextView);
        energyInTextView.setText(String.valueOf(EnergyIntake));

        TextView energyOutTextView = findViewById(id.energyOutTextView);
        energyOutTextView.setText(String.valueOf(EnergyOut));

        TextView totalEnergyTextView = findViewById(id.totalEnergyTextView);
        totalEnergyTextView.setText(String.valueOf(TotalEnergy));


    }

   public void hideSeekBars() {


       seekBarBreakfast.setVisibility(View.GONE);
       seekBarLunch.setVisibility(View.GONE);
       seekBarDinner.setVisibility(View.GONE);
       seekBarSnack.setVisibility(View.GONE);
       seekBarGym.setVisibility(View.GONE);
       seekBarSport.setVisibility(View.GONE);
       seekBarJogging.setVisibility(View.GONE);
   }

    public void saveDay(View view){

        Entry entry = new Entry();
        entry.Breakfast = Breakfast;
        entry.Lunch = Lunch;
        entry.Dinner = Dinner;
        entry.Snack = Snack;
        entry.Gym = Gym;
        entry.Sport = Sport;
        entry.Jogging = Jogging;
        entry.EnergyIntake =EnergyIntake;
        entry.EnergyOut = EnergyOut;
        entry.TotalEnergy = TotalEnergy;
        entry.date = date;

        SharedPreferences sharedPrefs = getSharedPreferences("MyPEFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        try {
            entryArrayList.add(entry);
        }catch (NullPointerException e){
            entryArrayList = new ArrayList<>();
            entryArrayList.add(entry);
        }
        String json = gson.toJson(entryArrayList);

        editor.putString("arraylist", json);
        editor.commit();
        int position = entryArrayList.indexOf(entry);
        Intent intent = new Intent(this, Dairy.class);
        intent.putExtra("index",position);
        startActivity(intent);

    }


    public void setBreakfast(int breakfast) {
        Breakfast = breakfast;
    }

    public void setLunch(int lunch) {
        Lunch = lunch;
    }

    public void setDinner(int dinner) {
        Dinner = dinner;
    }

    public void setSnack(int snack) {
        Snack = snack;
    }

    public void setGym(int gym) {
        Gym = gym;
    }

    public void setSport(int sport) {
        Sport = sport;
    }

    public void setJogging(int jogging) {
        Jogging = jogging;
    }
}
