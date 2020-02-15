package com.hexactive.proscheduler.ReminderModule;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hexactive.proscheduler.R;

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
            date.setText(reminderDetails.r_date);
            time.setText(reminderDetails.r_time);
            notification.setText(reminderDetails.notification);
            priority.setText(reminderDetails.priority);
            title.setText(reminderDetails.title);
            note.setText(reminderDetails.note);
            uid.setText(reminderDetails.uid);
            menuTemp=reminderDetails;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(reminderDetails.uid);
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
                    return false;
                }
            });

            Delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(itemView.getContext(),"Delete"+menuTemp.uid,Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String uid);
    }
}
