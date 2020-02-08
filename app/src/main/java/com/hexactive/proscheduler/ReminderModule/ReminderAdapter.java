package com.hexactive.proscheduler.ReminderModule;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {



    public ReminderAdapter()
    {

    }
    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
