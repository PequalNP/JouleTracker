package com.golfworld.jouletracker;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {
    private ArrayList<Entry> entryArrayList;
    public Activity context;

    public EntryAdapter(ArrayList<Entry> entryArrayList,Activity context) {
        this.entryArrayList = entryArrayList;
        this.context = context;

    }

    public EntryAdapter(Activity context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView date;
        public TextView TotalKJ;
        public Activity context;
        public int position;
        public ViewHolder(@NonNull View v, Activity c) {
            super(v);
            date = v.findViewById(R.id.cardDateTextView);
            TotalKJ = v.findViewById(R.id.cardTotalEnergyTextView);
            context = c;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Dairy.class);
                    intent.putExtra("index",position);
                    context.startActivity(intent);
                }
            });

        }


    }
    @NonNull
    @Override
    public EntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_card,parent,false);

        return new ViewHolder(cardView,context);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryAdapter.ViewHolder holder, int position) {
        try {
            if (position<0){
                throw new NullPointerException();
            }
            Entry entry = entryArrayList.get(position);


            holder.date.setText(entry.date);
            holder.position = position;
            String str = "Total: " + entry.TotalEnergy + " kj";
            holder.TotalKJ.setText(str);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return entryArrayList.size();
        }catch (NullPointerException e){
            e.printStackTrace();
            return -1;
        }
    }

}
