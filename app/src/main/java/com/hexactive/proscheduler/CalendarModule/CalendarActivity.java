package com.hexactive.proscheduler.CalendarModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hexactive.proscheduler.R;

public class CalendarActivity extends AppCompatActivity {
private CalendarView calendarView;
Button zoomin,zoomout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppThemeLight);
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
        zoomin=findViewById(R.id.zoomin);
        zoomout=findViewById(R.id.zoomout);
        zoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x = calendarView.getScaleX();
                float y = calendarView.getScaleY();

                calendarView.setScaleX(x+2);
                calendarView.setScaleY(y+2);
            }
        });

        zoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x = calendarView.getScaleX();
                float y = calendarView.getScaleY();

                if(x>1) {
                    calendarView.setScaleX(x - 2);
                    calendarView.setScaleY(y - 2);
                }
            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.oncal)
        {
            Intent intent=new Intent(CalendarActivity.this,OnlineCalendarActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
