package com.hexactive.proscheduler.ReminderModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.hexactive.proscheduler.R;

import java.util.ArrayList;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {
RecyclerView reminder_rv;
ReminderAdapter reminderAdapter;
List<ReminderDetails> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        reminder_rv=findViewById(R.id.reminder_rv);

        ReminderDetails temp=new ReminderDetails();
        temp.note="Hhahaha";
        temp.priority="Hhahaha";
        temp.notification="Hhahaha";
        temp.r_date="Hhahaha";
        temp.r_time="Hhahaha";
        temp.uid="Hhahaha";
        temp.title="Hhahaha";
        list=new ArrayList<ReminderDetails>();
        list.add(temp);

        reminderAdapter=new ReminderAdapter(list, new ReminderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String uid) {
                Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reminder_rv.setLayoutManager(layoutManager);
        reminder_rv.setAdapter(reminderAdapter);

    }
}
