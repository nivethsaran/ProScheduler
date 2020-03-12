package com.hexactive.proscheduler.MainModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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

        public void bind(final ReminderDetails reminderDetails) {
            date.setText("Date:"+reminderDetails.r_date.substring(0,10));
            time.setText("Time:"+reminderDetails.r_time);
            if(reminderDetails.notification.equals("1"))
            {
                notification.setText("Notification Enabled");
            }
            else
            {
                notification.setText("Notification Disabled");
            }
            if(reminderDetails.priority.equals("H"))
            {
                priority.setText("High priority");
                priority.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            }
            else if(reminderDetails.priority.equals("M"))
            {

                priority.setText("Medium priority");
                priority.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
//                priority.setTextColor(ContextCompat.getColor(context, R.color.yellow));
            }
            else if(reminderDetails.priority.equals("L"))
            {
                priority.setText("Low priority");
                priority.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
//                priority.setTextColor(ContextCompat.getColor(context, R.color.green));
            }
            priority.setTextColor(ContextCompat.getColor(context, R.color.bgColorMain));

//            priority.setText(reminderDetails.priority);
            title.setText(reminderDetails.title);
            note.setText(reminderDetails.note);
            uid.setText(reminderDetails.uid);
//            itemView.setOnTouchListener(new OnSwipeTouchListener(context) {
//                @Override
//                public void onSwipeLeft() {
//                    // Whatever
//                    super.onSwipeLeft();
//                    Log.d("Reminder",reminderDetails.rid);
//                    new AlertDialog.Builder(context)
//                            .setTitle("Title")
//                            .setMessage("Do you really want to whatever?")
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int whichButton) {
//                                    Log.d("Reminder","Something");
//                                }})
//                            .setNegativeButton(android.R.string.no, null).show();
//                }
//
//                @Override
//                public void onSwipeRight() {
//                    super.onSwipeRight();
//                    Log.d("Reminder",reminderDetails.rid);
//                    new AlertDialog.Builder(context)
//                            .setTitle("Title")
//                            .setMessage("Do you really want to whatever?")
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int whichButton) {
//                                    Log.d("Reminder","Something");
//                                }})
//                            .setNegativeButton(android.R.string.no, null).show();
//                }
//            });

        }


    }
}
