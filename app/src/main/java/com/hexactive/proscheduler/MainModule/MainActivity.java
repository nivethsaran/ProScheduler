package com.hexactive.proscheduler.MainModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;


import com.hexactive.proscheduler.CalendarModule.CalendarActivity;
import com.hexactive.proscheduler.ProfileModule.ProfileActivity;
import com.hexactive.proscheduler.R;
import com.hexactive.proscheduler.ReminderModule.ReminderActivity;
import com.hexactive.proscheduler.SettingsModule.SettingsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
CalendarView calendarView;
ImageButton calendar_btn,reminder_btn,settings_btn,profile_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar_btn=findViewById(R.id.calendar_btn);
        reminder_btn=findViewById(R.id.alarm_btn);
        settings_btn=findViewById(R.id.settings_btn);
        profile_btn=findViewById(R.id.profile_btn);

        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        reminder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(intent);
            }
        });

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        ImportantReminderFragment importantReminderFragment = new ImportantReminderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.important_reminder_container, importantReminderFragment).commit();

    }
}
