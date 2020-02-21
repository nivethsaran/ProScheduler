package com.hexactive.proscheduler.MainModule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hexactive.proscheduler.CalendarModule.CalendarActivity;
import com.hexactive.proscheduler.LoginModule.LoginActivity;
import com.hexactive.proscheduler.ProfileModule.ProfileActivity;
import com.hexactive.proscheduler.R;
import com.hexactive.proscheduler.ReminderModule.ReminderActivity;
import com.hexactive.proscheduler.SettingsModule.SettingsActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
CalendarView calendarView;
Spinner main_lang_spinner;
String language;
Button changeLan_btn;
Locale locale;
ImageButton calendar_btn,reminder_btn,settings_btn,profile_btn;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp=getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
        language=sp.getString("langauge","en");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar_btn=findViewById(R.id.calendar_btn);
        reminder_btn=findViewById(R.id.alarm_btn);
        settings_btn=findViewById(R.id.settings_btn);
        profile_btn=findViewById(R.id.profile_btn);
        main_lang_spinner=findViewById(R.id.spinner_login_language);
        changeLan_btn=findViewById(R.id.change_lan_btn);

        ArrayAdapter<String> priorityAdapter=new ArrayAdapter<String>(getBaseContext(),R.layout.priority_spinner_item,new String[]{"English","Spanish"});
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        main_lang_spinner.setAdapter(priorityAdapter);

        main_lang_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    language="en";
                    SharedPreferences preferences = getSharedPreferences("mycredentials", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("language", "en");
                    editor.commit();
                }
                else if(i==1)
                {
                    language="es";
                    SharedPreferences preferences = getSharedPreferences("mycredentials",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("language", "es");
                    editor.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        changeLan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locale = new Locale(language);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                refresh();

            }
        });




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


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Configuration config = getBaseContext().getResources().getConfiguration();
        // refresh your views here
        Locale.setDefault(locale);
        config.locale = locale;
        super.onConfigurationChanged(newConfig);

    }

    private void refresh() {
        finish();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
