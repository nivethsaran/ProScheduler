package com.hexactive.proscheduler.ReminderModule;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hexactive.proscheduler.R;

import org.jsoup.Jsoup;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

List<ReminderDetails> reminderDetails;
OnItemClickListener listener;
Context context;
LayoutInflater inflater;
    public ReminderAdapter(List<ReminderDetails> reminderDetails, OnItemClickListener listener,Context context)
    {
        this.reminderDetails=reminderDetails;
        this.listener=listener;
        this.context=context;
    }
    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.reminder_list_item,parent,false);
        ReminderViewHolder holder=new ReminderViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        holder.bind(reminderDetails.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return reminderDetails.size();
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ReminderDetails menuTemp;
        TextView date,time,notification,priority,note,title,uid;
        private float x1,x2;
        static final int MIN_DISTANCE = 150;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.list_date_tv);
            time=itemView.findViewById(R.id.list_time_tv);
            notification=itemView.findViewById(R.id.list_notification_tv);
            priority=itemView.findViewById(R.id.list_priority_tv);
            note=itemView.findViewById(R.id.list_note_tv);
            title=itemView.findViewById(R.id.list_title_tv);
            uid=itemView.findViewById(R.id.list_uid_tv);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final ReminderDetails reminderDetails, final OnItemClickListener listener) {
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
            menuTemp=reminderDetails;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(reminderDetails);

                }
            });

        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem Edit = contextMenu.add(Menu.NONE,1,1,"Edit");
            MenuItem Delete = contextMenu.add(Menu.NONE, 2, 2, "Delete");
            Edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(itemView.getContext(),"Edit"+menuTemp.uid,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,AddEditReminderActivity.class);
                    intent.putExtra("type","EDIT");
                    intent.putExtra("data",menuTemp);
                    context.startActivity(intent);
                    return false;
                }
            });

            Delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(itemView.getContext(),"Delete"+menuTemp.rid,Toast.LENGTH_SHORT).show();
                    String url="http://pro-scheduler-backend.herokuapp.com/deleteReminder/uid/"+menuTemp.uid+"/rid/"+menuTemp.rid;
                    new DeleteReminderTask().execute(url);
                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ReminderDetails reminderDetails);
    }

    class DeleteReminderTask extends AsyncTask<String,Void,Void>
    {
        String URL;
        @Override
        protected Void doInBackground(String... voids) {
            try{
                URL=voids[0];
                String resp = Jsoup.connect(URL).ignoreContentType(true).execute().body();
            }
            catch (Exception e)
            {
                Log.d("Error","Error");
            }
//            return resp;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
        }
    }
}
