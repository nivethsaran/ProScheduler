package com.hexactive.proscheduler.CalendarModule;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hexactive.proscheduler.R;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    List<EventDetails> eventDetails;

    Context context;
    LayoutInflater inflater;
    public CalendarAdapter(List<EventDetails> eventDetails)
    {
        this.eventDetails=eventDetails;

    }
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.event_item,parent,false);
        Log.d("Login","Inside Adapter");
        CalendarViewHolder holder=new CalendarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.bind(eventDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return eventDetails.size();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder{

        TextView date,time,title;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.list_date_tv);
            time=itemView.findViewById(R.id.list_time_tv);
            title=itemView.findViewById(R.id.list_title_tv);

        }

        public void bind(final EventDetails eventDetails) {
            Log.d("Login","Check One");
            date.setText(eventDetails.r_date);
            time.setText(eventDetails.r_time);
            title.setText(eventDetails.title);


        }


    }
}
