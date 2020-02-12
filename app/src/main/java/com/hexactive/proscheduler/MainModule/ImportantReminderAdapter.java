package com.hexactive.proscheduler.MainModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hexactive.proscheduler.R;
import com.hexactive.proscheduler.ReminderModule.ReminderDetails;

import java.util.List;

public class ImportantReminderAdapter extends RecyclerView.Adapter<ImportantReminderAdapter.ImportantReminderViewHolder> {

    List<ReminderDetails> reminderDetails;
    
    Context context;
    LayoutInflater inflater;
    public ImportantReminderAdapter(List<ReminderDetails> reminderDetails)
    {
        this.reminderDetails=reminderDetails;
        
    }
    @NonNull
    @Override
    public ImportantReminderAdapter.ImportantReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.reminder_list_item,parent,false);
        ImportantReminderViewHolder holder=new ImportantReminderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImportantReminderAdapter.ImportantReminderViewHolder holder, int position) {
        holder.bind(reminderDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return reminderDetails.size();
    }

    public class ImportantReminderViewHolder extends RecyclerView.ViewHolder{

        TextView date,time,notification,priority,note,title,uid;

        public ImportantReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.list_date_tv);
            time=itemView.findViewById(R.id.list_time_tv);
            notification=itemView.findViewById(R.id.list_notification_tv);
            priority=itemView.findViewById(R.id.list_priority_tv);
            note=itemView.findViewById(R.id.list_note_tv);
            title=itemView.findViewById(R.id.list_title_tv);
            uid=itemView.findViewById(R.id.list_uid_tv);
        }

        public void bind(ReminderDetails reminderDetails) {
            date.setText(reminderDetails.r_date);
            time.setText(reminderDetails.r_time);
            notification.setText(reminderDetails.notification);
            priority.setText(reminderDetails.priority);
            title.setText(reminderDetails.title);
            note.setText(reminderDetails.note);
            uid.setText(reminderDetails.uid);


        }


    }
}
