package com.hexactive.proscheduler.ReminderModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

List<ReminderDetails> reminderDetails;
OnItemClickListener listener;
Context context;
LayoutInflater inflater;
    public ReminderAdapter(List<ReminderDetails> reminderDetails, OnItemClickListener listener)
    {
        this.reminderDetails=reminderDetails;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        inflater=LayoutInflater.from(parent.getContext());

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder{
        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
