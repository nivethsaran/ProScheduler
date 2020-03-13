package com.hexactive.proscheduler.CalendarModule;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hexactive.proscheduler.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
private CalendarView calendarView;
RadioGroup rdg;
RadioButton rb1;
RadioButton rb2;
RadioButton rb3;
Button zoomin;
Button zoomout;
String sdate="";
String edate="";
long SDATE1;
long EDATE1;
RecyclerView rv;
CalendarAdapter calendarAdapter;
String url="";
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rdg=findViewById(R.id.rdg);

        rv=findViewById(R.id.event_rv);
        SDATE1=System.currentTimeMillis();
        EDATE1=System.currentTimeMillis()+86400000;



        sdate=formatter.format(new Date(SDATE1));
        edate=formatter.format(new Date(EDATE1));
        url="https://pro-scheduler-backend.herokuapp.com/geteventdata/sd/"+sdate+"/ed/"+edate;
        new EventTask().execute(url);
        calendarView=findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                sdate="";
                sdate+=i+"-";
                if(i1+1<10)
                {
                    sdate+="0"+(i1+1)+"-";
                }
                else
                {
                    sdate+=(i1+1)+"-";
                }

                if(i2<10)
                {
                    sdate+="0"+i2;
                }
                else
                {
                    sdate+=i2;
                }
                long millisSinceEpoch = 0;
                try {
                    millisSinceEpoch = formatter.parse(sdate).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(rb1.isChecked())
                {
                    edate=formatter.format(new Date(millisSinceEpoch+86400000));
                }
                else if(rb2.isChecked())
                {
                    edate=formatter.format(new Date(millisSinceEpoch+Long.parseLong("604800000")));
                }
                else if(rb3.isChecked())
                {
                    edate=formatter.format(new Date(millisSinceEpoch+Long.parseLong("2592000000")));
                }
                url="https://pro-scheduler-backend.herokuapp.com/geteventdata/sd/"+sdate+"/ed/"+edate;
                new EventTask().execute(url);
                Toast.makeText(getApplicationContext(),edate,Toast.LENGTH_SHORT).show();
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

                calendarView.setScaleX((float) (x+0.5));
                calendarView.setScaleY((float) (y+0.5));
            }
        });

        zoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x = calendarView.getScaleX();
                float y = calendarView.getScaleY();

                if(x>1) {
                    calendarView.setScaleX((float) (x-0.5));
                    calendarView.setScaleY((float) (x-0.5));
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

    class EventTask extends AsyncTask<String,Void,String>
    {

        EventDetails EventDetails;
        List<EventDetails> list;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... voids) {
                String json="";
                Log.d("Login","Check One");
                try {
                    url=voids[0];
                    Log.d("Login","URL:"+url);
                    json = Jsoup.connect(voids[0]).ignoreContentType(true).execute().body();
                    Log.d("Login","JSON:"+json);
                }
                catch (Exception e) {
                    Log.d("Login", "Error");
                }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            try{
                JSONArray dataArray=new JSONArray(json);
                list=new ArrayList<EventDetails>();
                for (int i=0;i<dataArray.length();i++)
                {
                    JSONObject temp=dataArray.getJSONObject(i);
                    EventDetails eventDetails=new EventDetails();
                    eventDetails.r_date=temp.getString("eventdate").substring(0,10);
                    eventDetails.r_time=temp.getString("eventtime");
                    eventDetails.title=temp.getString("eventname");
                    Log.d("Login","Check One");
                    list.add(eventDetails);
                }

                calendarAdapter=new CalendarAdapter(list);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv.setAdapter(calendarAdapter);
            }catch (Exception e)
            {
                Log.d("Login",e.getMessage());
            }
        }


    }
}
