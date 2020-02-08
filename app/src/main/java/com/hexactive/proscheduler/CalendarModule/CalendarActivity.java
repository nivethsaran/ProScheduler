package com.hexactive.proscheduler.CalendarModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.hexactive.proscheduler.R;

public class CalendarActivity extends AppCompatActivity {
private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView=findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Toast.makeText(getApplicationContext(),i+" "+i1+" "+i2,Toast.LENGTH_SHORT).show();
                calendarView.setHovered(true);
                calendarView.setPressed(true);
            }
        });
    }
}
